package com.qkzz.web.developer.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

import com.qkzz.web.developer.bean.GameInfo;
import com.qkzz.web.developer.dao.GameInfoDao;

@Service
public class GameInfoDaoImpl implements GameInfoDao {

	@Resource
	private JdbcTemplate gameTemplate;

	public int addGame(GameInfo bean) {
		return gameTemplate.update("insert into t_game_info(gamecode,name,intro,strategy,logo,url,host,developerid,status,createtime,width,height,uiurl) " +
				"values(?,?,?,?,?,?,?,?,0,now(),?,?,?)",new Object[]{bean.getGamecode(),bean.getName(),bean.getIntro(),bean.getStrategy(),bean.getLogo(),bean.getUrl(),bean.getHost(),bean.getDeveloperid(),bean.getWidth(),bean.getHeight(),bean.getUiurl()});
	}

	public int delGame(int id) {
		return gameTemplate.update("delete from t_game_info where id=?",new Object[]{id});
	}

	public int editGame(GameInfo bean) {
		return gameTemplate.update("update t_game_info set name=?,intro=?,strategy=?,logo=?,url=?,host=?,width=?,height=?,uiurl=? where id=?",new Object[]{bean.getName(),bean.getIntro(),bean.getStrategy(),bean.getLogo(),bean.getUrl(),bean.getHost(),bean.getWidth(),bean.getHeight(),bean.getUiurl(),bean.getId()});
	}

	public int editGameStatus(int gameID, int status) {
		return gameTemplate.update("update t_game_info set status=? where id=?",new Object[]{status,gameID});
	}

	public GameInfo getGame(int id) {
	    ParameterizedRowMapper<GameInfo> mapper = new ParameterizedRowMapper<GameInfo>() {
	        public GameInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	GameInfo bean = new GameInfo();
	        	bean.setId(rs.getInt("id"));
	        	bean.setGamecode(rs.getString("gamecode"));
	        	bean.setName(rs.getString("name"));
	        	bean.setIntro(rs.getString("intro"));
	        	bean.setStrategy(rs.getString("strategy"));
	        	bean.setLogo(rs.getString("logo"));
	        	bean.setUrl(rs.getString("url"));
	        	bean.setHost(rs.getString("host"));
	        	bean.setDeveloperid(rs.getInt("developerid"));
	        	bean.setStatus(rs.getInt("status"));
	        	bean.setCreatetime(rs.getString("createtime"));
	        	bean.setWidth(rs.getInt("width"));
	        	bean.setHeight(rs.getInt("height"));
	        	bean.setUiurl(rs.getString("uiurl"));
	        	return bean;
	        }
	    };
	 
	    try {
	    	return gameTemplate.queryForObject("select * from t_game_info where id=?",new Object[]{id},mapper);
	    } catch(Exception e) {
	    	return null;
	    }
	}

	public List<GameInfo> getGameList(int developerid,int startIndex,int num) {
	    ParameterizedRowMapper<GameInfo> mapper = new ParameterizedRowMapper<GameInfo>() {
	        public GameInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	GameInfo bean = new GameInfo();
	        	bean.setId(rs.getInt("id"));
	        	bean.setGamecode(rs.getString("gamecode"));
	        	bean.setName(rs.getString("name"));
	        	bean.setIntro(rs.getString("intro"));
	        	bean.setStrategy(rs.getString("strategy"));
	        	bean.setLogo(rs.getString("logo"));
	        	bean.setUrl(rs.getString("url"));
	        	bean.setHost(rs.getString("host"));
	        	bean.setDeveloperid(rs.getInt("developerid"));
	        	bean.setStatus(rs.getInt("status"));
	        	bean.setCreatetime(rs.getString("createtime"));
	        	bean.setWidth(rs.getInt("width"));
	        	bean.setHeight(rs.getInt("height"));
	        	bean.setUiurl(rs.getString("uiurl"));
	        	return bean;
	        }
	    };
	    
	    return gameTemplate.query("select * from t_game_info where developerid=? order by id desc limit ?,?",new Object[]{developerid,startIndex,num},mapper);
	}

	
	public int getGameMaxCount(int developerid) {
	    return gameTemplate.queryForInt("select count(*) from t_game_info where developerid=?",new Object[]{developerid});
	}

	
	/**
	 * 通过游戏编号获取对应的数字ID
	 * @param gamecode
	 * @return
	 */
	public int getIDByGameCode(String gamecode) {
	    return gameTemplate.queryForInt("select id from t_game_info where gamecode=?",new Object[]{gamecode});
	}
}
