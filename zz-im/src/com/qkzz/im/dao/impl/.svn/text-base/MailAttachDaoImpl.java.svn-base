package com.qkzz.im.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.im.bean.MailAttach;
import com.qkzz.im.dao.MailAttachDao;

public class MailAttachDaoImpl implements MailAttachDao {
	public static String dbim = Constant.dbim;

	@Override
	public int add(MailAttach bean) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("insert into mailattach").append(bean.getReceiveuid()&0x0f).append("(senduid,receiveuid,gameid,attime,status,checktype) values(?,?,?,now(),0,?)").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setLong(1, bean.getSenduid());
	    	ps.setLong(2, bean.getReceiveuid());
	    	ps.setInt(3, bean.getGameid());
	    	ps.setInt(4, bean.getChecktype());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	@Override
	public MailAttach getAttach(long receiveuid,int attachid) {
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from mailattach").append(receiveuid&0x0f).append(" where id=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setInt(1, attachid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				MailAttach obj = new MailAttach();
	        	obj.setId(rs.getInt("id"));
	        	obj.setSenduid(rs.getInt("senduid"));
	        	obj.setReceiveuid(rs.getInt("receiveuid"));
	        	obj.setGameid(rs.getInt("gameid"));
	        	obj.setAttime(rs.getString("attime"));
	        	obj.setStatus(rs.getInt("status"));
	        	obj.setChecktype(rs.getInt("checktype"));
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

	@Override
	public int getLastInsertID(long receiveuid) {
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select id from mailattach").append(receiveuid&0x0f).append(" where receiveuid=? order by id desc limit 1").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbim,sql);
	    	ps.setLong(1, receiveuid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("id");
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return 0;
	}

}
