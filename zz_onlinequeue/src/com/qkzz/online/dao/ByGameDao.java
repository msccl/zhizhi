package com.qkzz.online.dao;

import com.qkzz.online.bean.Queue;

public abstract interface ByGameDao
{
  public abstract boolean add(Queue paramQueue);

  public abstract boolean isExist(Queue paramQueue);

  public abstract boolean update(Queue paramQueue);
}