package com.qkzz.global;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

//import com.mchange.v2.c3p0.*;

/**
 * DBConn实现用
 * 
 * @author cycles1
 * 
 */
class ConnBuffer {

//	private static final boolean SRC_DATASRC = true;
//
//	private static final boolean SRC_MYTX = false;
//
//	private static final boolean SRC_PROXOOL = false;
	static final int SRC_DATASRC = 0;
	static final int SRC_PROXOOL = 1;
	static final int SRC_MYTX = 2;
	private static int DATA_SRC = SRC_DATASRC;
	
	static void setDataSrc(int src){
		if(src<0||src>2){
			return;
		}
		DATA_SRC = src;
	}
	
	String srcName;

	DataSource pool;

	ConnBuffer(String connName) {
		this.srcName = connName;
		this.init();
	}

	Connection get() {
		Connection con = null;
		try {
			con = pool.getConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return con;
	}

	void free(Connection conn) {
		if (conn == null)
			return;
		try {
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	void init() {
		if(DATA_SRC==SRC_DATASRC){
			pool = getDataSource(srcName);
		}else if(DATA_SRC==SRC_PROXOOL){
			pool = getProxSrc(srcName);
		}else {
			pool = getOwnSrc(srcName);
		}
	}

	/**
	 * -----------------------------------------------------------------
	 * 这里是各种数据库连接池的初始化操作
	 * -----------------------------------------------------------------
	 */

//	int[] getStatus() {
//		if (this.pool == null) {
//			return null;
//		}
//		if (this.pool instanceof PooledDataSource) {
//			try {
//				return new int[] {
//						((PooledDataSource) pool)
//								.getNumConnectionsDefaultUser(),
//						((PooledDataSource) pool)
//								.getNumBusyConnectionsDefaultUser(),
//						((PooledDataSource) pool)
//								.getNumIdleConnectionsDefaultUser(), };
//			} catch (SQLException ex) {
//				return null;
//			}
//		}
//		return null;
//	}

	private static DataSource getDataSource(String dbname) {
		DataSource ds = null;
		try {
			ds = (DataSource) ((Context) new InitialContext())
					.lookup("java:comp/env/jdbc/" + dbname);
		} catch (NamingException e) {
			e.printStackTrace();
		}

		if (ds == null) {
			System.out.println("DataSourceError :  " + dbname
					+ " didn't found!!!!");
		}
		return ds;
	}

	private static DataSource getOwnSrc(String dbname) {
		return null;
	}

	private static DataSource getProxSrc(String dbname) {
		return ProxoolSrc.getSrc(dbname);
	}
	
}
