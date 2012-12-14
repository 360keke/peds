package cn.com.framework.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import cn.com.framework.exception.DataAlreadyExistException;
import cn.com.framework.exception.DataNotFoundException;
import cn.com.framework.exception.ForeignKeyRecordFoundException;
import cn.com.framework.exception.SystemException;

public abstract interface BasicDao
{
  public abstract Vector find(BasicBean paramBasicBean, Connection paramConnection)
    throws SystemException, DataNotFoundException;

  public abstract Vector find(BasicBean paramBasicBean, Connection paramConnection, String paramString)
    throws SystemException, DataNotFoundException;

  public abstract Vector find(BasicBean paramBasicBean, Connection paramConnection, String paramString1, String paramString2)
    throws SystemException, DataNotFoundException;

  public abstract BasicBean load(BasicBean paramBasicBean, Connection paramConnection)
    throws SystemException, DataNotFoundException;

  public abstract BasicBean save(BasicBean paramBasicBean, Connection paramConnection)
    throws SystemException, ForeignKeyRecordFoundException, DataNotFoundException, DataAlreadyExistException;

  public abstract BasicBean insert(BasicBean paramBasicBean, Connection paramConnection)
    throws SystemException, DataAlreadyExistException;

  public abstract BasicBean update(BasicBean paramBasicBean, Connection paramConnection)
    throws DataNotFoundException, SystemException;

  public abstract BasicBean update(BasicBean paramBasicBean, Connection paramConnection, boolean paramBoolean)
    throws DataNotFoundException, SystemException;

  public abstract BasicBean delete(BasicBean paramBasicBean, Connection paramConnection)
    throws ForeignKeyRecordFoundException, DataNotFoundException, SystemException;

  public abstract Vector findBySQL(String paramString, BasicBean paramBasicBean, Connection paramConnection)
    throws SystemException, DataNotFoundException;

  public abstract Vector findBySQL(String paramString1, String paramString2, BasicBean paramBasicBean, Connection paramConnection)
    throws SystemException, DataNotFoundException;

  public abstract int executeBySQL(String paramString, Connection paramConnection)
    throws SystemException, DataNotFoundException;

  public abstract void setTransactionRollback(Connection paramConnection, boolean paramBoolean)
    throws SystemException;

  public abstract void setTransactionCommit(Connection paramConnection, boolean paramBoolean)
    throws SystemException;

  public abstract void setTransactionBegin(Connection paramConnection, short paramShort)
    throws SystemException;

  public abstract void closeConnection(Connection paramConnection, boolean paramBoolean)
    throws SystemException;

  public abstract Connection getConnection()
    throws SystemException;

  public abstract BigDecimal getSequence(String paramString, Connection paramConnection)
    throws SystemException;

  public abstract String getSelectStr(BasicBean paramBasicBean)
    throws SystemException;

  public abstract String getLoadStr(BasicBean paramBasicBean)
    throws SystemException;

  public abstract String getInsertStr(BasicBean paramBasicBean)
    throws SystemException;

  public abstract String getDeleteStr(BasicBean paramBasicBean)
    throws SystemException;

  public abstract String getUpdateStr(BasicBean paramBasicBean)
    throws SystemException;

  public abstract String getWhereStr(BasicBean paramBasicBean)
    throws SystemException;

  public abstract String primaryKeyToWhereStr(BasicBean paramBasicBean)
    throws SystemException;

  public abstract String formatForSQL(Object paramObject);

  public abstract String convertCountStatement(String paramString)
    throws SystemException;

  public abstract String convertPagingSql(String paramString1, String paramString2, int paramInt, long paramLong)
    throws SystemException;

  public abstract Vector getDataFromResultSet(ResultSet paramResultSet, BasicBean paramBasicBean)
    throws SystemException;

  public abstract int returnCountBySQL(String paramString, Connection paramConnection)
    throws SystemException;

  public abstract Vector findBySQLNotPaging(String paramString, BasicBean paramBasicBean, Connection paramConnection)
    throws SystemException, DataNotFoundException;

  public abstract ResultSet findBySql(String paramString, Connection paramConnection)
    throws SystemException, DataNotFoundException;
}