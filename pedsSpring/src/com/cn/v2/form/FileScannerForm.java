package com.cn.v2.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.AutoPopulatingList;

public class FileScannerForm {
	List<FileScanner> dir = new AutoPopulatingList(new ArrayList(), FileScanner.class);
	List<FileScanner> file = new AutoPopulatingList(new ArrayList(), FileScanner.class);

	public List getDir() {
		return dir;
	}

	public void setDir(List<FileScanner> dir) {
		this.dir = dir;
	}

	public List getFile() {
		return file;
	}

	public void setFile(List<FileScanner> file) {
		this.file = file;
	}
}
