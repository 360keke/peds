package cn.com.framework.actions;

import java.util.Hashtable;

public class ActionFactory
{
  private Hashtable actions;

  public ActionFactory()
  {
    this.actions = new Hashtable();
  }

  public Action getAction(String classname, ClassLoader loader)
    throws ClassNotFoundException, IllegalAccessException, InstantiationException
  {
    Action action = (Action)this.actions.get(classname);
    Class klass;
    if (action != null)
    {
      if (action.isActive()) {
        klass = loader.loadClass(classname);
        action = (Action)klass.newInstance();
      }
    } else if (action == null) {
      klass = loader.loadClass(classname);
      action = (Action)klass.newInstance();
      this.actions.put(classname, action);
    }

    action.setActive(true);
    return action;
  }
}