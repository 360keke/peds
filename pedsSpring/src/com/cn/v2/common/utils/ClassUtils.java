package com.cn.v2.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ClassUtils {
	private static Log log = LogFactory.getLog(ClassUtils.class);

	/**
	 * 普通类型对象强制拆包
	 * 
	 * @param c
	 * @return
	 */
	public static Class unBoxingClass(Class c) {
		if (c.equals(int.class) || c.equals(Integer.class)) {
			return int.class;
		} else if (c.equals(float.class) || c.equals(Float.class)) {
			return float.class;
		} else if (c.equals(double.class) || c.equals(Double.class)) {
			return double.class;
		} else if (c.equals(byte.class) || c.equals(Byte.class)) {
			return byte.class;
		} else if (c.equals(boolean.class) || c.equals(Boolean.class)) {
			return boolean.class;
		} else {
			return c;
		}
	}

	public static boolean judgeClassType(Class c) {
		if (c.equals(int.class) || c.equals(Integer.class)) {
			return true;
		} else if (c.equals(float.class) || c.equals(Float.class)) {
			return true;
		} else if (c.equals(double.class) || c.equals(Double.class)) {
			return true;
		} else if (c.equals(byte.class) || c.equals(Byte.class)) {
			return true;
		} else if (c.equals(boolean.class) || c.equals(Boolean.class)) {
			return true;
		} else if (c.equals(BigInteger.class)) {
			return true;
		} else if (c.equals(BigDecimal.class)) {
			return true;
		} else if(c.equals(String.class)){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 通过反射动态执行set方法调用
	 * 
	 * @param mapper
	 * @param methodName
	 * @param args
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void setMethodProxy(Object obj, String methodName, Object[] args) {
		Class[] argsClass = new Class[args.length];
		Object retuObj = null;
		for (int i = 0, j = args.length; i < j; i++) {
			if (args[i] != null) {
				argsClass[i] = ClassUtils.unBoxingClass(args[i].getClass());
			} else {
				argsClass[i] = "".getClass();
			}
		}
		try {
			Method method = obj.getClass().getMethod(methodName, argsClass);
			retuObj = method.invoke(obj, args);
		} catch (SecurityException e) {
			log.debug(e.getMessage());
		} catch (NoSuchMethodException e) {
			log.error(e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
		} catch (IllegalAccessException e) {
			log.debug(e.getMessage());
		} catch (InvocationTargetException e) {
			log.debug(e.getMessage());
		}
	}

	/**
	 * 通过反射动态执行get方法调用
	 * 
	 * @param mapper
	 * @param methodName
	 * @param args
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static Object getMethodProxy(Object obj, String methodName, Object[] args) {
		Class[] argsClass = new Class[args.length];
		Object retuObj = null;
		for (int i = 0, j = args.length; i < j; i++) {
			if (args[i] != null) {
				argsClass[i] = ClassUtils.unBoxingClass(args[i].getClass());
			} else {
				argsClass[i] = "".getClass();
			}
		}
		try {
			Method method = obj.getClass().getMethod(methodName, argsClass);
			retuObj = method.invoke(obj, args);
		} catch (SecurityException e) {
			log.debug(e.getMessage());
		} catch (NoSuchMethodException e) {
			log.error(e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage());
		} catch (IllegalAccessException e) {
			log.debug(e.getMessage());
		} catch (InvocationTargetException e) {
			log.debug(e.getMessage());
		}
		return retuObj;
	}

}
