package com.cn.v2.service.fileMgr.FileScan;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 递归调用 文件夹的文件扫描
 * 
 * @date Jan 9, 2012 11:16:39 AM
 * @description:
 */
public class DirectoryScanner implements Runnable {
	static final Log log = LogFactory.getLog(DirectoryScanner.class);
	private File dir;

	private IScannerVisitor visitor;

	public DirectoryScanner(File dir) {
		this.dir = dir;
	}

	public DirectoryScanner(File dir, IScannerVisitor visitor) {
		this(dir);
		this.visitor = visitor;
	}

	public void run() {
		// try {
		// if (dir.isDirectory()) {
		// File[] fileList = dir.listFiles(new FileScannerFilter("S"));// 根据文件后缀进行扫描
		// for (int i = 0; i < fileList.length; i++) {
		// File file = fileList[i];
		// DirectoryScanner sub = new DirectoryScanner(file, visitor);
		// Thread subThread = new Thread(sub);
		// subThread.start();
		// try {
		// subThread.join();
		// } catch (InterruptedException e) {
		// log.error(e.getMessage());
		// }
		// }
		// log.debug("end_dir:" + dir.getPath());
		// } else {
		// log.debug("file:" + dir.getPath());
		// if (visitor != null) {
		// visitor.visitor(dir);
		// }
		// }
		// } catch (Throwable e) {
		// log.debug(e.getMessage());
		// }
		scanFile(this.dir);
		visitor.setFlag(true);
		log.debug("生产线程结束***********************************" + visitor.getFlag());
	}

	public void scanFile(File fileDir) {
		log.debug(fileDir.getAbsoluteFile() + "************************************");
		if (fileDir.isDirectory()) {
			File[] files = fileDir.listFiles(new FileScannerFilter("S"));
			for (File file : files) {
				scanFile(file);
			}
		} else {
			if (visitor != null) {
				visitor.visitor(fileDir);
			}
		}
	}

	public File getDir() {
		return dir;
	}

	public void setDir(File dir) {
		this.dir = dir;
	}

	public IScannerVisitor getVisitor() {
		return visitor;
	}

	public void setVisitor(IScannerVisitor visitor) {
		this.visitor = visitor;
	}
}
