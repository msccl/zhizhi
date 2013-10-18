package com.qkzz.online.dao;

import com.qkzz.online.bean.Queue;

public abstract interface GameDao
{
  public abstract boolean addAll(Queue paramQueue);

  public abstract boolean isExistAll(int paramInt);

  public abstract boolean updateAll(Queue paramQueue);

  public abstract boolean add(Queue paramQueue);

  public abstract boolean isExist(int paramInt);

  public abstract boolean update(Queue paramQueue);
}