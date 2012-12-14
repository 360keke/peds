package cn.com.peds.common.bean;

public class SysParam {
	String code;
	String value;
	String description;

	public SysParam(String code, String value, String desc) {
		super();
		this.code = code;
		this.value = value;
		this.description = desc;
	}

	public SysParam() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return description;
	}

	public void setDesc(String desc) {
		this.description = desc;
	}

	public boolean equals(Object o) {
		SysParam sysParam = (SysParam) o;
		if (sysParam.getCode().equals(this.code)) {
			return true;
		} else {
			return false;
		}
	}
}
