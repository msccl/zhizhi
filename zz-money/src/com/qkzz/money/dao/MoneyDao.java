package com.qkzz.money.dao;

public interface MoneyDao {
	/**
	 * 增加金额
	 * @param uid
	 * @param money
	 * @return
	 */
	public int incMoney(long uid,int moneyInfoId,double money);
	
	/**
	 * 减少金额
	 * @param uid
	 * @param money
	 * @return
	 */
	public int decMoney(long uid,int moneyInfoId,double money);
	
	/**
	 * 更改金额
	 * @param uid
	 * @param money
	 * @return
	 */
	public int modifyMoney(long uid,int moneyInfoId,double money);

	/**
	 * 用户账户余额
	 * @param uid
	 * @return
	 */
	public double getMoney(long uid,int moneyInfoId);
	
	/**
	 * 获取支付密码
	 * @return
	 */
	public String getMoneyPayPassword(long uid,int moneyInfoId);

	/**
	 * 更改账户支付密码
	 * @param uid
	 * @param newpassword
	 * @return
	 */
	public int modifyPayPassword(long uid,int moneyInfoId,String newpassword);
	
	/**
	 * 更改账户状态
	 * @param uid
	 * @param status
	 * @return
	 */
	public int changeStatus(long uid,int moneyInfoId,int status);
	

	/**
	 * 资金账户是否锁定
	 * @param uid
	 * @return
	 */
	public boolean isMoneyBlack(long uid,int moneyInfoId);
	
	/**
	 * 验证支付密码是否正确
	 * @param uid
	 * @param password
	 * @return
	 */
	public boolean isValidatePassword(long uid,int moneyInfoId,String password);

	/**
	 * 增加资金账户
	 * @param uid
	 * @param initMoney ：初始数量
	 * @return
	 */
	public int addMoneyAccount(long uid,int moneyInfoId,double initMoney);
}
