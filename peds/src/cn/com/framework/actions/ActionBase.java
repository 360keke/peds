package cn.com.framework.actions;

import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.framework.events.ActionEvent;
import cn.com.framework.events.ActionListener;
import cn.com.framework.exception.BusinessException;
import cn.com.framework.exception.SystemException;
import cn.com.framework.exception.ValidateException;

public class ActionBase implements Action {
	protected boolean isSensitive = false;
	protected boolean hasSensitiveForms = false;

	private boolean actively = false;
	private Vector listeners = new Vector();
	ActionServlet servlet;

	public ActionBase() {
		addActionListener(new SensitiveActionListener());
	}

	public void addActionListener(ActionListener listener) {
		this.listeners.addElement(listener);
	}

	public void removeActionListener(ActionListener listener) {
		this.listeners.remove(listener);
	}

	public void fireEvent(ActionEvent event) throws BusinessException,
			SystemException {
		Enumeration it = this.listeners.elements();

		while (it.hasMoreElements()) {
			ActionListener listener = (ActionListener) it.nextElement();

			switch (event.getEventType()) {
			case 0:
				listener.beforeAction(event);
				break;
			case 1:
				listener.afterAction(event);
			}
		}
	}

	public boolean isSensitive() {
		return this.isSensitive;
	}

	public boolean hasSensitiveForms() {
		return this.hasSensitiveForms;
	}

	public void release() {
	}

	public void setServlet(ActionServlet servlet) {
		this.servlet = servlet;
	}

	public ActionServlet getServlet() {
		return this.servlet;
	}

	public boolean isActive() {
		return this.actively;
	}

	public void setActive(boolean flag) {
		this.actively = flag;
	}

	public ActionRouter perform(HttpServlet paramHttpServlet,
			HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse)
			throws SystemException, ValidateException {
		// TODO Auto-generated method stub
		return null;
	}
}
