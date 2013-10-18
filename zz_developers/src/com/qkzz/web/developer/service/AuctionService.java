package com.qkzz.web.developer.service;

import java.util.List;

import com.qkzz.util.ActiveBean;
import com.qkzz.web.developer.bean.Auction;
import com.qkzz.web.developer.dao.AuctionDao;

public class AuctionService {

	private static AuctionDao dao = ActiveBean.getBean("auctionDaoImpl", AuctionDao.class);

	
	/**
	 * 添加拍卖行
	 * @param bean
	 * @return
	 */
	public static int addAuction(Auction bean) {
		return dao.addAuction(bean);
	}
	
	/**
	 * 编辑拍卖行
	 * @param bean
	 * @return
	 */
	public static int editAuctionStatus(int auctionID,int status) {
		return dao.editAuctionStatus(auctionID, status);
	}
	
	/**
	 * 删除拍卖行
	 * @param id
	 * @return
	 */
	public static int delAuction(int id) {
		return dao.delAuction(id);
	}
	
	/**
	 * 根据ID获取拍卖行信息
	 * @param id
	 * @return
	 */
	public static Auction getAuction(int id) {
		return dao.getAuction(id);
	}
	
	/**
	 * 获取某个游戏的拍卖行信息
	 * @param gameid
	 * @return
	 */
	public static List<Auction> getGameAuction(int gameid,int startIndex,int num) {
		return dao.getGameAcution(gameid,startIndex,num);
	}
	
	/**
	 * 获取游戏拍卖行总量
	 * @param gameid
	 * @return
	 */
	public static int getGameAuctionMaxCount(int gameid) {
		return dao.getGameAuctionMaxCount(gameid);
	}

	/**
	 * 编辑拍卖行
	 * @param auction
	 * @return
	 */
	public static int editAuction(Auction auction) {
		return dao.editAuction(auction);
	}
}
