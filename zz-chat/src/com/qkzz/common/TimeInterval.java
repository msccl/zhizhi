package com.qkzz.common;
public class TimeInterval {

  static LRUMap TimeInterval = (LRUMap)MemManager.newMap("com.blueya.common.TimeInterval",new LRUMap(2000,2000));
  public TimeInterval() {
  }


  /**
   * 设置内容发表时间
   * @param keyStr String - key值
   */
  public static void setTime(String keyStr,String AppName) {
    keyStr = keyStr+"@"+AppName;
    long now = System.currentTimeMillis();
    TimeInterval.put(keyStr,new Long(now));
  }

  /**
   * 获取用户上次发表内容的时间
   * @param keyStr String
   * @return long
   */
  public static long getTime(String keyStr,String AppName) {
    long ret = 0;
    keyStr = keyStr+"@"+AppName;
    if(TimeInterval.containsKey(keyStr)) {
      ret = Long.parseLong(TimeInterval.get(keyStr).toString());
    }
    return ret;
  }

  public static boolean isFresh(String keyStr,String AppName,int intervalSecond) {
    long lastTime = getTime(keyStr,AppName);
    long now = System.currentTimeMillis();

    if(now - lastTime < intervalSecond*1000L) {
      return true;
    }
    return false;
  }

}
