package cn.com.peds.derby;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import cn.com.framework.exception.SystemException;

public class CallStore
  implements Serializable
{
  HashMap resultMap = new HashMap();

  public CallStore()
  {
  }

  public CallStore(HashMap registerPara, CallableStatement callstate)
    throws SQLException, SystemException
  {
    Iterator iterator = registerPara.keySet().iterator();
    while (iterator.hasNext()) {
      String key = (String)iterator.next();
      int index = Integer.parseInt(key);
      Object obj = callstate.getObject(index);
      if (obj instanceof ResultSet) {
        DataStore store = new DataStore((ResultSet)obj, true);
        this.resultMap.put(key, store);
      }
      else {
        this.resultMap.put(key, obj);
      }
    }
  }

  public DataStore getStore(int index)
    throws SystemException
  {
    Object o = getObject(index);
    if (o == null) {
      return null;
    }
    if (o instanceof DataStore) {
      return ((DataStore)o);
    }

    throw new SystemException("您要读取:'" + index + "'不能转换成DataStore类型");
  }

  public int getInt(int index)
    throws SystemException
  {
    Object o = getObject(index);
    if (o == null) {
      return 0;
    }
    if (o instanceof Integer) {
      return ((Integer)o).intValue();
    }

    System.out.println(o.getClass().getName());
    throw new SystemException("您要读取:'" + index + "'不能转换成int类型");
  }

  public double getDouble(int index)
    throws SystemException
  {
    Object o = getObject(index);
    if (o == null) {
      return 0.0D;
    }
    if (o instanceof Double) {
      return ((Double)o).doubleValue();
    }

    throw new SystemException("您要读取:'" + index + "'不能转换成Double类型");
  }

  public Date getDate(int index)
    throws SystemException
  {
    Object o = getObject(index);
    if (o == null) {
      return null;
    }
    if (o instanceof Date) {
      return ((Date)o);
    }

    throw new SystemException("您要读取:'" + index + "'不能转换成Date类型");
  }

  public Time getSqlTime(int index)
    throws SystemException
  {
    Object o = getObject(index);
    if (o == null) {
      return null;
    }
    if (o instanceof Date) {
      return new Time(((Date)o).getTime());
    }

    throw new SystemException("您要读取:'" + index + "'不能转换成Time类型");
  }

  public Timestamp getSqlTimestamp(int index)
    throws SystemException
  {
    Object o = getObject(index);
    if (o == null) {
      return null;
    }
    if (o instanceof Date) {
      return new Timestamp(((Date)o).getTime());
    }

    throw new SystemException("您要读取:'" + index + "'不能转换成Timestamp类型");
  }

  public String getString(int index)
    throws SystemException
  {
    Object o = getObject(index);
    if (o == null) {
      return null;
    }
    if (o instanceof String) {
      String str = (String)o;
      str = str.toString();
      return str.trim();
    }

    throw new SystemException("您要读取:'" + index + "'不是String类型");
  }

  public boolean getBoolean(int index)
    throws SystemException
  {
    Object o = getObject(index);
    if (o == null) {
      return false;
    }
    if (o instanceof Boolean) {
      return ((Boolean)o).booleanValue();
    }

    throw new SystemException("您要读取:'" + index + "'不是boolean类型");
  }

  public Object getObject(int index)
    throws SystemException
  {
    if (!(this.resultMap.containsKey(Integer.toString(index)))) {
      throw new SystemException("CallStore数据集中没有:'" + index + "'的数据!");
    }

    return this.resultMap.get(Integer.toString(index));
  }

  public Set keySet()
  {
    return this.resultMap.keySet();
  }

  public String[] getAllKeys()
  {
    Set keySet = keySet();
    int size = keySet.size();
    String[] keys = new String[size];
    keySet.toArray(keys);
    return keys;
  }

  public Object remove(int index)
  {
    return this.resultMap.remove(Integer.toString(index));
  }

  public void put(int index, Object value)
    throws SystemException
  {
    try
    {
      getObject(index);
    }
    catch (SystemException ex) {
      throw new SystemException("CallStore数据集中" + index + "已存在,请选删除后再添加");
    }
    if (value instanceof Number) {
      value = new Double(((Number)value).doubleValue());
    }
    else if (value instanceof Date) {
      value = new Date(((Date)value).getTime());
    }
    this.resultMap.put(Integer.toString(index), value);
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
        value = getObject(Integer.parseInt(keys[i]));
        if (value instanceof Date) {
          tmp.append(format.format((Date)value));
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
    sb.insert(0, "CallStore{");
    sb.append("}");
    return sb.toString();
  }
}