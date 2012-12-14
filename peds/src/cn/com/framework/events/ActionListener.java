package cn.com.framework.events;

import java.util.EventListener;

import cn.com.framework.exception.BusinessException;
import cn.com.framework.exception.SystemException;


public abstract interface ActionListener extends EventListener
{
  public abstract void beforeAction(ActionEvent paramActionEvent)
    throws BusinessException, SystemException;

  public abstract void afterAction(ActionEvent paramActionEvent)
    throws BusinessException, SystemException;
}