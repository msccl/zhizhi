package com.qkzz.chat.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.qkzz.chat.bean.ChatTeamTempMember;
import com.qkzz.chat.dao.ChatTeamTempMemberDao;
import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;

public class ChatTeamTempMemberDaoImpl implements ChatTeamTempMemberDao {

	public static String dbchat = Constant.dbchat;

	/**
	 * 添加临时用到列表中
	 */
	public int addTempMember(ChatTeamTempMember bean) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "insert into t_chat_team_temp_member(uid,name,teamid,attime,type,status) values(?,?,?,now(),?,0)";
	    	PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
			ps.setLong(1, bean.getUid());
			ps.setString(2, bean.getName());
			ps.setLong(3, bean.getTeamid());
			ps.setInt(4, bean.getType());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	/**
	 * 自己申请加入的成员列表，罗列给队伍的队长查看
	 */
	public List<ChatTeamTempMember> getApplyList(long teamid) {
		List<ChatTeamTempMember> ret = new ArrayList<ChatTeamTempMember>();
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbchat,"select * from t_chat_team_temp_member where teamid=? and status=0 and type=1");
			ps.setLong(1, teamid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ChatTeamTempMember c = new ChatTeamTempMember();
				c.setUid(rs.getLong("uid"));
				c.setName(rs.getString("name"));
				c.setType(rs.getInt("type"));
				c.setStatus(rs.getInt("status"));
				c.setAttime(rs.getString("attime"));
				c.setTeamid(rs.getLong("teamid"));
				rs.close();
				ret.add(c);
			}
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
	 * 获取邀请列表
	 */
	public List<ChatTeamTempMember> getInviteList(long uid) {
		List<ChatTeamTempMember> ret = new ArrayList<ChatTeamTempMember>();
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbchat,"select * from t_chat_team_temp_member where uid=? and status=0 and type=0");
			ps.setLong(1, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ChatTeamTempMember c = new ChatTeamTempMember();
				c.setUid(rs.getLong("uid"));
				c.setName(rs.getString("name"));
				c.setType(rs.getInt("type"));
				c.setStatus(rs.getInt("status"));
				c.setAttime(rs.getString("attime"));
				c.setTeamid(rs.getLong("teamid"));
				rs.close();
				ret.add(c);
			}
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
	 * 正式加入后需要更新临时用户记录状态
	 */
	public int updateStatus(long uid, long teamid, int status) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "update t_chat_team_temp_member set status=? where uid=? and teamid=? and status=0";
	    	PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
			ps.setInt(1, status);
			ps.setLong(2, uid);
			ps.setLong(3, teamid);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public ChatTeamTempMember getTempMember(long uid,long teamid) {
		ChatTeamTempMember c = null;
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbchat,"select * from t_chat_team_temp_member where uid=? and teamid=? and status=0");
			ps.setLong(1, uid);
			ps.setLong(2, teamid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				c = new ChatTeamTempMember();
				c.setUid(rs.getLong("uid"));
				c.setName(rs.getString("name"));
				c.setType(rs.getInt("type"));
				c.setStatus(rs.getInt("status"));
				c.setAttime(rs.getString("attime"));
				c.setTeamid(rs.getLong("teamid"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return c;
	}

	public ConcurrentHashMap<Long, Long> getTeamTempListByTeamID() {
		ConcurrentHashMap<Long, Long> ret = new ConcurrentHashMap<Long, Long>();
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbchat,"select teamid from t_chat_team_temp_member where status=0 and type=1 group by teamid");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				long teamid = rs.getLong("teamid");
				ret.put(teamid, teamid);
			}
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

	public ConcurrentHashMap<Long, Long> getTeamTempListByUID() {
		ConcurrentHashMap<Long, Long> ret = new ConcurrentHashMap<Long, Long>();
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbchat,"select uid from t_chat_team_temp_member where status=0 and type=0 group by uid");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				long uid = rs.getLong("uid");
				ret.put(uid, uid);
			}
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
