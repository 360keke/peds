package com.cn.v2.data.bean;

public class FileBean extends PedsBaseBean {
	private int id;// id
	private String path="";// 文件路径
	private String pathMd5="";
	private String suffix="";// 文件后缀
	private String fileName="";
	private float length;
	private String signature="";// 文件签名，判断文件是否重名
	private String createTime="";
	private String updateTime="";
	private int encryptType;// 0 不加密 1普通加密隐藏文件 2深度加密 需要密码进行解密
	private String encryptPwd="";
	private String encryptFileName="";
	private int catalogId;
	private String catalogName;
	private String keywords="";
	private String description="";
	private String rsrv1="";
	private String rsrv2="";

	public FileBean() {
	}

	public FileBean(int id, String path, String suffix, String fileName, float length, String signature, String createTime,
			String updateTime, int encryptType, String encryptPwd, String encryptFileName, String rsrv1, String rsrv2) {
		this.id = id;
		this.path = path;
		this.suffix = suffix;
		this.fileName = fileName;
		this.length = length;
		this.signature = signature;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.encryptType = encryptType;
		this.encryptPwd = encryptPwd;
		this.encryptFileName = encryptFileName;
		this.rsrv1 = rsrv1;
		this.rsrv2 = rsrv2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int getEncryptType() {
		return encryptType;
	}

	public void setEncryptType(int encryptType) {
		this.encryptType = encryptType;
	}

	public String getEncryptPwd() {
		return encryptPwd;
	}

	public void setEncryptPwd(String encryptPwd) {
		this.encryptPwd = encryptPwd;
	}

	public String getEncryptFileName() {
		return encryptFileName;
	}

	public void setEncryptFileName(String encryptFileName) {
		this.encryptFileName = encryptFileName;
	}

	public String getRsrv1() {
		return rsrv1;
	}

	public void setRsrv1(String rsrv1) {
		this.rsrv1 = rsrv1;
	}

	public String getRsrv2() {
		return rsrv2;
	}

	public void setRsrv2(String rsrv2) {
		this.rsrv2 = rsrv2;
	}

	public int getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

	public String getPathMd5() {
		return pathMd5;
	}

	public void setPathMd5(String pathMd5) {
		this.pathMd5 = pathMd5;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static void main(String[] args){
		FileBean file = new FileBean();
		System.out.println(file.toString());
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
}
