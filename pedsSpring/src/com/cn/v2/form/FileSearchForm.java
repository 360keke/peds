package com.cn.v2.form;

import java.util.Map;

/**
 * 文件管理分页参数类
 * 
 * @author yudabo
 */
public class FileSearchForm extends PaginationForm {

	String fileName="";
	String catalogName="";
	String catalogId="";
	String suffixs="";

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public String getSuffixs() {
		return suffixs;
	}

	public void setSuffixs(String suffixs) {
		this.suffixs = suffixs;
	}

	@Override
	public Map formToParamsMap() {
		Map params = super.formToParamsMap();
		params.put("fileName", this.fileName);
		params.put("catalogName", this.catalogName);
		params.put("catalogId", this.catalogId);
		params.put("suffixs", this.suffixs);
		return params;
	}

}
