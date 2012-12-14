package com.cn.v2.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

public class JettyHander {
	static final Log log = LogFactory.getLog(JettyHander.class);

	public static void main(String[] args) throws Exception {
		startJetty();// --启动应用服务器。
		// getHtml();
	}
	public static void startJetty() throws Exception {
		Server server = new Server();
		Connector conn = new SelectChannelConnector();
		conn.setPort(8001);// 监听端口
		server.setConnectors(new Connector[] { conn });
		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath("/peds");// 访问路径
		webapp.setResourceBase("./webapps");
		// webapp.setResourceBase("../"); //wrapper运行设置
		log.info("resourceBase=" + webapp.getResourceBase());
		// webapp.setTempDirectory(new File("../work")); //wrapper 运行设置
		// log.info("tempDir=" + webapp.getTempDirectory());
		server.setHandler(webapp);// 将项目集合加载到服务器中
		server.start();
	}

	public static void getHtml() {
		InputStream in;
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
				System.out.println(str);
				str = breader.readLine();
			}
		} catch (Exception e) {
		}
	}
}
