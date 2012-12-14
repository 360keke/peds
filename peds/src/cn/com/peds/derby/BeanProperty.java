package cn.com.peds.derby;

import java.io.Serializable;

public class BeanProperty
  implements Serializable
{
  protected String name;
  protected transient Class type;

  public BeanProperty(String name)
  {
    this(name, classN("java.lang.Object")); }

  public BeanProperty(String name, Class type) {
    this.name = name;
    this.type = type; }

  public String getName() {
    return this.name; }

  public void setName(String name) {
    this.name = name; }

  public void setType(Class type) {
    this.type = type; }

  public Class getType() {
    return this.type;
  }

  public static Class classN(String x0) {
    try {
      return Class.forName(x0);
    }
    catch (ClassNotFoundException ex) {
      throw new NoClassDefFoundError(ex.getMessage());
    }
  }
}