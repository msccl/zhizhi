package com.qkzz.chat.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qkzz.chat.bean.ChatContentLog;
import com.qkzz.chat.dao.ChatContentLogDao;
import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;

public class ChatContentLogDaoImpl implements ChatContentLogDao {

	public static String dbchat = Constant.dbchat;

	public int addLog(ChatContentLog log) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "insert into t_chat_content_log(channelid,fromuid,fromname,destuid,destname,content,gameid,attime,ip) values(?,?,?,?,?,?,?,?,?)";
	    	PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
	    	ps.setInt(1, log.getChannelid());
			ps.setLong(2, log.getFromuid());
			ps.setString(3, log.getFromname());
			ps.setLong(4, log.getDestuid());
			ps.setString(5, log.getDestname());
			ps.setString(6, log.getContent());
			ps.setInt(7, log.getGameid());
			ps.setLong(8, log.getAttime());
			ps.setString(9, log.getIp());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public ChatContentLog getLatestLog(long uid) {
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbchat,"select * from t_chat_content_log where fromuid=? order by attime desc limit 1");
			ps.setLong(1, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ChatContentLog c = new ChatContentLog();
				c.setChannelid(rs.getInt("channelid"));
				c.setFromuid(rs.getLong("fromuid"));
				c.setDestuid(rs.getLong("destuid"));
				c.setDestname(rs.getString("destname"));
				c.setGameid(rs.getInt("gameid"));
				c.setContent(rs.getString("content"));
				c.setAttime(rs.getLong("attime"));
				c.setIp(rs.getString("ip"));
				rs.close();
				return c;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return null;
	}

}
