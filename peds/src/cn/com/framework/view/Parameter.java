package cn.com.framework.view;

import java.io.Serializable;

public class Parameter implements Serializable {
	private String key;
	private String value;
	private boolean direct;

	public Parameter() {
		this.direct = false;
	}

	public boolean isDirect() {
		return this.direct;
	}

	public String getKey() {
		return this.key;
	}

	public String getValue() {
		return this.value;
	}

	public void setDirect(boolean direct) {
		this.direct = direct;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
