package com.beststar.framework.common.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.beststar.framework.common.crypt.CryptUtil;
import com.beststar.framework.common.exception.SystemException;
import com.bitmechanic.sql.ConnectionPool;
import com.bitmechanic.sql.ConnectionPoolManager;

public class ConnectionManager {
	static String CONNECTION_TYPE = "";
	static String DB_DRIVER = "";
	static String DB_URL = "";
	static String USER = "";
	static String PASSWORD = "";
	static String JNDI_DATASOURCE_NAME = "";
	static HashMap connectProperties = new HashMap();
	static HashMap dataSourceMap = new HashMap();

	static ConnectionPoolManager pool = null;

	private static void setResouceBundle(int dbnum) {
		if (connectProperties.get(dbnum + "") == null) {
			ResourceBundle rb = ResourceBundle.getBundle("DataBase");
			DB_DRIVER = rb.getString("DB_DRIVER" + dbnum);
			DB_URL = rb.getString("DB_URL" + dbnum);
			USER = rb.getString("USER" + dbnum);
			PASSWORD = CryptUtil.decrypt(rb.getString("PASSWORD" + dbnum));
			System.out.println("password="+PASSWORD);
			CONNECTION_TYPE = rb.getString("CONNECTION_TYPE" + dbnum);
			JNDI_DATASOURCE_NAME = rb.getString("JNDI_DATASOURCE_NAME" + dbnum);

			Properties properties = new Properties();
			properties.put("DB_DRIVER", DB_DRIVER);
			properties.put("DB_URL", DB_URL);
			properties.put("USER", USER);
			properties.put("PASSWORD", PASSWORD);
			properties.put("CONNECTION_TYPE", CONNECTION_TYPE);
			properties.put("JNDI_DATASOURCE_NAME", JNDI_DATASOURCE_NAME);
			connectProperties.put(dbnum + "", properties);
		} else {
			Properties properties = (Properties) connectProperties.get(dbnum + "");

			DB_DRIVER = (String) properties.get("DB_DRIVER");
			DB_URL = (String) properties.get("DB_URL");
			USER = (String) properties.get("USER");
			PASSWORD = (String) properties.get("PASSWORD");
			CONNECTION_TYPE = (String) properties.get("CONNECTION_TYPE");
			JNDI_DATASOURCE_NAME = (String) properties.get("JNDI_DATASOURCE_NAME");
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
				properties.put("java.naming.security.credentials", (PASSWORD == null) ? "" : PASSWORD);
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
			SystemException sysexception = new SystemException("获取DataSource：" + JNDI_DATASOURCE_NAME + "失败。");
			sysexception.setStackTrace(ex.getStackTrace());
			throw sysexception;
		}
	}

	public static Connection getConnection() throws SystemException {
		return getConnection(1);
	}

	public static Connection getConnection(int dbnum) throws SystemException {
		DataSource ds = null;
		Connection connection = null;
		ds = null;
		setResouceBundle(dbnum);
		if (("2".equals(CONNECTION_TYPE)) || ("3".equals(CONNECTION_TYPE))) {
			return getDataSourceConnection();
		}
		return getJDBCConnection();
	}

	private static Connection getJDBCConnection() throws SystemException {
		try {
			Class.forName(DB_DRIVER);
			return DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (ClassNotFoundException cnfe) {
			throw new SystemException(cnfe);
		} catch (SQLException cnfe) {
			throw new SystemException(cnfe);
		}
	}

	private static Connection getDataSourceConnection() throws SystemException {
		Object dataSource_obj = dataSourceMap.get(JNDI_DATASOURCE_NAME);

		if (dataSource_obj == null) {
			if ("2".equals(CONNECTION_TYPE)) {
				dataSource_obj = getPoolConnection();
				dataSourceMap.put(JNDI_DATASOURCE_NAME, dataSource_obj);
			} else if ("3".equals(CONNECTION_TYPE)) {
				dataSource_obj = getDataSource();
				dataSourceMap.put(JNDI_DATASOURCE_NAME, dataSource_obj);
			}
		}

		SystemException sysexception;
		if ((dataSource_obj != null) && (dataSource_obj instanceof DataSource))
			try {
				return ((DataSource) dataSource_obj).getConnection();
			} catch (SQLException ex) {
				sysexception = new SystemException("数据库无法连接！");
				sysexception.setStackTrace(ex.getStackTrace());
				throw sysexception;
			}
		if ((dataSource_obj != null) && (dataSource_obj instanceof ConnectionPool)) {
			try {
				Connection connect = ((ConnectionPool) dataSource_obj).getConnection();
				try {
					Statement statement = connect.createStatement();
					statement.close();
				} catch (SQLException ex) {
					((ConnectionPool) dataSource_obj).removeAllConnections();
					return getDataSourceConnection();
				}
				return connect;
			} catch (SQLException ex1) {
				sysexception = new SystemException("数据库无法连接！");
				sysexception.setStackTrace(ex1.getStackTrace());
				throw sysexception;
			}
		}
		throw new SystemException("没有您请求的数据源,请确认DataBase是否配置!");
	}

	private static ConnectionPool getPoolConnection() throws SystemException {
		try {
			pool = new ConnectionPoolManager(300);
			Class.forName(DB_DRIVER).newInstance();
			System.out.println("password="+PASSWORD);
			pool.addAlias(JNDI_DATASOURCE_NAME, DB_DRIVER, DB_URL, USER, PASSWORD, 20, 300, -1);

			pool.getPool(JNDI_DATASOURCE_NAME).setCacheStatements(false);
			return pool.getPool(JNDI_DATASOURCE_NAME);
		} catch (ClassNotFoundException ex) {
			throw new SystemException(ex);
		} catch (IllegalAccessException ex) {
			throw new SystemException(ex);
		} catch (InstantiationException ex) {
			throw new SystemException(ex);
		} catch (SQLException ex) {
			SystemException sysexception = new SystemException("数据库无法连接！");
			sysexception.setStackTrace(ex.getStackTrace());
			throw sysexception;
		}
	}
}