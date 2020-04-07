package com.demo.app.common;

import java.util.Properties;

/**
 * 系统中加载到内存的缓存信息
 */
public class ParameterCache {
	/**
	 * 系统属性
	 */
	public static Properties systemProperties;

	public static String getSystemProperties(String key) {
		return systemProperties.getProperty(key);
	}

	public static void setSystemProperties(Properties systemProperties) {
		ParameterCache.systemProperties = systemProperties;
	}

}
