package com.cn.v2.data.bean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PedsBaseBean {
	final Log log = LogFactory.getLog(PedsBaseBean.class);

	public String toString() {
		Class c = this.getClass();
		Field[] fields = c.getDeclaredFields();
		String result = "";
		for (int i = 0; i < fields.length; i++) {
			Object value = "";
			Field field = fields[i];
			Method[] methods = c.getDeclaredMethods();
			for (int j = 0; j < methods.length; j++) {
				Method method = methods[j];
				String fieldName = field.getName();
				String methodName = method.getName();
				if (methodName.equals("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1))) {
					try {
						value = method.invoke(this, null);
					} catch (IllegalArgumentException e) {
						log.debug(e.getMessage());
					} catch (IllegalAccessException e) {
						log.debug(e.getMessage());
					} catch (InvocationTargetException e) {
						log.debug(e.getMessage());
					}
					result += fieldName + "=" + value + ";";
				}
			}
		}
		if (result.endsWith(";")) {
			result = result.substring(0, result.length()-1);
		}
		return result;
	}
}
