package com.cn.v2.controller;

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

import com.cn.v2.common.GlobalContants;

/**
 * 过滤非法的会话 文件名：PedsLoginFilter.java<br>
 * 
 * @author 余达波<br>
 *         版本:<br>
 *         描述：<br>
 *         创建时间:2012-11-17 下午09:39:48<br>
 *         文件描述：<br>
 *         修改者：<br>
 *         修改日期：<br>
 *         修改描述：<br>
 */
public class PedsLoginFilter implements Filter {
	static final Log log = LogFactory.getLog(PedsLoginFilter.class);
	String exclude = "";
	private FilterConfig config;

	public void destroy() {
		this.config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String user_object = (String) req.getSession().getAttribute(GlobalContants.DEFAULT_USER_SESSION);
		log.info("user_sesion=" + user_object);
		if (user_object == null || "".equals(user_object)) {
			log.debug("进行判断1****************");
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
				log.debug("进行判断2****************");
				chain.doFilter(request, response);
			} else {
				log.debug("进行判断3****************");
				req.setAttribute(GlobalContants.ALERT_MESSAGE, "你还未登录，请先登录再进行其他操作！");
				req.getRequestDispatcher("/" + exclude).forward(req, res);
				// res.sendRedirect(req.getContextPath()+"/"+exclude);
			}
		} else {
			log.debug("进行判断4****************");
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig conf) throws ServletException {
		// 全局变量初始化...
		this.config = conf;
		exclude = conf.getInitParameter("exclude");
		// TODO :实现定时任务
	}

}
