package com.qkzz.client;

public class Main
{
  public static void main(String[] args)
  {
    System.out.println("    *********************************************");
    System.out.println("    **        Online distribution queue        **");
    System.out.println("    *********************************************");
    try
    {
      Thread a = new Thread(new Deamon());
      a.setDaemon(false);
      a.start();
    }
    catch (Exception localException)
    {
    }
  }
}