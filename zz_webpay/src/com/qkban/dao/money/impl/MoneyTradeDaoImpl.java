package com.qkban.dao.money.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

import com.qkban.bean.money.MoneyTradeBean;
import com.qkban.dao.money.MoneyTradeDao;

@Service
public class MoneyTradeDaoImpl implements MoneyTradeDao {

	@Resource
	private JdbcTemplate moneyTemplate;

	public int add(MoneyTradeBean moneytrade) {
		return moneyTemplate.update("insert into money_trade(role,transtype,transcontent,description,status,updatetime) values(?,?,?,?,?,now())",
				new Object[]{moneytrade.getRole(),moneytrade.getTranstype(),moneytrade.getTranscontent(),moneytrade.getDescription(),moneytrade.getStatus()});
	}

	public int del(int id) {
		return moneyTemplate.update("delete from money_trade where id="+id);
	}

	public List<MoneyTradeBean> getLockTradeIdList() {
	    ParameterizedRowMapper<MoneyTradeBean> mapper = new ParameterizedRowMapper<MoneyTradeBean>() {
	        public MoneyTradeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	MoneyTradeBean trade = new MoneyTradeBean();
	        	trade.setId(rs.getInt("id"));
	        	trade.setRole(rs.getInt("role"));
	        	trade.setTranstype(rs.getInt("transtype"));
	        	trade.setTranscontent(rs.getString("transcontent"));
	        	trade.setDescription(rs.getString("description"));
	        	trade.setStatus(rs.getInt("status"));
	        	trade.setUpdatetime(rs.getString("updatetime"));
	        	return trade;
	        }
	    };
	    
	    return moneyTemplate.query("select * from money_trade where status=-1 order by id desc",mapper);
	}

	public MoneyTradeBean getTrade(int id) {
	    ParameterizedRowMapper<MoneyTradeBean> mapper = new ParameterizedRowMapper<MoneyTradeBean>() {
	        public MoneyTradeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	MoneyTradeBean trade = new MoneyTradeBean();
	        	trade.setId(rs.getInt("id"));
	        	trade.setRole(rs.getInt("role"));
	        	trade.setTranstype(rs.getInt("transtype"));
	        	trade.setTranscontent(rs.getString("transcontent"));
	        	trade.setDescription(rs.getString("description"));
	        	trade.setStatus(rs.getInt("status"));
	        	trade.setUpdatetime(rs.getString("updatetime"));
	        	return trade;
	        }
	    };
	 
	    try {
	    	return moneyTemplate.queryForObject("select * from money_trade where id=?",new Object[]{id},mapper);
	    } catch(Exception e) {
	    	return null;
	    }
	}


	public List<MoneyTradeBean> getTradeList() {
	    ParameterizedRowMapper<MoneyTradeBean> mapper = new ParameterizedRowMapper<MoneyTradeBean>() {
	        public MoneyTradeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	MoneyTradeBean trade = new MoneyTradeBean();
	        	trade.setId(rs.getInt("id"));
	        	trade.setRole(rs.getInt("role"));
	        	trade.setTranstype(rs.getInt("transtype"));
	        	trade.setTranscontent(rs.getString("transcontent"));
	        	trade.setDescription(rs.getString("description"));
	        	trade.setStatus(rs.getInt("status"));
	        	trade.setUpdatetime(rs.getString("updatetime"));
	        	return trade;
	        }
	    };
	    
	    return moneyTemplate.query("select * from money_trade order by id desc",mapper);
	}

	public int update(MoneyTradeBean moneytrade) {
		return moneyTemplate.update("update money_trade set role=?,transtype=?,transcontent=?,description=?,status=?,updatetime=now() where id=?",
				new Object[]{moneytrade.getRole(),moneytrade.getTranstype(),moneytrade.getTranscontent(),moneytrade.getDescription(),moneytrade.getStatus(),moneytrade.getId()});
	}

}
