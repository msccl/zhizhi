package com.qkzz.client;

import com.qkzz.online.bean.Queue;
import com.qkzz.online.service.QueueService;
import com.qkzz.online.service.RoundService;
import java.util.List;

public class Deamon
  implements Runnable
{
  private static boolean isRuning = false;

  public void run()
  {
    while (true)
      try
      {
        if (!isRuning) {
          process();
        }
        Thread.sleep(500L); 
        continue;
      } catch (Exception ex) {
        isRuning = false;
        ex.printStackTrace();
      }
  }

  void process()
  {
    isRuning = true;

    List<Queue> list = QueueService.getByList(0L, 10L);
    if (!list.isEmpty())
    {
      for (Queue obj : list)
      {
        if (RoundService.addOrUpdate(obj)) {
          QueueService.delete(obj.getId());
        }
      }
    }
    isRuning = false;
  }
}