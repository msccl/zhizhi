package com.qkzz.user.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qkzz.common.Constant;
import com.qkzz.global.DBConn;
import com.qkzz.user.bean.Member;
import com.qkzz.user.dao.MemberDao;

public class MemberDaoImpl implements MemberDao {
	public static String dbuser = Constant.dbuser;

	public Member getById(long id){
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbuser,"select * from member where id=?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Member obj = new Member();
				obj.setId(rs.getLong("id"));
				obj.setName(rs.getString("name"));
				obj.setMobile(rs.getString("mobile"));
				obj.setPassword(rs.getString("password"));
				obj.setIslock(rs.getInt("islock"));
				obj.setDomainuid(rs.getString("domainuid"));
				obj.setDomain(rs.getString("domain"));
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

	public Member getByName(String name) {
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbuser,"select * from member where name=?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Member obj = new Member();
				obj.setId(rs.getLong("id"));
				obj.setName(rs.getString("name"));
				obj.setMobile(rs.getString("mobile"));
				obj.setPassword(rs.getString("password"));
				obj.setIslock(rs.getInt("islock"));
				obj.setDomainuid(rs.getString("domainuid"));
				obj.setDomain(rs.getString("domain"));
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

	
	public Member getByLogin(String name, String password){
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbuser,"select * from member where name=? and password=?");
			ps.setString(1, name);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Member obj = new Member();
				obj.setId(rs.getLong("id"));
				obj.setName(rs.getString("name"));
				obj.setMobile(rs.getString("mobile"));
				obj.setPassword(rs.getString("password"));
				obj.setIslock(rs.getInt("islock"));
				obj.setDomainuid(rs.getString("domainuid"));
				obj.setDomain(rs.getString("domain"));
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
	
	public int update(Member obj){
		DBConn conn = new DBConn();
	    try {
	        StringBuffer sql = new StringBuffer();
	        sql.append("update member set ");
	        sql.append("islock='").append(obj.getIslock()).append("',");
	        if (obj.getName()!=null && !"".equals(obj.getName())) {
		          sql.append("name='").append(obj.getName()).append("',");
		    }
	        if (obj.getPassword()!=null && !"".equals(obj.getPassword())) {
		          sql.append("password='").append(obj.getPassword()).append("',");
		    }
	        if (obj.getMobile()!=null && !"".equals(obj.getMobile())) {
		          sql.append("mobile='").append(obj.getMobile()).append("',");
		    }
	        sql.delete(sql.length() - 1, sql.length());
	        sql.append(" where id='").append(obj.getId()).append("'");
	        
	    	PreparedStatement ps = conn.getPreparedStmt(dbuser,sql.toString());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}
		
	public int add(Member obj){
		DBConn conn = new DBConn();
	    try {
	    	String sql = "insert into member(name, password) values(?,?)";
	    	PreparedStatement ps = conn.getPreparedStmt(dbuser,sql);
	    	ps.setString(1, obj.getName());
	    	ps.setString(2, obj.getPassword());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}
	
	public boolean isValidateName(String name){
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbuser,"select * from member where name=?");
			ps.setString(1, name);
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

	public String getName(long id){
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbuser,"select name from member where id=?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("name");
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return null;
	}

	
	public long getIDByName(String name) {
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbuser,"select id from member where name=?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getLong("id");
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return 0;
	}

	
	/**
	 * 获取外站用户注册信息
	 * @param domainuid ：外站用户ID
	 * @param domain ： 外站域名，例如3366.com
	 * @return
	 */
	public Member getDoaminUser(String domainuid,String domain) {
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbuser,"select * from member where domainuid=? and domain=?");
			ps.setString(1, domainuid);
			ps.setString(2, domain);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Member obj = new Member();
				obj.setId(rs.getLong("id"));
				obj.setName(rs.getString("name"));
				obj.setIslock(rs.getInt("islock"));
				obj.setDomainuid(rs.getString("domainuid"));
				obj.setDomain(rs.getString("domain"));
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
	
	/**
	 * 判断用户是否已经设置密码
	 * @param uid ：zz用户ID
	 * @param sign ：签名
	 * @return
	 */
	public boolean hasSetPassword(long uid) {
		DBConn conn = new DBConn();
		try {
			PreparedStatement ps = conn.getPreparedStmt(dbuser,"select password from member where id=?");
			ps.setLong(1, uid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if(rs.getString("password").equals("")) {
					return false;
				}
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
	
	/**
	 * 将外站用户添加到zz数据库
	 * @param name
	 * @param domainUID
	 * @param domain
	 * @return
	 */
	public int addDomainUser(String name,String domainUID,String domain) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = "insert into member(name, domainuid, domain) values(?,?,?)";
	    	PreparedStatement ps = conn.getPreparedStmt(dbuser,sql);
	    	ps.setString(1, name);
	    	ps.setString(2, domainUID);
	    	ps.setString(3, domain);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	
}
