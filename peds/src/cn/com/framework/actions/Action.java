package cn.com.framework.actions;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.framework.events.ActionEvent;
import cn.com.framework.events.ActionListener;
import cn.com.framework.exception.BusinessException;
import cn.com.framework.exception.SystemException;
import cn.com.framework.exception.ValidateException;

public abstract interface Action {
	public abstract ActionRouter perform(HttpServlet paramHttpServlet,
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse)
			throws SystemException, ValidateException;

	public abstract void addActionListener(ActionListener paramActionListener);

	public abstract void fireEvent(ActionEvent paramActionEvent)
			throws BusinessException, SystemException;

	public abstract boolean hasSensitiveForms();

	public abstract boolean isSensitive();

	public abstract void release();

	public abstract void setServlet(ActionServlet paramActionServlet);

	public abstract ActionServlet getServlet();

	public abstract boolean isActive();

	public abstract void setActive(boolean paramBoolean);
}
