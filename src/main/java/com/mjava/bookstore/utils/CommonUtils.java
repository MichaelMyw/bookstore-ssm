package com.mjava.bookstore.utils;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;




public class CommonUtils {
	
	/**
	 * 返回一个不重复的字符串
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	 /** 
     *  
     *  
     * Map转换层Bean，使用泛型免去了类型转换的麻烦。 
     * @param <T> 
     * @param map   
     * @param class1 
     * @return 
     */  
	@SuppressWarnings("rawtypes")
	public static <T> T toBean(Map map, Class<T> clazz) {
		try {
			/*
			 * 1. 通过参数clazz创建实例
			 * 2. 使用BeanUtils.populate把map的数据封闭到bean中
			 */
			T bean = clazz.newInstance();
			BeanUtils.populate(bean, map);
			return bean;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

}
