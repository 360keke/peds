package com.cn.v2.service.fileMgr.FileScan;

import java.io.File;
import java.io.FileFilter;

public class FileScannerFilter implements FileFilter {
	String type = "S";// N 表示根据名称 ; S 表示根据后缀 ;P 表示根据路径名 ; R 表示根据正则表达式

	FileScannerFilter(String type) {
		this.type = type;
	}

	public boolean accept(File pathname) {
		String fileName = pathname.getName();
		if (fileName.startsWith("$")) {
			return false;
		}
		if (pathname.isDirectory()) {
			return true;
		} else {
			if ("S".equals(this.type)) {
				return acceptBySuffix(pathname);
			} else if ("R".equals(this.type)) {
				return acceptByRegex(pathname);
			} else {
				return false;
			}
		}
	}

	public boolean acceptBySuffix(File file) {
		return file.getName().endsWith("pdf");
	}

	public boolean acceptByRegex(File file) {
		// TODO 需要实现文件名正则匹配
		return true;
	}
}
