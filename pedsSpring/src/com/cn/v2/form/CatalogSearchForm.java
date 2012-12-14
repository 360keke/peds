package com.cn.v2.form;

import java.util.Map;

public class CatalogSearchForm extends PaginationForm {
	String catalogName;
	String type="0";

	public CatalogSearchForm() {
		super.setOrderField("name");
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	@Override
	public Map formToParamsMap() {
		Map paramsmap = super.formToParamsMap();
		paramsmap.put("name", this.catalogName);
		paramsmap.put(type, this.type);
		return paramsmap;
	}

}
