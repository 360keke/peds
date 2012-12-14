package com.cn.v2.form;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页form
 * 
 * @author yudabo
 * 
 */
public class PaginationForm {
	int pageNum = 1;// 第几页 
	int numPerPage = 20;// 每页显示条数
	String orderField = ""; // 排序字段
	String orderDirection = "desc";// 排序方向
	int pageNumShown = 10;// 页标显示个数
	int currentPage = 1;// 当前页
	float totalCount;// 总条数
	public int getPageNum() {
		return pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
		this.pageNum=1;
		this.currentPage=1;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	public int getPageNumShown() {
		return pageNumShown;
	}

	public void setPageNumShown(int pageNumShown) {
		this.pageNumShown = pageNumShown;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	// public void setCurrentPage(int currentPage) {
	// this.currentPage = currentPage;
	// }

	public String getTotalCount() {
		String result = "";
		if (this.totalCount == 0) {
			result = "0";
		} else {
			result = String.valueOf(totalCount);
			if (result.indexOf(".") > 0) {
				result = result.substring(0, result.lastIndexOf("."));
			}
		}
		return result;

	}

	public void setTotalCount(float totalCount) {
		this.totalCount = totalCount;
		if (this.pageNum > (int) (this.totalCount / this.numPerPage)) {
			this.pageNum = 1;
		}
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
		this.currentPage = pageNum;
	}

	public String toString() {
		return "pageNum=" + this.pageNum + " numPerPage=" + this.numPerPage + " orderField=" + this.orderField + " orderDirection=" + this.orderDirection + " pageNumShown=" + this.pageNumShown
				+ " currentPage=" + this.currentPage + " totalCount=" + this.totalCount;
	}

	public float getOffset() {
		if (this.pageNum == 1) {
			return 1;
		} else {
			return (this.pageNum - 1) * this.numPerPage;
		}
	}

	public Map formToParamsMap() {
		Map paramsMap = new HashMap();
		paramsMap.put("pageNum", this.pageNum);
		paramsMap.put("numPerPage", this.numPerPage);
		paramsMap.put("orderField", this.orderField);
		paramsMap.put("orderDirection", this.orderDirection);
		paramsMap.put("currentPage", this.currentPage);
		paramsMap.put("offset", this.getOffset());
		paramsMap.put("nextfetch", this.numPerPage);
		return paramsMap;

	}
}
