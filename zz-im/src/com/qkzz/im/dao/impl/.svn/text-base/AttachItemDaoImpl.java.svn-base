package com.qkzz.im.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.im.bean.AttachItem;
import com.qkzz.im.bean.Inbox;
import com.qkzz.im.dao.AttachItemDao;

public class AttachItemDaoImpl implements AttachItemDao {
	public static String dbim = Constant.dbim;

	@Override
	public int addItem(AttachItem bean) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("insert into attachitem").append(bean.getAttachid()&0x0f).append("(attachid,type,itemid,count,gettime,status) values(?,?,?,?,now(),0)").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setInt(1, bean.getAttachid());
	    	ps.setInt(2, bean.getType());
	    	ps.setInt(3, bean.getItemid());
	    	ps.setDouble(4, bean.getCount());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	@Override
	public int changeStatus(int attachid,int id,int status) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("update attachitem").append(attachid&0x0f).append(" set status=? where id=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setInt(1, status);
	    	ps.setInt(2, id);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	@Override
	public List<AttachItem> getItemList(int attachid) {
	    List<AttachItem> ret = new ArrayList<AttachItem>();
		DBConn conn = new DBConn();
		try {
	    	String sql = new StringBuffer("select * from attachitem").append(attachid&0x0f).append(" where attachid=? and status=0").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
			ps.setInt(1, attachid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AttachItem obj = new AttachItem();
	        	obj.setId(rs.getInt("id"));
	        	obj.setAttachid(rs.getInt("attachid"));
	        	obj.setType(rs.getInt("type"));
	        	obj.setItemid(rs.getInt("itemid"));
	        	obj.setCount(rs.getDouble("count"));
	        	obj.setGettime(rs.getString("gettime"));
	        	obj.setStatus(rs.getInt("status"));
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
	public AttachItem getItem(int attachid, int type, int itemid) {
		DBConn conn = new DBConn();
		try {
	    	String sql = new StringBuffer("select * from attachitem").append(attachid&0x0f).append(" where attachid=? and type=? and itemid=? order by id desc limit 1").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
			ps.setInt(1, attachid);
			ps.setInt(2, type);
			ps.setInt(3, itemid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				AttachItem obj = new AttachItem();
	        	obj.setId(rs.getInt("id"));
	        	obj.setAttachid(rs.getInt("attachid"));
	        	obj.setType(rs.getInt("type"));
	        	obj.setItemid(rs.getInt("itemid"));
	        	obj.setCount(rs.getDouble("count"));
	        	obj.setGettime(rs.getString("gettime"));
	        	obj.setStatus(rs.getInt("status"));
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
