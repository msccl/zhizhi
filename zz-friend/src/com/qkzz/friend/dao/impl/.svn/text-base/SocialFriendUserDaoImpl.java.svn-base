package com.qkzz.friend.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.common.Constant;
import com.qkzz.friend.bean.SocialFriendUser;
import com.qkzz.friend.bean.SocialFriendUserListInfo;
import com.qkzz.friend.dao.SocialFriendUserDao;
import com.qkzz.global.DBConn;

public class SocialFriendUserDaoImpl implements SocialFriendUserDao {

	public static String dbfriend = Constant.dbfriend;

	@Override
	public int add(SocialFriendUser obj) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("insert into social_frienduser").append(obj.getUid()&0xff).append("(uid, fuid, fname,face,sex,birth,domain,createtime) values(?,?,?,?,?,?,?,now())").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbfriend,sql);
	    	ps.setLong(1, obj.getUid());
	    	ps.setString(2, obj.getFuid());
	    	ps.setString(3, obj.getFname());
	    	ps.setString(4, obj.getFace());
	    	ps.setInt(5, obj.getSex());
	    	ps.setString(6, obj.getBirth());
	    	ps.setString(7, obj.getDomain());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	@Override
	public int delete(long uid, String fuid, String domain) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("delete from social_frienduser").append(uid&0xff).append(" where uid=? and fuid=? and domain=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbfriend,sql);
	    	ps.setLong(1, uid);
	    	ps.setString(2, fuid);
	    	ps.setString(3, domain);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	@Override
	public List<SocialFriendUserListInfo> getList(long uid, String domain) {
		List<SocialFriendUserListInfo> ret = new ArrayList<SocialFriendUserListInfo>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from social_frienduser").append(uid&0xff).append(" where uid=? and domain=? order by createtime desc").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbfriend,sql);
			ps.setLong(1, uid);
			ps.setString(2, domain);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SocialFriendUserListInfo obj = new SocialFriendUserListInfo();
	        	obj.setUid(rs.getString("fuid"));
	        	obj.setName(rs.getString("fname"));
	        	obj.setFace(rs.getString("face"));
	        	obj.setSex(rs.getInt("sex"));
	        	obj.setBirth(rs.getString("birth"));
        		obj.setIsonline(0);

        		obj.setGameid(0);
        		obj.setGamename("");
        		obj.setGameurl("");
        		
        		obj.setFriendtype(1);
        		obj.setIsactive(0);
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

	@Override
	public SocialFriendUser getSocialFriendUser(long uid, String fuid,
			String domain) {
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from social_frienduser").append(uid&0xff).append(" where uid=? and fuid=? and domain=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbfriend,sql);
	    	ps.setLong(1, uid);
	    	ps.setString(2, fuid);
	    	ps.setString(3, domain);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				SocialFriendUser obj = new SocialFriendUser();
	        	obj.setUid(rs.getInt("uid"));
	        	obj.setFuid(rs.getString("fuid"));
	        	obj.setFname(rs.getString("fname"));
	        	obj.setFace(rs.getString("face"));
	        	obj.setSex(rs.getInt("sex"));
	        	obj.setBirth(rs.getString("birth"));
	        	obj.setDomain(rs.getString("domain"));
	        	obj.setCreatetime(rs.getString("createtime"));
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

}
