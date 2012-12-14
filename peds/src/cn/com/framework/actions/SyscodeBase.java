package cn.com.framework.actions;

import java.io.Serializable;

import cn.com.framework.exception.SystemException;

public class SyscodeBase
implements Serializable
{
public void loadSyscode()
  throws SystemException
{
}

public void reLoadDm(int dmlx)
  throws SystemException
{
}

public String getMultDms(int dmlx, String hzs)
{
  return null;
}

public String getMultHzs(int dmlx, String dms)
{
  return null;
}
}