package cn.com.framework.actions;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import cn.com.framework.dao.ActionBean;
import cn.com.framework.events.ActionEvent;
import cn.com.framework.exception.BusinessException;
import cn.com.framework.exception.DataNotFoundException;
import cn.com.framework.exception.LoginTimeOutException;
import cn.com.framework.exception.SystemException;
import cn.com.framework.exception.ValidateException;

public class ActionServlet extends HttpServlet {
	private ActionFactory factory;
	private ScreenMappings screenMappings;
	private String uploadRoot;

	public ActionServlet() {
		this.factory = new ActionFactory();
		this.screenMappings = null;
		this.uploadRoot = null;
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			String actionmanager_name = config
					.getInitParameter("action-manager");

			if (actionmanager_name == null) {
				actionmanager_name = "cn.com.framework.actions.ActionManager";
			}

			Class manager_calss = super.getClass().getClassLoader().loadClass(
					actionmanager_name);

			ActionManager action = (ActionManager) manager_calss.newInstance();

			getServletContext().setAttribute("ActionManagerBO", action);

			ActionManager action_manager_bo = (ActionManager) getServletContext()
					.getAttribute("ActionManagerBO");

			String actionMapping = config.getInitParameter("action-mappings");

			URL actionUrl = null;
			if (actionMapping != null) {
				actionUrl = getServletContext().getResource(actionMapping);
			}
			getServletContext().setAttribute("common-action-mappings",
					action_manager_bo.getActionCommonMappings(actionUrl));

			String screenMappingFilename = config
					.getInitParameter("screen-mappings");

			URL url = getServletContext().getResource(screenMappingFilename);
			InputStream screenXmlInputStream = url.openStream();
			this.screenMappings = getScreenMappings(screenXmlInputStream);

			getServletContext().setAttribute("ScreenMappings",
					this.screenMappings);

			// SyscodeFactory syscodefactory = new SyscodeFactory();
			// syscodefactory.getSyscodeBase();
		} catch (ClassNotFoundException ex) {
			throw new ServletException(ex);
		} catch (IllegalAccessException ex) {
			throw new ServletException(ex);
		} catch (InstantiationException ex) {
			throw new ServletException(ex);
		} catch (IOException ioe) {
			throw new ServletException(ioe);
		} catch (SAXException e) {
			throw new ServletException(e);
		} catch (SystemException se) {
			System.out.println(se.toString());
			if (se.getSourceException() != null) {
				if (se.getMessage() != null)
					;
				throw new ServletException(se.getSourceException());
			}

			throw new ServletException(se);
		}
	}

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException {
		Action action = null;
		try {
			action = getAction(req);
			ActionEvent event = new ActionEvent(action, 0, req, res);

			action.fireEvent(event);
			action.setServlet(this);
			ActionRouter router = performAction(action, req, res);
			action.setServlet(null);

			action.release();
			event.setEventType(1);
			action.fireEvent(event);

			routeAction(router, req, res);
		} catch (ValidateException e) {
			HttpSession session = req.getSession();
			String token = (String) session.getAttribute("token");
			if (token != null) {
				req.setAttribute("token", token.toString());
			}
			routeAction(new ActionRouter(e.getSourceForm()), req, res);
		} catch (LoginTimeOutException e) {
			req.setAttribute("message", "您登录时间过长,请重新登录!");
			routeAction(new ActionRouter("loginouttime-page"), req, res);
		} catch (BusinessException e) {
			req.setAttribute("message", "发生业务错误。" + e.getMessage());
			routeAction(dealwithException(req, e, "系统错误提示"), req, res);
		} catch (SystemException se) {
			if (se.getSourceException() != null) {
				if (se.getMessage() != null)
					;
				req.setAttribute("message", se.getMessage());
				routeAction(dealwithException(req, se, "系统错误提示"), req, res);
			} else {
				req.setAttribute("message", se.getMessage());
				routeAction(dealwithException(req, se, "系统错误提示"), req, res);
			}
		} finally {
			if (action != null) {
				action.setActive(false);
			}
		}
	}

	protected Action getAction(HttpServletRequest req) throws SystemException {
		Action action = null;
		try {
			action = this.factory.getAction(getActionClass(req, req
					.getSession(), getActionKey(req)), super.getClass()
					.getClassLoader());
		} catch (Exception ex) {
			throw new SystemException("你没有操作权限或登录时间过长,请重新登录!", ex);
		}
		return action;
	}

	public String getActionClass(HttpServletRequest req, HttpSession session,
			String actionName) throws SystemException, DataNotFoundException {
		HashMap actionMappings = null;
		ActionBean action_right_bean = null;

		ActionManager action_manager_bo = (ActionManager) getServletContext()
				.getAttribute("ActionManagerBO");

		actionMappings = action_manager_bo.getActionUserMappings(req);
		if (actionMappings != null) {
			action_right_bean = (ActionBean) actionMappings.get(actionName);
		}

		HashMap commonnMap = (HashMap) getServletContext().getAttribute(
				"common-action-mappings");

		if ((action_right_bean == null) && (commonnMap != null)) {
			action_right_bean = (ActionBean) commonnMap.get(actionName);
		}
		if (action_right_bean == null) {
			throw new SystemException("你没有操作权限或登录时间过长,请重新登录");
		}
		String class_name = action_right_bean.getClass_name();
		return class_name;
	}

	private String getActionKey(HttpServletRequest req) {
		String path = req.getServletPath();
		int slash = path.lastIndexOf("/");
		int period = path.lastIndexOf(".");

		if ((period > 0) && (period > slash)) {
			path = path.substring(slash + 1, period);
		}
		return path;
	}

	public ScreenMappings getScreenMappings(InputStream screenXmlInputStream)
			throws SAXException, IOException {
		XMLReader parser = XMLReaderFactory
				.createXMLReader("org.apache.xerces.parsers.SAXParser");

		ScreenMappingsHandler handler = new ScreenMappingsHandler();
		parser.setContentHandler(handler);
		parser.setErrorHandler(handler);
		parser.parse(new InputSource(screenXmlInputStream));
		return handler.getResult();
	}

	protected ActionRouter performAction(Action action, HttpServletRequest req,
			HttpServletResponse res) throws SystemException, ValidateException {
		ActionRouter router = null;

		router = action.perform(this, req, res);
		return router;
	}

	protected void routeAction(ActionRouter router, HttpServletRequest req,
			HttpServletResponse res) throws ServletException {
		try {
			router.route(this, req, res);
		} catch (Exception ex) {
		}
	}

	private static String getClassname(HttpServletRequest req) {
		String path = req.getServletPath();
		int slash = path.lastIndexOf("/");
		int period = path.lastIndexOf(".");

		if ((period > 0) && (period > slash)) {
			path = path.substring(slash + 1, period);
		}

		return path;
	}

	private ActionRouter dealwithException(HttpServletRequest req,
			Exception ex, String exception_name) {
		req.setAttribute("exception_name", exception_name);
		StringBuffer str = new StringBuffer();
		StackTraceElement[] trace = ex.getStackTrace();
		for (int i = 0; i < trace.length; ++i) {
			str.append("\tat " + trace[i]);
		}

		req.setAttribute("exception_moreinfo", str.toString());
		return new ActionRouter("debug-page");
	}
}
