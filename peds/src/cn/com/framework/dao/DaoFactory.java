package cn.com.framework.dao;

import java.util.Hashtable;

import cn.com.framework.exception.SystemException;

public class DaoFactory
{
  private static Hashtable daoSet = new Hashtable();

  public static BasicDao getDao()
    throws SystemException
  {
    return getDao("cn.com.framework.dao.OracleDaoImp");
  }

  public static BasicDao getDao(String className)
    throws SystemException
  {
    try
    {
      BasicDao dao = (BasicDao)daoSet.get(className);
      if (dao == null) {
        dao = (BasicDao)Class.forName(className).newInstance();
        daoSet.put(className, dao);
      }
      return dao;
    }
    catch (ClassNotFoundException cnfe) {
      throw new SystemException("无效的Dao类名。", cnfe);
    }
    catch (IllegalAccessException iae) {
      throw new SystemException(iae);
    }
    catch (InstantiationException ie) {
      throw new SystemException(ie);
    }
  }
}