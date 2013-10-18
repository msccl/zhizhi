package com.qkzz.common;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * 在应用中如果有静态并且变化较大hashmap以及list，
* 应当将生成的map和list在这里注册，可以在后台管理中获取这个集合并进行管理操作
 * @author cycles1
 *
 */

public class MemManager {
  private MemManager() {
  }

  static HashMap maps = new HashMap();
  static HashMap lists = new HashMap();

  /**
   * 获得所有内存的静态map，系统监视用
   * @return
   */
  public static Map allMap(){
    return maps;
  }
  /**
   * 获得所有内存静态list，系统监视用
   * @return
   */
  public static Map allList(){
    return lists;
  }

  /**
   * 取得指定类的map
   */
  public static Map getMap(String classPath,int index){
    return (Map)maps.get(classPath+"."+index);
  }
  /**
   * 取得指定类的map
   * @param classPath
   * @return
   */
  public static Map getMap(String classPath){
    return (Map)maps.get(classPath);
  }

  /**
   * 创建指定类名的Map，如果不存在就创建一个
   */
  public static Map newMap(String classPath, Map themap){
    Map old = (HashMap)maps.get(classPath);
    if(old!=null){
      old.clear();
      old=null;
    }
    maps.put(classPath,themap);
    return themap;
  }

  /**
   * 获取指定类名的Map，如果不存在就创建一个，在一个类中使用了多个hashmap的时候使用
   */
  public static Map newMap(String classPath,int index, Map themap){
    return newMap(classPath+"."+index,themap);
  }

  /**
   * 获取一个List，如果没有，创建一个
   */
  public static List newList(String className, List list){
    List old = (List)lists.get(className);
    if(old!=null){
      old.clear();
      old = null;
    }
    lists.put(className,list);
    return list;
  }
  /**
   * 获取一个ArrayList，如果没有，创建一个
   */
  public static List newList(String classPath,int index,List list){
    return newList(classPath+"."+index,list);
  }

}
