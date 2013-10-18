package com.qkzz.chat.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.chat.bean.ChatTeam;
import com.qkzz.chat.bean.TeamFreshBean;
import com.qkzz.chat.dao.ChatTeamDao;
import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;

public class ChatTeamDaoImpl implements ChatTeamDao {

	public static String dbchat = Constant.dbchat;

	/**
	 * 通过队伍ID获取队伍信息
	 * @param teamid
	 * @return
	 */
	public ChatTeam getTeam(long teamid) {
		ChatTeam c = null;
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbchat,"select * from t_chat_team where id=?");
			ps.setLong(1, teamid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				c = new ChatTeam();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setLeaderuid(rs.getLong("leaderuid"));
				c.setPassword(rs.getString("password"));
				c.setGameid(rs.getInt("gameid"));
				c.setCreatetime(rs.getString("createtime"));
				c.setLasttime(rs.getLong("lasttime"));
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return c;
	}
	
	
	/**
	 * 创建队伍
	 * @param bean
	 * @return
	 */
	public int addTeam(ChatTeam bean) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "insert into t_chat_team(name,leaderuid,password,createtime,lasttime,gameid) values(?,?,?,now(),?,?)";
	    	PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
			ps.setString(1, bean.getName());
			ps.setLong(2, bean.getLeaderuid());
			ps.setString(3, bean.getPassword());
			ps.setLong(4, bean.getLasttime());
			ps.setInt(5, bean.getGameid());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}
	
	/**
	 * 删除队伍记录
	 * @param teamid
	 * @return
	 */
	public int delTeam(long teamid) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "delete from t_chat_team where id=?";
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
	 * 获取用户创建的队伍ID
	 * @param uid
	 * @return
	 */
	public long getLastInsertID(long uid) {
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbchat,"select id from t_chat_team where leaderuid=? order by id desc limit 1");
			ps.setLong(1, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ret = rs.getInt("id");
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
	 * 设置新的队长
	 * @param teamid
	 * @param newUid
	 * @return
	 */
	public int setNewTeamLeader(long teamid,long newUid) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "update t_chat_team set leaderuid=? where id=?";
	    	PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
	    	ps.setLong(1, newUid);
			ps.setLong(2, teamid);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	
	/**
	 * 通过游戏ID获取频道ID
	 * @param gameid
	 * @return
	 */
	public long getTeamIDByGameID(int gameid) {
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbchat,"select id from t_chat_team where gameid=?");
			ps.setInt(1, gameid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ret = rs.getInt("id");
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
	 * 从内存表中获取队伍刷新列表
	 * @return
	 */
	public List<TeamFreshBean> getFreshList() {
		List<TeamFreshBean> ret = new ArrayList<TeamFreshBean>();
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbchat,"select * from t_chat_team_freshlist order by lasttime desc");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TeamFreshBean bean = new TeamFreshBean();
				bean.setGameid(rs.getInt("gameid"));
				bean.setTeamid(rs.getLong("teamid"));
				bean.setLasttime(rs.getLong("lasttime"));
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

	/**
	 * 添加队伍刷新标记记录
	 * 主要用于线程刷新使用
	 * @param gameid
	 * @param teamid
	 * @return
	 */
	public int addFreshRecord(int gameid,long teamid) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "replace into t_chat_team_freshlist(gameid,teamid,lasttime) values(?,?,?)";
	    	PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
	    	ps.setInt(1, gameid);
			ps.setLong(2, teamid);
			ps.setLong(3, System.currentTimeMillis());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	
	/**
	 * 清理刷新内存表中过期数据
	 * @param freshInterval
	 */
	public void clearOutOfDateFreshList(int freshInterval) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("delete from t_chat_team_freshlist where lasttime<?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbchat,sql);
			ps.setLong(1, System.currentTimeMillis()-freshInterval*1000L);
	    	ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
	}

}
