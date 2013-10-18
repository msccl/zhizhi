package com.qkzz.im.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.im.bean.Outbox;
import com.qkzz.im.dao.OutboxDao;

public class OutboxDaoImpl implements OutboxDao {
	public static String dbim = Constant.dbim;
	
	public int countByList(long uid){
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select count(*) from outbox").append(uid&0xff).append(" where senduid=? and isdel=0").toString();
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
	
	public List<Outbox> getByList(long uid, int first, int max){
		List<Outbox> ret = new ArrayList<Outbox>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from outbox").append(uid&0xff).append(" where senduid=? and isdel=0 order by createtime desc limit ?,?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
			ps.setLong(1, uid);
			ps.setInt(2, first);
			ps.setInt(3, max);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
	        	Outbox obj = new Outbox();
	        	obj.setId(rs.getInt("id"));
	        	obj.setSenduid(rs.getInt("senduid"));
	        	obj.setReceiveuid(rs.getInt("receiveuid"));
	        	obj.setRecname(rs.getString("recname"));
	        	obj.setTitle(rs.getString("title"));
	        	obj.setContent(rs.getString("content"));
	        	obj.setCreatetime(rs.getString("createtime"));
	        	obj.setIsdel(rs.getInt("isdel"));
	        	obj.setHtmlcode(rs.getString("htmlcode"));
	        	obj.setAttach(rs.getString("attach"));
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

	public int save(Outbox obj){
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("insert into outbox").append(obj.getSenduid()&0xff).append("(senduid,receiveuid,recname,title,content,htmlcode,createtime,isdel,attach) values(?,?,?,?,?,?,now(),?,?)").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setLong(1, obj.getSenduid());
	    	ps.setLong(2, obj.getReceiveuid());
	    	ps.setString(3, obj.getRecname());
	    	ps.setString(4, obj.getTitle());
	    	ps.setString(5, obj.getContent());
	    	ps.setString(6, obj.getHtmlcode());
	    	ps.setInt(7, 0);
	    	ps.setString(8, obj.getAttach());
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
	    	String sql = new StringBuffer("update outbox").append(uid&0xff).append(" set isdel=1 where senduid=? and id=?").toString();
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

	public Outbox getById(long uid, int id){
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from outbox").append(uid&0xff).append(" where senduid=? and id=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setLong(1, uid);
	    	ps.setInt(2, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
	        	Outbox obj = new Outbox();
	        	obj.setId(rs.getInt("id"));
	        	obj.setSenduid(rs.getInt("senduid"));
	        	obj.setReceiveuid(rs.getInt("receiveuid"));
	        	obj.setRecname(rs.getString("recname"));
	        	obj.setTitle(rs.getString("title"));
	        	obj.setContent(rs.getString("content"));
	        	obj.setCreatetime(rs.getString("createtime"));
	        	obj.setIsdel(rs.getInt("isdel"));
	        	obj.setHtmlcode(rs.getString("htmlcode"));
	        	obj.setAttach(rs.getString("attach"));
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
