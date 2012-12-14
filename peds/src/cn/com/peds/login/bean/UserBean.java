package cn.com.peds.login.bean;

import cn.com.framework.exception.SystemException;
import cn.com.peds.derby.DataStore;

public class UserBean {
	String name;
	String password;
	int role;
	int failNum;
	int lockFlag;

	public UserBean(String name, String password, int role, int failNum,
			int lockFlag) {
		super();
		this.name = name;
		this.password = password;
		this.role = role;
		this.failNum = failNum;
		this.lockFlag = lockFlag;
	}

	public UserBean(DataStore ds, int index) throws SystemException {
		this.name = ds.getString(index, "name");
		this.password = ds.getString(index, "password");
		this.role = ds.getInt(index, "role");
		this.failNum = ds.getInt(index, "faillogin");
		this.lockFlag = ds.getInt(index, "lockflag");

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getFailNum() {
		return failNum;
	}

	public void setFailNum(int failNum) {
		this.failNum = failNum;
	}

	public int getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(int lockFlag) {
		this.lockFlag = lockFlag;
	}
}
