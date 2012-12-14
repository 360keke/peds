package cn.com.peds.derby;

import java.io.Serializable;
import java.util.HashMap;

import cn.com.framework.exception.SystemException;

public class DynaBean
  implements Serializable
{
  private int pageSize = 0;
  private long pageCount = 0L;
  private long rowCount = 0L;
  private long pageNum = 0L;
  private String tablename;
  private String primaryKey;
  private HashMap propertiesMap;
  private HashMap beanValues;

  public DynaBean()
  {
    this.propertiesMap = new HashMap();
    this.beanValues = new HashMap();
  }

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

  public String getPrimaryKey()
  {
    return this.primaryKey;
  }

  public void setPrimaryKey(String primaryKey)
  {
    this.primaryKey = primaryKey;
  }

  public long getRowCount()
  {
    return this.rowCount;
  }

  public void setRowCount(long rowCount)
  {
    this.rowCount = rowCount;
  }

  public String getTablename()
  {
    return this.tablename;
  }

  public void setTablename(String tablename)
  {
    this.tablename = tablename;
  }

  public void addProperty(String name, Class type)
  {
    BeanProperty property = new BeanProperty(name, type);
    this.propertiesMap.put(name, property);
  }

  public void set(String name, Object value)
    throws SystemException
  {
    check(name, value);
    this.beanValues.put(name, value);
  }

  public Object get(String name)
    throws SystemException
  {
    if (this.propertiesMap.get(name) == null) {
      throw new SystemException("数据bean中没有'" + name + "'这个属性!");
    }
    Object value = this.beanValues.get(name);
    return value;
  }

  public void check(String name, Object value)
    throws SystemException
  {
    BeanProperty property = (BeanProperty)this.propertiesMap.get(name);
    if (property == null) {
      throw new SystemException("bean中不存在");
    }
    if (property.getType().isAssignableFrom(value.getClass())) {
      return;
    }
    throw new SystemException("bean中不存在");
  }

  public void clearProperty()
  {
    this.propertiesMap = new HashMap();
    this.beanValues = new HashMap();
  }

  public void clear()
  {
    this.beanValues = new HashMap();
  }
}