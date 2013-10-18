package com.qkzz.chat.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.chat.bean.Content;
import com.qkzz.chat.dao.WorldChatContentDao;
import com.qkzz.common.Constant;
import com.qkzz.game.bean.GameInfo;
import com.qkzz.game.service.GameInfoService;
import com.qkzz.global.DBConn;

public class WorldChatContentDaoImpl implements WorldChatContentDao {

	public static String dbchat = Constant.dbchat;

	/**
	 * 向聊天总表中添加内容
	 * @param content
	 * @return
	 */
	public int addContent(Content content) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("insert into t_chat_world_content(channelid,fromuid,fromname,destuid,destname,content,tktype,attime,gameid) values(?,?,?,?,?,?,?,?,?)").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
	    	ps.setInt(1, content.getChannelid());
	    	ps.setLong(2, content.getFromuid());
	    	ps.setString(3, content.getFromname());
	    	ps.setLong(4, content.getDestuid());
	    	ps.setString(5, content.getDestname());
	    	ps.setString(6, content.getContent());
	    	ps.setInt(7, content.getTktype());
	    	ps.setLong(8, System.currentTimeMillis());
	    	ps.setInt(9, content.getGameid());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;		
	}

	
	
	/**
	 * 某个时间段内的聊天总列表
	 * @param freshInterval ：时间间隔，单位：秒
	 * @return
	 */
	public List<Content> getAllList(int freshInterval) {
		List<Content> ret = new ArrayList<Content>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from t_chat_world_content where attime>? order by attime desc").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
			ps.setLong(1, System.currentTimeMillis()-freshInterval*1000L);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Content c = new Content();
				c.setChannelid(rs.getInt("channelid"));
				c.setFromuid(rs.getLong("fromuid"));
				c.setFromname(rs.getString("fromname"));
				c.setDestuid(rs.getLong("destuid"));
				c.setDestname(rs.getString("destname"));
				c.setContent(rs.getString("content"));
				c.setTktype(rs.getInt("tktype"));
				c.setAttime(rs.getLong("attime"));
				int gid = rs.getInt("gameid");
				c.setGameid(gid);
				GameInfo game = GameInfoService.getGame(gid);
				if(game != null) {
					c.setGame(game.getName());
					c.setGurl(game.getUrl());
					c.setShowlink(game.getShowlink());
				}
				ret.add(c);
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		if(ret.isEmpty()) {
			return null;
		}
		return ret;
	}

	
	/**
	 * 清理频道过期数据
	 * @param freshInterval
	 * @return
	 */
	public int clearOutOfDate(int freshInterval) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("delete from t_chat_world_content where attime<?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
			ps.setLong(1, System.currentTimeMillis()-freshInterval*1000L);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

}
