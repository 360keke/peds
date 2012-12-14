package cn.com.peds.derby;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Set;

import cn.com.framework.exception.SystemException;

public class DataObject
  implements Serializable
{
  HashMap values;

  public DataObject()
  {
    this.values = new HashMap();
  }

  public void put(String name, Object value)
    throws SystemException
  {
    if ((name == null) || (name.equals(""))) {
      throw new SystemException("name 不能为空!");
    }
    if (value instanceof Number) {
      value = new Double(((Number)value).doubleValue());
    }
    else if (value instanceof java.util.Date) {
      value = new java.util.Date(((java.util.Date)value).getTime());
    }
    this.values.put(name, value);
  }

  public void put(String name, double value)
    throws SystemException
  {
    put(name, new Double(value));
  }

  public void put(String name, boolean value)
    throws SystemException
  {
    put(name, new Boolean(value));
  }

  public void put(String name, long value)
    throws SystemException
  {
    put(name, new Long(value));
  }

  public Object getObject(String name)
    throws SystemException
  {
    if ((name == null) || (name.equals(""))) {
      throw new SystemException("传入参数不能为空!");
    }
    if (!(this.values.containsKey(name))) {
      throw new SystemException("DataObject数据集中没有:'" + name + "'的数据!");
    }

    return this.values.get(name);
  }

  public double getDouble(String name)
    throws SystemException
  {
    Object o = getObject(name);
    if (o == null) {
      return 0.0D;
    }
    if (o instanceof Double) {
      return ((Double)o).doubleValue();
    }

    throw new SystemException("您要读取:'" + name + "'不能转换成Double类型");
  }

  public int getInt(String name)
    throws SystemException
  {
    Object o = getObject(name);
    if (o == null) {
      return 0;
    }
    if (o instanceof Double) {
      return ((Double)o).intValue();
    }

    throw new SystemException("您要读取:'" + name + "'不能转换成int类型");
  }

  public java.util.Date getDate(String name)
    throws SystemException
  {
    Object o = getObject(name);
    if (o == null) {
      return null;
    }
    if (o instanceof java.util.Date) {
      return ((java.util.Date)o);
    }

    throw new SystemException("您要读取:'" + name + "'不能转换成Date类型");
  }

  public java.sql.Date getSqlDate(String name)
    throws SystemException
  {
    Object o = getObject(name);
    if (o == null) {
      return null;
    }
    if (o instanceof java.util.Date) {
      return new java.sql.Date(((java.util.Date)o).getTime());
    }

    throw new SystemException("您要读取:'" + name + "'不能转换成java.sql.Date类型");
  }

  public Time getSqlTime(String name)
    throws SystemException
  {
    Object o = getObject(name);
    if (o == null) {
      return null;
    }
    if (o instanceof java.util.Date) {
      return new Time(((java.util.Date)o).getTime());
    }

    throw new SystemException("您要读取:'" + name + "'不能转换成Time类型");
  }

  public Timestamp getSqlTimestamp(String name)
    throws SystemException
  {
    Object o = getObject(name);
    if (o == null) {
      return null;
    }
    if (o instanceof java.util.Date) {
      return new Timestamp(((java.util.Date)o).getTime());
    }

    throw new SystemException("您要读取:'" + name + "'不能转换成Timestamp类型");
  }

  public String getString(String name)
    throws SystemException
  {
    Object o = getObject(name);
    if (o == null) {
      return null;
    }
    if (o instanceof String) {
      String str = (String)o;
      str = str.toString();
      return str.trim();
    }

    throw new SystemException("您要读取:'" + name + "'不是String类型");
  }

  public boolean getBoolean(String name)
    throws SystemException
  {
    Object o = getObject(name);
    if (o == null) {
      return false;
    }
    if (o instanceof Boolean) {
      return ((Boolean)o).booleanValue();
    }

    throw new SystemException("您要读取:'" + name + "'不是boolean类型");
  }

  public long getLong(String name)
    throws SystemException
  {
    Object o = getObject(name);
    if (o == null) {
      return -1L;
    }
    if (o instanceof Long) {
      return ((Long)o).longValue();
    }

    throw new SystemException("您要读取:'" + name + "'不是long类型");
  }

  public Set keySet()
  {
    return this.values.keySet();
  }

  public String[] getAllKeys()
  {
    Set keySet = keySet();
    int size = keySet.size();
    String[] keys = new String[size];
    keySet.toArray(keys);
    return keys;
  }

  public Object remove(String name)
  {
    name = name;
    return this.values.remove(name);
  }

  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    String[] keys = getAllKeys();
    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    for (int i = 0; i < keys.length; ++i) {
      StringBuffer tmp = new StringBuffer();
      tmp.append(keys[i] + "=");
      Object value = null;
      try {
        value = getObject(keys[i]);
        if (value instanceof java.util.Date) {
          tmp.append(format.format((java.util.Date)value));
        }
        else if (value instanceof String) {
          String str = (String)value;
          str = str.replaceAll("\t", "\\\\t");
          str = str.replaceAll("\n", "\\\\n");
          if (str.length() > 500) {
            tmp.append("\"" + str.substring(0, 500) + " ...\"(length:" + ((String)value).length() + ")");
          }
          else
          {
            tmp.append("\"" + str + "\"");
          }
        }
        else {
          tmp.append(value);
        }
      }
      catch (Exception ex) {
        tmp.append("$ERROR_GETTING_VALUE$");
      }
      tmp.append(", ");
      if (value instanceof DataStore) {
        sb.append(tmp);
      }
      else {
        sb.insert(0, tmp);
      }
    }

    if (sb.length() >= 2) {
      sb.delete(sb.length() - 2, sb.length());
    }
    sb.insert(0, "DataObject{");
    sb.append("}");
    return sb.toString();
  }
}