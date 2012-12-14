package cn.com.framework.dao;

public class ActionBean extends BasicBean
{
  String action_name;
  String class_name;

  public Object[] getPrimaryKey()
  {
    return null;
  }

  public String getTablename() {
    return null;
  }

  public String getAutoId() {
    return null;
  }

  public String getClass_name() {
    return this.class_name;
  }

  public void setClass_name(String class_name) {
    this.class_name = class_name;
  }

  public String getAction_name() {
    return this.action_name;
  }

  public void setAction_name(String action_name) {
    this.action_name = action_name;
  }
}