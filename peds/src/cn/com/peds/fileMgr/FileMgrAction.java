package cn.com.peds.fileMgr;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import cn.com.framework.actions.ActionBase;
import cn.com.framework.actions.ActionRouter;
import cn.com.framework.exception.SystemException;
import cn.com.framework.exception.ValidateException;
import cn.com.peds.derby.DataStore;
import cn.com.peds.fileMgr.dao.PedsFileDao;

public class FileMgrAction extends ActionBase {

	@Override
	public ActionRouter perform(HttpServlet servlet, HttpServletRequest req, HttpServletResponse res) throws SystemException, ValidateException {
		String method = req.getParameter("method");
		if ("normal_index_buttom".equals(method)) {
			JSONArray files = PedsFileDao.getDefaultFiles(-1);
			req.setAttribute("files", files);
			return new ActionRouter("normal_index_buttom_page");
		} else if ("decryptFile".equals(method)) {
			return decryptFile(servlet, req, res);
		} else if ("initBat".equals(method)) {
			return initBatProcessFile(servlet, req, res);
		}
		return null;
	}

	public ActionRouter decryptFile(HttpServlet servlet, HttpServletRequest req, HttpServletResponse res) {
		int id = Integer.parseInt(req.getParameter("id"));
		String password = req.getParameter("password");
		String msg = new FileManager().decryptFile(id, password);
		try {
			res.setContentType("text/html; charset=utf-8");
			res.getWriter().println(msg);
			res.getWriter().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionRouter initBatProcessFile(HttpServlet servlet, HttpServletRequest req, HttpServletResponse res) {

		return new ActionRouter("bat_process_file_page");
	}
}
