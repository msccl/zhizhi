package com.qkzz.game.util;



/**
 *
 * @author ibm
 */
public class Pager {

	/**
	 * 最大页面
	 * @param count int - 总行数
	 * @param size int - 每页显示数
	 * @return int
	 */
	public static int calMaxPage(int count,int size) {
		int page = 1;
	    if (count % size == 0) {
	    	page = count / size;
	    } else {
	    	page = count / size + 1;
	    }
	    return page < 1? 1 : page;
	}

	/**
	 * 当前页面
	 * @param maxpage int - 最大页数
	 * @param page int - 当前传入页数
	 * @return int
	 */
	private static int calCurrentPage(int maxpage, int page) {
		if (page>maxpage && maxpage>0) {
			page = maxpage;
		}
		return page < 1? 1 : page;
	}
	
	/**
	 * 处理每页条数
	 * @param size
	 * @return
	 */
	public static int getSize(int size){
		if(size<1){
			return 2;
		}
		if(size>100){
			return 100;
		}
		return size;
	}
	
	/**
	 * 获取当前页码
	 * @param count
	 * @param size
	 * @param page
	 * @return
	 */
	public static int calCurrentPage(int count, int size, int page){
		return calCurrentPage(calMaxPage(count, size), page);
	}
	
}
