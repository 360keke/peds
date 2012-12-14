package cn.com.peds.fileMgr;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import cn.com.peds.common.CryptUtil;
import cn.com.peds.common.DESEncryptFile;
import cn.com.peds.common.PropertyManager;
import cn.com.peds.common.StringUtils;
import cn.com.peds.common.dao.SysParamDao;
import cn.com.peds.derby.DataStore;
import cn.com.peds.fileMgr.bean.PedsFileBean;
import cn.com.peds.fileMgr.dao.PedsFileDao;

public class FileManager {
	public boolean openFile(String file) {
		boolean flag = true;
		if (Desktop.isDesktopSupported()) {
			Desktop deskop = Desktop.getDesktop();
			try {
				deskop.open(new File(file));
			} catch (IOException e) {
				flag = false;
				e.printStackTrace();
			}
		}
		return flag;
	}

	public static void browse(String url) {
		String osName = System.getProperty("os.name", "");
		try {
			if (osName.startsWith("Mac OS")) {
				Class fileMgr = Class.forName("com.apple.eio.FileManager");
				Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[] { String.class });
				openURL.invoke(null, new Object[] { url });
			} else if (osName.startsWith("Windows")) {
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
			} else { // assume Unix or Linux
				String[] browsers = { "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
				String browser = null;
				for (int count = 0; count < browsers.length && browser == null; count++)
					if (Runtime.getRuntime().exec(new String[] { "which", browsers[count] }).waitFor() == 0)
						browser = browsers[count];
				if (browser == null)
					throw new NoSuchMethodException("Could not find web browser");
				else
					Runtime.getRuntime().exec(new String[] { browser, url });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void list(String listPath, String filterRule) {
		System.out.println("开始处理文件:" + listPath + filterRule);
		if (listPath == null || "".equals(listPath)) {
			return;
		}
		// try {
		// // listPath = new String(listPath.getBytes(), "iso-8859-1");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		File file = new File(listPath);
		if (!file.isDirectory()) {
			String[] splits = file.getName().split("\\.");
			String filename = splits[0];
			String fileSuffix = splits[splits.length - 1];
			if (filterFile(fileSuffix, filterRule)) {
				String path = file.getPath().replace(filename + "." + fileSuffix, "");
				float length = file.length() / 1024 / 1024;
				String lastModify = StringUtils.LongToString(file.lastModified(), "yyyy-MM-dd:HH:mm:ss");
				String createtime = StringUtils.DateToString(new Date(), "yyyy-MM-dd:HH:mm:ss");
				String updatetime = createtime;
				boolean canRead = file.canRead();
				boolean canWrite = file.canWrite();
				boolean canExecute = file.canExecute();
				boolean isHidden = file.isHidden();
				PedsFileBean pedsfile = new PedsFileBean(path, filename, fileSuffix, length, lastModify, createtime, updatetime, canRead,
						canWrite, canExecute, isHidden);
				processFile(pedsfile);
			}
		} else if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				list(files[i].getPath(), filterRule);
			}
		}
	}

	public void processFile(PedsFileBean file) {
		String encryptPwd = "";
		String encryptFilename = "";
		try {
			// 判断是否需要对文件采用默认密码加密
			boolean isEncrypt = Boolean.parseBoolean(PropertyManager.getProperty("isneedencrypt"));
			if (isEncrypt) {
				String path = file.getPath();
				String filename = file.getFileName();
				encryptPwd = CryptUtil.decrypt(PropertyManager.getProperty("security.defaultkey"));
				encryptFilename = filename + ".peds." + file.getSuffix();
				file.setEncryptFileName(encryptFilename);
				file.setEncryptPwd(encryptPwd);
				file.setIsencrypt("1");

				DESEncryptFile.blockEncCode(path + filename + "." + file.getSuffix(), path + encryptFilename, encryptPwd);
				boolean isDelete = Boolean.parseBoolean(PropertyManager.getProperty("isdelete"));
				if (isDelete) {
					new File(path).delete();
				}
			} else {
				file.setIsencrypt("0");
			}
			// 判断文件是否存在，存在就更新，不存在就insert
			if (PedsFileDao.fileIsExists(file.getSignature())) {
				updatePedsFile(file);
			} else {
				this.createPedsFile(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param file
	 * @param filterRule
	 *            过滤文件
	 * @return
	 */
	public boolean filterFile(String suffix, String filterRule) {
		boolean flag = false;
		if (!"".equals(suffix)) {
			String[] rule = filterRule.split(";");
			for (String r : rule) {
				if (suffix.equals(r)) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 获取文件的后缀名
	 * 
	 * @param filename
	 * @return
	 */
	public String getFileSuffixName(String filename) {
		String suffix = "未知文件类型";
		if (filename != null && !"".equals(filename)) {
			String[] split = filename.split("\\.");
			suffix = split[split.length - 1];
		}
		return suffix;
	}

	public int createPedsFile(PedsFileBean file) {
		return PedsFileDao.saveFile(file);
	}

	/**
	 * 更新文件属性方法
	 * 
	 * @param file
	 */
	public void updatePedsFile(PedsFileBean file) {
		PedsFileDao.updateFile(file);

	}

	public String decryptFile(int id, String password) {
		JSONObject jsobj = new JSONObject();
		boolean flag = true;
		String msg = "解压成功！";
		try {
			JSONObject file = PedsFileDao.getDefaultFiles(id).getJSONObject(0);
			String encryptFilename = file.getString("encryptfilename");
			String path = file.getString("path");
			String filename = file.getString("filename");
			String suffix = file.getString("suffix");
			System.out.println(path + ";" + filename + ";" + encryptFilename + ";" + suffix);
			System.out.println(path + "/" + encryptFilename + "/" + suffix + "*********************");
			DESEncryptFile.blockDecCode("c:\\temp\\从1万到100万.peds.pdf", "c:\\temp\\从1万到100万.pdf", "peds_file_manager");
		} catch (Exception e) {
			e.printStackTrace();
			msg = "解密失败！";
			flag = false;
		}
		try {
			jsobj.put("state", flag);
			jsobj.put("msg", msg);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsobj.toString();
	}

	public String getAllFiles(int id) {
		String result = PedsFileDao.getDefaultFiles(id).toString();
		return result;
	}

	public static void main(String[] args) throws IllegalArgumentException, ClassNotFoundException, IllegalAccessException,
			InterruptedException, InvocationTargetException, IOException, NoSuchMethodException {
		// String filterRule = SysParamDao.getSysParamValue("filterrule");//
		// 获取文件过滤规则
		// FileManager fm = new FileManager();
		// fm.list("c:\\temp", filterRule);
		// FileManager.browse("http://localhost:8080/");
		// File[] roots = File.listRoots();
		// for (int i = 0; i < roots.length; i++) {
		// System.out.println(roots[i].getPath() + roots[i].getName() + roots[i].getFreeSpace());
		// }
		File file = new File("d:\\dzh\\data\\sh\\STKINFO60.DAT");
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		String str = "";
		while((str= br.readLine()) !=null){
			System.out.println(ByteUtil.decode(str));
		}
	}
}
