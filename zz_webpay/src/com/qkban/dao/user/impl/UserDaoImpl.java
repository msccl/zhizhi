package com.qkban.dao.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.qkban.bean.user.User;
import com.qkban.dao.user.UserDao;

@Service
public class UserDaoImpl implements UserDao {
	
	@Resource
	private JdbcTemplate userTemplate;

	public User getById(int id){
	    String sql = "select * from user where id=?";
	    ParameterizedRowMapper<User> mapper = new ParameterizedRowMapper<User>() {
	        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	User obj = new User();
	        	obj.setId(rs.getInt("id"));
	        	obj.setName(rs.getString("name"));
	        	obj.setPassword(rs.getString("password"));
	        	obj.setMobile(rs.getString("mobile"));
	        	obj.setPwdquestion(rs.getString("pwdquestion"));
	        	obj.setPwdanswer(rs.getString("pwdanswer"));
	        	obj.setLastip(rs.getString("lastip"));
	        	obj.setLasttime(rs.getString("lasttime"));
	        	obj.setPreip(rs.getString("preip"));
	        	obj.setPretime(rs.getString("pretime"));
	         return obj;
	        }
	    };
	    try{
	    	return userTemplate.queryForObject(sql, new Object[]{id}, mapper);
	    }catch(Exception e){
	    	return null;
	    }
	}
	
	public int save(final User obj){
		final String sql="insert into user(name,password,mobile,pwdquestion,pwdanswer,regtime,lastip,lasttime,islock,preip,pretime) values(?,?,?,?,?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.userTemplate.update(new PreparedStatementCreator(){   
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, obj.getName());
                ps.setString(2, obj.getPassword());
                ps.setString(3, obj.getMobile());
                ps.setString(4, obj.getPwdquestion());
                ps.setString(5, obj.getPwdanswer());
                ps.setString(6, obj.getLasttime());
                ps.setString(7, obj.getLastip());
                ps.setString(8, obj.getLasttime());
                ps.setInt(9, obj.getIslock());
                ps.setString(10, obj.getLastip());
                ps.setString(11, obj.getLasttime());
                return ps;   
            }  
        }, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	public int update(int uid, String ip, String lasttime){
		String sql="update user set preip=lastip, pretime=lasttime, lastip=?, lasttime=? where id=?";
		return userTemplate.update(sql, new Object[]{
				ip,
				lasttime,
				uid
		});
	}

	public int updateMobile(int uid, String mobile){
		String sql="update user set mobile=? where id=?";
		return userTemplate.update(sql, new Object[]{
				mobile,
				uid
		});
	}

	public int updatePassword(int uid, String password){
		String sql="update user set password=? where id=?";
		return userTemplate.update(sql, new Object[]{
				password,
				uid
		});
	}

	public int updateAnswer(int uid, String answer){
		String sql="update user set pwdanswer=? where id=?";
		return userTemplate.update(sql, new Object[]{
				answer,
				uid
		});
	}

	public int updateLock(int uid){
		String sql="update user set lock=1 where id=?";
		return userTemplate.update(sql, new Object[]{
				uid
		});
	}
	
	public User getByLogin(String name, String password){
	    String sql = "select * from user where name=? and password=?";
	    ParameterizedRowMapper<User> mapper = new ParameterizedRowMapper<User>() {
	        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	User obj = new User();
	        	obj.setId(rs.getInt("id"));
	        	obj.setName(rs.getString("name"));
	        	obj.setPassword(rs.getString("password"));
	        	obj.setMobile(rs.getString("mobile"));
	        	obj.setPwdquestion(rs.getString("pwdquestion"));
	        	obj.setPwdanswer(rs.getString("pwdanswer"));
	        	obj.setLastip(rs.getString("lastip"));
	        	obj.setLasttime(rs.getString("lasttime"));
	        	obj.setPreip(rs.getString("preip"));
	        	obj.setPretime(rs.getString("pretime"));
	         return obj;
	        }
	    };
	    try{
	    	return userTemplate.queryForObject(sql, new Object[]{name, password}, mapper);
	    }catch(Exception e){
	    	return null;
	    }
	}
	
	public boolean isValidateMobile(String mobile){
	    String sql = "select count(*) from user where mobile=?";
	    return userTemplate.queryForInt(sql, new Object[]{mobile})>0?true:false;
	}

	public boolean isValidateName(String name){
	    String sql = "select count(*) from user where name=?";
	    return userTemplate.queryForInt(sql, new Object[]{name})>0?true:false;
	}
	
	public User getByName(String name){
	    String sql = "select * from user where name=?";
	    ParameterizedRowMapper<User> mapper = new ParameterizedRowMapper<User>() {
	        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	User obj = new User();
	        	obj.setId(rs.getInt("id"));
	        	obj.setName(rs.getString("name"));
	        	obj.setNickname(rs.getString("nickname"));
	        	obj.setPassword(rs.getString("password"));
	        	obj.setMobile(rs.getString("mobile"));
	        	obj.setPwdquestion(rs.getString("pwdquestion"));
	        	obj.setPwdanswer(rs.getString("pwdanswer"));
	        	obj.setLastip(rs.getString("lastip"));
	        	obj.setLasttime(rs.getString("lasttime"));
	        	obj.setPreip(rs.getString("preip"));
	        	obj.setPretime(rs.getString("pretime"));
	         return obj;
	        }
	    };
	    try{
	    	return userTemplate.queryForObject(sql, new Object[]{name}, mapper);
	    }catch(Exception e){
	    	return null;
	    }
	}
	
}
