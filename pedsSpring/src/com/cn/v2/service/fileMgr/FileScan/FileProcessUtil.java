package com.cn.v2.service.fileMgr.FileScan;

import java.awt.Desktop;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.filechooser.FileSystemView;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cn.v2.common.utils.CryptUtil;
import com.cn.v2.common.utils.MD5Utils;
import com.cn.v2.common.utils.MapperBeansUtil;
import com.cn.v2.common.utils.PropertyManager;
import com.cn.v2.common.utils.StringUtils;
import com.cn.v2.data.bean.FileBean;
import com.cn.v2.data.mapper.FileMapper;
import com.cn.v2.service.fileMgr.DESEncryptFile;

public class FileProcessUtil {
	static final Log log = LogFactory.getLog(FileProcessUtil.class);
	static FileMapper mapper = (FileMapper) MapperBeansUtil.getBean("fileMapper");

	public static FileBean fileWrapperBean(File file) {
		String fileName = file.getName();
		String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		String signature = MD5Utils.getFileMD5String(file);
		String path = file.getAbsolutePath();
		long length = file.length() / 1024;
		String updatetime = StringUtils.LongToString(file.lastModified(), "yyyy-MM-dd:HH:mm:ss");
		String createtime = StringUtils.DateToString(new Date(), "yyyy-MM-dd:HH:mm:ss");
		FileBean fileBean = new FileBean();
		fileBean.setFileName(fileName);
		fileBean.setSuffix(fileSuffix);
		fileBean.setLength(length);
		fileBean.setPath(path);
		fileBean.setCreateTime(createtime);
		fileBean.setUpdateTime(updatetime);
		fileBean.setEncryptType(0);// TODO : 从加载到内存中的参数表中获取
		fileBean.setSignature(signature);
		fileBean.setPathMd5(MD5Utils.getMD5String(path));
		return fileBean;
	}

	public static void processFile(File file) {
		FileBean bean = fileWrapperBean(file);
		log.debug(mapper);
		log.debug(bean.toString());
		Map paramsMap = new HashMap();
		paramsMap.put("signature", bean.getSignature());
		log.debug(paramsMap);
		if (mapper.fileIsExists(paramsMap) == 0) {// 已经存在位置相同内容相同的文件，不进行任何处理
			log.debug("文件不存在，保存文件！");
			try {
				// 判断是否需要对文件采用默认密码加密
				if (bean.getEncryptType() == 2) {// 0 不加密 1隐藏文件 2 加密存储
					String path = bean.getPath();
					String filename = bean.getFileName();
					String encryptPwd = CryptUtil.decrypt(bean.getEncryptPwd());
					String encryptFilename = filename + ".peds." + bean.getSuffix();
					bean.setEncryptFileName(encryptFilename);
					DESEncryptFile.blockEncCode(path + filename + "." + bean.getSuffix(), path + encryptFilename, encryptPwd);
					boolean isDelete = Boolean.parseBoolean(PropertyManager.getProperty("isdelete"));// TODO 从内存参数中读取
					if (isDelete) {
						new File(path).delete();
					}
				}
				if (bean.getEncryptType() == 1) {
					FileProcessUtil.setFileHidden(bean.getPath() + File.separator + bean.getFileName());
				}
				mapper.addFile(bean);
			} catch (Exception e) {
				// TODO:记录失败日志 时间 动作 详细描述
				log.debug(e.getMessage());
			}
		} else {
			log.debug("文件已存在，进入判断是否要放入重复文件列表流程！");
			paramsMap.put("pathmd5", bean.getPathMd5());
			if (mapper.fileIsExists(paramsMap) == 0) {// MD5签名一致，则认为是重复文件，放入重复文件列表
				// TODO : 存入重复文件列表 创建时间 路径 已存在文件位置
				mapper.addFileToDuplicate(bean);
			}
		}
		log.debug(bean.getFileName() + "文件处理完成");
	}

	public static String decryptFile(int id, String password) {
		JSONObject jsobj = new JSONObject();
		boolean flag = true;
		String msg = "解压成功！";
		try {
			FileBean file = mapper.getFileById(id);
			String encryptFilename = file.getEncryptFileName();
			String path = file.getPath();
			String filename = file.getFileName();
			String suffix = file.getSuffix();
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

	/**
	 * 使用系统默认程序打开文件
	 * 
	 * @param file
	 * @return
	 */
	public static boolean openFile(String file) {
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

	/**
	 * 设置文件为隐藏文件
	 * 
	 * @param filePath
	 */
	public static void setFileHidden(String filePath) {
		try {
			String command = "attrib " + "\"" + filePath + "\"" + " +H";
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	public static String getOsType() {
		String osName = System.getProperty("os.name", "");
		if (osName.startsWith("Mac OS")) {
			return "Mac";
		} else if (osName.startsWith("Windows")) {
			return "Windows";
		} else { // assume Unix or Linux
			return "Unix";
		}
	}

	public static String listDirTree(String path, int level, String pid) {
		level++;
		String osType = getOsType();
		if ("Windows".equals(osType) && "".equals(path)) {
			return getWindowsRoot(level, pid);
		} else {
			JSONArray arrJson = new JSONArray();
			File dir = new File(path);
			File[] files = dir.listFiles(new FileFilter() {

				public boolean accept(File pathname) {
					if (pathname.getName().startsWith("$")) {
						return false;
					} else {
						return true;
					}
				}

			});
			int count = 1;
			try {
				if (files == null) {
					JSONObject json = new JSONObject();
					json.put("id", level + "_" + count++);
					json.put("pId", pid);
					json.put("name", "无可显示的文件或目录！");
					json.put("path", "");
					json.put("level", level);
					json.put("isParent", false);
					arrJson.put(json);
					return arrJson.toString();// 光驱或者移动磁盘为加载
				}
				for (File file : files) {
					JSONObject json = new JSONObject();

					json.put("id", level + "_" + count++);
					json.put("pId", pid);
					json.put("name", file.getName());
					json.put("path", file.getAbsolutePath());
					json.put("level", level);
					json.put("isParent", file.isDirectory() ? true : false);
					arrJson.put(json);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return arrJson.toString();

		}

	}

	/**
	 * 获取windows系统的磁盘跟节点
	 * 
	 * @return
	 */
	public static String getWindowsRoot(int level, String pid) {
		FileSystemView fileSystemView = FileSystemView.getFileSystemView();
		File[] roots = File.listRoots();
		JSONArray jsonArr = new JSONArray();
		int count = 1;
		try {
			for (File file : roots) {
				String name = fileSystemView.getSystemDisplayName(file);
				JSONObject json = new JSONObject();
				json.put("id", level + "_" + count++);
				json.put("pid", pid);
				json.put("name", "".equals(name) ? file.getAbsolutePath() : name);
				json.put("path", file.getAbsolutePath());
				json.put("isParent", true);
				jsonArr.put(json);
			}
		} catch (NullPointerException e) {
			log.debug(e.getMessage());
		} catch (JSONException e) {
			log.debug(e.getMessage());
		}
		return jsonArr.toString();
	}

	/**
	 * 获取操作系统的根节点
	 * 
	 * @return
	 */
	public static String getFileRoot() {
		String osType = getOsType();
		JSONObject root = new JSONObject();
		try {
			root.put("id", 1);
			root.put("pid", 0);
			root.put("isParent", true);
			if ("Windows".equals(osType)) {
				root.put("name", "Windows资源管理器");
				root.put("path", "");
			} else {
				root.put("name", "/");
				root.put("path", "/");
			}
			root.put("level", 0);
		} catch (NullPointerException e) {
			log.debug(e.getMessage());
		} catch (JSONException e) {
			log.debug(e.getMessage());
		}
		return root.toString();
	}

	public static void main(String[] args) {
		System.out.println(getFileRoot());
	}
}