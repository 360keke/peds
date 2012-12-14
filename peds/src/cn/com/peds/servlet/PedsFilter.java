package cn.com.peds.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.com.peds.derby.DataObject;
import cn.com.peds.login.LoginBo;

/**
 * 过滤非法的会话 文件名：VenusFilter.java<br>
 * 
 * @author 余达波<br>
 *         版本:<br>
 *         描述：<br>
 *         创建时间:2012-11-8 下午09:39:48<br>
 *         文件描述：<br>
 *         修改者：<br>
 *         修改日期：<br>
 *         修改描述：<br>
 */
public class PedsFilter implements Filter {
	final Log log = LogFactory.getLog(PedsFilter.class);
	String exclude = "index.jsp,login.htm";
	String redirecturl = "/index.jsp";
	private FilterConfig config;

	public void destroy() {
		this.config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		DataObject user_object = (DataObject) req.getSession().getAttribute(LoginBo.SESSION_LOGIN_USERBEAN);
		// 检查session是否包含用户信息
		if (user_object == null) {
			String url = req.getRequestURI();
			url = url.substring(url.lastIndexOf("/") + 1, url.length());
			String str[] = exclude.split(",");
			boolean bn = false;
			for (int i = 0; i < str.length; i++) {
				if (str[i].equalsIgnoreCase(url)) {
					bn = true;
					break;
				}
			}
			if (bn) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + redirecturl);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig conf) throws ServletException {
		// 全局变量初始化...
		this.config = conf;
		exclude = conf.getInitParameter("exclude");
	}

}
