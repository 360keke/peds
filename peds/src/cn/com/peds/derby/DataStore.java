package cn.com.peds.derby;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Vector;

import cn.com.framework.exception.SystemException;

public class DataStore
  implements Serializable
{
  HashMap propertiesMap;
  boolean lowerCase;
  Vector resultRows;

  public Vector getResultRows() {
	return resultRows;
}

public void setResultRows(Vector resultRows) {
	this.resultRows = resultRows;
}

public DataStore()
  {
    this.propertiesMap = new HashMap();
    this.resultRows = new Vector();
  }

  public DataStore(ResultSet resultSet) throws SQLException, SystemException {
    this(resultSet, 1, 2147483647, true, false);
  }

  public DataStore(ResultSet resultSet, boolean opened) throws SQLException, SystemException
  {
    this(resultSet, 1, 2147483647, true, opened);
  }

  public DataStore(ResultSet resultSet, int begin, int end) throws SQLException, SystemException
  {
    this(resultSet, begin, end, true);
  }

  public DataStore(ResultSet resultSet, int begin, int end, boolean lowerCase)
    throws SQLException, SystemException
  {
    this(resultSet, begin, end, true, false);
  }

  public DataStore(ResultSet resultSet, int begin, int end, boolean lowerCase, boolean opened)
    throws SQLException, SystemException
  {
    this.lowerCase = lowerCase;
    this.propertiesMap = new HashMap();
    this.resultRows = new Vector();
    if (resultSet == null) {
      throw new SystemException("ResultSet 为空!");
    }
    ResultSetMetaData metadata = resultSet.getMetaData();
    createPropertys(metadata);
    try {
      loadResult(resultSet, begin, end, opened);
    }
    catch (IOException ex) {
      System.out.println(ex);
      throw new SystemException(ex);
    }
  }

  public void createPropertys(ResultSetMetaData metadata)
    throws SQLException
  {
    int n = metadata.getColumnCount();
    for (int i = 0; i < n; ++i) {
      String name = null;
      if (this.lowerCase) {
        name = metadata.getColumnName(i + 1).toLowerCase();
      }
      else {
        name = metadata.getColumnName(i + 1);
      }
      String className = null;
      try {
        className = metadata.getColumnClassName(i + 1);
      }
      catch (SQLException e)
      {
      }
      Class classzz;
      if (className != null) {
        classzz = loadClass(className);
      }
      else {
        classzz = classN("java.lang.Object");
      }
      BeanProperty property = new BeanProperty(name, classzz);
      this.propertiesMap.put(name, property);
    }
  }

  public void loadResult(ResultSet resultSet, int begin, int end, boolean opened)
    throws SQLException, SystemException, IOException
  {
    if ((!(opened)) && 
      (!(resultSet.next()))) {
      return;
    }

    do
    {
      if (resultSet.getRow() < begin) {
        continue;
      }

      if (resultSet.getRow() >= end) {
        return;
      }
      DataObject rowO = new DataObject();
      ResultSetMetaData metadata = resultSet.getMetaData();
      int column = metadata.getColumnCount();
      for (int i = 0; i < column; ++i)
      {
        String column_name = metadata.getColumnName(i + 1);
        if (this.lowerCase)
          column_name = column_name.toLowerCase();
        Object result;
        if ((metadata.getColumnType(i + 1) == -1) || (metadata.getColumnType(i + 1) == 2005))
        {
          Reader reader = resultSet.getCharacterStream(i + 1);
          if (reader == null)
            result = null;
          else
            try
            {
              StringBuffer strbuff = new StringBuffer();
              while (true) {
                int ch = 0;
                try {
                  ch = reader.read();
                }
                catch (IOException ex1) {
                  reader.skip(1L);
                  ch = reader.read();
                }
                if (ch == -1) {
                  break;
                }
                strbuff.append((char)ch);
              }

              reader.close();
              result = strbuff.toString();
            }
            catch (IOException ex) {
              result = null;
            }
        }
        else
        {
          result = resultSet.getObject(i + 1);
        }
        rowO.put(column_name, result);
      }
      this.resultRows.add(rowO);
    }
    while (resultSet.next());
  }

  public DataObject getRow(int row)
    throws SystemException
  {
    checkRow(row);
    return ((DataObject)this.resultRows.get(row));
  }

  public String getString(int row, String column)
    throws SystemException
  {
    return getRow(row).getString(column);
  }

  public double getDouble(int row, String column)
    throws SystemException
  {
    return getRow(row).getDouble(column);
  }

  public int getInt(int row, String column)
    throws SystemException
  {
    return getRow(row).getInt(column);
  }

  public boolean getBoolean(int row, String column)
    throws SystemException
  {
    return getRow(row).getBoolean(column);
  }

  public java.util.Date getDate(int row, String column)
    throws SystemException
  {
    return getRow(row).getDate(column);
  }

  public long getLong(int row, String column)
    throws SystemException
  {
    return getRow(row).getLong(column);
  }

  public void addDataObject(DataObject dataobject)
  {
    this.resultRows.add(dataobject);
  }

  public void insertDataObjectAt(DataObject dataobject, int index)
  {
    this.resultRows.insertElementAt(dataobject, index);
  }

  public void removeDataObjectAt(int index)
  {
    this.resultRows.removeElementAt(index);
  }

  public java.sql.Date getSqlDate(int row, String column)
    throws SystemException
  {
    return getRow(row).getSqlDate(column);
  }

  public Timestamp getSqlTimestamp(int row, String column)
    throws SystemException
  {
    return getRow(row).getSqlTimestamp(column);
  }

  public Time getSqlTime(int row, String column)
    throws SystemException
  {
    return getRow(row).getSqlTime(column);
  }

  public Object getObject(int row, String column)
    throws SystemException
  {
    return getRow(row).getObject(column);
  }

  private void checkRow(int row)
    throws SystemException
  {
    if ((row >= 0) && (row < rowCount()))
      return;
    throw new SystemException("没有符合条件的记录!");
  }

  public int rowCount()
  {
    return this.resultRows.size();
  }

  protected Class loadClass(String className)
    throws SQLException
  {
    try
    {
      return super.getClass().getClassLoader().loadClass(className);
    }
    catch (ClassNotFoundException ex) {
      throw new SQLException("不能载入列的类型 '" + className + "': " + ex);
    }
  }

  public static Class classN(String x0)
  {
    try
    {
      return Class.forName(x0);
    }
    catch (ClassNotFoundException ex) {
      throw new NoClassDefFoundError(ex.getMessage());
    }
  }
}