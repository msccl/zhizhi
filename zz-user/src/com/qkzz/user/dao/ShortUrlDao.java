package com.qkzz.user.dao;

public interface ShortUrlDao {

	/**
	 * 通过短代码获取真实的url
	 * @param shortCode
	 * @return
	 */
	String get(String shortCode);

	/**
	 * 添加段地址信息
	 * @param shortCode
	 * @param url
	 * @return
	 */
	int add(String shortCode,String url);
	
	/**
	 * 判断短地址是否存在，如果存在，不再添加
	 * @param shortCode
	 * @return
	 */
	boolean isExist(String shortCode);

}
