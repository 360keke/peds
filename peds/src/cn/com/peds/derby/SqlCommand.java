package cn.com.peds.derby;

public class SqlCommand {
	String name;
	String sql;

	public SqlCommand(String name, String sql) {
		this.name = name;
		this.sql = sql;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

}
