package com.cn.v2.common.derbyMgr;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DerbyManager {
	public static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	public static String url = "jdbc:derby:" + "pedsDB";
	public static Connection conn = null;

	public final static String getBasePath() {
		String basePath = DerbyManager.class.getResource("/").getPath();
		basePath = URLDecoder.decode(basePath);
		// basePath = basePath.replace("/WEB-INF/classes/", "");
		if (System.getProperty("os.name").startsWith("Windows")) {
			basePath = basePath.substring(1);
		}
		return basePath;
	}

	public static void main(String[] args) {

		try {
			Statement s = DerbyManager.open().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String sql = "select * from app.peds_files where id=1";
			ResultSet rs = s.executeQuery(sql);
			s.close();
			System.out.println("Closed result set and statement" );
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
			if (conn == null) {
				System.out.println(url);
				conn = DriverManager.getConnection(url, props);
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
		try {
			DriverManager.getConnection(url + ";shutdown=true");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取序列值
	 * 
	 * @param sequenceName
	 * @return
	 */
	// public static int getSequence(String sequenceName){
	// String sql = "select "+sequenceName+".nextvalue from dual";
	//		
	// }
}
