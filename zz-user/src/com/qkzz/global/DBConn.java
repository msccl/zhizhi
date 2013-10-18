package com.qkzz.global;

import java.sql.*;
import java.util.*;


public class DBConn {


	private static final boolean CHECK_SPEED = false;// 是否检测数据执行速度
	private long startms;// 测试sql执行速度用

	//各Datasource Pool的集合
	private static HashMap<String, ConnBuffer> poolMap = (HashMap<String, ConnBuffer>) new HashMap<String, ConnBuffer>();

	//记录所有sql语句，为调试与测试使用
	private String sqlSentence = null;

	private Connection syscon = null;
	private Statement stmt = null;
	private String conName = null;



	/**
	 * 通过资源名获取数据库连接
	 * 
	 * @param connName
	 *            String
	 * @return Connection
	 */
	static private Connection getConn(String connName) {
		ConnBuffer cb = (ConnBuffer) poolMap.get(connName);
		if (cb == null) {
			cb = new ConnBuffer(connName);
			if (cb == null) {
				return null;
			}
			poolMap.put(connName, cb);
		}
		return cb.get();
	}



	/**
	 * 获取statement连接
	 * 
	 * @param dbname
	 *            String 数据源名称
	 * @return Statement
	 */
	public Statement getStmt(String dbname, String sql) {
		this.sqlSentence = null;
		if (syscon != null) {
			this.closeStmt();
		}
		this.sqlSentence = sql;
		this.conName = dbname;
		syscon = getConn(this.conName);
		try {
			stmt = syscon.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (CHECK_SPEED) {
			startms = System.currentTimeMillis();
		}
		return stmt;
	}

	/**
	 * 获取prepared数据源
	 * 
	 * @param dbname
	 *            String 数据源名称
	 * @param sql
	 *            String 预置sql语句
	 * @throws SQLException
	 * @return PreparedStatement
	 */
	public PreparedStatement getPreparedStmt(String dbname, String sql)
			throws SQLException {
		this.sqlSentence = null;
		if (syscon != null) {
			try {
				this.closeStmt();
			} catch (Exception ex) {
			}
		}
		this.sqlSentence = sql;
		this.conName = dbname;
		syscon = getConn(this.conName);
		try {
			stmt = syscon.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (CHECK_SPEED) {
			startms = System.currentTimeMillis();
		}
		return (PreparedStatement) stmt;
	}

	/**
	 * 将stmt返回数据库连接
	 */
	public void closeStmt() {
		if (CHECK_SPEED) {
			if (sqlSentence != null) {
				startms = System.currentTimeMillis() - startms;
				System.out.println(startms + "ms@Sql:" + sqlSentence);
				this.sqlSentence = null;
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception Err) {
				// Err.printStackTrace();
			}
			stmt = null;
		}
		ConnBuffer cb = (ConnBuffer) poolMap.get(this.conName);
		if (cb != null) {
			cb.free(syscon);
		} else {
			try {
				syscon.close();
			} catch (Exception ex) {
			}
			syscon = null;
		}
	}

	public static Connection getConnection(String dbname) {
		return getConn(dbname);
	}

	/**
	 * @param rs
	 * @param psmt
	 * @param conn
	 */
	public static void closeResource(ResultSet rs, PreparedStatement psmt,
			Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex2) {
				ex2.printStackTrace();
			}
		}
	}

	public static void freeConnection(Statement st, Connection conn) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
	}


	/**
	 * ----------------------------------------------------------------
	 * 以下为使用DBConn的常用静态操作
	 * ----------------------------------------------------------------
	 */
	/**
	 * 建立数据库查询部分,数据库自定,并调用resultSetToList对查询结果封装<br>
	 * 
	 * @deprecated
	 * @param connName
	 *            String 数据库连接名
	 * @param sql
	 *            String sql语句
	 * @return ArrayList
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList executeQuery(String connName, String sql) {
		if (sql.indexOf(";") > -1) {
			System.out.println("InvalidSql:" + sql + "@" + connName);
			return new ArrayList();
		}
		ArrayList ret = new ArrayList();
		DBConn conn = new DBConn();
		Statement st = conn.getStmt(connName, sql);
		try {
			ResultSet rs = st.executeQuery(sql);
			ret = resultSetToList(rs);
			rs.close();
		} catch (SQLException ex1) {
			ex1.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return ret;
	}

	/**
	 * 将查询结果封装成List。<br>
	 * List中元素类型为封装一行数据的Map，Map key为字段名（注意大小写），value为相应字段值
	 * 
	 * @param rs
	 *            ResultSet
	 * @return List
	 * @throws java.sql.SQLException
	 */
	public static ArrayList<Map<Object, Object>> resultSetToList(ResultSet rs)
			throws java.sql.SQLException {
		ResultSetMetaData meta = rs.getMetaData();
		int columnCount = meta.getColumnCount();
		ArrayList<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		Map<Object, Object> rowData;
		while (rs.next()) {
			rowData = new HashMap<Object, Object>(columnCount);
			for (int i = 1; i <= columnCount; i++) {
				if (rs.getObject(i) != null) {
					rowData.put(meta.getColumnName(i), rs.getObject(i));
				} else {
					rowData.put(meta.getColumnName(i), "");
				}
			}
			list.add(rowData);
		}
		return list;
	}


	/**
	 * 数据更新
	 * 
	 * @param connName
	 *            String 连接池名称
	 * @param db
	 *            String sql语句
	 * @return int
	 */
	public static int executeUpdate(String connName, String sql) {
		if (sql.indexOf(";") > -1) {
			System.out.println("InvalidSql:" + sql + "@" + connName);
			return 0;
		}

		DBConn conn = new DBConn();
		Statement st = conn.getStmt(connName, sql);
		int ret = -1;
		try {
			ret = st.executeUpdate(sql);
		} catch (SQLException ex1) {
			ex1.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return ret;
	}

	/**
	 * 获取一个整数
	 * 
	 * @param connName
	 *            String 连接池名称
	 * @param sql
	 *            String sql语句
	 * @return int
	 */
	public static int getInt(String connName, String sql) {
		if (sql.indexOf(";") > -1) {
			System.out.println("InvalidSql:" + sql + "@" + connName);
			return -1;
		}

		DBConn conn = new DBConn();
		Statement st = conn.getStmt(connName, sql);
		int ret = -1;
		try {
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				ret = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException ex1) {
			ex1.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return ret;
	}


	/**
	 * 获取一个字符串
	 * 
	 * @param connName
	 *            String 连接池编号
	 * @param sql
	 *            String sql语句
	 * @return int
	 */
	public static String getString(String connName, String sql) {
		if (sql.indexOf(";") > -1) {
			System.out.println("InvalidSql:" + sql + "@" + connName);
			return null;
		}

		DBConn conn = new DBConn();
		Statement st = conn.getStmt(connName, sql);
		String ret = null;
		try {
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				ret = rs.getString(1);
			}
			rs.close();
		} catch (SQLException ex1) {
			ex1.printStackTrace();
		} finally {
			conn.closeStmt();
		}
		return ret;
	}


	public static void setProxoolSrc(String path) {
		confPath = path;
		ConnBuffer.setDataSrc(ConnBuffer.SRC_PROXOOL);
//		freeConnection(null,getConnection("online"));//proxool connection provider
//		ConnectionProvider.needProxoolResource(false);
		
	}

	public static void setDataSrc() {
		ConnBuffer.setDataSrc(ConnBuffer.SRC_DATASRC);
	}

	public static void setOwnSrc() {
		ConnBuffer.setDataSrc(ConnBuffer.SRC_MYTX);
	}

	public static void setConfPath(String path) {
		confPath = path;
	}

	public static String getConfPath() {
		return confPath;
	}

	private static String confPath = null;

}
