package com.qkzz.friend.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.common.Constant;
import com.qkzz.friend.bean.FriendGroup;
import com.qkzz.friend.dao.FriendGroupDao;
import com.qkzz.global.DBConn;

public class FriendGroupDaoImpl implements FriendGroupDao {
	public static String dbim = Constant.dbim;

	/**
	 * 添加分组
	 * @param uid
	 * @param groupName
	 * @return
	 */
	public int addGroup(long uid,String groupName) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("insert into friend_group").append(uid&0xff).append("(uid, groupname, createtime) values(?,?,now())").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setLong(1, uid);
	    	ps.setString(2, groupName);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}
	
	/**
	 * 增加默认分组
	 * 可以在用户注册的时候添加，也可以在读取列表的时候添加
	 * @param uid
	 * @return
	 */
	public int addDefaultGroup(long uid,String groupName) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("insert into friend_group").append(uid&0xff).append("(uid, groupname, isdefault,createtime) values(?,?,1,now())").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setLong(1, uid);
	    	ps.setString(2, groupName);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	/**
	 * 获取默认分组ID
	 * @param uid
	 * @return
	 */
	public long getDefaultGroupID(long uid) {
		long ret = -1;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select id from friend_group").append(uid&0xff).append(" where uid=? and isdefault=1").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
			ps.setLong(1, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ret = rs.getLong("id");
			} else {
				this.addDefaultGroup(uid, "好友");
				ret = this.getLastInsertGroupID(uid);
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
	 * 删除好友分组
	 * @param uid
	 * @param groupID
	 * @return
	 */
	public int delGroup(long uid,long groupID) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("delete from friend_group").append(uid&0xff).append(" where id=? and uid=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setLong(1, groupID);
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
	 * 编辑分组
	 * @param uid
	 * @param groupid
	 * @param newGroupName
	 * @return
	 */
	public int editGroup(long uid,long groupid,String newGroupName) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("update friend_group").append(uid&0xff).append(" set groupname=? where id=? and uid=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setString(1, newGroupName);
	    	ps.setLong(2, groupid);
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
	 * 获取用户好友分组列表
	 * @param uid
	 * @return
	 */
	public List<FriendGroup> getGroupList(long uid) {
		List<FriendGroup> ret = new ArrayList<FriendGroup>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from friend_group").append(uid&0xff).append(" where uid=? order by id").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
			ps.setLong(1, uid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				FriendGroup bean = new FriendGroup();
				bean.setId(rs.getLong("id"));
				bean.setGroupname(rs.getString("groupname"));
				bean.setUid(rs.getLong("uid"));
				bean.setIsdefault(rs.getInt("isdefault"));
				bean.setCreatetime(rs.getString("createtime"));
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
	 * 获取分组信息
	 * @param groupid
	 * @return
	 */
	public FriendGroup getGroup(long uid,long groupid) {
		FriendGroup bean = null;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from friend_group").append(uid&0xff).append(" where uid=? and id=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
			ps.setLong(1, uid);
			ps.setLong(2, groupid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				bean = new FriendGroup();
				bean.setId(rs.getLong("id"));
				bean.setGroupname(rs.getString("groupname"));
				bean.setUid(rs.getLong("uid"));
				bean.setIsdefault(rs.getInt("isdefault"));
				bean.setCreatetime(rs.getString("createtime"));
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return bean;
	}

	
	/**
	 * 获取用户最新添加的分组ID
	 * @param uid
	 * @return
	 */
	public long getLastInsertGroupID(long uid) {
		long ret = -1;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select id from friend_group").append(uid&0xff).append(" where uid=? order by id desc limit 1").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
			ps.setLong(1, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ret = rs.getLong("id");
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
	 * 分组名称是否存在
	 * @param uid
	 * @param groupName
	 * @return
	 */
	public boolean isGroupNameExist(long uid,String groupName) {
		boolean ret = false;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select id from friend_group").append(uid&0xff).append(" where uid=? and groupname=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
			ps.setLong(1, uid);
			ps.setString(2, groupName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ret = true;
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
	 * 是否为我的好友分组
	 * @param uid
	 * @param groupid
	 * @return
	 */
	public boolean isMyFriendGroup(long uid,long groupid) {
		boolean ret = false;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select id from friend_group").append(uid&0xff).append(" where id=? and uid=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
			ps.setLong(1, groupid);
			ps.setLong(2, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ret = true;
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
	 * 获取用户已经添加的好友分组数量
	 * @param uid
	 * @return
	 */
	public int getTotalGroupNum(long uid) {
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select count(*) from friend_group").append(uid&0xff).append(" where uid=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
			ps.setLong(1, uid);
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

}
