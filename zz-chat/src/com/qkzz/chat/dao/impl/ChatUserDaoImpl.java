package com.qkzz.chat.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qkzz.chat.bean.ChatUser;
import com.qkzz.chat.dao.ChatUserDao;
import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;

public class ChatUserDaoImpl implements ChatUserDao {

	public static String dbchat = Constant.dbchat;

	/**
	 * 获取聊天频道中用户的信息
	 * @param uid
	 * @return
	 */
	public ChatUser getChatUserInfo(long uid) {
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbchat,"select * from t_chat_user where uid=?");
			ps.setLong(1, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ChatUser user = new ChatUser();
				user.setUid(uid);
				user.setNickname(rs.getString("nickname"));
				user.setStatus(rs.getInt("status"));
				user.setUpdatetime(rs.getString("updatetime"));
				user.setLasttime(rs.getLong("lasttime"));
				rs.close();
				return user;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return null;
	}

	/**
	 * 新用户第一次使用聊天频道时，加入到该表中
	 * @param uid
	 * @param nickname
	 * @return
	 */
	public int addChatUser(long uid,String nickname) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "insert into t_chat_user(uid,nickname,updatetime) values(?,?,now())";
	    	PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
	    	ps.setLong(1, uid);
	    	ps.setString(2, nickname);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;		
	}

	/**
	 * 判断用户是否已经存在于聊天用户表中
	 * @param uid
	 * @return
	 */
	public boolean isChatUserExist(long uid) {
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbchat,"select * from t_chat_user where uid=?");
			ps.setLong(1, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				rs.close();
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return false;
	}

	/**
	 * 更新用户信息，主要是用户昵称的保存更新
	 * @param uid
	 * @param nickname
	 * @return
	 */
	public int updateChatUserInfo(long uid,String nickname) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "update t_chat_user set nickname=?,updatetime=now(),lasttime=? where uid=?";
	    	PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
	    	ps.setString(1, nickname);
	    	ps.setLong(2, System.currentTimeMillis());
	    	ps.setLong(3, uid);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;		
	}
	
	/**
	 * 更新用户最后聊天时间
	 * @param uid
	 * @return
	 */
	public int updateLastTime(long uid) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "update t_chat_user set lasttime=? where uid=?";
	    	PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
	    	ps.setLong(1, System.currentTimeMillis());
	    	ps.setLong(2, uid);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;		
	}

}
