package com.qkzz.chat.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qkzz.chat.bean.ChatTeamMember;
import com.qkzz.chat.dao.ChatTeamMemberAllDao;
import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;

public class ChatTeamMemberAllDaoImpl implements ChatTeamMemberAllDao {
	
	public static String dbchat = Constant.dbchat;

	/**
	 * 加入队伍
	 * @param bean
	 * @return
	 */
	public int joinTeam(ChatTeamMember bean) {
		DBConn conn = new DBConn();
	    try {
			String sql = new StringBuffer("insert into t_chat_team_member_all(uid,name,teamid,jointime) values(?,?,?,now())").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
	    	ps.setLong(1, bean.getUid());
			ps.setString(2, bean.getName());
			ps.setLong(3, bean.getTeamid());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}
	
	/**
	 * 退出队伍
	 * @param teamid
	 * @param uid
	 * @return
	 */
	public int quitTeam(long teamid,long uid) {
		DBConn conn = new DBConn();
	    try {
			String sql = new StringBuffer("delete from t_chat_team_member_all where teamid=? and uid=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
	    	ps.setLong(1, teamid);
			ps.setLong(2, uid);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}
	
	
	/**
	 * 解散队伍时将队伍中的成员记录全部删除
	 * @param teamid
	 * @return
	 */
	public int dismissTeam(long teamid) {
		DBConn conn = new DBConn();
	    try {
			String sql = new StringBuffer("delete from t_chat_team_member_all where teamid=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
	    	ps.setLong(1, teamid);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}
	
	/**
	 * 通过用户UID获取队伍信息
	 * @param uid
	 * @return
	 */
	public long getTeamidByUID(long uid) {
		long ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select teamid from t_chat_team_member_all where uid=?").toString();
			System.out.println(sql+"===uid:"+uid);
			PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
			ps.setLong(1, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("teamid==="+rs.getLong("teamid"));
				return rs.getLong("teamid");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		System.out.println("555555555555");
		return ret;
	}

}
