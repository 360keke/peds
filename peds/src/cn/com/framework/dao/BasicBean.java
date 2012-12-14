package cn.com.framework.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import cn.com.framework.exception.SystemException;
import cn.com.framework.utils.BeanUtils;
import cn.com.framework.utils.XMLSerializable;

public abstract class BasicBean extends XMLSerializable
  implements Cloneable, Serializable
{
  private int pageSize;
  private long pageCount;
  private long rowCount;
  private long pageNum;
  public static final int NONE = 0;
  public static final int INSERT = 1;
  public static final int UPDATE = 2;
  public static final int DELETE = 3;
  private int action;
  private boolean updateType;

  public BasicBean()
  {
    this.pageSize = 0;
    this.pageCount = 0L;
    this.rowCount = 0L;
    this.pageNum = 0L;

    this.action = 0;
    this.updateType = true;
  }

  public abstract Object[] getPrimaryKey();

  public abstract String getAutoId();

  public abstract String getTablename();

  public long getPageCount()
  {
    return this.pageCount;
  }

  public void setPageCount(long pageCount)
  {
    this.pageCount = pageCount;
  }

  public long getPageNum()
  {
    return this.pageNum;
  }

  public void setPageNum(long pageNum)
  {
    this.pageNum = pageNum;
  }

  public int getPageSize()
  {
    return this.pageSize;
  }

  public void setPageSize(int pageSize)
  {
    this.pageSize = pageSize;
  }

  public long getRowCount()
  {
    return this.rowCount;
  }

  public void setRowCount(long rowCount)
  {
    this.rowCount = rowCount;
  }

  public int getAction()
  {
    return this.action;
  }

  public void setAction(int action)
  {
    this.action = action;
  }

  public Collection getPropertyNames()
  {
    return BeanUtils.getProperties(super.getClass());
  }

  public Map getPropertyValuesMap()
    throws SystemException
  {
    Vector properties = (Vector)getPropertyNames();
    HashMap map = new HashMap();
    for (int i = 0; i < properties.size(); ++i) {
      String name = (String)properties.get(i);
      map.put(name, getPropertyValue(name));
    }
    return map;
  }

  public Object getPropertyValue(String propertyName)
    throws SystemException
  {
    return BeanUtils.getPropertyValue(this, propertyName);
  }

  public void putPropertyValue(String propertyName, Object value)
    throws SystemException
  {
    BeanUtils.setPropertyValue(this, propertyName, value);
  }

  public boolean getUpdateType()
  {
    return this.updateType;
  }

  public void setUpdateType(boolean updateType)
  {
    this.updateType = updateType;
  }
}