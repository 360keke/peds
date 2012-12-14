package cn.com.framework.actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.framework.view.Screen;

public class ActionRouter {
	private final String key;
	private final boolean isForward;
	private final HashMap parameterForward;

	public ActionRouter(String key) {
		this(key, true);
	}

	public ActionRouter(String key, boolean isForward) {
		this.parameterForward = null;
		this.key = key;
		this.isForward = isForward;
	}

	public ActionRouter(String key, boolean isForward, HashMap parameter) {
		this.parameterForward = parameter;
		this.key = key;
		this.isForward = isForward;
	}

	public synchronized void route(GenericServlet servlet,
			HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		ScreenMappings screenMappings = (ScreenMappings) servlet
				.getServletContext().getAttribute("ScreenMappings");

		Object url = null;
		url = screenMappings.getScreen(this.key);
		if (url == null) {
			throw new ServletException("没有发现名为" + this.key
					+ "的Screen定义，请查看screen_mapping.xml的设置。");
		}

		String jspUrl = null;
		if (url.getClass().getName().equalsIgnoreCase("java.lang.String")) {
			jspUrl = (String) url;
		} else if (url.getClass().getName().endsWith("Screen")) {
			req.setAttribute("SCREEN_TEMPLATE", url);
			jspUrl = ((Screen) url).getTemplate();
		}
		if (jspUrl == null) {
			throw new ServletException("无效的跳转页面" + this.key
					+ ", 请检查screen_mappings.xml文件定义。");
		}

		String partSql = "";
		if (this.parameterForward != null) {
			Iterator para_iter = this.parameterForward.keySet().iterator();
			while (para_iter.hasNext()) {
				Object key = para_iter.next();

				if (!(partSql.equals(""))) {
					partSql = partSql + "&";
				}
				partSql = partSql + key + "="
						+ ((String) this.parameterForward.get(key));
			}
			if (!(partSql.equals(""))) {
				if (jspUrl.indexOf("?") != -1)
					partSql = "&" + partSql;
				else {
					partSql = "?" + partSql;
				}
			}
		}

		if (this.isForward) {
			servlet.getServletContext().getRequestDispatcher(
					res.encodeURL(jspUrl + partSql)).forward(req, res);
		} else {
			res.sendRedirect(req.getContextPath() + "/" + jspUrl + partSql);
		}
	}
}
