package com.qkzz.global;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 请主意，在web-app中，listener元素位于所有的servlet 元素之前以及所有filter-mapping元素之后。
 * 
 * @author cycles1
 * 
 */
public class QKListener implements ServletContextListener {

	static ServletContext sc = null;

	static String realPath = "/";

	static String confPath = "/";

	public void contextInitialized(ServletContextEvent event) {
		try {

			System.out.print("System starting........");
			sc = event.getServletContext();
			realPath = sc.getRealPath("/");
			confPath = sc.getRealPath("/WEB-INF/");
			System.out.println("confPath = "+confPath);
			Properties props = System.getProperties();
			props.setProperty("config-root-path", confPath);
			
			String datasrc = sc.getInitParameter("datasrc");
			if(datasrc!=null){
				if(datasrc.equalsIgnoreCase("proxool")){
					System.out.println("proxool)");
					DBConn.setProxoolSrc(confPath);
					System.out.println(confPath + "proxool.xml");
				}else if(datasrc.equalsIgnoreCase("txown")){
					System.out.println("txown)");
					DBConn.setOwnSrc();
				}else {
					System.out.println("tomcat)");
					DBConn.setDataSrc();
				}
			}else {
				System.out.println("default)");
				DBConn.setDataSrc();
			}

			DBConn.setConfPath(confPath);
			System.out.println("over");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
	}
}
