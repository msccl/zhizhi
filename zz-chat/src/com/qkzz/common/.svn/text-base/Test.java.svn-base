package com.qkzz.common;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "http://www.7kban.com/share.jsp?gid=111&uid=111";
		String queryString = url.substring(url.indexOf("?")+1);
		String[] tmp = queryString.split("&");
		
		for(int i=0;i<tmp.length;i++) {
			String para = tmp[i];
			if(para.indexOf("uid") != -1) {
				int uid = Integer.parseInt(para.substring(para.indexOf("=")+1));
				System.out.println(uid);
			}
		}
	}

}
