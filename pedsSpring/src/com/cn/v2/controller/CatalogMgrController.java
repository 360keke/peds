package com.cn.v2.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.cn.v2.data.bean.CatalogBean;
import com.cn.v2.data.mapper.CatalogMapper;
import com.cn.v2.form.CatalogSearchForm;
import com.cn.v2.form.PaginationForm;

public class CatalogMgrController extends BasePaginationController {
	String navTabId = "";
	private CatalogMapper catalogMapper;

	public ModelAndView list(HttpServletRequest req, HttpServletResponse res) {
		PaginationForm form = new CatalogSearchForm();
		List<CatalogBean> list = catalogMapper.getCatalogsByType(form.formToParamsMap());
		float totalCount = catalogMapper.getTotalCount(form.formToParamsMap());
		form.setTotalCount(totalCount);
		req.setAttribute("search", form);
		return new ModelAndView("/catalogMgr/catalogList.jsp", "catalogList", list);
	}

	public ModelAndView search(HttpServletRequest req, HttpServletResponse res, CatalogSearchForm form) {
		List<CatalogBean> list = catalogMapper.getCatalogsByType(form.formToParamsMap());
		float totalCount = catalogMapper.getTotalCount(form.formToParamsMap());
		form.setTotalCount(totalCount);
		req.setAttribute("catalogList", list);
		return new ModelAndView("/catalogMgr/catalogList.jsp", "search", form);
	}

	public ModelAndView lookUp(HttpServletRequest req, HttpServletResponse res, CatalogSearchForm form) {
		String catalogId = req.getParameter("catalogId");
		Map paramsMap = form.formToParamsMap();
		if (catalogId != null && !"".equals(catalogId)) {
			paramsMap.put("id", Integer.parseInt(catalogId));
		}
		List<CatalogBean> list = catalogMapper.getCatalogsByType(paramsMap);
		float totalCount = catalogMapper.getTotalCount(paramsMap);
		form.setTotalCount(totalCount);
		req.setAttribute("catalogList", list);
		return new ModelAndView("/catalogMgr/catalogLookUp.jsp", "search", form);
	}

	public ModelAndView del(HttpServletRequest req, HttpServletResponse res) {
		int id = Integer.parseInt(req.getParameter("catalogId"));
		String message = "删除成功！";
		Exception ex = null;
		try {
			catalogMapper.delCatalogById(id);
		} catch (Exception e) {
			ex = e;
		}
		super.ajaxResult(message, "", this.navTabId, "", "", "", ex, res);
		return null;
	}

	public ModelAndView gotoEdit(HttpServletRequest req, HttpServletResponse res) {
		int id = Integer.parseInt(req.getParameter("catalogId"));
		CatalogBean bean = catalogMapper.getCatalogById(id);
		return new ModelAndView("/catalogMgr/catalogEdit.jsp", "catalog", bean);
	}

	public ModelAndView edit(HttpServletRequest req, HttpServletResponse res, CatalogBean bean) {
		String message = "修改成功！";
		Exception ex = null;
		try {
			catalogMapper.updateCatalog(bean);
		} catch (Exception e) {
			ex = e;
		}
		super.ajaxResult(message, "", this.navTabId, "", "closeCurrent", "", ex, res);
		return null;

	}

	public ModelAndView gotoAdd(HttpServletRequest req, HttpServletResponse res, CatalogBean bean) {
		return new ModelAndView("/catalogMgr/catalogAdd.jsp");
	}

	public ModelAndView add(HttpServletRequest req, HttpServletResponse res, CatalogBean bean) {
		Exception ex = null;
		try {
			catalogMapper.addCatalog(bean);
		} catch (Exception e) {
			ex = e;
		}
		super.ajaxResult("添加成功！", "", "", "", "closeCurrent", "", ex, res);
		return null;
	}

	/**
	 * 批量删除
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView batDel(HttpServletRequest req, HttpServletResponse res) {
		String idsParams = req.getParameter("ids");
		String ids = "";
		if (idsParams != null && !"".equals(idsParams)) {
			String[] arrIds = ids.split("&");
			for (int i = 0; i < arrIds.length; i++) {
				String value = arrIds[i].split("=")[1];
				ids += value + ",";
			}
			if (ids.endsWith(",")) {
				ids = ids.substring(0, ids.length() - 2);
			}
		}
		return null;
	}

	public String getNavTabId() {
		return navTabId;
	}

	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}

	public CatalogMapper getCatalogMapper() {
		return catalogMapper;
	}

	public void setCatalogMapper(CatalogMapper catalogMapper) {
		this.catalogMapper = catalogMapper;
	}
}
