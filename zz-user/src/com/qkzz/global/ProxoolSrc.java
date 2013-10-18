package com.qkzz.global;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;

public class ProxoolSrc implements DataSource {

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("proxool."+dbname);
	}

//	public static void setConfRoot(String filepath){
//		filePath = (filepath.endsWith("/")?filepath:filepath+"/")+"proxool.xml";
//	}
	private static void initPath(){
		filePath = DBConn.getConfPath();
		if(filePath!=null){
			filePath = (filePath.endsWith("/")?filePath:filePath+"/")+"proxool.xml";
		}else {
			System.out.println("ATTENTION!!! proxool.xml path not defined!!!");
			filePath = "/usr/waproot/WEB-INF/proxool.xml";
		}
	}
	private static String filePath = null;
	private static HashMap<String,ProxoolSrc> inst = null;//new HashMap();
	static synchronized ProxoolSrc getSrc(String srcName){
		if(inst==null){
			try {
				initPath();
//				String path = new FilePath().getPath("proxool.xml");
				JAXPConfigurator.configure(filePath, false);
			} catch (ProxoolException e) {
				e.printStackTrace();
				return null;
			}
			inst = new HashMap<String,ProxoolSrc>();
		}
		ProxoolSrc src = (ProxoolSrc)inst.get(srcName);
		if(src!=null){
			return src;
		}
		src = new ProxoolSrc(srcName);
		inst.put(srcName,src);
		return src;
	}

	
	private String dbname;
	
	private ProxoolSrc(String srcName){
		this.dbname = srcName;
	}
	/**
	 * @deprecated 未实现
	 */
	public Connection getConnection(String username, String password)
			throws SQLException {
		return null;
	}

	/**
	 * @deprecated 未实现
	 */
	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	/**
	 * @deprecated 未实现
	 */
	public void setLogWriter(PrintWriter out) throws SQLException {

	}

	/**
	 * @deprecated 未实现
	 */
	public void setLoginTimeout(int seconds) throws SQLException {

	}

	/**
	 * @deprecated 未实现
	 */
	public int getLoginTimeout() throws SQLException {
			return 0;
	}

	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
