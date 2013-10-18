package com.qkzz.web.stat.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

import com.qkzz.web.stat.bean.StatAll;
import com.qkzz.web.stat.dao.StatAllDao;

@Service
public class StatAllDaoImpl implements StatAllDao {
	
	@Resource
	public JdbcTemplate statTemplate;
	
	public int countByList(String begindate, String enddate) {
	    return statTemplate.queryForInt("select count(*) from stat_all where createdate>=? and createdate<=?",new Object[]{begindate,enddate});
	}
	
	public List<StatAll> getByList(String begindate, String enddate, int first, int max) {
	    ParameterizedRowMapper<StatAll> mapper = new ParameterizedRowMapper<StatAll>() {
	        public StatAll mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	StatAll obj = new StatAll();
	        	obj.setId(rs.getInt("id"));
	        	obj.setCreatedate(rs.getString("createdate"));
	        	obj.setNum(rs.getInt("num"));
	        	obj.setIp_num(rs.getInt("ip_num"));
	        	obj.setUserid_num(rs.getInt("userid_num"));
	        	return obj;
	        }
	    };
	    return statTemplate.query("select * from stat_all where createdate>=? and createdate<=? order by id desc limit ?,?",new Object[]{begindate,enddate,first,max},mapper);
	}
	
}
