package com.qkzz.web.developer.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.qkzz.web.developer.dao.ValidateEntryDao;

@Service
public class ValidateEntryDaoImpl implements ValidateEntryDao {
	
	@Resource
	private JdbcTemplate gameTemplate;

	public String getValue(String key){
		String value = null;
		try{
			value = gameTemplate.queryForObject("select indent from validate_entry where indentnumber=?", new Object[]{key}, String.class);
		}catch (Exception e){
		}
		return value;
	}
	
	public int save(String indentnumber, String indent){
		String sql="insert into validate_entry(indentnumber, indent, createtime) values(?,?,?)";
		return gameTemplate.update(sql, new Object[]{
				indentnumber,
				indent,
				System.currentTimeMillis()
		});
	}
	
	public void deleteTimeOut(){
		long now = System.currentTimeMillis();
		String sql = "delete from validate_entry where ("+now+"-createtime)>300000";
		gameTemplate.update(sql);
	}
	
}
