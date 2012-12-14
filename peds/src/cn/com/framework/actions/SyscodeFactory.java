package cn.com.framework.actions;

import java.io.Serializable;
import java.util.ResourceBundle;

import cn.com.framework.exception.SystemException;

public class SyscodeFactory
implements Serializable
{
private static SyscodeBase SYSCODEBASE_STATIC = null;

public SyscodeBase getSyscodeBase()
{
  if (SYSCODEBASE_STATIC != null) {
    return SYSCODEBASE_STATIC;
  }
  ResourceBundle rb = ResourceBundle.getBundle("DataBase");
  String codeutils = rb.getString("codeUtils");
  try {
    Class Syscode_calss = super.getClass().getClassLoader().loadClass(codeutils);
    SYSCODEBASE_STATIC = (SyscodeBase)Syscode_calss.newInstance();
    SYSCODEBASE_STATIC.loadSyscode();
    return SYSCODEBASE_STATIC;
  }
  catch (SystemException ex) {
  }
  catch (IllegalAccessException ex) {
  }
  catch (InstantiationException ex) {
  }
  catch (ClassNotFoundException ex) {
  }
  return null;
}
}
