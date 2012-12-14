package com.cn.v2.service.fileMgr.FileScan;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * 扫描器之访问者实现--文件访问者
 * 
 * @author 高伟
 * @date Jan 9, 2012 5:43:12 PM
 * @description:
 */
public class FileScannerVisitor implements IScannerVisitor {
	boolean flag = false;
	private List<File> fileList = new LinkedList<File>();

	public void visitor(Object obj) {
		if (obj instanceof File) {
			File file = (File) obj;
			synchronized (fileList) {
				if (!fileList.contains(file)) {
					fileList.add(file);
				}
				fileList.notify();
			}
		}
	}

	public Object getScannerResult() {
		return fileList;
	}

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
