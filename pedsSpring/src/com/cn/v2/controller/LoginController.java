package com.cn.v2.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.cn.v2.common.GlobalContants;
import com.cn.v2.common.utils.CryptUtil;
import com.cn.v2.common.utils.PropertyManager;

public class LoginController extends MultiActionController {
	protected final Log log = LogFactory.getLog(LoginController.class);

	public ModelAndView login(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String req_username = ServletRequestUtils.getRequiredStringParameter(req, "username").toString();
		String req_password = ServletRequestUtils.getRequiredStringParameter(req, "password").toString();
		String pwd = PropertyManager.getProperty("password").toString();
		String username = PropertyManager.getProperty("username").toString();
		System.out.println("req_username=" + req_username + "username=" + username);
		System.out.println("req_password=" + req_password + "pwd ==" + CryptUtil.decrypt(pwd));
		if (username.equals(req_username) && pwd.equals(CryptUtil.encrypt(req_password))) {
			req.getSession().setAttribute(GlobalContants.DEFAULT_USER_SESSION, username + ":" + pwd);
			return new ModelAndView("/normal_index_main.jsp");
		} else {
			req.setAttribute(GlobalContants.ALERT_MESSAGE, "用户名或者密码不正确！");
			return new ModelAndView("/index.jsp");
		}
	}

	public ModelAndView quit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.getSession().removeAttribute(GlobalContants.DEFAULT_USER_SESSION);
		return new ModelAndView("/index.jsp");

	}

	public ModelAndView getHtml(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setAttribute("html", getHtml());
		return new ModelAndView("/html.jsp", "html", getHtml());
	}

	public static String getHtml() {
		InputStream in;
		StringBuilder sb = new StringBuilder();
		try {
			URL url = new java.net.URL(
					"https://mybank.icbc.com.cn//icbc/newperbank/main/login.jsp?injectTranName=&injectTranData=&injectSignStr=&lastUserName=&destpage=&injectSignStrV=");
			HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
			connection = (java.net.HttpURLConnection) url.openConnection();// 模拟成IE
			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
			connection.connect();
			in = connection.getInputStream();
			BufferedReader breader = new BufferedReader(new InputStreamReader(in, "GBK"));
			String str = breader.readLine();

			while (str != null) {
				sb.append(str);
				str = breader.readLine();
			}
		} catch (Exception e) {
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
}
