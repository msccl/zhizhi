package com.qkban.dao.money.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

import com.qkban.bean.money.UserMoneyLogBean;
import com.qkban.dao.money.MoneyLogDao;

@Service
public class MoneyLogDaoImpl implements MoneyLogDao {

	@Resource
	private JdbcTemplate moneyTemplate;

	public int add(UserMoneyLogBean log) {
		return moneyTemplate.update("insert into user_moneylog"+(log.getUid()&0x0f)+"(uid,money,tradeid,remark,createtime) values(?,?,?,?,now())",new Object[]{log.getUid(),log.getMoney(),log.getTradeid(),log.getRemark()});
	}

	public List<UserMoneyLogBean> getMoneyLogList(int uid, int start, int size) {
	    ParameterizedRowMapper<UserMoneyLogBean> mapper = new ParameterizedRowMapper<UserMoneyLogBean>() {
	        public UserMoneyLogBean mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	UserMoneyLogBean log = new UserMoneyLogBean();
	        	log.setId(rs.getInt("id"));
	        	log.setUid(rs.getInt("uid"));
	        	log.setMoney(rs.getDouble("money"));
	        	log.setTradeid(rs.getInt("tradeid"));
	        	log.setRemark(rs.getString("remark"));
	        	log.setCreatetime(rs.getString("createtime"));
	        	return log;
	        }
	    };
	    
	    return moneyTemplate.query("select * from user_moneylog"+(uid&0x0f)+" where uid="+uid+" order by id desc limit "+start+","+size,mapper);
	}

	public int logMaxCount(int uid) {
		return moneyTemplate.queryForInt("select count(*) from user_moneylog"+(uid&0x0f)+" where uid="+uid);
	}

	
	/**
	 * 充�?�记录列�?
	 * @param uid
	 * @param start
	 * @param size
	 * @return
	 */
	public List<UserMoneyLogBean> getAddMoneyLogList(int uid,int start,int size) {
	    ParameterizedRowMapper<UserMoneyLogBean> mapper = new ParameterizedRowMapper<UserMoneyLogBean>() {
	        public UserMoneyLogBean mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	UserMoneyLogBean log = new UserMoneyLogBean();
	        	log.setId(rs.getInt("id"));
	        	log.setUid(rs.getInt("uid"));
	        	log.setMoney(rs.getDouble("money"));
	        	log.setTradeid(rs.getInt("tradeid"));
	        	log.setRemark(rs.getString("remark"));
	        	log.setCreatetime(rs.getString("createtime"));
	        	return log;
	        }
	    };
	    
	    return moneyTemplate.query("select * from user_moneylog"+(uid&0x0f)+" where uid="+uid+" and tradeid in (5,6,7,8) order by id desc limit "+start+","+size,mapper);
	}
	
	
	/**
	 * 充�?�记录�?�量
	 * @param uid
	 * @return
	 */
	public int addMoneyLogMaxCount(int uid) {
		return moneyTemplate.queryForInt("select count(*) from user_moneylog"+(uid&0x0f)+" where uid="+uid+" and tradeid in (5,6,7,8)");
	}

}
