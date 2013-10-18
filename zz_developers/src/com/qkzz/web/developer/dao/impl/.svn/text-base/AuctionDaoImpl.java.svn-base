package com.qkzz.web.developer.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

import com.qkzz.web.developer.bean.Auction;
import com.qkzz.web.developer.dao.AuctionDao;

@Service
public class AuctionDaoImpl implements AuctionDao {

	@Resource
	private JdbcTemplate gameTemplate;

	public int addAuction(Auction bean) {
		return gameTemplate.update("insert into t_game_auction(gameid,name,swfurl,status,createtime) " +
				"values(?,?,?,0,now())",new Object[]{bean.getGameid(),bean.getName(),bean.getSwfurl()});
	}

	public int delAuction(int id) {
		return gameTemplate.update("delete from t_game_auction where id=?",new Object[]{id});
	}

	public int editAuctionStatus(int auctionID,int status) {
		return gameTemplate.update("update t_game_auction set status=? where id=?",new Object[]{status,auctionID});
	}

	public int editAuction(Auction auction) {
		return gameTemplate.update("update t_game_auction set name=?,swfurl=? where id=? and gameid=?",new Object[]{auction.getName(),auction.getSwfurl(),auction.getId(),auction.getGameid()});
	}

	
	public Auction getAuction(int id) {
	    ParameterizedRowMapper<Auction> mapper = new ParameterizedRowMapper<Auction>() {
	        public Auction mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Auction bean = new Auction();
	        	bean.setId(rs.getInt("id"));
	        	bean.setGameid(rs.getInt("gameid"));
	        	bean.setName(rs.getString("name"));
	        	bean.setSwfurl(rs.getString("swfurl"));
	        	bean.setStatus(rs.getInt("status"));
	        	bean.setCreatetime(rs.getString("createtime"));
	        	return bean;
	        }
	    };
	 
	    try {
	    	return gameTemplate.queryForObject("select * from t_game_auction where id=?",new Object[]{id},mapper);
	    } catch(Exception e) {
	    	return null;
	    }
	}

	public List<Auction> getGameAcution(int gameid,int startIndex,int num) {
	    ParameterizedRowMapper<Auction> mapper = new ParameterizedRowMapper<Auction>() {
	        public Auction mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Auction bean = new Auction();
	        	bean.setId(rs.getInt("id"));
	        	bean.setGameid(rs.getInt("gameid"));
	        	bean.setName(rs.getString("name"));
	        	bean.setSwfurl(rs.getString("swfurl"));
	        	bean.setStatus(rs.getInt("status"));
	        	bean.setCreatetime(rs.getString("createtime"));
	        	return bean;
	        }
	    };
	    
	    return gameTemplate.query("select * from t_game_auction where gameid=? order by id desc limit ?,?",new Object[]{gameid,startIndex,num},mapper);
	}

	
	public int getGameAuctionMaxCount(int gameid) {
	    return gameTemplate.queryForInt("select count(*) from t_game_auction where gameid=?",new Object[]{gameid});
	}

}
