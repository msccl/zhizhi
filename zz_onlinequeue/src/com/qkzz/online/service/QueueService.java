package com.qkzz.online.service;

import com.qkzz.common.ActiveBean;
import com.qkzz.online.bean.Queue;
import com.qkzz.online.dao.QueueDao;
import java.util.List;

public class QueueService
{
  private static QueueDao dao = (QueueDao)ActiveBean.getBean("queueDaoImpl", QueueDao.class);

  public static int delete(long id) {
    return dao.delete(id);
  }

  public static long countByList() {
    return dao.countByList();
  }

  public static List<Queue> getByList(long first, long max) {
    return dao.getByList(first, max);
  }

  public static void main(String[] args)
  {
    List<Queue> list = getByList(0L, 2L);
    if (!list.isEmpty())
    {
      for (Queue obj : list) {
        System.out.println("game:" + obj.getGameid() + ",uid:" + obj.getUid() + ",name:" + obj.getName());
        if (RoundService.addOrUpdate(obj))
          delete(obj.getId());
      }
    }
  }
}