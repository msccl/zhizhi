package com.qkzz.web.developer.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

import com.qkzz.web.developer.bean.MoneyInfo;
import com.qkzz.web.developer.dao.MoneyInfoDao;

@Service
public class MoneyInfoDaoImpl implements MoneyInfoDao {

	@Resource
	private JdbcTemplate moneyTemplate;


	public int addMoneyInfo(MoneyInfo bean) {
		return moneyTemplate.update("insert into t_game_money_info(name,canexchange,exchangerate,gameid,status,createtime) " +
				"values(?,?,?,?,0,now())",new Object[]{bean.getName(),bean.getCanexchange(),bean.getExchangerate(),bean.getGameid()});
	}

	public int delMoneyInfo(int id) {
		return moneyTemplate.update("delete from t_game_money_info where id=?",new Object[]{id});
	}

	public int editMoneyInfo(MoneyInfo bean) {
		return moneyTemplate.update("update t_game_money_info set name=?,canexchange=?,exchangerate=?,status=0 " +
				"where id=?",new Object[]{bean.getName(),bean.getCanexchange(),bean.getExchangerate(),bean.getId()});
	}

	public List<MoneyInfo> getGameMoneyInfo(int gameid,int startIndex,int num) {
	    ParameterizedRowMapper<MoneyInfo> mapper = new ParameterizedRowMapper<MoneyInfo>() {
	        public MoneyInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	MoneyInfo bean = new MoneyInfo();
	        	bean.setId(rs.getInt("id"));
	        	bean.setName(rs.getString("name"));
	        	bean.setCanexchange(rs.getInt("canexchange"));
	        	bean.setExchangerate(rs.getDouble("exchangerate"));
	        	bean.setGameid(rs.getInt("gameid"));
	        	bean.setStatus(rs.getInt("status"));
	        	bean.setCreatetime(rs.getString("createtime"));
	        	return bean;
	        }
	    };
	    
	    return moneyTemplate.query("select * from t_game_money_info where gameid=? order by id desc limit ?,?",new Object[]{gameid,startIndex,num},mapper);
	}

	public int getGameMoneyMaxCount(int gameid) {
	    return moneyTemplate.queryForInt("select count(*) from t_game_money_info where gameid=?",new Object[]{gameid});
	}

	
	
	public MoneyInfo getMoneyInfo(int id) {
	    ParameterizedRowMapper<MoneyInfo> mapper = new ParameterizedRowMapper<MoneyInfo>() {
	        public MoneyInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	MoneyInfo bean = new MoneyInfo();
	        	bean.setId(rs.getInt("id"));
	        	bean.setName(rs.getString("name"));
	        	bean.setCanexchange(rs.getInt("canexchange"));
	        	bean.setExchangerate(rs.getDouble("exchangerate"));
	        	bean.setGameid(rs.getInt("gameid"));
	        	bean.setStatus(rs.getInt("status"));
	        	bean.setCreatetime(rs.getString("createtime"));
	        	return bean;
	        }
	    };
	 
	    try {
	    	return moneyTemplate.queryForObject("select * from t_game_money_info where id=?",new Object[]{id},mapper);
	    } catch(Exception e) {
	    	return null;
	    }
	}

	public int getLastInsertID(int gameid, String name) {
	    return moneyTemplate.queryForInt("select id from t_game_money_info where gameid=? and name=?",new Object[]{gameid,name});
	}

}
