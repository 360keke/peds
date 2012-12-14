package cn.com.framework.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import oracle.jdbc.OracleResultSet;
import oracle.sql.BLOB;
import oracle.sql.CLOB;
import cn.com.framework.exception.DataAlreadyExistException;
import cn.com.framework.exception.DataNotFoundException;
import cn.com.framework.exception.ForeignKeyRecordFoundException;
import cn.com.framework.exception.SystemException;
import cn.com.framework.utils.BeanUtils;
import cn.com.peds.common.StringUtils;
import cn.com.peds.derby.ConnectionManager;

public class OracleDaoImp implements BasicDao {

	public void setTransactionRollback(Connection connection, boolean flag)
			throws SystemException {
		if (!(flag))
			return;
		try {
			connection.rollback();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			throw new SystemException(e);
		}
	}

	public void setTransactionCommit(Connection connection, boolean flag)
			throws SystemException {
		if (!(flag))
			return;
		try {
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			throw new SystemException(e);
		}
	}

	public void setTransactionBegin(Connection connection, short operate)
			throws SystemException {
		try {
			connection.setAutoCommit(false);
			switch (operate) {
			case 1:
			case 2:
			case 4:
			case 5:
				connection.setTransactionIsolation(2);

				break;
			case 3:
			default:
				connection.setTransactionIsolation(2);
			}

		} catch (SQLException e) {
			throw new SystemException(e);
		}
	}

	public Connection getConnection() throws SystemException {
		return ConnectionManager.getConnection();
	}

	public void closeConnection(Connection connection, boolean flag)
			throws SystemException {
		if (!(flag))
			return;
		try {
			if ((connection != null) && (!(connection.isClosed())))
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("关闭 SQL Connection 失败。", e);
		}
	}

	public void closeStatement(Statement statement, boolean flag)
			throws SystemException {
		if (!(flag))
			return;
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			throw new SystemException("关闭 SQL Statement 失败。", e);
		}
	}

	public void closeResult(ResultSet rs, boolean flag) throws SystemException {
		if (!(flag))
			return;
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			throw new SystemException("关闭 SQL ResultSet 失败。", e);
		}
	}

	protected BigDecimal getAutoId(String autoId, Connection connection)
			throws SystemException {
		return getSequence(autoId, connection);
	}

	public BigDecimal getSequence(String autoId, Connection connection)
			throws SystemException {
		boolean flag = false;
		Statement statement = null;
		ResultSet rs = null;
		BigDecimal sequence_id = null;
		try {
			if (connection == null) {
				connection = getConnection();
				setTransactionBegin(connection, (short) 3);
				flag = true;
			}
			statement = connection.createStatement();

			String sql = "SELECT " + autoId + ".NEXTVAL FROM DUAL";
			rs = statement.executeQuery(sql);
			if (rs.next()) {
				sequence_id = rs.getBigDecimal(1);
			}

			setTransactionCommit(connection, flag);

		} catch (SQLException se) {
		} catch (SystemException e) {
		} finally {
			closeResult(rs, flag);
			closeStatement(statement, flag);
			closeConnection(connection, flag);
		}
		return sequence_id;
	}

	public Vector find(BasicBean bean, Connection connection)
			throws SystemException, DataNotFoundException {
		return find(bean, connection, null);
	}

	public Vector find(BasicBean bean, Connection connection, String partSql)
			throws SystemException, DataNotFoundException {
		return find(bean, connection, partSql, null);
	}

	// ERROR //
	public Vector find(BasicBean bean, Connection connection, String partSql,
			String orderBySql) throws SystemException, DataNotFoundException {
		return null;
	}

	public BasicBean load(BasicBean bean, Connection connection)
			throws SystemException, DataNotFoundException {
		return null;
	}

	public Vector findBySQL(String sql, BasicBean bean, Connection connection)
			throws SystemException, DataNotFoundException {
		return findBySQL(sql, null, bean, connection);
	}

	public Vector findBySQL(String sql, String orderBySql, BasicBean bean,
			Connection connection) throws SystemException,
			DataNotFoundException {
		String orderByStr = (orderBySql == null) ? "" : orderBySql;
		boolean flag = false;
		Statement statement = null;
		ResultSet rs = null;
		Vector v = null;
		try {
			if (connection == null) {
				connection = getConnection();
				setTransactionBegin(connection, (short) 3);
				flag = true;
			}
			statement = connection.createStatement();
			if (sql != null) {
				String countSql = convertCountStatement(sql);

				rs = statement.executeQuery(countSql);
				if ((rs != null) && (rs.next())) {
					bean.setRowCount(rs.getLong(1));
				}
				if (bean.getRowCount() == 0L) {
					throw new DataNotFoundException();
				}

				if ((bean.getPageNum() != 0L) && (bean.getPageSize() != 0)) {
					double pageCount = Math.ceil(bean.getRowCount()
							/ bean.getPageSize());

					if (pageCount < bean.getPageNum()) {
						bean.setPageNum((long) pageCount);
					}

					bean.setPageCount((long) pageCount);

					sql = convertPagingSql(sql, bean, orderByStr);
				} else {
					sql = sql + orderByStr;
				}

				rs = (OracleResultSet) statement.executeQuery(sql);

				v = getDataFromResultSet(rs, bean);

				if ((v == null) || (v.size() == 0)) {
					throw new DataNotFoundException();
				}

				setTransactionCommit(connection, flag);

				closeResult(rs, flag);
			}
			throw new SystemException("select语句不能为空。");
		} catch (SQLException se) {
		} catch (DataNotFoundException se) {
		} catch (SystemException e) {
		} finally {
			closeResult(rs, flag);
			closeStatement(statement, flag);
			closeConnection(connection, flag);
		}
		return v;
	}

	public BasicBean insert(BasicBean bean, Connection connection)
			throws DataAlreadyExistException, SystemException {
		boolean flag = false;
		Statement statement = null;
		try {
			if (connection == null) {
				connection = getConnection();
				setTransactionBegin(connection, (short) 1);
				flag = true;
			}

			if (bean.getAutoId() != null) {
				String singlePK = (String) bean.getPrimaryKey()[0];
				String setMethodName = "set"
						+ singlePK.substring(0, 1).toUpperCase()
						+ singlePK.substring(1);

				Method setMethod = bean.getClass().getMethod(setMethodName,
						new Class[] { Class.forName("java.math.BigDecimal") });

				setMethod.invoke(bean, new Object[] { getAutoId(bean
						.getAutoId(), connection) });
			}

			statement = connection.createStatement();
			HashMap clobs = new HashMap();
			String sql = getInsertStr(bean, clobs);

			int returnCount = 0;
			if (sql != null) {
				returnCount = statement.executeUpdate(sql);

				if ((clobs != null) && (!(clobs.isEmpty()))) {
					Iterator columns = clobs.keySet().iterator();
					while (columns.hasNext()) {
						String columnName = columns.next().toString();
						Object content = clobs.get(columnName);
						if (Class.forName("java.lang.String").isAssignableFrom(
								content.getClass())) {
							setClobColumn(bean, columnName, (String) clobs
									.get(columnName), connection);
						} else {
							if (!(Class.forName("java.io.File")
									.isAssignableFrom(content.getClass()))) {
								continue;
							}

							setBlobColumn(bean, columnName, (File) clobs
									.get(columnName), connection);
						}

					}

				}

			} else {
				throw new SystemException("insert语句生成错误。");
			}

			setTransactionCommit(connection, flag);
		} catch (SQLException e) {
			setTransactionRollback(connection, flag);
			int code = e.getErrorCode();
			if (code == 1) {
				throw new DataAlreadyExistException();
			}

			throw new SystemException(e);
		} catch (SystemException e) {
			setTransactionRollback(connection, flag);
			throw e;
		} catch (NoSuchMethodException e) {
			setTransactionRollback(connection, flag);
			throw new SystemException(e);
		} catch (InvocationTargetException e) {
			setTransactionRollback(connection, flag);
			throw new SystemException(e);
		} catch (IllegalAccessException e) {
			setTransactionRollback(connection, flag);
			throw new SystemException(e);
		} catch (ClassNotFoundException e) {
			setTransactionRollback(connection, flag);
			throw new SystemException(e);
		} finally {
			closeStatement(statement, flag);
			closeConnection(connection, flag);
		}
		return bean;
	}

	public BasicBean update(BasicBean bean, Connection connection)
			throws DataNotFoundException, SystemException {
		return update(bean, connection, false);
	}

	public BasicBean update(BasicBean bean, Connection connection,
			boolean updateAllColumns) throws DataNotFoundException,
			SystemException {
		boolean flag = false;
		Statement statement = null;
		try {
			if (connection == null) {
				connection = getConnection();
				setTransactionBegin(connection, (short) 4);
				flag = true;
			}

			statement = connection.createStatement();
			HashMap clobs = new HashMap();

			String sql = getUpdateStr(bean, clobs, updateAllColumns);
			int returnCount = 0;
			if (sql != null) {
				returnCount = statement.executeUpdate(sql);
				if (returnCount == 0) {
					throw new DataNotFoundException();
				}

				if ((clobs != null) && (!(clobs.isEmpty()))) {
					Iterator columns = clobs.keySet().iterator();
					while (columns.hasNext()) {
						String columnName = columns.next().toString();
						Object content = clobs.get(columnName);
						if (Class.forName("java.lang.String").isAssignableFrom(
								content.getClass())) {
							setClobColumn(bean, columnName, (String) clobs
									.get(columnName), connection);
						} else {
							if (!(Class.forName("java.io.File")
									.isAssignableFrom(content.getClass()))) {
								continue;
							}

							setBlobColumn(bean, columnName, (File) clobs
									.get(columnName), connection);
						}

					}

				}

				setTransactionCommit(connection, flag);
			} else {
				throw new SystemException("update语句生成错误。");
			}

			setTransactionCommit(connection, flag);
		} catch (SQLException e) {
			setTransactionRollback(connection, flag);
			throw new SystemException(e);
		} catch (DataNotFoundException e) {
			setTransactionRollback(connection, flag);
			throw new SystemException(e);
		} catch (ClassNotFoundException e) {
			setTransactionRollback(connection, flag);
			throw new SystemException(e);
		} catch (SystemException e) {
			setTransactionRollback(connection, flag);
			throw e;
		} finally {
			closeStatement(statement, flag);
			closeConnection(connection, flag);
		}
		return bean;
	}

	public BasicBean save(BasicBean bean, Connection connection)
			throws ForeignKeyRecordFoundException, SystemException,
			DataNotFoundException, DataAlreadyExistException {
		boolean flag = false;
		try {
			if (connection == null) {
				connection = getConnection();
				setTransactionBegin(connection, (short) 5);
				flag = true;
			}
			switch (bean.getAction()) {
			case 0:
				break;
			case 1:
				bean = insert(bean, connection);
				break;
			case 2:
				bean = update(bean, connection, bean.getUpdateType());
				break;
			case 3:
				bean = delete(bean, connection);
				break;
			default:
				throw new SystemException(
						"无效的Action标志，请设置为下列之一：BasicBean.INSERT，BasicBean.UPDATE，BasicBean.DELETE 。");
			}

			setTransactionCommit(connection, flag);
		} catch (DataAlreadyExistException e) {
			setTransactionRollback(connection, flag);
			throw e;
		} catch (DataNotFoundException e) {
			setTransactionRollback(connection, flag);
			throw e;
		} catch (SystemException e) {
			setTransactionRollback(connection, flag);
			throw e;
		} finally {
			closeConnection(connection, flag);
		}
		return bean;
	}

	public BasicBean delete(BasicBean bean, Connection connection)
			throws ForeignKeyRecordFoundException, DataNotFoundException,
			SystemException {
		boolean flag = false;
		Statement statement = null;
		try {
			if (connection == null) {
				connection = getConnection();
				setTransactionBegin(connection, (short) 2);
				flag = true;
			}
			statement = connection.createStatement();

			String sql = getDeleteStr(bean);
			int returnCount = 0;
			if (sql != null) {
				returnCount = statement.executeUpdate(sql);
				if (returnCount == 0)
					throw new DataNotFoundException();
			} else {
				throw new SystemException("delete语句生成错误。");
			}

			setTransactionCommit(connection, flag);
		} catch (DataNotFoundException e) {
			setTransactionRollback(connection, flag);
			throw e;
		} catch (SQLException e) {
			int code = e.getErrorCode();
			if (code == 2292) {
				setTransactionRollback(connection, flag);
				throw new ForeignKeyRecordFoundException();
			}

			setTransactionRollback(connection, flag);
			throw new SystemException(e);
		} catch (SystemException e) {
			setTransactionRollback(connection, flag);
			throw e;
		} finally {
			closeStatement(statement, flag);
			closeConnection(connection, flag);
		}
		return bean;
	}

	public String getSelectStr(BasicBean bean) throws SystemException {
		return "SELECT * FROM " + bean.getTablename().toUpperCase()
				+ getWhereStr(bean);
	}

	public String getLoadStr(BasicBean bean) throws SystemException {
		return "SELECT * FROM " + bean.getTablename().toUpperCase()
				+ primaryKeyToWhereStr(bean);
	}

	public String getInsertStr(BasicBean bean) throws SystemException {
		return getInsertStr(bean, null);
	}

	private String getInsertStr(BasicBean bean, HashMap clobColumns)
			throws SystemException {
		String valueStr = "";
		String insertStr = null;
		Class beanClass = bean.getClass();
		Vector columns = BeanUtils.getProperties(bean.getClass());
		for (int i = 0; i < columns.size(); ++i) {
			String column = (String) columns.get(i);
			if ((column.equalsIgnoreCase("action"))
					|| (column.equalsIgnoreCase("updateType"))
					|| (column.equalsIgnoreCase("pageCount"))
					|| (column.equalsIgnoreCase("pageNum"))
					|| (column.equalsIgnoreCase("pageSize")))
				continue;
			if (column.equalsIgnoreCase("rowCount")) {
				continue;
			}

			if ((column.equalsIgnoreCase("createTime"))
					|| (column.equalsIgnoreCase("lastUpdateTime"))
					|| (column.equalsIgnoreCase("czsjc"))) {
				if (insertStr == null) {
					insertStr = " ( ";
					valueStr = " VALUES ( ";
				} else if (insertStr.startsWith(" ( ")) {
					insertStr = insertStr + " , ";
					valueStr = valueStr + " , ";
				}
				insertStr = insertStr + column.toUpperCase();
				valueStr = valueStr + "SYSDATE";
			} else {
				String subMethodName = column.substring(0, 1).toUpperCase()
						+ column.substring(1);
				try {
					Method method = null;
					try {
						method = beanClass.getMethod("get" + subMethodName,
								new Class[0]);
					} catch (NoSuchMethodException nsme) {
						method = beanClass.getMethod("is" + subMethodName,
								new Class[0]);
					}

					Object value = method.invoke(bean, new Class[0]);
					if (value != null) {
						if (Class.forName("java.sql.Clob").isAssignableFrom(
								value.getClass())) {
							if (clobColumns == null) {
								clobColumns = new HashMap();
							}
							clobColumns.put(column.toUpperCase(), new String(
									((CLOB) value).getBytes()));
						}

						if (Class.forName(
								"com.beststar.framework.common.dao.ImageBlob")
								.isAssignableFrom(value.getClass())) {
							if (clobColumns == null) {
								clobColumns = new HashMap();
							}
							clobColumns.put(column.toUpperCase(),
									((ImageBlob) value).getValue());
						}

						if (Class.forName(
								"com.beststar.framework.common.dao.StringClob")
								.isAssignableFrom(value.getClass())) {
							if (clobColumns == null) {
								clobColumns = new HashMap();
							}
							clobColumns.put(column.toUpperCase(),
									((StringClob) value).getValue());
						}

						if (insertStr == null) {
							insertStr = " ( ";
							valueStr = " VALUES ( ";
						} else if (insertStr.startsWith(" ( ")) {
							insertStr = insertStr + " , ";
							valueStr = valueStr + " , ";
						}
						insertStr = insertStr + column.toUpperCase();
						valueStr = valueStr + formatForSQL(value);
					}
				} catch (ClassNotFoundException e) {
					throw new SystemException(e);
				} catch (NoSuchMethodException value) {
					throw new SystemException(value);
				} catch (InvocationTargetException ite) {
					throw new SystemException(ite);
				} catch (IllegalAccessException iae) {
					throw new SystemException(iae);
				}
			}
		}
		if (insertStr == null) {
			return null;
		}

		return "INSERT INTO " + bean.getTablename() + insertStr + ")"
				+ valueStr + ")";
	}

	public String getDeleteStr(BasicBean bean) throws SystemException {
		return "DELETE FROM " + bean.getTablename()
				+ primaryKeyToWhereStr(bean);
	}

	public String getUpdateStr(BasicBean bean) throws SystemException {
		return getUpdateStr(bean, null, false);
	}

	private String getUpdateStr(BasicBean bean, HashMap clobColumns,
			boolean updateAllColumns) throws SystemException {
		String setStr = null;
		Class beanClass = bean.getClass();
		Vector columns = BeanUtils.getProperties(bean.getClass());
		for (int i = 0; i < columns.size(); ++i) {
			String column = (String) columns.get(i);
			if ((column.equalsIgnoreCase("action"))
					|| (column.equalsIgnoreCase("updateType"))
					|| (column.equalsIgnoreCase("pageCount"))
					|| (column.equalsIgnoreCase("pageNum"))
					|| (column.equalsIgnoreCase("pageSize"))
					|| (column.equalsIgnoreCase("rowCount")))
				continue;
			if (column.equalsIgnoreCase("createTime")) {
				continue;
			}

			if ((column.equalsIgnoreCase("lastUpdateTime"))
					|| (column.equalsIgnoreCase("czsjc"))) {
				if (setStr == null) {
					setStr = " SET ";
				} else if (setStr.startsWith(" SET")) {
					setStr = setStr + " , ";
				}
				setStr = setStr + column.toUpperCase() + "= SYSDATE ";
			} else {
				String subMethodName = column.substring(0, 1).toUpperCase()
						+ column.substring(1);
				try {
					Method method = null;
					try {
						method = beanClass.getMethod("get" + subMethodName,
								new Class[0]);
					} catch (NoSuchMethodException nsme) {
						method = beanClass.getMethod("is" + subMethodName,
								new Class[0]);
					}

					Object value = method.invoke(bean, new Class[0]);
					if ((value != null) || (updateAllColumns)) {
						if (value != null) {
							if (Class.forName("java.sql.Clob")
									.isAssignableFrom(value.getClass())) {
								if (clobColumns == null) {
									clobColumns = new HashMap();
								}
								clobColumns.put(column.toUpperCase(),
										new String(((CLOB) value).getBytes()));
							}

							if (Class
									.forName(
											"com.beststar.framework.common.dao.ImageBlob")
									.isAssignableFrom(value.getClass())) {
								if (clobColumns == null) {
									clobColumns = new HashMap();
								}
								clobColumns.put(column.toUpperCase(),
										((ImageBlob) value).getValue());
							}

							if (Class
									.forName(
											"com.beststar.framework.common.dao.StringClob")
									.isAssignableFrom(value.getClass())) {
								if (clobColumns == null) {
									clobColumns = new HashMap();
								}
								clobColumns.put(column.toUpperCase(),
										((StringClob) value).getValue());
							}

						}

						if (setStr == null) {
							setStr = " SET ";
						} else if (setStr.startsWith(" SET")) {
							setStr = setStr + " , ";
						}
						setStr = setStr + column.toUpperCase() + "="
								+ formatForSQL(value);
					}
				} catch (ClassNotFoundException e) {
					throw new SystemException(e);
				} catch (NoSuchMethodException value) {
					throw new SystemException(value);
				} catch (InvocationTargetException ite) {
					throw new SystemException(ite);
				} catch (IllegalAccessException iae) {
					throw new SystemException(iae);
				}
			}
		}
		if (setStr == null) {
			return null;
		}

		return "UPDATE " + bean.getTablename() + setStr
				+ primaryKeyToWhereStr(bean);
	}

	public String getWhereStr(BasicBean bean) throws SystemException {
		String whereStr = null;
		Class beanClass = bean.getClass();
		Vector columns = BeanUtils.getProperties(bean.getClass());
		String tableName = bean.getTablename().toUpperCase() + ".";

		for (int i = 0; i < columns.size(); ++i) {
			String column = (String) columns.get(i);
			if ((column.equalsIgnoreCase("action"))
					|| (column.equalsIgnoreCase("updateType"))
					|| (column.equalsIgnoreCase("pageCount"))
					|| (column.equalsIgnoreCase("pageNum"))
					|| (column.equalsIgnoreCase("pageSize")))
				continue;
			if (column.equalsIgnoreCase("rowCount")) {
				continue;
			}

			String subMethodName = column.substring(0, 1).toUpperCase()
					+ column.substring(1);
			try {
				Method method = null;
				try {
					method = beanClass.getMethod("get" + subMethodName,
							new Class[0]);
				} catch (NoSuchMethodException nsme) {
					method = beanClass.getMethod("is" + subMethodName,
							new Class[0]);
				}

				Class returnType = method.getReturnType();
				Object value = method.invoke(bean, new Class[0]);
				if (value != null) {
					if (whereStr == null) {
						whereStr = " WHERE ";
					} else if (whereStr.startsWith(" WHERE")) {
						whereStr = whereStr + " AND ";
					}
					if (returnType.getName().equalsIgnoreCase(
							"java.lang.String")) {
						whereStr = whereStr + tableName + column.toUpperCase()
								+ " LIKE " + formatForSQL(value);
					} else {
						whereStr = whereStr + tableName + column.toUpperCase()
								+ " = " + formatForSQL(value);
					}
				}
			} catch (NoSuchMethodException nsme) {
				throw new SystemException(nsme);
			} catch (InvocationTargetException ite) {
				throw new SystemException(ite);
			} catch (IllegalAccessException iae) {
				throw new SystemException(iae);
			}
		}
		if (whereStr == null) {
			return "";
		}

		return whereStr;
	}

	public String primaryKeyToWhereStr(BasicBean bean) throws SystemException {
		String whereStr = null;
		String tableName = bean.getTablename().toUpperCase() + ".";

		Object[] objs = bean.getPrimaryKey();
		for (int i = 0; i < objs.length; i += 2) {
			String column = (String) objs[i];
			Object value = objs[(i + 1)];
			if (value != null) {
				if (whereStr == null) {
					whereStr = " WHERE ";
				} else if (whereStr.startsWith(" WHERE")) {
					whereStr = whereStr + " AND ";
				}
				whereStr = whereStr + tableName + column.toUpperCase() + "="
						+ formatForSQL(value);
			} else {
				throw new SystemException("主键不能为空。");
			}
		}
		if (whereStr == null) {
			return "";
		}

		return whereStr;
	}

	public String formatForSQL(Object obj) {
		if (obj == null) {
			return "NULL";
		}
		DateFormat df_dt = DateFormat.getDateInstance();
		DateFormat date_format = DateFormat.getDateTimeInstance();
		java.util.Date low_dt = null;
		try {
			low_dt = df_dt.parse("1900-01-01 00:00:00");
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		String className = obj.getClass().getName();
		try {
			if (className.equalsIgnoreCase("java.lang.String")) {
				String buffer = (String) obj;
				buffer = StringUtils.replace(buffer, "'", "''");
				return "'" + buffer + "'";
			}

			if (className.equalsIgnoreCase("java.sql.Timestamp")) {
				if (((Timestamp) obj).getTime() <= low_dt.getTime()) {
					return null;
				}

				return "to_date('"
						+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(obj) + "', 'YYYY-MM-DD HH24:MI:SS')";
			}

			if (className.equalsIgnoreCase("java.sql.Date")) {
				if (((java.sql.Date) obj).getTime() <= low_dt.getTime()) {
					return null;
				}

				return "to_date('"
						+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(obj) + "', 'YYYY-MM-DD HH24:MI:SS')";
			}

			if (className.equalsIgnoreCase("java.util.Date")) {
				if (((java.util.Date) obj).getTime() <= low_dt.getTime()) {
					return null;
				}

				return "to_date('"
						+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.format(obj) + "', 'YYYY-MM-DD HH24:MI:SS')";
			}

			if (className.equalsIgnoreCase("java.lang.Integer")) {
				return obj.toString();
			}
			if (className.equalsIgnoreCase("java.sql.BigDecimal")) {
				return obj.toString();
			}
			if (Class.forName("java.sql.Clob").isAssignableFrom(obj.getClass())) {
				return "EMPTY_CLOB()";
			}

			if (Class.forName("com.beststar.framework.common.dao.ImageBlob")
					.isAssignableFrom(obj.getClass())) {
				return "EMPTY_BLOB()";
			}

			if (Class.forName("com.beststar.framework.common.dao.StringClob")
					.isAssignableFrom(obj.getClass())) {
				return "EMPTY_CLOB()";
			}
		} catch (ClassNotFoundException e) {
		}
		return obj.toString();
	}

	public String convertCountStatement(String sql) throws SystemException {
		String countSql = new String();
		countSql = "select count(*) from (" + sql + ")";
		return countSql;
	}

	public String convertPagingSql(String sql, BasicBean bean)
			throws SystemException {
		return convertPagingSql(sql, bean, null);
	}

	public String convertPagingSql(String sql, String orderBySql, int pageSize,
			long pageNum) throws SystemException {
		String orderBySqlStr = (orderBySql == null) ? "" : orderBySql;
		long end = pageSize * pageNum;
		long begin = end - pageSize;

		return "SELECT * FROM (SELECT ROWNUM RNO,ORIGINAL_BEAN.* FROM ( " + sql
				+ orderBySqlStr + ") ORIGINAL_BEAN WHERE ROWNUM <="
				+ String.valueOf(end) + orderBySqlStr + ") ORIGINAL_BEANNEW "
				+ "WHERE RNO > " + String.valueOf(begin);
	}

	public String convertPagingSql(String sql, BasicBean bean, String orderBySql)
			throws SystemException {
		int pageSize = bean.getPageSize();
		long pageNum = bean.getPageNum();
		if ((pageSize != 0) && (pageNum != 0L)) {
			return convertPagingSql(sql, orderBySql, pageSize, pageNum);
		}

		return sql;
	}

	public Vector getDataFromResultSet(ResultSet rs, BasicBean bean)
			throws SystemException {
		int pageSize = bean.getPageSize();
		long pageNum = bean.getPageNum();
		long pageCount = bean.getPageCount();
		long rowCount = bean.getRowCount();
		Vector objs = new Vector();
		Vector columns = BeanUtils.getProperties(bean.getClass());
		try {
			while (rs.next()) {
				Object obj = bean.getClass().newInstance();
				for (int i = 0; i < columns.size(); ++i) {
					String column = (String) columns.get(i);
					if ((column.equalsIgnoreCase("action"))
							|| (column.equalsIgnoreCase("updateType"))
							|| (column.equalsIgnoreCase("pageCount"))
							|| (column.equalsIgnoreCase("pageNum"))
							|| (column.equalsIgnoreCase("pageSize")))
						continue;
					if (column.equalsIgnoreCase("rowCount")) {
						continue;
					}

					String subMethodName = column.substring(0, 1).toUpperCase()
							+ column.substring(1);

					Method getMethod = null;
					try {
						getMethod = bean.getClass().getMethod(
								"get" + subMethodName, new Class[0]);
					} catch (NoSuchMethodException nme) {
						getMethod = bean.getClass().getMethod(
								"is" + subMethodName, new Class[0]);
					}

					Class typeName = getMethod.getReturnType();
					Method setMethod = bean.getClass().getMethod(
							"set" + subMethodName, new Class[] { typeName });
					try {
						if (typeName.getName().equalsIgnoreCase(
								"java.lang.String")) {
							String tmp = rs.getString(column.toUpperCase());
							if (tmp != null) {
								tmp = tmp.trim();
							}
							setMethod.invoke(obj, new Object[] { tmp });
						} else if ((typeName.getName()
								.equalsIgnoreCase("java.lang.Integer"))
								|| (typeName.getName().equalsIgnoreCase("int"))) {
							setMethod.invoke(obj, new Object[] { new Integer(rs
									.getInt(column.toUpperCase())) });
						} else if ((typeName.getName()
								.equalsIgnoreCase("java.lang.Long"))
								|| (typeName.getName().equalsIgnoreCase("long"))) {
							setMethod.invoke(obj, new Object[] { new Long(rs
									.getLong(column.toUpperCase())) });
						} else if ((typeName.getName()
								.equalsIgnoreCase("java.lang.Float"))
								|| (typeName.getName()
										.equalsIgnoreCase("float"))) {
							setMethod.invoke(obj, new Object[] { new Float(rs
									.getFloat(column.toUpperCase())) });
						} else if ((typeName.getName()
								.equalsIgnoreCase("java.lang.Double"))
								|| (typeName.getName()
										.equalsIgnoreCase("double"))) {
							setMethod.invoke(obj, new Object[] { new Double(rs
									.getDouble(column.toUpperCase())) });
						} else if (typeName.getName().equalsIgnoreCase(
								"java.sql.Date")) {
							setMethod.invoke(obj, new Object[] { rs
									.getDate(column.toUpperCase()) });
						} else if (typeName.getName().equalsIgnoreCase(
								"java.util.Date")) {
							setMethod.invoke(obj, new Object[] { rs
									.getDate(column.toUpperCase()) });
						} else if (typeName.getName().equalsIgnoreCase(
								"java.math.BigDecimal")) {
							setMethod.invoke(obj, new Object[] { rs
									.getBigDecimal(column.toUpperCase()) });
						} else if (typeName.getName().equalsIgnoreCase(
								"java.sql.Timestamp")) {
							setMethod.invoke(obj, new Object[] { rs
									.getTimestamp(column.toUpperCase()) });
						} else {
							CLOB source;
							if (Class.forName("java.sql.Clob")
									.isAssignableFrom(typeName)) {
								source = (CLOB) rs
										.getClob(column.toUpperCase());

								if (source != null) {
									CLOB target = CLOB.empty_lob();
									target.setBytes(getDBClobContent(source)
											.getBytes());

									setMethod.invoke(obj,
											new Object[] { target });
								}

							} else if (Class.forName(
									"cn.com.framework.dao.ImageBlob")
									.isAssignableFrom(typeName)) {
								BLOB bsource = (BLOB) rs.getBlob(column
										.toUpperCase());

								if (bsource != null) {
									File file = getDBBlobContent(bsource);
									ImageBlob target = new ImageBlob("");
									target.setValue(file);
									setMethod.invoke(obj,
											new Object[] { target });
								}

							} else if (Class.forName(
									"cn.com.framework.dao.StringClob")
									.isAssignableFrom(typeName)) {
								source = null;
								source = (CLOB) rs
										.getClob(column.toUpperCase());

								if (source != null) {
									String content = getDBClobContent(source);
									String str = new String(
											getDBClobContent(source));
									if (str != null) {
										StringClob target = new StringClob(str);
										setMethod.invoke(obj,
												new Object[] { target });
									} else {
										setMethod.invoke(obj, null);
									}
								}
							}
						}
					} catch (SQLException e) {
						if (17006 != e.getErrorCode()) {
							throw new SystemException(e);
						}
					}
				}

				((BasicBean) obj).setPageCount(pageCount);
				((BasicBean) obj).setPageNum(pageNum);
				((BasicBean) obj).setPageSize(pageSize);
				((BasicBean) obj).setRowCount(rowCount);
				objs.add(obj);
			}
		} catch (ClassNotFoundException se) {
			throw new SystemException(se);
		} catch (SQLException se) {
			System.out.println(se.toString());
			throw new SystemException(se);
		} catch (NoSuchMethodException nme) {
			throw new SystemException(nme);
		} catch (IllegalAccessException iae) {
			throw new SystemException(iae);
		} catch (InstantiationException ie) {
			throw new SystemException(ie);
		} catch (InvocationTargetException ite) {
			throw new SystemException(ite);
		}
		return objs;
	}

	private void setClobColumn(BasicBean bean, String columnName,
			String content, Connection connection) throws SystemException {
		Statement statement = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			if (connection == null) {
				connection = getConnection();
				setTransactionBegin(connection, (short) 4);
				flag = true;
			}

			String colbFindSql = "SELECT " + columnName + " FROM "
					+ bean.getTablename() + primaryKeyToWhereStr(bean)
					+ " FOR UPDATE";

			statement = connection.createStatement();
			rs = statement.executeQuery(colbFindSql);
			if (rs.next()) {
				CLOB target = ((OracleResultSet) rs).getCLOB(1);
				setDBClobContent(content, target);
			}

			setTransactionCommit(connection, flag);
		} catch (SQLException se) {
		} finally {
			closeResult(rs, flag);
			closeStatement(statement, flag);
			closeConnection(connection, flag);
		}
	}

	private void setDBClobContent(String source, CLOB target)
			throws SystemException {
		try {
			Reader instream = new StringReader(source);

			Writer outstream = target.getCharacterOutputStream();
			int length = 0;
			char[] buffer = new char[10];
			while ((length = instream.read(buffer)) != -1) {
				outstream.write(buffer, 0, length);
			}
			outstream.flush();
			outstream.close();
			instream.close();
		} catch (IOException e) {
			throw new SystemException(e);
		} catch (SQLException e) {
			throw new SystemException(e);
		}
	}

	private String getDBClobContent(CLOB source) throws SystemException {
		if (source == null)
			return null;
		try {
			Reader instream = source.getCharacterStream();

			char[] buffer = new char[10];

			int length = 0;
			StringBuffer str = new StringBuffer();

			while ((length = instream.read(buffer)) != -1) {
				for (int t = 0; t < length; ++t) {
					str.append(buffer[t]);
				}
			}

			instream.close();
			return str.toString();
		} catch (IOException e) {
			throw new SystemException(e);
		} catch (SQLException e) {
			throw new SystemException(e);
		}
	}

	private void setBlobColumn(BasicBean bean, String columnName, File content,
			Connection connection) throws SystemException {
		Statement statement = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			if (connection == null) {
				connection = getConnection();
				setTransactionBegin(connection, (short) 4);
				flag = true;
			}

			String blobFindSql = " SELECT " + columnName + " FROM "
					+ bean.getTablename() + primaryKeyToWhereStr(bean)
					+ " FOR UPDATE ";

			statement = connection.createStatement();
			rs = statement.executeQuery(blobFindSql);
			if (rs.next()) {
				BLOB target = ((OracleResultSet) rs).getBLOB(1);
				setDBBlobContent(content, target);
			}

			setTransactionCommit(connection, flag);
		} catch (SQLException se) {
		} finally {
			closeResult(rs, flag);
			closeStatement(statement, flag);
			closeConnection(connection, flag);
		}
	}

	private void setDBBlobContent(File source, BLOB target)
			throws SystemException {
		try {
			FileInputStream instream = new FileInputStream(source);
			OutputStream outstream = target.getBinaryOutputStream();

			byte[] buffer = new byte[10];
			int length = 0;

			while ((length = instream.read(buffer)) != -1) {
				outstream.write(buffer, 0, length);
			}
			instream.close();
			outstream.flush();
			outstream.close();
		} catch (IOException e) {
			throw new SystemException(e);
		} catch (SQLException e) {
			throw new SystemException(e);
		}
	}

	private File getDBBlobContent(BLOB source) throws SystemException {
		if (source == null)
			return null;
		try {
			InputStream instream = source.getBinaryStream();
			File file = new File(System.getProperty("java.io.tmpdir")
					+ "testImageBlob");

			FileOutputStream fw = new FileOutputStream(file);

			byte[] buffer = new byte[source.getBufferSize()];
			int length = 0;

			while ((length = instream.read(buffer)) != -1) {
				fw.write(buffer, 0, length);
			}
			instream.close();
			fw.close();
			return file;
		} catch (IOException e) {
			e.printStackTrace();
			throw new SystemException(e);
		} catch (SQLException e) {
			throw new SystemException(e);
		}
	}

	public int returnCountBySQL(String sql, Connection connection)
			throws SystemException {
		Statement statement = null;
		ResultSet rs = null;
		boolean flag = false;
		int rownum = 0;

		if (connection == null) {
			connection = getConnection();
			setTransactionBegin(connection, (short) 3);
			flag = true;
		}
		try {
			statement = connection.createStatement();

			rs = statement.executeQuery(sql);

			if ((rs != null) && (rs.next())) {
				rownum = rs.getInt(1);
			}

			setTransactionCommit(connection, flag);

		} catch (SQLException ex) {
		} finally {
			closeResult(rs, flag);
			closeStatement(statement, flag);
			closeConnection(connection, flag);
		}
		return rownum;
	}

	public Vector findBySQLNotPaging(String sql, BasicBean bean,
			Connection connection) throws SystemException,
			DataNotFoundException {
		Statement statement = null;
		ResultSet rs = null;
		boolean flag = false;
		Vector v = null;
		try {
			if (connection == null) {
				connection = getConnection();
				setTransactionBegin(connection, (short) 3);
				flag = true;
			}
			statement = connection.createStatement();
			rs = (OracleResultSet) statement.executeQuery(sql);

			v = getDataFromResultSet(rs, bean);
			if ((v == null) || (v.size() == 0)) {
				throw new DataNotFoundException();
			}

			setTransactionCommit(connection, flag);

		} catch (SQLException se) {
		} catch (DataNotFoundException se) {
		} catch (SystemException e) {
		} finally {
			closeResult(rs, flag);
			closeStatement(statement, flag);
			closeConnection(connection, flag);
		}
		return v;
	}

	public ResultSet findBySql(String sql, Connection connection)
			throws SystemException, DataNotFoundException {
		Statement statement = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			if (connection == null) {
				connection = getConnection();
				setTransactionBegin(connection, (short) 3);
				flag = true;
			}
			statement = connection.createStatement();
			rs = (OracleResultSet) statement.executeQuery(sql);

			return rs;
		} catch (SQLException ex) {
			throw new SystemException(ex);
		} catch (SystemException ex) {
			throw ex;
		} finally {
			closeStatement(statement, flag);
			closeConnection(connection, flag);
		}
	}

	public int executeBySQL(String paramString, Connection paramConnection)
			throws SystemException, DataNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}
}
