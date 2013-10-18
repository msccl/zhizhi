package com.qkzz.im.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.im.bean.Inbox;
import com.qkzz.im.dao.InboxDao;

public class InboxDaoImpl implements InboxDao {
	public static String dbim = Constant.dbim;
	
	public int countByStatus(long uid){
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select count(*) from inbox").append(uid&0xff).append(" where receiveuid=? and status=0 and isdel=0").toString();
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
	
	public List<Inbox> getByStatus(long uid, int first, int max){
		List<Inbox> ret = new ArrayList<Inbox>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from inbox").append(uid&0xff).append(" where receiveuid=? and status=0 and isdel=0 order by createtime desc limit ?,?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
			ps.setLong(1, uid);
			ps.setInt(2, first);
			ps.setInt(3, max);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
	        	Inbox obj = new Inbox();
	        	obj.setId(rs.getInt("id"));
	        	obj.setSenduid(rs.getInt("senduid"));
	        	obj.setSendname(rs.getString("sendname"));
	        	obj.setReceiveuid(rs.getInt("receiveuid"));
	        	obj.setTitle(rs.getString("title"));
	        	obj.setContent(rs.getString("content"));
	        	obj.setCreatetime(rs.getString("createtime"));
	        	obj.setIsdel(rs.getInt("isdel"));
	        	obj.setHtmlcode(rs.getString("htmlcode"));
	        	obj.setStatus(rs.getInt("status"));
	        	obj.setAttachid(rs.getInt("attachid"));
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
	
	public int countByList(long uid, boolean system){
	    String sql = new StringBuffer("select count(*) from inbox").append(uid&0xff).append(" where isdel=0 and receiveuid=? and senduid<=1000").toString();
	    if(!system){
	    	sql = new StringBuffer("select count(*) from inbox").append(uid&0xff).append(" where isdel=0 and receiveuid=? and senduid>1000").toString();
	    }
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbim, sql);
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
	
	public List<Inbox> getByList(long uid, boolean system, int first, int max){
	    String sql = new StringBuffer("select * from inbox").append(uid&0xff).append(" where isdel=0 and receiveuid=? order by createtime desc limit ?,?").toString();
//	    String sql = new StringBuffer("select * from inbox").append(uid&0xff).append(" where isdel=0 and receiveuid=? and senduid<=1000 order by createtime desc limit ?,?").toString();
//	    if(!system){
//	    	sql = new StringBuffer("select * from inbox").append(uid&0xff).append(" where isdel=0 and receiveuid=? and senduid>1000 order by createtime desc limit ?,?").toString();
//	    }
	    System.out.println("sql:==="+sql+"===first:"+first+"===max:"+max);
	    List<Inbox> ret = new ArrayList<Inbox>();
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
			ps.setLong(1, uid);
			ps.setInt(2, first);
			ps.setInt(3, max);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
	        	Inbox obj = new Inbox();
	        	obj.setId(rs.getInt("id"));
	        	obj.setSenduid(rs.getInt("senduid"));
	        	obj.setSendname(rs.getString("sendname"));
	        	obj.setReceiveuid(rs.getInt("receiveuid"));
	        	obj.setTitle(rs.getString("title"));
	        	obj.setContent(rs.getString("content"));
	        	obj.setCreatetime(rs.getString("createtime"));
	        	obj.setIsdel(rs.getInt("isdel"));
	        	obj.setHtmlcode(rs.getString("htmlcode"));
	        	obj.setStatus(rs.getInt("status"));
	        	obj.setAttachid(rs.getInt("attachid"));
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

	public int save(Inbox obj){
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("insert into inbox").append(obj.getReceiveuid()&0xff).append("(senduid,sendname,receiveuid,type,title,content,htmlcode,createtime,isdel,status,attachid) values(?,?,?,?,?,?,?,?,?,?,?)").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setLong(1, obj.getSenduid());
	    	ps.setString(2, obj.getSendname());
	    	ps.setLong(3, obj.getReceiveuid());
	    	ps.setInt(4, obj.getType());
	    	ps.setString(5, obj.getTitle());
	    	ps.setString(6, obj.getContent());
	    	ps.setString(7, obj.getHtmlcode());
	    	ps.setString(8, obj.getCreatetime());
	    	ps.setInt(9, 0);
	    	ps.setInt(10, 0);
	    	ps.setInt(11, obj.getAttachid());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}
	
	public int delete(long uid, int id){
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("update inbox").append(uid&0xff).append(" set isdel=1 where receiveuid=? and id=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setLong(1, uid);
	    	ps.setInt(2, id);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}
	
	public int updateStatus(long uid, int id){
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("update inbox").append(uid&0xff).append(" set status=1 where receiveuid=? and id=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setLong(1, uid);
	    	ps.setInt(2, id);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}
	
	public Inbox getById(long uid, int id){
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from inbox").append(uid&0xff).append(" where receiveuid=? and id=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setLong(1, uid);
	    	ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
	        	Inbox obj = new Inbox();
	        	obj.setId(rs.getInt("id"));
	        	obj.setSenduid(rs.getInt("senduid"));
	        	obj.setSendname(rs.getString("sendname"));
	        	obj.setReceiveuid(rs.getInt("receiveuid"));
	        	obj.setTitle(rs.getString("title"));
	        	obj.setContent(rs.getString("content"));
	        	obj.setCreatetime(rs.getString("createtime"));
	        	obj.setIsdel(rs.getInt("isdel"));
	        	obj.setHtmlcode(rs.getString("htmlcode"));
	        	obj.setStatus(rs.getInt("status"));
	        	obj.setAttachid(rs.getInt("attachid"));
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
