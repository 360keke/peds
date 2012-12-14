package com.cn.v2.common.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.cn.v2.data.mapper.FileMapper;

public class MapperBeansUtil {
	
	static ApplicationContext ac = null;
	static {
		ac = new FileSystemXmlApplicationContext("classpath:applicationContext-mapperBeans.xml");
	}

	public static Object getBean(String beanName) {
		return ac.getBean(beanName);
	}

	public static void main(String[] args) {
		FileMapper mapper = (FileMapper) getBean("fileMapper");
		System.out.println(mapper.getFileById(0));
		
	}
}
