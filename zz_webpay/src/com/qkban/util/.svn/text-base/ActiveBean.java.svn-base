package com.qkban.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author root
 */

public class ActiveBean {

	/**
	 * 加载的配置文�?
	 * 通过扫描Annotation注册Bean
	 */
    private static ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
    
    /**
     * 获得注册Bean(2.x)   
     * @param beanName String 注册Bean的名�?
     * @return
     */
    public static Object getBean(String beanName){
    	return ctx.getBean(beanName);
    }
    
    /**
     * 获得注册Bean(3.x)
     * @param beanName String 注册Bean名称
     * @param classzz Class<T> 注册Bean类型
     * @return
     */
    public static <T> T getBean(String beanName, Class<T> classzz) {
    	return ctx.getBean(beanName, classzz);
    }
    
    public static void main(String [] args){
    	for(int i=1; i<=1000 ;i++){
    		System.out.println((i+"").hashCode());
    	}
    }
    
	
}
