package com.qkzz.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ActiveBean
{
  private static ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });

  public static Object getBean(String beanName)
  {
    return ctx.getBean(beanName);
  }

  public static <T> T getBean(String beanName, Class<T> classzz)
  {
    return ctx.getBean(beanName, classzz);
  }

  public static void main(String[] args) {
//    for (int i = 1; i <= 1000; i++) 
//      System.out.println(i.hashCode());
  }
}