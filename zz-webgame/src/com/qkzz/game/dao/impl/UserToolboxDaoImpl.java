package com.qkzz.game.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qkzz.common.Constant;
import com.qkzz.game.bean.UserToolbox;
import com.qkzz.game.dao.UserToolboxDao;
import com.qkzz.game.service.bo.UserboxTools;
import com.qkzz.global.DBConn;

public class UserToolboxDaoImpl implements UserToolboxDao {

	public static String dbgame = Constant.dbgame;

	public int countByList(long uid) {
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select count(*) from t_user_toolbox").append(uid & 0xff).append(" where uid=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setLong(1, uid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				ret = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return ret;
	}

	public List<UserToolbox> getByList(long uid, int first, int max) {
		List<UserToolbox> ret = new ArrayList<UserToolbox>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from t_user_toolbox").append(uid & 0xff).append(" where uid=? order by createtime desc limit ?,?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setLong(1, uid);
			ps.setInt(2, first);
			ps.setInt(3, max);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserToolbox obj = new UserToolbox();
	        	obj.setId(rs.getInt("id"));
	        	obj.setUid(rs.getLong("uid"));
	        	obj.setGameid(rs.getInt("gameid"));
	        	obj.setGamename(rs.getString("gamename"));
	        	obj.setToolsid(rs.getInt("toolsid"));
	        	obj.setNum(rs.getInt("num"));
	        	obj.setCreatetime(rs.getString("createtime"));
				ret.add(obj);
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

	public int countByList(long uid, int gameid) {
		int ret = 0;
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select count(*) from t_user_toolbox").append(uid & 0xff).append(" where uid=? and gameid=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setLong(1, uid);
			ps.setInt(2, gameid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				ret = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return ret;
	}

	/**
	 * 获取道具箱中所有道具的详细信息，不需要分页
	 * @param uid
	 * @param gameid
	 * @return
	 */
	public List<UserboxTools> getAllToolsList(long uid,int gameid) {
		List<UserboxTools> ret = new ArrayList<UserboxTools>();
		DBConn conn = new DBConn();
		try {
			String sql= new StringBuffer("select a.uid,a.num,a.createtime,b.id,b.gameid,b.name,b.intro,b.img,b.canexchange,b.canauction,b.functiondefine,b.moneyid,b.price from t_user_toolbox").append(uid & 0xff).append(" a,t_game_tools").append(gameid & 0x0f).append(" b where a.uid=? and a.gameid=? and a.gameid=b.gameid and a.toolsid=b.id and b.status=1").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setLong(1, uid);
			ps.setInt(2, gameid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserboxTools bean = new UserboxTools();
	        	bean.setId(rs.getInt("id"));
	        	bean.setGameid(rs.getInt("gameid"));
	        	bean.setUid(rs.getLong("uid"));
	        	bean.setNum(rs.getInt("num"));
	        	bean.setName(rs.getString("name"));
	        	bean.setIntro(rs.getString("intro"));
	        	bean.setImg(rs.getString("img"));
	        	bean.setCanexchange(rs.getInt("canexchange"));
	        	bean.setCanauction(rs.getInt("canauction"));
	        	bean.setFunctiondefine(rs.getString("functiondefine"));
	        	bean.setMoneyid(rs.getInt("moneyid"));
	        	bean.setPrice(rs.getDouble("price"));
	        	bean.setCreatetime(rs.getString("createtime"));
				ret.add(bean);
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

	
	public List<UserToolbox> getByList(long uid, int gameid, int first, int max) {
		List<UserToolbox> ret = new ArrayList<UserToolbox>();
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from t_user_toolbox").append(uid & 0xff).append(" where uid=? and gameid=? order by createtime desc limit ?,?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setLong(1, uid);
			ps.setInt(2, gameid);
			ps.setInt(3, first);
			ps.setInt(4, max);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserToolbox obj = new UserToolbox();
	        	obj.setId(rs.getInt("id"));
	        	obj.setUid(rs.getLong("uid"));
	        	obj.setGameid(rs.getInt("gameid"));
	        	obj.setGamename(rs.getString("gamename"));
	        	obj.setToolsid(rs.getInt("toolsid"));
	        	obj.setNum(rs.getInt("num"));
	        	obj.setCreatetime(rs.getString("createtime"));
				ret.add(obj);
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
	
	public UserToolbox getByInfo(long uid, int gameid, int toolsid){
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select * from t_user_toolbox").append(uid & 0xff).append(" where uid=? and gameid=? and toolsid=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setLong(1, uid);
			ps.setInt(2, gameid);
			ps.setInt(3, toolsid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				UserToolbox obj = new UserToolbox();
	        	obj.setId(rs.getInt("id"));
	        	obj.setUid(rs.getLong("uid"));
	        	obj.setGameid(rs.getInt("gameid"));
	        	obj.setGamename(rs.getString("gamename"));
	        	obj.setToolsid(rs.getInt("toolsid"));
	        	obj.setNum(rs.getInt("num"));
	        	obj.setCreatetime(rs.getString("createtime"));
				return obj;//
			}
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return null;
	}
	
	public boolean isValidateTool(long uid, int gameid, int toolsid){
		DBConn conn = new DBConn();
		try {
			String sql = new StringBuffer("select id from t_user_toolbox").append(uid & 0xff).append(" where uid=? and gameid=? and toolsid=?").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setLong(1, uid);
			ps.setInt(2, gameid);
			ps.setInt(3, toolsid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
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
	
	public int incToolsNum(long uid, int gameid, int toolsid, int num) {
		System.out.println("===uid:"+uid+"===gameid:"+gameid+"====toolsid:"+toolsid+"==num:"+num);
		DBConn conn = new DBConn();
	    try {
			String sql = new StringBuffer("update t_user_toolbox").append(uid & 0xff).append(" set num=num+? where uid=? and gameid=? and toolsid=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
	    	ps.setInt(1, num);
	    	ps.setLong(2, uid);
	    	ps.setInt(3, gameid);
	    	ps.setInt(4, toolsid);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public int decToolsNum(long uid, int gameid, int toolsid, int num) {
		DBConn conn = new DBConn();
	    try {
			String sql = new StringBuffer("update t_user_toolbox").append(uid & 0xff).append(" set num=num-? where uid=? and gameid=? and toolsid=?").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
	    	ps.setInt(1, num);
	    	ps.setLong(2, uid);
	    	ps.setInt(3, gameid);
	    	ps.setInt(4, toolsid);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public int removeToolsNumZero(long uid) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("delete from t_user_toolbox").append(uid & 0xff).append(" where num=0").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public int add(UserToolbox obj) {
		DBConn conn = new DBConn();
	    try {
	    	String sql = new StringBuffer("insert into t_user_toolbox").append(obj.getUid() & 0xff).append("(uid,gameid,gamename,toolsid,num,createtime) values(?,?,?,?,?,now())").toString();
	    	PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
	    	ps.setLong(1, obj.getUid());
	    	ps.setInt(2, obj.getGameid());
	    	ps.setString(3, obj.getGamename());
	    	ps.setInt(4, obj.getToolsid());
	    	ps.setInt(5, obj.getNum());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	

	public int createTable0(int i) {
		DBConn conn = new DBConn();
	    try {
	    	StringBuffer sql = new StringBuffer();
	    	
	    	sql.append("CREATE TABLE `t_game_auction_tools_list").append(i).append("` (");
	    	sql.append("  `id` int(11) NOT NULL AUTO_INCREMENT,");
	    	sql.append("  `gameid` int(11) NOT NULL DEFAULT '0' COMMENT '游戏ID',");
	    	sql.append("  `uid` int(11) NOT NULL DEFAULT '0' COMMENT '提供道具拍卖的用户ID',");
	    	sql.append("  `auctionid` int(11) NOT NULL DEFAULT '0' COMMENT '拍卖行ID',");
	    	sql.append("  `toolsid` int(11) NOT NULL DEFAULT '0' COMMENT '道具ID',");
	    	sql.append("  `num` int(11) NOT NULL DEFAULT '0' COMMENT '数量',");
	    	sql.append("  `moneyid` int(11) NOT NULL DEFAULT '0' COMMENT '使用的货币ID',");
	    	sql.append("  `price` double NOT NULL DEFAULT '0' COMMENT '道具拍卖单价',");
	    	sql.append("  `status` int(1) NOT NULL DEFAULT '0' COMMENT '道具拍卖状态 0:正常 1:暂停 2:下架',");
	    	sql.append("  `createtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',");
	    	sql.append("  PRIMARY KEY (`id`),");
	    	sql.append("  KEY `auctionid` (`auctionid`),");
	    	sql.append("  KEY `status` (`status`),");
	    	sql.append("  KEY `gameid` (`gameid`)");
	    	sql.append(") ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='游戏道具拍卖行关系表'");
	    	
	    	PreparedStatement ps = conn.getPreparedStmt(dbgame,sql.toString());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}
	
	public int createTable1(int i) {
		DBConn conn = new DBConn();
	    try {
	    	StringBuffer sql = new StringBuffer();
	    	
	    	sql.append("CREATE TABLE `t_game_store_tools_list").append(i).append("` (");
	    	sql.append("  `id` int(11) NOT NULL AUTO_INCREMENT,");
	    	sql.append("  `gameid` int(11) NOT NULL DEFAULT '0' COMMENT '游戏ID',");
	    	sql.append("  `storeid` int(11) NOT NULL DEFAULT '0' COMMENT '商店ID',");
	    	sql.append("  `toolsid` int(11) NOT NULL DEFAULT '0' COMMENT '道具ID',");
	    	sql.append("  `num` int(11) NOT NULL DEFAULT '0' COMMENT '数量',");
	    	sql.append("  `status` int(11) NOT NULL DEFAULT '0' COMMENT '销售状态 0:正常 1:暂停 2：下架',");
	    	sql.append("  `createtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',");
	    	sql.append("  PRIMARY KEY (`id`),");
	    	sql.append("  KEY `storeid` (`storeid`),");
	    	sql.append("  KEY `status` (`status`),");
	    	sql.append("  KEY `gameid` (`gameid`)");
	    	sql.append(") ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='游戏道具商店关系表'");	    	
	    	
	    	PreparedStatement ps = conn.getPreparedStmt(dbgame,sql.toString());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	public int createTable2(int i) {
		DBConn conn = new DBConn();
	    try {
	    	StringBuffer sql = new StringBuffer();
	    	
	    	sql.append("CREATE TABLE `t_user_toolbox").append(i).append("` (");
	    	sql.append("  `id` bigint(20) NOT NULL AUTO_INCREMENT,");
	    	sql.append("  `uid` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',");
	    	sql.append("  `gameid` int(11) NOT NULL DEFAULT '0' COMMENT '游戏ID',");
	    	sql.append("  `gamename` varchar(20) NOT NULL DEFAULT '' COMMENT '游戏名称',");
	    	sql.append("  `toolsid` int(11) NOT NULL DEFAULT '0' COMMENT '道具ID',");
	    	sql.append("  `num` int(11) NOT NULL DEFAULT '0' COMMENT '道具数量',");
	    	sql.append("  `createtime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',");
	    	sql.append("  PRIMARY KEY (`id`),");
	    	sql.append("  KEY `uid` (`uid`),");
	    	sql.append("  KEY `gameid` (`gameid`)");
	    	sql.append(") ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户道具包表'");
	    	
	    	PreparedStatement ps = conn.getPreparedStmt(dbgame,sql.toString());
	    	return ps.executeUpdate();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    	conn.closeStmt();
	    }
		return -1;
	}

	/**
	 * 获取道具箱中排除当前游戏的其他所有道具的详细信息，不需要分页
	 * @param uid
	 * @param gameid
	 * @return
	 */
	@Override
	public List<UserboxTools> getAllOtherGameToolsList(long uid, int gameid) {
		List<UserboxTools> ret = new ArrayList<UserboxTools>();
		DBConn conn = new DBConn();
		try {
			String sql= new StringBuffer("select a.uid,a.num,a.createtime,b.id,b.gameid,b.name,b.intro,b.img,b.canexchange,b.canauction,b.functiondefine,b.moneyid,b.price from t_user_toolbox").append(uid & 0xff).append(" a,t_game_tools").append(gameid & 0x0f).append(" b where a.uid=? and a.gameid<>? and a.gameid=b.gameid and a.toolsid=b.id and b.status=1").toString();
			PreparedStatement ps = conn.getPreparedStmt(dbgame,sql);
			ps.setLong(1, uid);
			ps.setInt(2, gameid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserboxTools bean = new UserboxTools();
	        	bean.setId(rs.getInt("id"));
	        	bean.setGameid(rs.getInt("gameid"));
	        	bean.setUid(rs.getLong("uid"));
	        	bean.setNum(rs.getInt("num"));
	        	bean.setName(rs.getString("name"));
	        	bean.setIntro(rs.getString("intro"));
	        	bean.setImg(rs.getString("img"));
	        	bean.setCanexchange(rs.getInt("canexchange"));
	        	bean.setCanauction(rs.getInt("canauction"));
	        	bean.setFunctiondefine(rs.getString("functiondefine"));
	        	bean.setMoneyid(rs.getInt("moneyid"));
	        	bean.setPrice(rs.getDouble("price"));
	        	bean.setCreatetime(rs.getString("createtime"));
				ret.add(bean);
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

	
}
