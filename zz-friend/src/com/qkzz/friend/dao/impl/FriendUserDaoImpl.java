package com.qkzz.friend.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.common.Constant;
import com.qkzz.friend.bean.FriendUser;
import com.qkzz.friend.bean.FriendUserListInfo;
import com.qkzz.friend.bean.SocialFriendUserListInfo;
import com.qkzz.friend.dao.FriendUserDao;
import com.qkzz.friend.service.GameActiveService;
import com.qkzz.game.bean.GameInfo;
import com.qkzz.game.service.GameInfoService;
import com.qkzz.global.DBConn;
import com.qkzz.user.bean.PlayLog;
import com.qkzz.user.bean.User;
import com.qkzz.user.service.PlayLogService;
import com.qkzz.user.service.UserService;

public class FriendUserDaoImpl implements FriendUserDao {
	public static String dbim = Constant.dbim;
	
	public FriendUser getByFriendUser(long uid, long fuid){
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from frienduser").append(uid&0xff).append(" where uid=? and fuid=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setLong(1, uid);
	    	ps.setLong(2, fuid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
	        	FriendUser obj = new FriendUser();
	        	obj.setUid(rs.getInt("uid"));
	        	obj.setFuid(rs.getInt("fuid"));
	        	obj.setFname(rs.getString("fname"));
	        	obj.setGroupid(rs.getLong("groupid"));
	        	obj.setCreatetime(rs.getString("createtime"));
	        	obj.setRemark(rs.getString("remark"));
	        	
//	        	//获取分组名称
//	        	String groupName = "好友";
//	        	FriendGroup fg = new FriendGroupDaoImpl().getGroup(uid, rs.getLong("groupid"));
//	        	if(fg != null) {
//	        		groupName = fg.getGroupname();
//	        	}
//	        	
//	        	obj.setGroupname(groupName);
	        	
	            return obj;
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return null;		
	}
	
	public int countByList(long uid) {
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select count(*) from frienduser").append(uid&0xff).append(" where uid=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
			ps.setLong(1, uid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
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

	public List<FriendUser> getByList(long uid, int first, int max) {
		List<FriendUser> ret = new ArrayList<FriendUser>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from frienduser").append(uid&0xff).append(" where uid=? order by createtime desc limit ?,?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
			ps.setLong(1, uid);
			ps.setInt(2, first);
			ps.setInt(3, max);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
	        	FriendUser obj = new FriendUser();
	        	obj.setUid(rs.getLong("uid"));
	        	obj.setFuid(rs.getLong("fuid"));
	        	obj.setFname(rs.getString("fname"));
	        	obj.setGroupid(rs.getLong("groupid"));
	        	obj.setCreatetime(rs.getString("createtime"));
	        	obj.setRemark(rs.getString("remark"));
				ret.add(obj);
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
	 * 获取好友列表
	 * 完整信息
	 * @param uid
	 * @param first
	 * @param max
	 * @return
	 */
	public List<FriendUserListInfo> getFriendList(long uid, int first, int max) {
		List<FriendUserListInfo> ret = new ArrayList<FriendUserListInfo>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from frienduser").append(uid&0xff).append(" where uid=? order by createtime desc limit ?,?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
			ps.setLong(1, uid);
			ps.setInt(2, first);
			ps.setInt(3, max);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				FriendUserListInfo obj = new FriendUserListInfo();

	        	User user = UserService.getByUid(rs.getLong("fuid"));
	        	if(user != null) {
	        		obj.setUid(rs.getLong("fuid"));
	        		obj.setName(user.getName());
	        		obj.setGroupid(rs.getLong("groupid"));
	        		obj.setSex(user.getSex());
	        		obj.setIsonline(user.getIsonline());
	        		obj.setMood(user.getMood());
	        		obj.setFaceurl(user.getFaceurl());
	        		obj.setRemark(rs.getString("remark"));
	        		//获取用户正在玩，或者最后玩过的游戏信息
	        		PlayLog log = PlayLogService.getLog(rs.getLong("fuid"));
	        		if(log != null) {
		        		GameInfo game = GameInfoService.getGame(log.getGameid());
		        		if(game != null) {
			        		obj.setGameid(game.getId());
			        		obj.setGamename(game.getName());
			        		obj.setGameurl(game.getUrl());
		        		} else {
			        		obj.setGameid(0);
			        		obj.setGamename("");
			        		obj.setGameurl("");
		        		}
	        		} else {
		        		obj.setGameid(0);
		        		obj.setGamename("");
		        		obj.setGameurl("");
	        		}
	        		ret.add(obj);
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

	
	public boolean isFriendUser(long uid, long fuid){
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from frienduser").append(uid&0xff).append(" where uid=? and fuid=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setLong(1, uid);
	    	ps.setLong(2, fuid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return false;
	}

	public int save(FriendUser obj) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("insert into frienduser").append(obj.getUid()&0xff).append("(uid, fuid, fname,groupid, createtime) values(?,?,?,?,now())").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setLong(1, obj.getUid());
	    	ps.setLong(2, obj.getFuid());
	    	ps.setString(3, obj.getFname());
	    	ps.setLong(4, obj.getGroupid());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public int delete(long uid, long fuid) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("delete from frienduser").append(uid&0xff).append(" where uid=? and fuid=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setLong(1, uid);
	    	ps.setLong(2, fuid);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}
	
	
	/**
	 * 将好友移动到新的分组
	 * @param uid
	 * @param friendUid
	 * @param groupid
	 * @return
	 */
	public int changeGroup(long uid,long friendUid,long groupid) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("update frienduser").append(uid&0xff).append(" set groupid=? where uid=? and fuid=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setLong(1, groupid);
	    	ps.setLong(2, uid);
	    	ps.setLong(3, friendUid);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}
			
	/**
	 * 将某个分组下的所有用户都转移到默认分组
	 * @param uid
	 * @param groupid
	 * @return
	 */
	public int changeGroupToDefault(long uid,long groupid,long defaultGroupID) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("update frienduser").append(uid&0xff).append(" set groupid=? where uid=? and groupid=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setLong(1, defaultGroupID);
	    	ps.setLong(2, uid);
	    	ps.setLong(3, groupid);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	@Override
	public int changeRemark(long uid, long friendUid, String newRemark) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("update frienduser").append(uid&0xff).append(" set remark=? where uid=? and fuid=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setString(1, newRemark);
	    	ps.setLong(2, uid);
	    	ps.setLong(3, friendUid);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	@Override
	public List<SocialFriendUserListInfo> getSocialFriendList(long uid, int gameid) {
		List<SocialFriendUserListInfo> ret = new ArrayList<SocialFriendUserListInfo>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from frienduser").append(uid&0xff).append(" where uid=? order by createtime desc").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
			ps.setLong(1, uid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SocialFriendUserListInfo obj = new SocialFriendUserListInfo();

	        	User user = UserService.getByUid(rs.getLong("fuid"));
	        	if(user != null) {
	        		obj.setUid(rs.getString("fuid"));
	        		obj.setName(rs.getString("remark").equals("")?user.getName():rs.getString("remark"));
	        		obj.setFace(user.getFaceurl());
	        		obj.setSex(user.getSex());
	        		obj.setBirth(user.getBirth());
	        		obj.setIsonline(user.getIsonline());
	        		//获取用户正在玩，或者最后玩过的游戏信息
	        		PlayLog log = PlayLogService.getLog(rs.getLong("fuid"));
	        		if(log != null) {
		        		GameInfo game = GameInfoService.getGame(log.getGameid());
		        		if(game != null) {
			        		obj.setGameid(game.getId());
			        		obj.setGamename(game.getName());
			        		obj.setGameurl(game.getUrl());
		        		} else {
			        		obj.setGameid(0);
			        		obj.setGamename("");
			        		obj.setGameurl("");
		        		}
	        		} else {
		        		obj.setGameid(0);
		        		obj.setGamename("");
		        		obj.setGameurl("");
	        		}
	        		obj.setFriendtype(0);
	        		if(user.getDomain() == null || user.getDomain().equals("")) {
		        		obj.setIsactive(0);
	        		} else {
	        			obj.setIsactive(GameActiveService.isActiveGame(user.getDomainuid(), user.getDomain(), gameid)?1:0);
	        		}
	        		ret.add(obj);
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

	
}
