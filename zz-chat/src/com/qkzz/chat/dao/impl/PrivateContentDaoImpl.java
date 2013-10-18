package com.qkzz.chat.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.chat.bean.Content;
import com.qkzz.chat.bean.PrivateContent;
import com.qkzz.chat.dao.PrivateContentDao;
import com.qkzz.common.Constant;
import com.qkzz.game.bean.GameInfo;
import com.qkzz.game.service.GameInfoService;
import com.qkzz.global.DBConn;

public class PrivateContentDaoImpl implements PrivateContentDao {

	public static String dbchat = Constant.dbchat;

	/**
	 * 添加发送者聊天内容
	 * @param content
	 */
	public int addFromContent(PrivateContent content) {
//		long tableID = ChatService.getPrivateContentTableID(content.getFromuid(), content.getDestuid());
//		String key = ChatService.getPrivateKeystr(content.getFromuid(), content.getDestuid());
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("insert into t_chat_private_content").
	    	append(content.getFromuid()&0xff).
	    	append("(uid,fromuid,fromname,destuid,destname,content,tktype,attime,channelid,gameid) values(?,?,?,?,?,?,?,?,?,?)").toString();
	    	System.out.println("====sql:"+sql);
	    	PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
	    	ps.setLong(1, content.getFromuid());
	    	ps.setLong(2, content.getFromuid());
	    	ps.setString(3, content.getFromname());
	    	ps.setLong(4, content.getDestuid());
	    	ps.setString(5, content.getDestname());
	    	ps.setString(6, content.getContent());
	    	ps.setInt(7, content.getTktype());
	    	ps.setLong(8, System.currentTimeMillis());
	    	ps.setLong(9, content.getChannelid());
	    	ps.setInt(10, content.getGameid());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;		
	}
	
	/**
	 * 添加目标用户聊天内容
	 * @param content
	 * @return
	 */
	public int addDestContent(PrivateContent content) {
//		long tableID = ChatService.getPrivateContentTableID(content.getFromuid(), content.getDestuid());
//		String key = ChatService.getPrivateKeystr(content.getFromuid(), content.getDestuid());
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("insert into t_chat_private_content").
	    	append(content.getDestuid()&0xff).
	    	append("(uid,fromuid,fromname,destuid,destname,content,tktype,attime,channelid,gameid) values(?,?,?,?,?,?,?,?,?,?)").toString();
	    	System.out.println("====sql:"+sql);
	    	PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
	    	ps.setLong(1, content.getDestuid());
	    	ps.setLong(2, content.getFromuid());
	    	ps.setString(3, content.getFromname());
	    	ps.setLong(4, content.getDestuid());
	    	ps.setString(5, content.getDestname());
	    	ps.setString(6, content.getContent());
	    	ps.setInt(7, content.getTktype());
	    	ps.setLong(8, System.currentTimeMillis());
	    	ps.setLong(9, content.getChannelid());
	    	ps.setInt(10, content.getGameid());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;		
	}

	
	/**
	 * 获取私聊频道的聊天内容列表，可分页
	 * @param fromuid
	 * @param destuid
	 * @param freshInterval ：时间间隔，单位：秒
	 * @param startIndex
	 * @param number
	 * @return
	 */
	public List<Content> getList(long uid,long lasttime,int number) {
//		long tableID = ChatService.getPrivateContentTableID(fromuid, destuid);
//		String key = ChatService.getPrivateKeystr(fromuid, destuid);

		List<Content> ret = new ArrayList<Content>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from t_chat_private_content").append(uid&0xff).append(" where uid=? and attime>? order by attime asc limit ?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
			ps.setLong(1, uid);
			ps.setLong(2, lasttime);
			ps.setInt(3, number);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if(rs.getLong("fromuid") != uid ) {
					Content c = new Content();
	//				c.setUid(rs.getLong("uid"));
					c.setFromuid(rs.getLong("fromuid"));
					c.setFromname(rs.getString("fromname"));
					c.setDestuid(rs.getLong("destuid"));
					c.setDestname(rs.getString("destname"));
					c.setContent(rs.getString("content"));
					c.setTktype(rs.getInt("tktype"));
					c.setAttime(rs.getLong("attime"));
					c.setChannelid(rs.getInt("channelid"));
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
	 * 某个时间段内的聊天内容总量
	 * @param fromuid
	 * @param destuid
	 * @param freshInterval ：时间间隔，单位：秒
	 * @return
	 */
	public int getMaxCount(long uid,long lasttime) {
//		long tableID = ChatService.getPrivateContentTableID(fromuid, destuid);
//		String key = ChatService.getPrivateKeystr(fromuid, destuid);

		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select count(*) from t_chat_private_content").append(uid&0xff).append(" where uid=? and attime>?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
			ps.setLong(1, uid);
			ps.setLong(2, lasttime);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ret = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return ret;
	}
	
	/**
	 * 某个时间段内的聊天总列表
	 * @param fromuid
	 * @param destuid
	 * @param freshInterval
	 * @return
	 */
	public List<Content> getAllList(long uid,long lasttime) {
//		long tableID = ChatService.getPrivateContentTableID(fromuid, destuid);
//		String key = ChatService.getPrivateKeystr(fromuid, destuid);
		
		List<Content> ret = new ArrayList<Content>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from t_chat_private_content").append(uid&0xff).append(" where uid=? and attime>? order by attime asc").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
			ps.setLong(1, uid);
			ps.setLong(2, lasttime);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Content c = new Content();
//				c.setUid(rs.getLong("uid"));
				c.setFromuid(rs.getLong("fromuid"));
				c.setFromname(rs.getString("fromname"));
				c.setDestuid(rs.getLong("destuid"));
				c.setDestname(rs.getString("destname"));
				c.setContent(rs.getString("content"));
				c.setTktype(rs.getInt("tktype"));
				c.setAttime(rs.getLong("attime"));
				c.setChannelid(rs.getInt("channelid"));
				c.setGameid(rs.getInt("gameid"));
				c.setGame(GameInfoService.getName(rs.getInt("gameid")));
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
	 * 清理私聊频道过期数据
	 * @param tableID
	 * @param freshInterval
	 * @return
	 */
	public int clearOutOfDate(int tableID,int freshInterval) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("delete from t_chat_private_content").
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
