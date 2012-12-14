package com.cn.v2.common.utils;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * httpclient 工具类
 * @author yudabo
 *
 */
public class HttpClientUtil {
	static final Log log = LogFactory.getLog(HttpClientUtil.class);

	public static String executeUrl(String httpMethod, String url) {
		log.debug("httpmethod="+httpMethod+" "+url);
		HttpClient httpClient = new HttpClient();
		HttpMethod method = null;
		if ("POST".equals(httpMethod)) {
			method = new PostMethod(url);
		} else {
			method = new GetMethod(url);
		}
		String result = "";
		try {
			httpClient.executeMethod(method);
			result = method.getResponseBodyAsString();
			log.debug("url="+url+":response="+result);
		} catch (HttpException e) {
			log.debug(e.getMessage());
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		return result;
	}
	public static String executeUrl(String url){
		return executeUrl("get", url);
	}
}
