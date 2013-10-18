package com.qkzz.chat.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.chat.bean.ChatTeamMember;
import com.qkzz.chat.dao.ChatTeamMemberDao;
import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;

public class ChatTeamMemberDaoImpl implements ChatTeamMemberDao {
	
	public static String dbchat = Constant.dbchat;

	/**
	 * 加入队伍
	 * @param bean
	 * @return
	 */
	public int joinTeam(ChatTeamMember bean) {
		DBConn conn = new DBConn();
	    try {
			String sql = new StringBuffer("insert into t_chat_team_member").append(bean.getTeamid()&0x0f).append("(uid,name,teamid,jointime) values(?,?,?,now())").toString();
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
			String sql = new StringBuffer("delete from t_chat_team_member").append(teamid&0x0f).append(" where teamid=? and uid=?").toString();
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
			String sql = new StringBuffer("delete from t_chat_team_member").append(teamid&0x0f).append(" where teamid=?").toString();
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
	 * 获取队伍成员数量
	 * @param teamid
	 * @return
	 */
	public int getTeamMemberNum(long teamid) {
		int num = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select count(*) from t_chat_team_member").append(teamid&0x0f).append(" where teamid=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
			ps.setLong(1, teamid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return num;
	}

	
	/**
	 * 是否是队伍成员
	 * @param teamid
	 * @param uid
	 * @return
	 */
	public boolean isTeamMember(long teamid,long uid) {
		boolean ret = false;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select uid from t_chat_team_member").append(teamid&0x0f).append(" where teamid=? and uid=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
			ps.setLong(1, teamid);
			ps.setLong(2, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ret = true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return ret;
	}
	
	/**
	 * 获取队伍成员列表
	 * @param teamid
	 * @return
	 */
	public List<ChatTeamMember> getMemberList(long teamid) {
		List<ChatTeamMember> ret = new ArrayList<ChatTeamMember>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from t_chat_team_member").append(teamid&0x0f).append(" where teamid=? order by jointime").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
			ps.setLong(1, teamid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ChatTeamMember bean = new ChatTeamMember();
				bean.setUid(rs.getLong("uid"));
				bean.setName(rs.getString("name"));
				bean.setTeamid(teamid);
				bean.setJointime(rs.getString("jointime"));
				ret.add(bean);
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
	
}
