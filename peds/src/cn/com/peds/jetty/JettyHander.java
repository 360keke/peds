package cn.com.peds.jetty;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

public class JettyHander {

	public static void main(String[] args) throws Exception {
		startJetty();// --启动应用服务器。
	}

	public static void startJetty() throws Exception {
		Server server = new Server();
		Connector conn = new SelectChannelConnector();
		conn.setPort(8001);// 监听端口
		server.setConnectors(new Connector[] { conn });
		ContextHandlerCollection contexts = new ContextHandlerCollection();// 加载项目集合
		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath("/");// 访问路径
		webapp.setWar("peds");// web项目路径 ./根目录
		contexts.addHandler(webapp);// 将 webapp项目加载到项目集合中
		server.setHandler(contexts);// 将项目集合加载到服务器中

		server.start();
		server.join();
	}
}
