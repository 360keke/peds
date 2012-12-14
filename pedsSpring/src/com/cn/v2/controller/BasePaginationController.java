package com.cn.v2.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.cn.v2.common.utils.JsonUtils;

public class BasePaginationController extends MultiActionController {
	private final Log log = LogFactory.getLog(CatalogMgrController.class);
	/**
	 * @param statusCode
	 * @param message
	 * @param relId
	 * @param navTabId
	 * @param forwarUrl
	 * @param callbackType
	 * @param confirmMsg
	 * @param ex
	 *            捕获异常 无异常传null
	 * @return
	 */
	public void ajaxResult(String message, String relId, String navTabId, String forwarUrl, String callbackType, String confirmMsg, Exception ex, HttpServletResponse res) {
		try {
			String statusCode = "200";
			if (ex != null) {
				String exMsg = ex.getMessage();
				log.debug(exMsg);
				message = exMsg.substring(0, exMsg.length() <= 50 ? exMsg.length() : 50);
				statusCode = "300";
				if (navTabId != null && !"".equals(navTabId)) {
					navTabId = "";
				}
				if (callbackType != null && !"".equals(callbackType)) {
					callbackType = "";
				}
			}
			JSONObject json = JsonUtils.getResultJson(statusCode, message, relId, navTabId, forwarUrl, callbackType, confirmMsg);
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(json.toString());
			res.getWriter().flush();
		} catch (NullPointerException e) {
			log.debug(e.getMessage());
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
	}
}
