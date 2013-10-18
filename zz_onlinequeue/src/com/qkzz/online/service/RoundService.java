package com.qkzz.online.service;

import com.qkzz.common.ActiveBean;
import com.qkzz.online.bean.Queue;
import com.qkzz.online.dao.ByGameDao;
import com.qkzz.online.dao.GameDao;
import com.qkzz.online.dao.OnlineDao;

public class RoundService
{
  private static OnlineDao onlinedao = (OnlineDao)ActiveBean.getBean("onlineDaoImpl", OnlineDao.class);
  private static GameDao gamedao = (GameDao)ActiveBean.getBean("gameDaoImpl", GameDao.class);
  private static ByGameDao bygamedao = (ByGameDao)ActiveBean.getBean("byGameDaoImpl", ByGameDao.class);

  private static boolean addOnline(Queue obj)
  {
    if (onlinedao.isExistAll(obj.getUid()))
      onlinedao.updateAll(obj);
    else {
      onlinedao.addAll(obj);
    }

    if (onlinedao.isExist(obj.getUid())) {
      return onlinedao.update(obj);
    }
    return onlinedao.add(obj);
  }

  private static boolean addGame(Queue obj)
  {
    if (gamedao.isExistAll(obj.getUid()))
      gamedao.updateAll(obj);
    else {
      gamedao.addAll(obj);
    }

    if (gamedao.isExist(obj.getUid())) {
      return gamedao.update(obj);
    }
    return gamedao.add(obj);
  }

  private static boolean addByGame(Queue obj)
  {
    if (bygamedao.isExist(obj)) {
      return bygamedao.update(obj);
    }
    return bygamedao.add(obj);
  }

  public static boolean addOrUpdate(Queue obj)
  {
    return (addOnline(obj)) && (addGame(obj)) && (addByGame(obj));
  }
}