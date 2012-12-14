package com.cn.v2.service.fileMgr.FileScan;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author 余达波
 * @date 2012-11-29 20：58
 * @description:负责分发文件
 */
public class FileProcessDispather implements Runnable {
	final Log log = LogFactory.getLog(FileProcessDispather.class);
	private IScannerVisitor visitor;
	private ExecutorService pools;

	public FileProcessDispather(IScannerVisitor visitor) {
		this.visitor = visitor;
		/**
		 * 使用无大小限制的连接池 通过下边任务控制，完成线程数量的控制
		 */
		this.pools = Executors.newCachedThreadPool();
	}

	public void run() {
		log.debug("visitor status is :" + visitor.getFlag() + "while before ***************");
		while (((List) visitor.getScannerResult()).size() > 0 || !visitor.getFlag()) {
			List<File> fileList = (List<File>) visitor.getScannerResult();
			log.debug("visitor status is " + visitor.getFlag() + "size=" + fileList.size() + "******************************");
			synchronized (fileList) {
				if (fileList.size() == 0 &&!visitor.getFlag()) {
					try {
						fileList.wait();
					} catch (InterruptedException e) {
						log.error(e.getMessage());
					}
				}
			}
			if (fileList.size() <= 100) {
				scheduleTasks(5);
			} else if (fileList.size() > 100) {
				scheduleTasks(20);
			} else if (fileList.size() > 1000) {
				scheduleTasks(50);
			} else if (fileList.size() > 2000) {
				scheduleTasks(100);
			}
		}
		pools.shutdown();
		log.debug("消费线程结束*********************");
	}

	private void scheduleTasks(int count) {
		for (int j = 0; j < count; j++) {
			pools.submit(new FileProcessor((List<File>) visitor.getScannerResult()));
		}
	}

}