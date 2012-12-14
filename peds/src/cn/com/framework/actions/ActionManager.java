package cn.com.framework.actions;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import cn.com.framework.bo.BasicBo;
import cn.com.framework.dao.ActionBean;
import cn.com.framework.exception.SystemException;
import cn.com.framework.utils.ObjectCreater;

public class ActionManager extends BasicBo
{
  public HashMap getActionCommonMappings(URL actionConfig)
    throws SystemException
  {
    try
    {
      InputStream inputStream = actionConfig.openStream();
      ObjectCreater creator = new ObjectCreater(inputStream);
      ActionMappings actionMappings = (ActionMappings)creator.createObject();
      Hashtable category = actionMappings.getCategory();
      Hashtable mappings = (Hashtable)category.get("Actions");
      HashMap map = new HashMap();
      Iterator iter = mappings.keySet().iterator();
      while (iter.hasNext()) {
        ActionBean bean = new ActionBean();
        String key = (String)iter.next();
        bean.setAction_name(key);
        bean.setClass_name((String)mappings.get(key));
        map.put(key, bean);
      }
      return map;
    }
    catch (SystemException ex) {
      throw ex;
    }
    catch (IOException ex) {
      throw new SystemException("读取action-config出错!");
    }
  }

  public HashMap getActionUserMappings(HttpServletRequest req)
    throws SystemException
  {
    return new HashMap();
  }
}