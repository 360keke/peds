package cn.com.peds.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import sun.jdbc.odbc.ee.ConnectionPool;
import cn.com.framework.exception.SystemException;

public class ConnectionManager {
	static String CONNECTION_TYPE = "";
	static String DB_DRIVER = "";
	static String DB_URL = "";
	static String USER = "";
	static String PASSWORD = "";
	static String JNDI_DATASOURCE_NAME = "";
	static HashMap connectProperties = new HashMap();
	static HashMap dataSourceMap = new HashMap();

	private static void setResouceBundle(int dbnum) {
		if (connectProperties.get(dbnum + "") == null) {
			ResourceBundle rb = ResourceBundle.getBundle("DataBase");
			DB_DRIVER = rb.getString("DB_DRIVER" + dbnum);
			DB_URL = rb.getString("DB_URL" + dbnum);
			USER = rb.getString("USER" + dbnum);
			PASSWORD = rb.getString("PASSWORD" + dbnum);
			CONNECTION_TYPE = rb.getString("CONNECTION_TYPE" + dbnum);
			JNDI_DATASOURCE_NAME = rb.getString("JNDI_DATASOURCE_NAME" + dbnum);

			Properties properties = new Properties();
			properties.put("DB_DRIVER", DB_DRIVER);
			properties.put("DB_URL", DB_URL);
			properties.put("USER", USER);
			properties.put("PASSWORD", PASSWORD);
			properties.put("CONNECTION_TYPE", CONNECTION_TYPE);
			properties.put("JNDI_DATASOURCE_NAME", JNDI_DATASOURCE_NAME);
		} else {
			Properties properties = (Properties) connectProperties.get(dbnum
					+ "");

			DB_DRIVER = (String) properties.get("DB_DRIVER");
			DB_URL = (String) properties.get("DB_URL");
			USER = (String) properties.get("USER");
			PASSWORD = (String) properties.get("PASSWORD");
			CONNECTION_TYPE = (String) properties.get("CONNECTION_TYPE");
			JNDI_DATASOURCE_NAME = (String) properties
					.get("JNDI_DATASOURCE_NAME");
		}
	}

	private static InitialContext getInitialContext() throws SystemException {
		Properties properties = null;
		try {
			properties = new Properties();
			if ((DB_DRIVER != null) && (!(DB_DRIVER.trim().equals("")))) {
				properties.put("java.naming.factory.initial", DB_DRIVER);
			}

			if ((DB_URL != null) && (!(DB_URL.trim().equals("")))) {
				properties.put("java.naming.provider.url", DB_URL);
			}
			if ((USER != null) && (!(USER.trim().equals("")))) {
				properties.put("java.naming.security.principal", USER);
				properties.put("java.naming.security.credentials",
						(PASSWORD == null) ? "" : PASSWORD);
			}

			if (properties.size() > 0) {
				return new InitialContext(properties);
			}
			return new InitialContext();
		} catch (Exception e) {
			System.err.println("不能连接到数据源 " + DB_URL);

			System.err.println("Please make sure that the server is running.");
			throw new SystemException(e);
		}
	}

	private static DataSource getDataSource() throws SystemException {
		DataSource ds = null;
		try {
			InitialContext ctx = getInitialContext();
			ds = (DataSource) ctx.lookup(JNDI_DATASOURCE_NAME);
			return ds;
		} catch (Exception ex) {
			SystemException sysexception = new SystemException("获取DataSource："
					+ JNDI_DATASOURCE_NAME + "失败。");
			sysexception.setStackTrace(ex.getStackTrace());
			throw sysexception;
		}
	}

	public static Connection getConnection() throws SystemException {
		return getConnection(1);
	}

	public static Connection getConnection(int dbnum) throws SystemException {
		setResouceBundle(dbnum);
		return getJDBCConnection();
	}

	private static Connection getJDBCConnection() {
		Connection conn = null;
		try {
			Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException cnfe) {
			cnfe.printStackTrace();
		}
		return conn;
	}

}