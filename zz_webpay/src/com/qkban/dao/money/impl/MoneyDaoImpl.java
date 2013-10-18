package com.qkban.dao.money.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.qkban.dao.money.MoneyDao;

@Service
public class MoneyDaoImpl implements MoneyDao {

	
	@Resource
	private JdbcTemplate moneyTemplate;

	public int addMoneyAccount(int uid, double initMoney) {
		return moneyTemplate.update("insert into money(uid,money,createtime) values(?,?,now())",new Object[]{uid,initMoney});
	}

	public int changeStatus(int uid, int status) {
		return moneyTemplate.update("update money set status="+status+" where uid="+uid);
	}

	public int decMoney(int uid, double money) {
		return moneyTemplate.update("update money set money=money-"+money+",lasttime=now() where uid="+uid);
	}

	public double getMoney(int uid) {
		try {
			return moneyTemplate.queryForObject("select money from money where uid="+uid,Double.class);
		} catch(Exception e) {
			this.addMoneyAccount(uid, 0);
			return 0;
		}
	}

	public String getMoneyPayPassword(int uid) {
		return moneyTemplate.queryForObject("select paypassword from money where uid="+uid, String.class);
	}

	public int incMoney(int uid, double money) {
		return moneyTemplate.update("update money set money=money+"+money+",lasttime=now() where uid="+uid);
	}

	public boolean isMoneyBlack(int uid) {
		return moneyTemplate.queryForInt("select status from money where uid="+uid) == 1;
	}

	public boolean isValidatePassword(int uid, String password) {
		return moneyTemplate.queryForObject("select paypassword from money where uid="+uid, String.class).equals(password);
	}

	public int modifyMoney(int uid, double money) {
		return moneyTemplate.update("update money set money="+money+",lasttime=now() where uid="+uid);
	}

	public int modifyPayPassword(int uid, String newpassword) {
		return moneyTemplate.update("update money set paypassword='"+newpassword+"' where uid="+uid);
	}

}
