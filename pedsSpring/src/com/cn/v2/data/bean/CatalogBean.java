package com.cn.v2.data.bean;

public class CatalogBean {
	private int id;// id
	private int parentId;// 父分类id
	private String name="";// 名称
	private int type;// 分类 0 文件分类 1 资源分类
	private String rsrv1="";// 保留字段
	private String parentName="";//父分类名称

	CatalogBean() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getRsrv1() {
		return rsrv1;
	}

	public void setRsrv1(String rsrv1) {
		this.rsrv1 = rsrv1;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
