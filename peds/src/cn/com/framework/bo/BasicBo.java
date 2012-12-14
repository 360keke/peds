package cn.com.framework.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import cn.com.framework.dao.BasicBean;
import cn.com.framework.dao.BasicDao;
import cn.com.framework.dao.DaoFactory;
import cn.com.framework.exception.DataAlreadyExistException;
import cn.com.framework.exception.DataNotFoundException;
import cn.com.framework.exception.ForeignKeyRecordFoundException;
import cn.com.framework.exception.SystemException;

public class BasicBo implements Serializable, BasicDao {
	protected BasicDao dao = null;

	public BasicBo() {
		try {
			this.dao = DaoFactory.getDao();
		} catch (SystemException e) {
			e.printStackTrace();
		}
	}

	public Vector find(BasicBean bean, Connection connection)
			throws SystemException, DataNotFoundException {
		return this.dao.find(bean, connection);
	}

	public Vector find(BasicBean bean, Connection connection, String partSql,
			String orderBySql) throws SystemException, DataNotFoundException {
		return this.dao.find(bean, connection, partSql, orderBySql);
	}

	public Vector find(BasicBean bean, Connection connection, String partSql)
			throws SystemException, DataNotFoundException {
		return this.dao.find(bean, connection, partSql);
	}

	public BasicBean load(BasicBean bean, Connection connection)
			throws SystemException, DataNotFoundException {
		return this.dao.load(bean, connection);
	}

	public BasicBean save(BasicBean bean, Connection connection)
			throws SystemException, ForeignKeyRecordFoundException,
			DataNotFoundException, DataAlreadyExistException {
		return this.dao.save(bean, connection);
	}

	public BasicBean insert(BasicBean bean, Connection connection)
			throws SystemException, DataAlreadyExistException {
		return this.dao.insert(bean, connection);
	}

	public BasicBean update(BasicBean bean, Connection connection)
			throws DataNotFoundException, SystemException {
		return this.dao.update(bean, connection);
	}

	public BasicBean update(BasicBean bean, Connection connection,
			boolean updateAllColumn) throws DataNotFoundException,
			SystemException {
		return this.dao.update(bean, connection);
	}

	public BasicBean delete(BasicBean bean, Connection connection)
			throws ForeignKeyRecordFoundException, DataNotFoundException,
			SystemException {
		return this.dao.delete(bean, connection);
	}

	public Vector findBySQL(String sql, BasicBean bean, Connection connection)
			throws SystemException, DataNotFoundException {
		return this.dao.findBySQL(sql, bean, connection);
	}

	public Vector findBySQL(String sql, String orderBySql, BasicBean bean,
			Connection connection) throws SystemException,
			DataNotFoundException {
		return this.dao.findBySQL(sql, orderBySql, bean, connection);
	}

	public int executeBySQL(String sql, Connection connection)
			throws SystemException, DataNotFoundException {
		return this.dao.executeBySQL(sql, connection);
	}

	public void setTransactionRollback(Connection connection, boolean flag)
			throws SystemException {
		this.dao.setTransactionRollback(connection, flag);
	}

	public void setTransactionCommit(Connection connection, boolean flag)
			throws SystemException {
		this.dao.setTransactionCommit(connection, flag);
	}

	public void setTransactionBegin(Connection connection, short operate)
			throws SystemException {
		this.dao.setTransactionBegin(connection, operate);
	}

	public void closeConnection(Connection connection, boolean flag)
			throws SystemException {
		this.dao.closeConnection(connection, flag);
	}

	public Connection getConnection() throws SystemException {
		return this.dao.getConnection();
	}

	public BigDecimal getSequence(String autoId, Connection connection)
			throws SystemException {
		return this.dao.getSequence(autoId, connection);
	}

	public String getSelectStr(BasicBean bean) throws SystemException {
		return this.dao.getSelectStr(bean);
	}

	public String getLoadStr(BasicBean bean) throws SystemException {
		return this.dao.getLoadStr(bean);
	}

	public String getInsertStr(BasicBean bean) throws SystemException {
		return this.dao.getInsertStr(bean);
	}

	public String getDeleteStr(BasicBean bean) throws SystemException {
		return this.dao.getDeleteStr(bean);
	}

	public String getUpdateStr(BasicBean bean) throws SystemException {
		return this.dao.getUpdateStr(bean);
	}

	public String getWhereStr(BasicBean bean) throws SystemException {
		return this.dao.getWhereStr(bean);
	}

	public String primaryKeyToWhereStr(BasicBean bean) throws SystemException {
		return this.dao.primaryKeyToWhereStr(bean);
	}

	public String formatForSQL(Object obj) {
		return this.dao.formatForSQL(obj);
	}

	public String convertCountStatement(String sql) throws SystemException {
		return this.dao.convertCountStatement(sql);
	}

	public Vector getDataFromResultSet(ResultSet rs, BasicBean bean)
			throws SystemException {
		return this.dao.getDataFromResultSet(rs, bean);
	}

	public String convertPagingSql(String sql, String orderBySql, int pageSize,
			long pageNum) throws SystemException {
		return this.dao.convertPagingSql(sql, orderBySql, pageSize, pageNum);
	}

	public int returnCountBySQL(String sql, Connection connection)
			throws SystemException {
		return this.dao.returnCountBySQL(sql, connection);
	}

	public Vector findBySQLNotPaging(String sql, BasicBean bean,
			Connection connection) throws SystemException,
			DataNotFoundException {
		return this.dao.findBySQLNotPaging(sql, bean, connection);
	}

	public ResultSet findBySql(String sql, Connection connection)
			throws SystemException, DataNotFoundException {
		return null;
	}
}