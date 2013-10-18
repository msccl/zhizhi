package com.qkzz.chat.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.chat.bean.Content;
import com.qkzz.chat.dao.ChannelContentDao;
import com.qkzz.common.Constant;
import com.qkzz.game.bean.GameInfo;
import com.qkzz.game.service.GameInfoService;
import com.qkzz.global.DBConn;

public class ChannelContentDaoImpl implements ChannelContentDao {

	public static String dbchat = Constant.dbchat;

	/**
	 * 添加聊天内容
	 * @param content
	 */
	public int addContent(Content content) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("insert into t_chat_channel_content").
	    	append(content.getGameid()&0xff).
	    	append("(channelid,fromuid,fromname,destuid,destname,content,tktype,attime,gameid) values(?,?,?,?,?,?,?,?,?)").toString();
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
	
//	/**
//	 * 获取某个频道的聊天内容列表，可分页
//	 * @param channelid
//	 * @param freshInterval ：时间间隔，单位：秒
//	 * @param startIndex
//	 * @param number
//	 * @return
//	 */
//	public List<Content> getList(int channelid,int freshInterval,int startIndex,int number) {
//		List<Content> ret = new ArrayList<Content>();
//		DBConn conn = new DBConn();
//		try {
//			String sql = new StringBuffer("select * from t_chat_channel_content").append(channelid&0xff).append(" where channelid=? and attime>? order by attime asc limit ?,?").toString();
//			PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
//			ps.setInt(1, channelid);
//			ps.setLong(2, System.currentTimeMillis()-freshInterval*1000L);
//			ps.setInt(3, startIndex);
//			ps.setInt(4, number);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				Content c = new Content();
//				c.setFromuid(rs.getLong("fromuid"));
//				c.setFromname(rs.getString("fromname"));
//				c.setDestuid(rs.getLong("destuid"));
//				c.setDestname(rs.getString("destname"));
//				c.setChannelid(rs.getInt("channelid"));
//				c.setContent(rs.getString("content"));
//				c.setGameid(rs.getInt("gameid"));
//				c.setTktype(rs.getInt("tktype"));
//				c.setAttime(rs.getLong("attime"));
//				ret.add(c);
//			}
//			rs.close();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		} finally {
//			conn.closeStmt();
//		}
//		if(ret.isEmpty()) {
//			return null;
//		}
//		return ret;
//	}
	
//	/**
//	 * 某个时间段内的聊天内容总量
//	 * @param channelid
//	 * @param freshInterval ：时间间隔，单位：秒
//	 * @return
//	 */
//	public int getMaxCount(int channelid,int freshInterval) {
//		int ret = 0;
//		DBConn conn = new DBConn();
//		try {
//			String sql = new StringBuffer("select count(*) from t_chat_channel_content").append(channelid&0xff).append(" where channelid=? and attime>?").toString();
//			PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
//			ps.setInt(1, channelid);
//			ps.setLong(2, System.currentTimeMillis()-freshInterval*1000L);
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				ret = rs.getInt(1);
//			}
//			rs.close();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		} finally {
//			conn.closeStmt();
//		}
//		return ret;
//	}
	
//	/**
//	 * 某个时间段内的聊天总列表
//	 * @param channel
//	 * @param freshInterval ：时间间隔，单位：秒
//	 * @return
//	 */
//	public List<Content> getAllList(int channelid,int freshInterval) {
//		List<Content> ret = new ArrayList<Content>();
//		DBConn conn = new DBConn();
//		try {
//			String sql = new StringBuffer("select * from t_chat_channel_content").append(channelid&0xff).append(" where channelid=? and attime>? order by attime asc").toString();
//			PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
//			ps.setInt(1, channelid);
//			ps.setLong(2, System.currentTimeMillis()-freshInterval*1000L);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				Content c = new Content();
//				c.setFromuid(rs.getLong("fromuid"));
//				c.setFromname(rs.getString("fromname"));
//				c.setDestuid(rs.getLong("destuid"));
//				c.setDestname(rs.getString("destname"));
//				c.setChannelid(rs.getInt("channelid"));
//				c.setContent(rs.getString("content"));
//				c.setTktype(rs.getInt("tktype"));
//				c.setGameid(rs.getInt("gameid"));
//				c.setAttime(rs.getLong("attime"));
//				ret.add(c);
//			}
//			rs.close();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		} finally {
//			conn.closeStmt();
//		}
//		if(ret.isEmpty()) {
//			return null;
//		}
//		return ret;
//	}
	

	/**
	 * 某个时间段内的当前游戏聊天总列表
	 * @param channel
	 * @param freshInterval ：时间间隔，单位：秒
	 * @return
	 */
	public List<Content> getAllList(int channelid,int gameid,int freshInterval) {
		List<Content> ret = new ArrayList<Content>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from t_chat_channel_content").append(gameid&0xff).append(" where channelid=? and gameid=? and attime>? order by attime desc").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
			ps.setInt(1, channelid);
			ps.setInt(2, gameid);
			ps.setLong(3, System.currentTimeMillis()-freshInterval*1000L);
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
	 * @param tableID
	 * @param freshInterval
	 * @return
	 */
	public int clearOutOfDate(int tableID,int freshInterval) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("delete from t_chat_channel_content").
	    	append(tableID).
	    	append(" where attime<?").toString();
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
