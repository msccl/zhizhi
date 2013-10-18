package com.qkzz.util;

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
	private static int calMaxPage(int count,int size) {
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
			return 10;
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
	
	/**
	 * 分页代码
	 * @param count		总条数
	 * @param size		每页条数
	 * @param page		当前页码
	 * @param url		网址
	 * @return
	 */
    public static String pagination(int count, int size, int page, String url) {
    	String param = "";
    	if(url.indexOf("?")!=-1){
    		param = url.substring(url.indexOf("?"));
    		url   = url.substring(0, url.indexOf("?"));
    	}
		url = url.endsWith("/")? url : url+"/";
		url += size + "/";
		String urlstr = "";
		int allpage = calMaxPage(count, size);
		if (allpage > 1) {
			if (page > 1) {
				urlstr += "<span class=\"pre bbt\"><a href=\"" + url + (page - 1) + param + "\">"
							+ "&lt;&lt;</a></span>";
			} else {
				urlstr += "<span class=\"pre bbt\">&lt;&lt;</span>";
			}

			for (int i = 1; i <= allpage; i++) {
				if (i == page)
					urlstr += "<a href=\"" + url + i + param + "\" class=\"pagenow\">" + i + "</a> ";
				else
					urlstr += "<a href=\"" + url + i + param + "\">" + i + "</a> ";
			}
			
			if (page > 1) {
				if (page < allpage) {
					urlstr += "<span class=\"next bbt\"><a href=\"" + url + (page + 1) + param + "\">"
							+ "&gt;&gt;</a></span>";
				} else {
					urlstr += "<span class=\"next bbt\">&gt;&gt;</span>";
				}
			} else {
				urlstr += "<span class=\"next bbt\"><a href=\"" + url + (page + 1) + param + "\">"
				+ "&gt;&gt;</a></span>";
			}

		}
		return urlstr;
	}
    
}
