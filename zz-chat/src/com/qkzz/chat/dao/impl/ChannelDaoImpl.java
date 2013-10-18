package com.qkzz.chat.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.chat.bean.Channel;
import com.qkzz.chat.dao.ChannelDao;
import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;

public class ChannelDaoImpl implements ChannelDao {

	public static String dbchat = Constant.dbchat;

	public Channel getChannel(int id) {
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbchat,"select * from t_chat_channel where id=?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Channel c = new Channel();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setType(rs.getInt("type"));
				c.setExtraid(rs.getInt("extraid"));
				c.setCreatorid(rs.getInt("creatorid"));
				c.setStatus(rs.getInt("status"));
				c.setCreatetime(rs.getString("createtime"));
				rs.close();
				return c;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return null;
	}

	public List<Channel> getList(int startIndex, int number) {
		List<Channel> ret = new ArrayList<Channel>();
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbchat,"select * from t_chat_channel where status=0 order by id desc limit "+startIndex+","+number);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Channel c = new Channel();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setType(rs.getInt("type"));
				c.setExtraid(rs.getInt("extraid"));
				c.setCreatorid(rs.getInt("creatorid"));
				c.setStatus(rs.getInt("status"));
				c.setCreatetime(rs.getString("createtime"));
				ret.add(c);
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

	public List<Channel> getAllList() {
		List<Channel> ret = new ArrayList<Channel>();
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbchat,"select * from t_chat_channel where status=0 order by id desc");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Channel c = new Channel();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setType(rs.getInt("type"));
				c.setGameid(rs.getInt("gameid"));
				c.setExtraid(rs.getInt("extraid"));
				c.setCreatorid(rs.getInt("creatorid"));
				c.setStatus(rs.getInt("status"));
				c.setCreatetime(rs.getString("createtime"));
				ret.add(c);
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

	
	public int getChannelIDByGameID(int gameid) {
		int ret = -1;
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbchat,"select id from t_chat_channel where status=0 and gameid=?");
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

}
