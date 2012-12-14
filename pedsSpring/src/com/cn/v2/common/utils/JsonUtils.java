package com.cn.v2.common.utils;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;

import com.cn.v2.data.bean.FileBean;
import com.cn.v2.form.CatalogSearchForm;

public class JsonUtils {
	private static Log log = LogFactory.getLog(JsonUtils.class);

	/**
	 * @param c
	 * @param json
	 * @return 返回bean对象并从json对象中将bean自动赋值
	 * @throws JSONException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static Object get(JSONObject json, String key) {
		Object value = null;
		try {
			value = json.get(key);
		} catch (JSONException e) {
			log.debug(e.getMessage());
		}
		return value;
	}

	public static Object jsonToObject(Class c, JSONObject json) throws JSONException, InstantiationException, IllegalAccessException {
		Object obj = c.newInstance();
		if (json == null) {
			return obj;
		} else {
			Field[] fields = c.getFields();
			Object value;
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				if (ClassUtils.judgeClassType(fields[i].getClass())) {
					value = json.getString(fields[i].getName());
					ClassUtils.setMethodProxy(obj, methodName, new Object[] { value });
				} else {
					value = json.getJSONObject(fields[i].getName());
					ClassUtils.setMethodProxy(obj, methodName, new Object[] { jsonToObject(fields[i].getClass(), (JSONObject) value) });
				}
			}
		}
		return obj;
	}

	/**
	 * 将对象bean转换成json对象。
	 * 
	 * @param obj
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject ObjectToJson(Object obj) throws JSONException {
		JSONObject json = new JSONObject();
		if (obj == null || ClassUtils.judgeClassType(obj.getClass())) {
			return null;
		} else {
			Field[] fields = obj.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				String fieldName = field.getName();
				String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				System.out.println(fieldName);
				if (ClassUtils.judgeClassType(field.getType())) {
					json.put(fieldName, ClassUtils.getMethodProxy(obj, methodName, new Object[] {}));
				} else {
					json.put(fieldName, JsonUtils.ObjectToJson(ClassUtils.getMethodProxy(obj, methodName, new Object[] {})));
				}
			}
		}
		return json;
	}

	/**
	 * @param statusCode
	 *            200 ok 300 error 301 timeout
	 * @param message
	 * @param relId
	 * @param navTabId
	 *            要刷新的navTabId
	 * @param forwarUrl
	 *            当callbacktype=forward forwardurl 才 需要填写
	 * @param callbackType
	 *            forward closeCurrent
	 * @param confirmMsg
	 * @return
	 */
	public static JSONObject getResultJson(String statusCode, String message, String relId, String navTabId, String forwarUrl, String callbackType, String confirmMsg) {
		JSONObject result = new JSONObject();
		try {
			result.put("statusCode", statusCode);
			result.put("message", message);
			result.put("relId", relId);
			result.put("navTabId", navTabId);
			result.put("forwardUrl", forwarUrl);
			result.put("callbackType", callbackType);
		} catch (Exception e) {
		}
		return result;
	}

	public static void main(String[] args) throws JSONException {
	}
}
