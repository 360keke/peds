package cn.com.peds.login.action;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.framework.actions.ActionBase;
import cn.com.framework.actions.ActionRouter;
import cn.com.framework.exception.SystemException;
import cn.com.peds.common.CryptUtil;
import cn.com.peds.common.GlobalContants;
import cn.com.peds.derby.DataStore;
import cn.com.peds.login.LoginBo;
import cn.com.peds.login.bean.UserBean;
import cn.com.peds.login.dao.LoginDao;

public class LoginAction extends ActionBase {

	public ActionRouter perform(HttpServlet servlet, HttpServletRequest req,
			HttpServletResponse res) throws SystemException {
		// System.out.println("提交：" + req.getQueryString());
		// 原静态密码认证登陆
		String login_name = req.getParameter("login_name");
		String password = req.getParameter("password");
		DataStore store = LoginDao.validLoginUser(login_name);
		UserBean user_object = (UserBean) req.getSession().getAttribute(
				LoginBo.SESSION_LOGIN_USERBEAN);
		// 账号不正确的处理
		if (store == null
				&& req.getAttribute(GlobalContants.ALERT_MESSAGE) != null) {
			req.setAttribute(GlobalContants.ALERT_MESSAGE, "数据库异常！");
			return new ActionRouter("index_page");
		}
		if (store.rowCount() < 1) {
			req.setAttribute(GlobalContants.ALERT_MESSAGE, "账号不正确！");
			return new ActionRouter("index_page");
		}
		UserBean bean = new UserBean(store, 0);
		String pasword1 = CryptUtil.encryptSHA(password);
		if (CryptUtil.verifySHA(bean.getPassword(), pasword1)) {
			req.setAttribute(GlobalContants.ALERT_MESSAGE, "密码不正确！");
			return new ActionRouter("index_page");
		}
		System.out.println(bean.getPassword() + "  "
				+ CryptUtil.encryptSHA(password));
		if (1 == bean.getLockFlag()) {
			req
					.setAttribute(GlobalContants.ALERT_MESSAGE,
							"您的帐号已经被锁定,请与管理员联系！");
			return new ActionRouter("index_page");

		}
		if (user_object != null) {
			// 是不是同一个用户登录,
			if (login_name.equals(user_object.getName())
					&& (CryptUtil.encryptSHA(password).equals(user_object
							.getPassword()))) {
				// 是同一个用户直接跳目标页面
				return new ActionRouter("normal_index_main_page");
			} else {
				// 注销原用户登录的Session
				req.getSession()
						.removeAttribute(LoginBo.SESSION_LOGIN_USERBEAN);
				// 保存在线信息
			}
		}
		req.getSession().setAttribute(LoginBo.SESSION_LOGIN_USERBEAN, bean);
		return new ActionRouter("normal_index_main_page");
	}

	public static void main(String[] args) {
		Object[] param = { "T100000000", "13550001105" };
		ResourceBundle rb = ResourceBundle.getBundle("DataBase");

	}
}