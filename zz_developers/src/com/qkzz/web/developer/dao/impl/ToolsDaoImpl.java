package com.qkzz.web.developer.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

import com.qkzz.web.developer.bean.Tools;
import com.qkzz.web.developer.dao.ToolsDao;

@Service
public class ToolsDaoImpl implements ToolsDao {

	@Resource
	private JdbcTemplate gameTemplate;

	public int addTools(Tools bean) {
		return gameTemplate.update("insert into t_game_tools"+(bean.getGameid()&0x0f)+"(gameid,name,intro,img,canexchange,canauction,candrop,functiondefine,moneyid,price,status,createtime) " +
				"values(?,?,?,?,?,?,?,?,?,?,0,now())",new Object[]{bean.getGameid(),bean.getName(),bean.getIntro(),bean.getImg(),bean.getCanexchange(),bean.getCanauction(),bean.getCandrop(),bean.getFunctiondefine(),bean.getMoneyid(),bean.getPrice()});
	}

	/**
	 * 获取最新添加的道具ID
	 * @param gameid
	 * @param name
	 * @return
	 */
	public int getLastToolsID(int gameid,String name) {
	    return gameTemplate.queryForInt("select id from t_game_tools"+(gameid&0x0f)+" where gameid=? and name=? order by id desc limit 1",new Object[]{gameid,name});
	}
	
	public int delTools(int gameid,int id) {
		return gameTemplate.update("delete from t_game_tools"+(gameid&0x0f)+" where id=?",new Object[]{id});
	}

	public int editTools(Tools bean) {
		return gameTemplate.update("update t_game_tools"+(bean.getGameid()&0x0f)+" set name=?,intro=?,canexchange=?,canauction=?,candrop=?,functiondefine=?,moneyid=?,price=?,img=?" +
				" where id=?",new Object[]{bean.getName(),bean.getIntro(),bean.getCanexchange(),bean.getCanauction(),bean.getCandrop(),bean.getFunctiondefine(),bean.getMoneyid(),bean.getPrice(),bean.getImg(),bean.getId()});
	}

	public int changeStatus(int gameid,int id,int status) {
		return gameTemplate.update("update t_game_tools"+(gameid&0x0f)+" set status=? where id=?",new Object[]{status,id});
	}

	public List<Tools> getGameTools(int gameid,int startIndex,int num) {
	    ParameterizedRowMapper<Tools> mapper = new ParameterizedRowMapper<Tools>() {
	        public Tools mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Tools bean = new Tools();
	        	bean.setId(rs.getInt("id"));
	        	bean.setGameid(rs.getInt("gameid"));
	        	bean.setName(rs.getString("name"));
	        	bean.setIntro(rs.getString("intro"));
	        	bean.setImg(rs.getString("img"));
	        	bean.setCanauction(rs.getInt("canauction"));
	        	bean.setCanexchange(rs.getInt("canexchange"));
	        	bean.setCandrop(rs.getInt("candrop"));
	        	bean.setFunctiondefine(rs.getString("functiondefine"));
	        	bean.setMoneyid(rs.getInt("moneyid"));
	        	bean.setPrice(rs.getDouble("price"));
	        	bean.setStatus(rs.getInt("status"));
	        	bean.setCreatetime(rs.getString("createtime"));
	        	return bean;
	        }
	    };
	    
	    return gameTemplate.query("select * from t_game_tools"+(gameid&0x0f)+" where gameid=? order by id desc limit ?,?",new Object[]{gameid,startIndex,num},mapper);
	}

	
	public int getGameToolsMaxCount(int gameid) {
	    return gameTemplate.queryForInt("select count(*) from t_game_tools"+(gameid&0x0f)+" where gameid=?",new Object[]{gameid});
	}

	
	public Tools getTools(int gameid,int id) {
	    ParameterizedRowMapper<Tools> mapper = new ParameterizedRowMapper<Tools>() {
	        public Tools mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Tools bean = new Tools();
	        	bean.setId(rs.getInt("id"));
	        	bean.setGameid(rs.getInt("gameid"));
	        	bean.setName(rs.getString("name"));
	        	bean.setIntro(rs.getString("intro"));
	        	bean.setImg(rs.getString("img"));
	        	bean.setCanauction(rs.getInt("canauction"));
	        	bean.setCanexchange(rs.getInt("canexchange"));
	        	bean.setCandrop(rs.getInt("candrop"));
	        	bean.setFunctiondefine(rs.getString("functiondefine"));
	        	bean.setMoneyid(rs.getInt("moneyid"));
	        	bean.setPrice(rs.getDouble("price"));
	        	bean.setStatus(rs.getInt("status"));
	        	bean.setCreatetime(rs.getString("createtime"));
	        	return bean;
	        }
	    };
	 
	    try {
	    	return gameTemplate.queryForObject("select * from t_game_tools"+(gameid&0x0f)+" where id=?",new Object[]{id},mapper);
	    } catch(Exception e) {
	    	return null;
	    }
	}

}
