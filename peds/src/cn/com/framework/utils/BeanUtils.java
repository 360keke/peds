package cn.com.framework.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import cn.com.framework.dao.BasicBean;
import cn.com.framework.exception.SystemException;
import cn.com.peds.common.StringUtils;

public class BeanUtils
{
  public static Vector getProperties(Class beanClass)
  {
    Vector properties = new Vector();

    Method[] methods = beanClass.getMethods();

    for (int i = 0; i < methods.length; ++i) {
      Method method = methods[i];
      String name = method.getName();
      Class type = method.getReturnType();
      if ((name.startsWith("get")) || (name.startsWith("is"))) {
        String property = null;
        if (name.startsWith("is")) {
          property = name.substring(2);
        }
        else {
          property = name.substring(3);
        }
        try
        {
          Method setMethod = beanClass.getMethod("set" + property, new Class[] { type });

          properties.add(StringUtils.firstCharToLowerCase(property));
        }
        catch (SecurityException ex) {
        }
        catch (NoSuchMethodException ex) {
        }
      }
    }
    return properties;
  }

  public static Class getPropertyType(Class beanClass, String name)
    throws SystemException
  {
    Vector properties = new Vector();
    String getName = "get" + StringUtils.firstCharToUpperCase(name);
    String isName = "is" + StringUtils.firstCharToUpperCase(name);
    Method method = null;
    try {
      try {
        method = beanClass.getMethod(getName, new Class[0]);
      }
      catch (NoSuchMethodException e) {
        method = beanClass.getMethod(isName, new Class[0]);
      }
      return method.getReturnType();
    }
    catch (NoSuchMethodException e) {
      throw new SystemException("无效的属性。", e);
    }
  }

  public static Object getPropertyValue(Object bean, String name)
    throws SystemException
  {
    Class beanClass = bean.getClass();
    String getName = "get" + StringUtils.firstCharToUpperCase(name);
    String isName = "is" + StringUtils.firstCharToUpperCase(name);
    Method method = null;
    try {
      try {
        method = beanClass.getMethod(getName, new Class[0]);
      }
      catch (NoSuchMethodException e) {
        method = beanClass.getMethod(isName, new Class[0]);
      }
      return method.invoke(bean, new Object[0]);
    }
    catch (NoSuchMethodException e) {
      throw new SystemException("无效的属性。", e);
    }
    catch (IllegalAccessException iae) {
      throw new SystemException(iae);
    }
    catch (InvocationTargetException ite) {
      throw new SystemException(ite);
    }
  }

  public static void setPropertyValue(Object bean, String name, Object value)
    throws SystemException
  {
    Class beanClass = bean.getClass();
    String getMethodName = "get" + StringUtils.firstCharToUpperCase(name);
    String setMethodName = "set" + StringUtils.firstCharToUpperCase(name);
    try {
      Method getMethod = beanClass.getMethod(getMethodName, new Class[0]);

      Method setMethod = beanClass.getMethod(setMethodName, new Class[] { getMethod.getReturnType() });

      setMethod.invoke(bean, new Object[] { value });
    }
    catch (NoSuchMethodException e) {
      throw new SystemException("无效的属性。", e);
    }
    catch (IllegalAccessException iae) {
      throw new SystemException(iae);
    }
    catch (InvocationTargetException ite) {
      throw new SystemException(ite);
    }
  }

  public static void setPropertyValue(Object bean, String name, Object value, Class propertyClass)
    throws SystemException
  {
    if ((value != null) && (!(propertyClass.isAssignableFrom(value.getClass())))) {
      throw new SystemException("value与PropertyClass类型不符。");
    }
    setPropertyValue(bean, name, value);
  }

  public static void setPropertyValues(Object bean, Map valueMap)
    throws SystemException
  {
    Set keySet = valueMap.keySet();
    Iterator iterator = keySet.iterator();
    if (iterator.hasNext()) {
      Object key = iterator.next();
      Object value = valueMap.get(key);
      setPropertyValue(bean, key.toString(), value);
    }
  }

  public static Vector getColValuesOfBean(Vector v_bean, String property)
    throws SystemException
  {
    Vector right_v = new Vector();
    if (v_bean != null) {
      for (int i = 0; i < v_bean.size(); ++i) {
        BasicBean bean = (BasicBean)v_bean.get(i);
        Object value = getPropertyValue(bean, property);
        right_v.add(value);
      }
    }
    return right_v;
  }
}