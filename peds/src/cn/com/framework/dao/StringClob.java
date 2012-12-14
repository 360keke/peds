package cn.com.framework.dao;

import java.io.Serializable;

public class StringClob
  implements Serializable
{
  private String value;

  public StringClob(String str)
  {
    this.value = str;
  }

  public String getValue() {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}