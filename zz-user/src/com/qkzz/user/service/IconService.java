package com.qkzz.user.service;

import java.util.List;

import com.qkzz.common.LRUMap;
import com.qkzz.user.bean.Icon;
import com.qkzz.user.dao.IconDao;
import com.qkzz.user.dao.impl.IconDaoImpl;

public class IconService {
	
	private static IconDao dao = new IconDaoImpl();
	private static LRUMap<Integer, Icon> iconMap = new LRUMap<Integer, Icon>(200);;
	
	private static long lastFreshTime = System.currentTimeMillis();
	
	public static String getByUrl(Integer id){
		if(iconMap.containsKey(id)){
			return iconMap.get(id).getUrl();
		}else{
			getByMap();
			if(iconMap.containsKey(id)){
				return iconMap.get(id).getUrl();
			}
			return "";
		}
	}
	
	public static LRUMap<Integer, Icon> getByMap() {
		if (iconMap == null || iconMap.isEmpty() || System.currentTimeMillis()-lastFreshTime > 1000 * 60 * 5) {
			List<Icon> list = dao.getByList(0,200);
			if(list != null) {
				for (int i = 0; i < list.size(); i++) {
					Icon obj = list.get(i);
					iconMap.put(obj.getId(), obj);
				}
			}
			lastFreshTime = System.currentTimeMillis();
		}
		return iconMap;
	}
	
	public static void main(String [] args){
		
	}
	
}
