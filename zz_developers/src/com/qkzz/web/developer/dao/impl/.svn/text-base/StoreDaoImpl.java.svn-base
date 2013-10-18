package com.qkzz.web.developer.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

import com.qkzz.web.developer.bean.Store;
import com.qkzz.web.developer.dao.StoreDao;

@Service
public class StoreDaoImpl implements StoreDao {

	@Resource
	private JdbcTemplate gameTemplate;

	public int addStore(Store bean) {
		return gameTemplate.update("insert into t_game_store(gameid,name,swfurl,status,createtime,canrecycle,recyclerate) " +
				"values(?,?,?,0,now(),?,?)",new Object[]{bean.getGameid(),bean.getName(),bean.getSwfurl(),bean.getCanrecycle(),bean.getRecyclerate()});
	}

	public int delStore(int id) {
		return gameTemplate.update("delete from t_game_store where id=?",new Object[]{id});
	}

	public int editStoreStatus(int storeID,int status) {
		return gameTemplate.update("update t_game_store set status=? where id=?",new Object[]{status,storeID});
	}

	
	public int editStore(Store store) {
		return gameTemplate.update("update t_game_store set name=?,swfurl=?,canrecycle=?,recyclerate=? where id=? and gameid=?",new Object[]{store.getName(),store.getSwfurl(),store.getCanrecycle(),store.getRecyclerate(),store.getId(),store.getGameid()});
	}

	
	public List<Store> getGameStore(int gameid,int startIndex,int num) {
	    ParameterizedRowMapper<Store> mapper = new ParameterizedRowMapper<Store>() {
	        public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Store bean = new Store();
	        	bean.setId(rs.getInt("id"));
	        	bean.setGameid(rs.getInt("gameid"));
	        	bean.setName(rs.getString("name"));
	        	bean.setSwfurl(rs.getString("swfurl"));
	        	bean.setStatus(rs.getInt("status"));
	        	bean.setCreatetime(rs.getString("createtime"));
	        	bean.setCanrecycle(rs.getInt("canrecycle"));
	        	bean.setRecyclerate(rs.getDouble("recyclerate"));
	        	return bean;
	        }
	    };
	    
	    return gameTemplate.query("select * from t_game_store where gameid=? order by id desc limit ?,?",new Object[]{gameid,startIndex,num},mapper);
	}
	
	
	public int getGameStoreMaxCount(int gameid) {
	    return gameTemplate.queryForInt("select count(*) from t_game_store where gameid=?",new Object[]{gameid});
	}


	public Store getStore(int id) {
	    ParameterizedRowMapper<Store> mapper = new ParameterizedRowMapper<Store>() {
	        public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Store bean = new Store();
	        	bean.setId(rs.getInt("id"));
	        	bean.setGameid(rs.getInt("gameid"));
	        	bean.setName(rs.getString("name"));
	        	bean.setSwfurl(rs.getString("swfurl"));
	        	bean.setStatus(rs.getInt("status"));
	        	bean.setCreatetime(rs.getString("createtime"));
	        	bean.setCanrecycle(rs.getInt("canrecycle"));
	        	bean.setRecyclerate(rs.getDouble("recyclerate"));
	        	return bean;
	        }
	    };
	 
	    try {
	    	return gameTemplate.queryForObject("select * from t_game_store where id=?",new Object[]{id},mapper);
	    } catch(Exception e) {
	    	return null;
	    }
	}

	public int getLastInsertID(int gameid, String name) {
	    return gameTemplate.queryForInt("select id from t_game_store where gameid=? and name=?",new Object[]{gameid,name});
	}

}
