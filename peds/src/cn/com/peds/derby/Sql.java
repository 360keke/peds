package cn.com.peds.derby;

import java.io.Serializable;
import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import cn.com.framework.exception.DataNotFoundException;
import cn.com.framework.exception.SystemException;

public class Sql implements Serializable {
	String sqlString;
	private Object[] para;
	private HashMap registerMap;
	private DynaBean bean;
	private int totalrow;
	int dbNum;

	public Sql() {
		this(1);
	}

	public Sql(int dbNum) {
		this.registerMap = null;

		this.totalrow = 0;
		this.dbNum = 1;

		this.sqlString = "";
		this.para = null;
		this.bean = null;
		this.dbNum = dbNum;
		this.registerMap = new HashMap();
	}

	public void registerOutDate(int index) throws SystemException {
		registerOutParamter(index, 91);
	}

	public void registerOutInt(int index) throws SystemException {
		registerOutParamter(index, 4);
	}

	public void registerOutString(int index) throws SystemException {
		registerOutParamter(index, 12);
	}

	public void registerOutDouble(int index) throws SystemException {
		registerOutParamter(index, 8);
	}

	public void registerOutDataStore(int index) throws SystemException {
		registerOutParamter(index, -10);
	}

	public void registerOutBoolean(int index) throws SystemException {
		registerOutParamter(index, 16);
	}

	public void registerOutParamter(int index, int type) throws SystemException {
		checkIndex(index);
		this.registerMap.put(Integer.toString(index), Integer.toString(type));
	}

	public void setSql(String sql) {
		this.sqlString = "";
		this.para = null;
		this.bean = null;
		this.sqlString = sql;
		this.para = new Object[getParaCount(this.sqlString)];
		this.registerMap = new HashMap();
		this.totalrow = 0;
	}

	public void setDynaBean(DynaBean bean) {
		this.bean = bean;
	}

	public void setNull(int index, int sqlType) throws SystemException {
		checkIndex(index);
		this.para[(index - 1)] = new NullValue(sqlType);
	}

	public void setString(int index, String value) throws SystemException {
		checkIndex(index);
		if (value == null) {
			setNull(index, 12);
		} else {
			this.para[(index - 1)] = value;
		}
	}

	public void setLongVarchar(int index, String value) throws SystemException {
		checkIndex(index);
		if (value == null) {
			setNull(index, -1);
		} else
			this.para[(index - 1)] = new LongVarcharValue(value);
	}

	public void setInt(int index, int value) throws SystemException {
		checkIndex(index);
		this.para[(index - 1)] = new Integer(value);
	}

	public void setDouble(int index, double value) throws SystemException {
		checkIndex(index);
		this.para[(index - 1)] = new Double(value);
	}

	public void setLong(int index, long value) throws SystemException {
		checkIndex(index);
		this.para[(index - 1)] = new Long(value);
	}

	public void setFloat(int index, float value) throws SystemException {
		checkIndex(index);
		this.para[(index - 1)] = new Float(value);
	}

	public void setBoolean(int index, boolean value) throws SystemException {
		checkIndex(index);
		this.para[(index - 1)] = new Boolean(value);
	}

	public void setDate(int index, java.sql.Date value) throws SystemException {
		checkIndex(index);
		if (value == null) {
			setNull(index, 91);
		} else
			this.para[(index - 1)] = value;
	}

	public void setClob(int index, String value) throws SystemException {
		checkIndex(index);
		if (value == null) {
			setNull(index, -1);
		} else
			this.para[(index - 1)] = new LongVarcharValue(value);
	}

	public void setDate(int index, java.util.Date value) throws SystemException {
		if (value == null) {
			setTimestamp(index, null);
		} else
			setTimestamp(index, new Timestamp(value.getTime()));
	}

	public void setTimestamp(int index, Timestamp value) throws SystemException {
		checkIndex(index);
		if (value == null) {
			setNull(index, 93);
		} else
			this.para[(index - 1)] = value;
	}

	private void checkIndex(int index) throws SystemException {
		if ((index < 1) || (index > this.para.length))
			throw new SystemException("超出参数的总数!");
	}

	private int getParaCount(String sqlStr) {
		int count = 0;
		int index = -1;
		while (true) {
			index = sqlStr.indexOf("?", index + 1);
			if (index == -1) {
				break;
			}
			if (!(isInConstantString(sqlStr, index)))
				;
			++count;
		}

		return count;
	}

	private boolean isInConstantString(String sqlStr, int index) {
		char[] ca = sqlStr.substring(0, index).toCharArray();
		int count = 0;
		for (int i = 0; i < ca.length; ++i) {
			if (ca[i] == '\'') {
				++count;
			}
		}

		return (count % 2 == 1);
	}

	private void setParas(Object[] para, PreparedStatement pstmt)
			throws SystemException, SQLException {
		if (para == null) {
			return;
		}
		for (int i = 0; i < para.length; ++i) {
			Object o = para[i];
			if (o instanceof Integer) {
				pstmt.setInt(i + 1, ((Integer) o).intValue());
			} else if (o instanceof Double) {
				pstmt.setDouble(i + 1, ((Double) o).doubleValue());
			} else if (o instanceof Long) {
				pstmt.setLong(i + 1, ((Long) o).longValue());
			} else if (o instanceof Boolean) {
				pstmt.setBoolean(i + 1, ((Boolean) o).booleanValue());
			} else if (o instanceof String) {
				pstmt.setString(i + 1, (String) o);
			} else if (o instanceof Float) {
				pstmt.setFloat(i + 1, (Float) o);
			} else if (o instanceof java.sql.Date) {
				pstmt.setDate(i + 1, (java.sql.Date) o);
			} else if (o instanceof Timestamp) {
				pstmt.setTimestamp(i + 1, (Timestamp) o);
			} else if (o instanceof LongVarcharValue) {
				LongVarcharValue lvValue = (LongVarcharValue) o;
				pstmt.setCharacterStream(i + 1, new StringReader(lvValue
						.getValue()), lvValue.getLength());
			}
			// else if (o instanceof CLOB) {
			// pstmt.setClob(i + 1, (Clob)o);
			// }
			else if (o instanceof NullValue) {
				pstmt.setNull(i + 1, ((NullValue) o).getType());
			} else {
				if (o == null) {
					throw new SystemException("参数不能为null");
				}

				throw new SystemException("参数的类型本类不能处理!");
			}
		}
	}

	private void setParas(Object[] para, HashMap outMap, CallableStatement pstmt)
			throws SystemException, SQLException {
		if (para == null) {
			return;
		}
		for (int i = 0; i < para.length; ++i) {
			if (outMap.containsKey(Integer.toString(i + 1))) {
				pstmt
						.registerOutParameter(i + 1, Integer
								.parseInt((String) outMap.get(Integer
										.toString(i + 1))));
			} else {
				Object o = para[i];
				if (o instanceof Integer) {
					pstmt.setInt(i + 1, ((Integer) o).intValue());
				} else if (o instanceof Double) {
					pstmt.setDouble(i + 1, ((Double) o).doubleValue());
				} else if (o instanceof Long) {
					pstmt.setLong(i + 1, ((Long) o).longValue());
				} else if (o instanceof Boolean) {
					pstmt.setBoolean(i + 1, ((Boolean) o).booleanValue());
				} else if (o instanceof String) {
					pstmt.setString(i + 1, (String) o);
				} else if (o instanceof java.sql.Date) {
					pstmt.setDate(i + 1, (java.sql.Date) o);
				} else if (o instanceof Timestamp) {
					pstmt.setTimestamp(i + 1, (Timestamp) o);
				} else if (o instanceof LongVarcharValue) {
					LongVarcharValue lvValue = (LongVarcharValue) o;
					pstmt.setCharacterStream(i + 1, new StringReader(lvValue
							.getValue()), lvValue.getLength());
				}
				// else if (o instanceof CLOB) {
				// pstmt.setClob(i + 1, (Clob)o);
				// }
				else if (o instanceof NullValue) {
					pstmt.setNull(i + 1, ((NullValue) o).getType());
				} else {
					if (o == null) {
						throw new SystemException("参数不能为null");
					}

					throw new SystemException("参数的类型本类不能处理!");
				}
			}
		}
	}

	public DataStore executePage(String orderBySql, long pageSize,
			long pageNum, String rowidName) throws SystemException {
		if (pageNum < 1L) {
			pageNum = 1L;
		}

		this.totalrow = selectRowCount();
		if (this.totalrow == 0) {
			return new DataStore();
		}

		if ((pageNum != 0L) && (pageSize != 0L)) {
			double pageCount = Math.ceil(this.totalrow / pageSize);
			if (pageCount < pageNum) {
				pageNum = (long) pageCount;
			}

		}

		long end = pageSize * (pageNum - 1L);
		long begin = end - pageSize;
		String pageSql = "";
		pageSql = "select top " + pageSize + " * from (" + this.sqlString
				+ ") xx where xx." + rowidName + " not in (" + "select top "
				+ end + " " + rowidName + " from (" + this.sqlString + ") xx "
				+ orderBySql + ") " + orderBySql;

		Connection con = null;

		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(pageSql);
			int m;
			if (this.para != null) {
				Object[] para_tmp = new Object[this.para.length * 2];
				for (m = 0; m < this.para.length; ++m) {
					para_tmp[m] = this.para[m];
					para_tmp[(m + this.para.length)] = this.para[m];
				}
				setParas(this.para, pstmt);
			}
			rs = pstmt.executeQuery();
			DataStore store = new DataStore(rs);
			if (store == null) {
				store = new DataStore();
			}

			return store;
		} catch (SQLException ex) {
			throw new SystemException(ex);
		} finally {
			closeResult(rs);
			closeStatement(pstmt);
			closeConnection(con, true);
		}
	}

	public DataStore executePage(String orderBySql, long pageSize, long pageNum)
			throws SystemException {
		if (pageNum < 1L) {
			pageNum = 1L;
		}

		this.totalrow = selectRowCount();
		if (this.totalrow == 0) {
			return new DataStore();
		}

		if ((pageNum != 0L) && (pageSize != 0L)) {
			double pageCount = Math.ceil(this.totalrow / pageSize);
			if (pageCount < pageNum) {
				pageNum = (long) pageCount;
			}

		}

		String orderBySqlStr = (orderBySql == null) ? "" : orderBySql;
		long end = pageSize * pageNum;
		long begin = end - pageSize;

		String pageSql = "";
		pageSql = "SELECT * FROM (SELECT ROWNUM RNO,ORIGINAL_BEAN.* FROM ( "
				+ this.sqlString + ") ORIGINAL_BEAN WHERE ROWNUM <="
				+ String.valueOf(end) + ") ORIGINAL_BEANNEW " + "WHERE RNO > "
				+ String.valueOf(begin);

		Connection con = null;
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(pageSql);
			rs = pstmt.executeQuery();
			DataStore store = new DataStore(rs);
			if (store == null) {
				store = new DataStore();
			}
			return store;
		} catch (SQLException ex) {
			throw new SystemException(ex);
		} finally {
			closeResult(rs);
			closeStatement(pstmt);
			closeConnection(con, true);
		}
	}

	public CallStore executeCall(Connection connection) throws SystemException {
		boolean flag = false;
		if (connection == null) {
			connection = getConnection();
			flag = true;
		}
		CallableStatement callstate = null;
		try {
			callstate = connection.prepareCall(this.sqlString);
			setParas(this.para, this.registerMap, callstate);
			callstate.execute();
			return new CallStore(this.registerMap, callstate);
		} catch (SQLException ex) {
			SystemException exp = new SystemException(ex);
			throw exp;
		} finally {
			closeStatement(callstate);
			closeConnection(connection, flag);
		}
	}

	public int selectRowCount() throws SystemException {
		Connection con = null;
		con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i;
		try {
			pstmt = con.prepareStatement("select count(*) from ("
					+ this.sqlString + ") aa");

			setParas(this.para, pstmt);
			rs = pstmt.executeQuery();
			rs.next();
			i = rs.getInt(1);
		} catch (SQLException sqle) {
			throw new SystemException(sqle);
		} finally {
			closeResult(rs);
			closeStatement(pstmt);
			closeConnection(con, true);
		}
		return i;
	}

	public DataStore executeQuery() throws SystemException {
		return executeQuery(1, 2147483647);
	}

	public DataStore executeQuery(Connection con) throws SystemException {
		return executeQuery(1, 2147483647, con);
	}

	public DataStore executeQuery(int begin, int end) throws SystemException {
		return executeQuery(begin, end, null);
	}

	public DataStore executeQuery(int begin, int end, Connection con)
			throws SystemException {
		boolean flag = false;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		if (con == null) {
			con = getConnection();
			flag = true;
		}
		try {
			pstmt = con.prepareStatement(this.sqlString,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			setParas(this.para, pstmt);
			rs = pstmt.executeQuery();
			if (end != 2147483647) {
				end += 1;
			}

			DataStore store = new DataStore(rs, begin, end);
			return store;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			throw new SystemException(ex);
		} finally {
			closeResult(rs);
			closeStatement(pstmt);
			closeConnection(con, flag);
		}
	}

	public DataStore executeQueryStatement() throws SystemException {
		return executeQuery(1, 2147483647);
	}

	public DataStore executeQueryStatement(Connection con)
			throws SystemException {
		return executeQuery(1, 2147483647, con);
	}

	public DataStore executeQueryStatement(int begin, int end)
			throws SystemException {
		return executeQuery(begin, end, null);
	}

	public DataStore executeQueryStatement(int begin, int end, Connection con)
			throws SystemException {
		boolean flag = false;
		Statement st = null;
		ResultSet rs = null;
		if (con == null) {
			con = getConnection();
			flag = true;
		}
		try {
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery(this.sqlString);
			if (end != 2147483647) {
				end += 1;
			}

			DataStore store = new DataStore(rs, begin, end);
			return store;
		} catch (SQLException ex) {
			System.out.println(ex);
			throw new SystemException(ex);
		} finally {
			closeResult(rs);
			closeStatement(st);
			closeConnection(con, flag);
		}
	}

	public int executeUpdate(Connection connection) throws SystemException,
			DataNotFoundException {
		return executeUpdate(connection, null);
	}

	public int executeUpdate(Connection connection, DynaBean bean)
			throws SystemException, DataNotFoundException {
		Connection con = connection;
		boolean flag = false;
		PreparedStatement pstmt = null;
		int i = 0;
		try {
			if (connection == null) {
				con = getConnection();
				flag = true;
			}
			pstmt = con.prepareStatement(this.sqlString);
			setParas(this.para, pstmt);
			i = pstmt.executeUpdate();
			if (i == 0)
				throw new DataNotFoundException();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (SystemException ex) {
			throw ex;
		} finally {
			closeStatement(pstmt);
			closeConnection(con, flag);
		}
		return i;
	}

	// private void setDBClobContent(String source, CLOB target)
	// throws SystemException
	// {
	// try
	// {
	// Reader instream = new StringReader(source);
	//
	// Writer outstream = target.getCharacterOutputStream();
	// int length = 0;
	// char[] buffer = new char[10];
	// while ((length = instream.read(buffer)) != -1) {
	// outstream.write(buffer, 0, length);
	// }
	// outstream.flush();
	// outstream.close();
	// instream.close();
	// }
	// catch (IOException e) {
	// throw new SystemException(e);
	// }
	// catch (SQLException e) {
	// throw new SystemException(e);
	// }
	// }

	public int getTotalRow() {
		return this.totalrow;
	}

	public String getSqlString() throws SystemException {
		return getSqlString(this.para);
	}

	private String getSqlString(Object[] para) throws SystemException {
		if (para == null) {
			return this.sqlString;
		}
		String sqlStr = this.sqlString;
		for (int i = 0; i < para.length; ++i) {
			String value;
			if (this.registerMap.containsKey(Integer.toString(i))) {
				value = "";
			} else {
				Object o = para[i];

				if ((o instanceof Integer) || (o instanceof Double)
						|| (o instanceof Boolean)) {
					value = o.toString();
				} else if (o instanceof String) {
					value = "'" + ((String) o).replaceAll("'", "''") + "'";
				} else if (o instanceof Timestamp) {
					Sql _tmp = this;
					Sql _tmp1 = this;
					value = "to_date('"
							+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.format(o) + "','"
							+ "yyyy-mm-dd hh24:mi:ss" + "')";
				} else if (o instanceof java.sql.Date) {
					Sql _tmp2 = this;
					Sql _tmp3 = this;
					value = "to_date('"
							+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
									.format(o).substring(0, 10) + "','"
							+ "yyyy-mm-dd hh24:mi:ss".substring(0, 10) + "')";
				} else if (o instanceof LongVarcharValue) {
					LongVarcharValue lvValue = (LongVarcharValue) o;
					value = "'" + lvValue.getValue() + "'";
				} else if (o instanceof NullValue) {
					value = "''";
				}
				// else if (o instanceof CLOB) {
				// value = "ORAL.CLOB()";
				// }
				else {
					if (o == null) {
						throw new SystemException("设置sql的参数不能为null");
					}

					throw new SystemException("设置sql参数的类型本类不能处理");
				}
				int index = getFirstParaIndex(sqlStr);
				try {
					sqlStr = sqlStr.substring(0, index) + value
							+ sqlStr.substring(index + 1);
				} catch (Exception ex) {
					throw new SystemException(ex);
				}
			}
		}
		return sqlStr;
	}

	private int getFirstParaIndex(String sqlStr) throws SystemException {
		int index = 0;
		try {
			index = -1;
			do {
				index = sqlStr.indexOf("?", index + 1);
				if (index == -1) {
					return -1;
				}
			} while (isInConstantString(sqlStr, index));
		} catch (Exception ex) {
			throw new SystemException(ex);
		}
		return index;
	}

	public String toString() {
		try {
			return "Sql{" + getSqlString() + "}";
		} catch (SystemException ex) {
		}
		return null;
	}

	public Connection getConnection() throws SystemException {
		return ConnectionManager.getConnection(this.dbNum);
	}

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

	public void closeConnection(Connection connection, boolean flag)
			throws SystemException {
		if (!(flag))
			return;
		try {
			if ((connection != null) && (!(connection.isClosed()))) {
				if (!(connection.getAutoCommit())) {
					setTransactionCommit(connection, flag);
				}
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("关闭 SQL Connection 失败。", e);
		}
	}

	public void closeStatement(PreparedStatement statement)
			throws SystemException {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			throw new SystemException("关闭 SQL Statement 失败。", e);
		}
	}

	public void closeStatement(Statement statement) throws SystemException {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			throw new SystemException("关闭 SQL Statement 失败。", e);
		}
	}

	public void closeResult(ResultSet rs) throws SystemException {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			throw new SystemException("关闭 SQL ResultSet 失败。", e);
		}
	}

	private class NullValue implements Serializable {
		private int type;

		public int getType() {
			return this.type;
		}

		public NullValue(int type) {
			this.type = type;
		}
	}

	private class LongVarcharValue implements Serializable {
		private String value;

		public String getValue() {
			return this.value;
		}

		public int getLength() {
			return this.value.length();
		}

		public LongVarcharValue(String value) {
			if (value == null) {
				value = "";
			}
			this.value = value;
		}
	}
}