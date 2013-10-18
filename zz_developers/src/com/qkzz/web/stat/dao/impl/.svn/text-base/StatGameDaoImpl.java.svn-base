package com.qkzz.web.stat.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qkzz.web.stat.bean.StatGame;
import com.qkzz.web.stat.dao.StatGameDao;


@Service
public class StatGameDaoImpl implements StatGameDao {
	
	@Resource
	public JdbcTemplate statTemplate;
	
	public int countByList(String gamecode, String begindate, String enddate) {
		if(StringUtils.hasText(begindate) && StringUtils.hasText(enddate)){
			return statTemplate.queryForInt("select count(*) from stat_game where gamecode=? and createdate>=? and createdate<=?",new Object[]{gamecode,begindate,enddate});
		}else{
			return statTemplate.queryForInt("select count(*) from stat_game where gamecode=?",new Object[]{gamecode});
		}
	}
	
	public List<StatGame> getByList(String gamecode, String begindate, String enddate, int first, int max) {
	    ParameterizedRowMapper<StatGame> mapper = new ParameterizedRowMapper<StatGame>() {
	        public StatGame mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	StatGame obj = new StatGame();
	        	obj.setId(rs.getInt("id"));
	        	obj.setCreatedate(rs.getString("createdate"));
	        	obj.setGamecode(rs.getString("gamecode"));
	        	obj.setGamename(rs.getString("gamename"));
	        	obj.setUid(rs.getInt("uid"));
	        	obj.setNum(rs.getInt("num"));
	        	obj.setIp_num(rs.getInt("ip_num"));
	        	obj.setUserid_num(rs.getInt("userid_num"));
	        	return obj;
	        }
	    };
		if(StringUtils.hasText(begindate) && StringUtils.hasText(enddate)){
			return statTemplate.query("select * from stat_game where gamecode=? and createdate>=? and createdate<=? order by id desc limit ?,?",new Object[]{gamecode,begindate,enddate,first,max},mapper);
		}else{
			return statTemplate.query("select * from stat_game where gamecode=? order by id desc limit ?,?",new Object[]{gamecode,first,max},mapper);
		}
	}
	
	public List<String> getByGamecodeList(String date){
	    ParameterizedRowMapper<String> mapper = new ParameterizedRowMapper<String>() {
	        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	return rs.getString("gamecode");
	        }
	    };
	    return statTemplate.query("select gamecode from stat_game where createdate=?",new Object[]{date},mapper);
	}
	
	
	public StatGame getByGamecode(String gamecode, String date){
	    ParameterizedRowMapper<StatGame> mapper = new ParameterizedRowMapper<StatGame>() {
	        public StatGame mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	StatGame obj = new StatGame();
	        	obj.setId(rs.getInt("id"));
	        	obj.setCreatedate(rs.getString("createdate"));
	        	obj.setGamecode(rs.getString("gamecode"));
	        	obj.setGamename(rs.getString("gamename"));
	        	obj.setUid(rs.getInt("uid"));
	        	obj.setNum(rs.getInt("num"));
	        	obj.setIp_num(rs.getInt("ip_num"));
	        	obj.setUserid_num(rs.getInt("userid_num"));
	        	return obj;
	        }
	    };
	    try{
	    	return statTemplate.queryForObject("select * from stat_game where gamecode=? and createdate=?",new Object[]{gamecode,date},mapper);
	    }catch(Exception e){
	    	return null;
	    }

	}
	
	
}
