package com.qkban.dao.money;

public interface MoneyDao {
	/**
	 * 增加金额
	 * @param uid
	 * @param money
	 * @return
	 */
	public int incMoney(int uid,double money);
	
	/**
	 * 减少金额
	 * @param uid
	 * @param money
	 * @return
	 */
	public int decMoney(int uid,double money);
	
	/**
	 * 更改金额
	 * @param uid
	 * @param money
	 * @return
	 */
	public int modifyMoney(int uid,double money);

	/**
	 * 用户账户余额
	 * @param uid
	 * @return
	 */
	public double getMoney(int uid);
	
	/**
	 * 获取支付密码
	 * @return
	 */
	public String getMoneyPayPassword(int uid);

	/**
	 * 更改账户支付密码
	 * @param uid
	 * @param newpassword
	 * @return
	 */
	public int modifyPayPassword(int uid,String newpassword);
	
	/**
	 * 更改账户状�??
	 * @param uid
	 * @param status
	 * @return
	 */
	public int changeStatus(int uid,int status);
	

	/**
	 * 资金账户是否锁定
	 * @param uid
	 * @return
	 */
	public boolean isMoneyBlack(int uid);
	
	/**
	 * 验证支付密码是否正确
	 * @param uid
	 * @param password
	 * @return
	 */
	public boolean isValidatePassword(int uid,String password);

	/**
	 * 增加资金账户
	 * @param uid
	 * @param initMoney ：初始数�?
	 * @return
	 */
	public int addMoneyAccount(int uid,double initMoney);
}
