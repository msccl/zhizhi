package com.qkzz.web.developer.dao;

import java.util.List;

import com.qkzz.web.developer.bean.Auction;

public interface AuctionDao {

	/**
	 * 添加拍卖行
	 * @param bean
	 * @return
	 */
	public int addAuction(Auction bean);
	
	/**
	 * 编辑拍卖行状态
	 * @param bean
	 * @return
	 */
	public int editAuctionStatus(int auctionID,int status);
	
	
	/**
	 * 编辑拍卖行
	 * @param auction
	 * @return
	 */
	public int editAuction(Auction auction);
	
	
	
	/**
	 * 删除拍卖行
	 * @param id
	 * @return
	 */
	public int delAuction(int id);
	
	/**
	 * 根据ID获取拍卖行信息
	 * @param id
	 * @return
	 */
	public Auction getAuction(int id);
	
	/**
	 * 获取某个游戏的拍卖行信息
	 * @param gameid
	 * @return
	 */
	public List<Auction> getGameAcution(int gameid,int startIndex,int num);
	
	/**
	 * 拍卖行总量
	 * @param gameid
	 * @return
	 */
	public int getGameAuctionMaxCount(int gameid);
}
