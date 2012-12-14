package com.cn.v2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import com.cn.v2.data.bean.CatalogBean;
import com.cn.v2.data.bean.FileBean;
import com.cn.v2.form.FileScannerForm;
import com.cn.v2.form.FileSearchForm;
import com.cn.v2.service.fileMgr.FileMgrService;
import com.cn.v2.service.fileMgr.FileScan.FileProcessUtil;

public class FileMgrController extends BasePaginationController {
	static final Log log = LogFactory.getLog(FileMgrController.class);
	String navTabId = "";
	FileMgrService service;

	public String getNavTabId() {
		return navTabId;
	}

	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}

	public FileMgrService getService() {
		return service;
	}

	public void setService(FileMgrService service) {
		this.service = service;
	}

	public ModelAndView search(HttpServletRequest req, HttpServletResponse res, FileSearchForm form) {
		log.debug(form.toString());
		List<CatalogBean> list = service.search(form.formToParamsMap());
		long totalCount = service.getFileTotalCount(form.formToParamsMap());
		form.setTotalCount(totalCount);
		req.setAttribute("fileList", list);
		return new ModelAndView("/fileMgr/fileList.jsp", "search", form);
	}

	public ModelAndView gotoAdd(HttpServletRequest req, HttpServletResponse res) {
		return null;
	}

	public ModelAndView add(HttpServletRequest req, HttpServletResponse res, FileBean bean) {
		return null;
	}

	public ModelAndView gotoEdit(HttpServletRequest req, HttpServletResponse res) {
		return null;
	}

	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res) {
		return null;
	}

	public ModelAndView del(HttpServletRequest req, HttpServletResponse res) {
		String[] ids = req.getParameterValues("ids");
		Exception ex = null;
		String result = "删除成功！";
		try {
			service.delFiles(ids);
		} catch (Exception e) {
			ex = e;
			result = "操作失败！";
		}
		super.ajaxResult(result, "", this.navTabId, "", "", "", ex, res);
		return null;
	}

	public ModelAndView batDel(HttpServletRequest req, HttpServletResponse res) {
		return null;
	}

	public ModelAndView updateCatalog(HttpServletRequest req, HttpServletResponse res) {
		return null;
	}

	public ModelAndView fileScanner(HttpServletRequest req, HttpServletResponse res, FileScannerForm form) {
		List dir = form.getDir();
		if (form.getFile().size() > 0) {
			dir.addAll(form.getFile());
		}
		service.fileScanner(dir);
		super.ajaxResult("扫描中…………", "", "", "file_scan", "", "", null, res);
		return null;
	}

	public ModelAndView treeRoot(HttpServletRequest req, HttpServletResponse res) {
		String root = FileProcessUtil.getFileRoot();
		req.setAttribute("roots", root);
		return new ModelAndView("/windowsTree.jsp");
	}

	public ModelAndView jTreeRoot(HttpServletRequest req, HttpServletResponse res) {
		String root = FileProcessUtil.getFileRoot();
		req.setAttribute("roots", root);
		return new ModelAndView("/jwindowsTree.jsp");
	}

	public ModelAndView ajaxWindowsTree(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getParameter("path");
		String pid = req.getParameter("id");
		int level = Integer.parseInt(req.getParameter("level"));
		String result = FileProcessUtil.listDirTree(path, level, pid);
		try {
			if ("".equals(result)) {
				super.ajaxResult("光驱未放碟盘或者移动磁盘连接有问题，请查看！", "", navTabId, "", "", "", null, res);
			} else {
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(result);
				res.getWriter().flush();
			}
		} catch (IOException e) {
		}
		return null;
	}
}
