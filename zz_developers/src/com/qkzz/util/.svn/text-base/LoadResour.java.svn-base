package com.qkzz.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class LoadResour {


	/**
	 * 读取配置文件
	 * 配置指定目录(可以在web application之外)
	 * @return
	 */
    public static String getMMSRootPath(String name){
        String init = null;
        Properties prop = new Properties();
        try{
             InputStream is = LoadResour.class.getResourceAsStream("/init.properties");
             prop.load(is);
             init = prop.getProperty(name);
             if(is!=null)
                is.close();
        }catch(IOException ex){
            System.out.println("读取配置文件[init.properties]失败.");
            ex.printStackTrace();
        }
        return init;
    }
	
	public static String getFilePath() {
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyyMM/dd/");
		return sdf.format(new java.util.Date());
	}
	
	/**
	 * 获取文件名
	 * @param fileName
	 * @return
	 */
    public static String getFileName(String fileName){
        if(fileName.lastIndexOf(".")!=-1){
    		int pos = fileName.lastIndexOf(".");
    		fileName = fileName.substring(0, pos);
            return fileName;
        }
        return "未命名";
    }

    /**
     * 获取文件后缀扩展名
     * @param fileName
     * @return
     */
    public static String getExtention(String fileName){
        if(fileName.lastIndexOf(".")!=-1){
            int pos = fileName.lastIndexOf(".");
            return fileName.substring(pos+1);
        }
        return null;
    }
    
    /**
     * 创建目录
     * @param filePath
     */
    public static void mkdir(String filePath) {
        if(filePath!=null && !filePath.equals("")){
            try {
                File d = new File(filePath);
                if(!d.exists()){
                    d.mkdirs();
                }
            }catch (Exception e) { 
                System.out.println("新建目录操作出错");
            }
        }
    } 
    
    
	
}
