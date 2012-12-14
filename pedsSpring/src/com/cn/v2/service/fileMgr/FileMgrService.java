package com.cn.v2.service.fileMgr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cn.v2.data.bean.FileBean;
import com.cn.v2.data.mapper.FileMapper;
import com.cn.v2.form.FileScanner;
import com.cn.v2.service.fileMgr.FileScan.DirectoryScanner;
import com.cn.v2.service.fileMgr.FileScan.FileProcessDispather;
import com.cn.v2.service.fileMgr.FileScan.FileProcessUtil;
import com.cn.v2.service.fileMgr.FileScan.FileScannerVisitor;

/**
 * @author yudabo at 20121115 peds 管理系统文件处理类包含文件扫描 文件入库，文件加密，文件解密
 */

public class FileMgrService {
	static final Log log = LogFactory.getLog(FileMgrService.class);
	FileMapper mapper;

	public int addFile(FileBean file) throws Exception {
		int id = mapper.addFile(file);
		return id;
	}

	public List search(Map params) {
		List files = mapper.search(params);
		return files;
	}

	public int delFileById(int id) {
		int count = mapper.delFileById(id);
		return count;
	}

	public int delFiles(String[] ids) {
		int count =0;
		for(String key :ids){
			int id = Integer.parseInt(key);
			mapper.delFileById(id);
			count++;
		}
		return count;
	}

	public long getFileTotalCount(Map params) {
		long count = mapper.getFileTotalCount(params);
		return count;
	}

	public void fileScanner(List dir) {
		for (int i = 0; i < dir.size(); i++) {
			String path = ((FileScanner) dir.get(i)).getName();
			File file = new File(path);
			if (file.isDirectory()) {
				FileScannerVisitor visitor = new FileScannerVisitor();
				/**
				 * 启动扫描线程
				 */
				DirectoryScanner scanner = new DirectoryScanner(file, visitor);
				Thread scanThread = new Thread(scanner);
				scanThread.start();
				/**
				 * 启动处理线程
				 */
				FileProcessDispather processor = new FileProcessDispather(visitor);
				Thread procThread = new Thread(processor);
				procThread.start();

				/**
				 * 等待扫描完成
				 */
				try {
					scanThread.join();
					procThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				FileProcessUtil.processFile(file);
			}
		}
	}

	/**
	 * 使用默认浏览器打开访问地址
	 * 
	 * @param url
	 */
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

	/**
	 * 更新文件属性方法
	 * 
	 * @param file
	 */
	public void updatePedsFile(FileBean file) {
		mapper.updateFile(file);

	}

	public static void main(String[] args) throws IllegalArgumentException, ClassNotFoundException, IllegalAccessException, InterruptedException, InvocationTargetException, IOException,
			NoSuchMethodException {
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
		while ((str = br.readLine()) != null) {
			System.out.println(ByteUtil.decode(str));
		}
	}

	public FileMapper getMapper() {
		return mapper;
	}

	public void setMapper(FileMapper mapper) {
		this.mapper = mapper;
	}
}
