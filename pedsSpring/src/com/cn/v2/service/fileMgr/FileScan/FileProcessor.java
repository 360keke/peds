package com.cn.v2.service.fileMgr.FileScan;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cn.v2.common.utils.CryptUtil;
import com.cn.v2.common.utils.MD5Utils;
import com.cn.v2.common.utils.MapperBeansUtil;
import com.cn.v2.common.utils.PropertyManager;
import com.cn.v2.common.utils.StringUtils;
import com.cn.v2.data.bean.FileBean;
import com.cn.v2.data.mapper.FileMapper;
import com.cn.v2.service.fileMgr.DESEncryptFile;

/**
 * 单个文件处理单元，实现文件的重复判断，入库、加密、md5签名生成、移动等
 * 
 * @author 余达波
 * @date 2012-11-29 20：54
 * @description:
 */
public class FileProcessor implements Runnable {
	final Log log = LogFactory.getLog(FileProcessor.class);
	private List<File> fileList;

	public FileProcessor(List<File> fileList) {
		this.fileList = fileList;
	}

	public void run() {
		String filename = "";
		synchronized (fileList) {
			if (fileList.size() > 0) {
				File file = fileList.remove(fileList.size() - 1);
				log.debug("process file:" + file.getAbsolutePath());
				FileProcessUtil.processFile(file);
				filename = file.getAbsolutePath();
			}
		}
		log.debug(filename + "文件处理结束！");
	}
}
