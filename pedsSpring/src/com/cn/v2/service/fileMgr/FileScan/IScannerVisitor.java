package com.cn.v2.service.fileMgr.FileScan;

/**
 * 扫描器观察者接口
 * 
 * @author
 * @date Jan 9, 2012 5:43:39 PM
 * @description:
 */
public interface IScannerVisitor {
	/**
	 * 
	 * visitor
	 * 
	 * @param obj
	 * @auth 余达波 Jan 9, 2012 7:13:59 PM
	 */
	public void visitor(Object obj);

	/**
	 * 返回扫描结果 getScannerResult
	 * 
	 * @return Jan 9, 2012 7:13:35 PM
	 */
	public Object getScannerResult();

	public boolean getFlag();

	public void setFlag(boolean flag);

}
