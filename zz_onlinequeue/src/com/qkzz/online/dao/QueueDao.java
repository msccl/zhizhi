package com.qkzz.online.dao;

import com.qkzz.online.bean.Queue;
import java.util.List;

public abstract interface QueueDao
{
  public abstract int delete(long paramLong);

  public abstract long countByList();

  public abstract List<Queue> getByList(long paramLong1, long paramLong2);
}