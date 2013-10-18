package com.qkban.dao.money.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

import com.qkban.bean.money.AlipayWebLogBean;
import com.qkban.dao.money.AlipayWebLogDao;


@Service
public class AlipayWebLogDaoImpl implements AlipayWebLogDao {

	@Resource
	private JdbcTemplate moneyTemplate;

	/**
	 * 添加充�?�记�?
	 * @param indentnumber
	 * @param userid
	 * @param money
	 * @param ip
	 * @return
	 */
	public int addLog(String indentnumber,String userid,double money,String ip) {
		return moneyTemplate.update("insert into alipay_weblog(indentnumber,userid,money,ip,attime) values(?,?,?,?,now())",new Object[]{indentnumber,userid,money,ip});
	}
	
	
	/**
	 * 用户log�?大数�?
	 * @param uid
	 * @return
	 */
	public int logMaxCount(String userid) {
		return moneyTemplate.queryForInt("select count(*) from alipay_weblog where userid="+userid);
	}
	
	/**
	 * 用户log列表
	 * @param uid
	 * @param start
	 * @param size
	 * @return
	 */
	public List<AlipayWebLogBean> getMoneyLogList(String userid,int start,int size) {
	    ParameterizedRowMapper<AlipayWebLogBean> mapper = new ParameterizedRowMapper<AlipayWebLogBean>() {
	        public AlipayWebLogBean mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	AlipayWebLogBean log = new AlipayWebLogBean();
	        	log.setId(rs.getInt("id"));
	        	log.setUserid(rs.getString("userid"));
	        	log.setMoney(rs.getDouble("money"));
	        	log.setIndentnumber(rs.getString("indentnumber"));
	        	log.setIp(rs.getString("ip"));
	        	log.setAttime(rs.getString("attime"));
	        	return log;
	        }
	    };
	    
	    return moneyTemplate.query("select * from alipay_weblog where userid="+userid+" order by id desc limit "+start+","+size,mapper);
	}


	/**
	 * 充�?�记录�?�量
	 * @return
	 */
	public int logMaxCount() {
		return moneyTemplate.queryForInt("select count(*) from alipay_weblog");
	}
	
	/**
	 * 用户log列表
	 * @param uid
	 * @param start
	 * @param size
	 * @return
	 */
	public List<AlipayWebLogBean> getMoneyLogList(int start,int size) {
	    ParameterizedRowMapper<AlipayWebLogBean> mapper = new ParameterizedRowMapper<AlipayWebLogBean>() {
	        public AlipayWebLogBean mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	AlipayWebLogBean log = new AlipayWebLogBean();
	        	log.setId(rs.getInt("id"));
	        	log.setUserid(rs.getString("userid"));
	        	log.setMoney(rs.getDouble("money"));
	        	log.setIndentnumber(rs.getString("indentnumber"));
	        	log.setIp(rs.getString("ip"));
	        	log.setAttime(rs.getString("attime"));
	        	return log;
	        }
	    };
	    
	    return moneyTemplate.query("select * from alipay_weblog order by id desc limit "+start+","+size,mapper);
	}

}
