package cn.com.peds.fileMgr.bean;

import cn.com.peds.common.CryptUtil;

public class PedsFileBean {
	public String getEncryptPwd() {
		return encryptPwd;
	}

	public void setEncryptPwd(String encryptPwd) {
		this.encryptPwd = encryptPwd;
	}

	public String getIsencrypt() {
		return isencrypt;
	}

	public void setIsencrypt(String isencrypt) {
		this.isencrypt = isencrypt;
	}

	public String getEncryptFileName() {
		return encryptFileName;
	}

	public void setEncryptFileName(String encryptFileName) {
		this.encryptFileName = encryptFileName;
	}

	int id;
	String path;
	String fileName;
	String suffix;
	float length;
	String lastModify;
	String createTime;
	String updateTime;
	boolean canRead;
	boolean canWrite;
	boolean canExecute;
	boolean isHidden;
	String signature;// 文件签名信息
	String encryptPwd;// 加密密码
	String isencrypt;// 是否已经加密标记
	String encryptFileName;// 加密后的文件名称

	public PedsFileBean(String path, String fileName, String suffix,
			float length, String lastModify, String createTime,
			String updateTime, boolean canRead, boolean canWrite,
			boolean canExecute, boolean isHidden) {
		this.path = path;
		this.fileName = fileName;
		this.suffix = suffix;
		this.length = length;
		this.lastModify = lastModify;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.canRead = canRead;
		this.canWrite = canWrite;
		this.canExecute = canExecute;
		this.isHidden = isHidden;
		this.signature = CryptUtil.encryptSHA(path + fileName + suffix);
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public String getLastModify() {
		return lastModify;
	}

	public void setLastModify(String lastModify) {
		this.lastModify = lastModify;
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

	public int isCanRead() {
		return canRead ? 0 : 1;
	}

	public void setCanRead(boolean canRead) {
		this.canRead = canRead;
	}

	public int isCanWrite() {
		return canWrite ? 0 : 1;
	}

	public void setCanWrite(boolean canWrite) {
		this.canWrite = canWrite;
	}

	public int isCanExecute() {
		return canExecute ? 0 : 1;
	}

	public void setCanExecute(boolean canExecute) {
		this.canExecute = canExecute;
	}

	public int isHidden() {
		return isHidden ? 0 : 1;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
