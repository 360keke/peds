package com.cn.v2.service.fileMgr.FileScan;

import java.io.File;

public class FileScannerTest {
	public static void main(String[] args) {
		File baseDir = new File("C:\\葡萄酒");
		long starttime = System.currentTimeMillis();
		
		FileScannerVisitor visitor = new FileScannerVisitor();
		/**
		 * 启动扫描线程
		 */
		DirectoryScanner scanner = new DirectoryScanner(baseDir, visitor);
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
       long endtime = System.currentTimeMillis();
		/**
		 * 打印扫描结果
		 */
		System.out.println("cost time :"+(endtime-starttime));
		System.out.println("=-=-=-=-=-=-=-=-=结果-=-=-=-=-=-=-=-=-=");
		System.out.println("Directory:" + scanner.getDir().getPath());

	}
}
