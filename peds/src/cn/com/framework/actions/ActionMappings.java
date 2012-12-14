package cn.com.framework.actions;

import java.util.Hashtable;

import cn.com.framework.exception.SystemException;
import cn.com.framework.utils.XMLSerializable;

public class ActionMappings extends XMLSerializable
{
  private Hashtable category;

  public Hashtable getCategory()
  {
    return this.category;
  }

  public void setCategory(Hashtable category) {
    this.category = category;
  }

  public String getURL(String category, String key) throws SystemException {
    Hashtable mappings = (Hashtable)this.category.get(category);
    if (mappings.get(key) == null) {
      throw new SystemException("没有类为" + category + "，名为" + key + "的定义，请查看action_mappings.xml的设置。");
    }

    if (mappings.get(key).getClass().getName().equalsIgnoreCase("java.lang.String"))
    {
      String mapping = (String)mappings.get(key);
      return mapping;
    }

    throw new SystemException("类为" + category + "，名为" + key + "的URL定义不是String，请查看action_mappings.xml的设置。");
  }

  public Object getURLObject(String category, String key)
    throws SystemException
  {
    Hashtable mappings = (Hashtable)this.category.get(category);
    if (mappings.get(key) == null) {
      throw new SystemException("没有类为" + category + "，名为" + key + "的定义，请查看action_mappings.xml的设置。");
    }

    return mappings.get(key);
  }
}
