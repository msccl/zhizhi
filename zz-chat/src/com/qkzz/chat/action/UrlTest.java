package com.qkzz.chat.action;

import java.util.ArrayList;
import java.util.HashMap;

public class UrlTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(UrlTest.getStr(123456L));
		
		System.out.println(UrlTest.getLong("E7W"));
	}

	
	/****************************************************
	 * 将十进制的数字按规则转化成的字符串并能还原成数字
	 ***************************************************/
	public static String getStr(long l){
		StringBuffer bu = new StringBuffer();
		long hi = l / max;
		long mod = l % max;
		bu.append(c.get(mod));
		while(hi>0){
			mod = hi % max;
			bu.append(c.get(mod));
			hi = hi / max;
		}
		return bu.toString();
	}
	
	public static String getStr(int l){
		StringBuffer bu = new StringBuffer();
		int hi = l / max;
		int mod = l % max;
		bu.append(a.get(mod));
		while(hi>0){
			mod = hi % max;
			bu.append(a.get(mod));
			hi = hi / max;
		}
		return bu.toString();
	}
	
	public static long getLong(String str){
		long ret = 0;
		int length = str.length();
		String key = "";
		for(int i = 0;i<length;i++){
			key = str.substring(i, i+1);
			if(i>0){
				ret += Math.pow(max, i)*d.get(key);
			}else{
				ret += d.get(key);
			}
		}
		return ret;
	}
	
	public static int getInt(String str){
		int ret = 0;
		int length = str.length();
		String key = "";
		for(int i = 0;i<length;i++){
			key = str.substring(i, i+1);
			if(i>0){
				ret += Math.pow(max, i)*b.get(key);
			}else{
				ret += b.get(key);
			}
			//System.out.println("key = "+key);
		}
		return ret;
	}
	
	
	private static ArrayList<String> a = new ArrayList<String>();
	private static HashMap<Long,String> c = new HashMap<Long,String>();
	private static HashMap<String,Integer> b = new HashMap<String,Integer>();
	private static HashMap<String,Long> d = new HashMap<String,Long>();
	
	public static final int max;
	static{
		for(int i = 0;i<10;i++){
			a.add(""+i);
		}
		for(int i = 65;i<=90;i++){
			a.add(String.valueOf((char)i));
		}
		for(int i = 97;i<=122;i++){
			a.add(String.valueOf((char)i));
		}
		
		for(int i = 0;i<a.size();i++){
			b.put(a.get(i), i);
			d.put(a.get(i), (long)i);
			c.put((long)i, a.get(i));
		}
		
		a.add("(");
		a.add(")");
		a.add("[");
		a.add("]");
		a.add("-");
		a.add("@");
		
		max = a.size();
	}
}
