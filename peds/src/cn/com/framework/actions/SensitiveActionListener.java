package cn.com.framework.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import cn.com.framework.events.ActionEvent;
import cn.com.framework.events.ActionListener;
import cn.com.framework.exception.BusinessException;
import cn.com.framework.exception.SystemException;

public class SensitiveActionListener implements ActionListener {
	public void beforeAction(ActionEvent event) throws BusinessException,
			SystemException {
		Action action = (Action) event.getSource();

		if (action.isSensitive()) {
			HttpServletRequest req = event.getRequest();
			String requestToken = req.getParameter("token");
			String sessionToken = (String) req.getSession().getAttribute(
					"token");

			if ((sessionToken != null) && (requestToken != null)
					&& (sessionToken.equals(requestToken)))
				return;
			throw new BusinessException("对不起，此表单数据不能重复提交。");
		}
	}

	public void afterAction(ActionEvent event) throws BusinessException,
			SystemException {
		Action action = (Action) event.getSource();
		HttpServletRequest req = event.getRequest();
		HttpSession session = req.getSession();

		if (action.hasSensitiveForms()) {
			Token token = new Token(req);
			session.setAttribute("token", token.toString());
			req.setAttribute("token", token.toString());
		}
		if (action.isSensitive())
			session.removeAttribute("token");
	}
}