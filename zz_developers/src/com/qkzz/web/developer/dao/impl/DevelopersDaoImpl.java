package com.qkzz.web.developer.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

import com.qkzz.web.developer.bean.Developers;
import com.qkzz.web.developer.dao.DevelopersDao;

@Service
public class DevelopersDaoImpl implements DevelopersDao {

	@Resource
	private JdbcTemplate gameTemplate;

	public int editInfo(Developers bean) {
		return gameTemplate.update("update t_developers set name=?,intro=?,provinceid=?,cityid=?,address=?,members=?,phone=?,logo=?,tags=? " +
				"where id=?",new Object[]{bean.getName(),bean.getIntro(),bean.getProvinceid(),bean.getCityid(),bean.getAddress(),bean.getMembers(),bean.getPhone(),bean.getLogo(),bean.getTags(),bean.getId()});
	}

	public int editPassword(int developerid, String newPwd) {
		return gameTemplate.update("update t_developers set password=? where id=?",new Object[]{newPwd,developerid});
	}

	public boolean isValidate(String email,String password) {
		return gameTemplate.queryForObject("select password from t_developers where email=?",new Object[]{email},String.class).equals(password);
	}

	public int add(Developers bean) {
		return gameTemplate.update("insert into t_developers(email,password,name,identifier,createtime) " +
				"values(?,?,?,?,now())",new Object[]{bean.getEmail(),bean.getPassword(),bean.getName(),bean.getIdentifier()});
	}

	public boolean isEmailExist(String email) {
		return gameTemplate.queryForInt("select count(*) from t_developers where email=?",new Object[]{email})>0;
	}

	public String getPassword(int developerid) {
		return gameTemplate.queryForObject("select password from t_developers where id=?",new Object[]{developerid},String.class);
	}
	
	public Developers getByLogin(String email,String password) {
	    ParameterizedRowMapper<Developers> mapper = new ParameterizedRowMapper<Developers>() {
	        public Developers mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Developers bean = new Developers();
	        	bean.setId(rs.getInt("id"));
	        	bean.setName(rs.getString("name"));
	        	bean.setIntro(rs.getString("intro"));
	        	bean.setProvinceid(rs.getInt("provinceid"));
	        	bean.setCityid(rs.getInt("cityid"));
	        	bean.setAddress(rs.getString("address"));
	        	bean.setMembers(rs.getString("members"));
	        	bean.setPhone(rs.getString("phone"));
	        	bean.setEmail(rs.getString("email"));
	        	bean.setPassword(rs.getString("password"));
	        	bean.setLogo(rs.getString("logo"));
	        	bean.setTags(rs.getString("tags"));
	        	bean.setGrade(rs.getString("grade"));
	        	bean.setIdentifier(rs.getString("identifier"));
	        	bean.setCreatetime(rs.getString("createtime"));
	        	bean.setIsverified(rs.getInt("isverified"));
	        	return bean;
	        }
	    };
	 
	    try {
	    	Developers ub = gameTemplate.queryForObject("select * from t_developers where email=?",new Object[]{email},mapper);
	    	if(ub.getPassword().equals(password)) {
	    		return ub;
	    	}
	    	return null;
	    } catch(Exception e) {
	    	return null;
	    }

	}

	
	public Developers getByID(int id) {
	    ParameterizedRowMapper<Developers> mapper = new ParameterizedRowMapper<Developers>() {
	        public Developers mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Developers bean = new Developers();
	        	bean.setId(rs.getInt("id"));
	        	bean.setName(rs.getString("name"));
	        	bean.setIntro(rs.getString("intro"));
	        	bean.setProvinceid(rs.getInt("provinceid"));
	        	bean.setCityid(rs.getInt("cityid"));
	        	bean.setAddress(rs.getString("address"));
	        	bean.setMembers(rs.getString("members"));
	        	bean.setPhone(rs.getString("phone"));
	        	bean.setEmail(rs.getString("email"));
	        	bean.setPassword(rs.getString("password"));
	        	bean.setLogo(rs.getString("logo"));
	        	bean.setTags(rs.getString("tags"));
	        	bean.setGrade(rs.getString("grade"));
	        	bean.setIdentifier(rs.getString("identifier"));
	        	bean.setCreatetime(rs.getString("createtime"));
	        	bean.setIsverified(rs.getInt("isverified"));
	        	return bean;
	        }
	    };
	    try {
	    	return gameTemplate.queryForObject("select * from t_developers where id=?",new Object[]{id},mapper);
	    } catch(Exception e) {
	    	return null;
	    }
	}

}
