package cn.com.peds.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import cn.com.peds.common.PropertyManager;

public class DerbyManager {
	public static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	public static String protocol = "jdbc:derby:";
	public static Connection conn = null;

	public static void main(String[] args) {

		try {
			Statement s = DerbyManager.open().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			String sql = "insert into peds_runparams(code,value) values(filterrule,pdf)";

			s.execute(sql);

			s.close();
			System.out.println("Closed result set and statement");
			conn.commit();
			conn.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static Connection open() {
		try {
			Class.forName(driver).newInstance();
			Properties props = new Properties();
			PropertyManager pm = PropertyManager.getInstance();
			props.put("user", pm.getProperty("username"));
			props.put("password", pm.getProperty("password"));
			String database = pm.getProperty("databasename");
			if (conn == null) {
				conn = DriverManager.getConnection(protocol + database
						+ ";create=true", props);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void commit() {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeDataBase() {
		String database = PropertyManager.getProperty("databasename");
		try {
			DriverManager.getConnection("jdbc:derby:" + database
					+ ";shutdown=true");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取序列值
	 * @param sequenceName
	 * @return
	 */
//	public static int getSequence(String sequenceName){
//		String sql = "select "+sequenceName+".nextvalue from dual";
//		
//	}
}
