package com.libsys.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 功能：封装连接数据库的方法
 *
 */
public class DBUtil {
	
	//单例模式
	private static DBUtil db = new DBUtil();
	public static DBUtil getInstance() {
		return db;
	}

	//驱动类名
	private static final String className = "com.mysql.jdbc.Driver";
	//连接数据库的字符串url
	private static final String url = "jdbc:mysql://localhost:3306/db_lib";
	//数据库用户名
	private static final String user = "root";
	//连接数据库的密码
	private static final String password = "123";
	
	/**
	 * 在构造方法中注册驱动
	 */
	private DBUtil(){
		try {
			Class.forName(className);
			System.out.println("加载驱动成功！");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取Connection对象
	 */
	public Connection getConnection(){
		//声明连接
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("连接成功！");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 释放资源
	 */
	public void closeAll(Connection conn,Statement stmt,ResultSet rs){
		try {
			if(rs != null){
				rs.close();
				System.out.println("ResultSet对象关闭");
			}
			if(stmt != null){
				stmt.close();
				System.out.println("Statement对象关闭");
			}
			if(conn != null){
				conn.close();
				System.out.println("Connection对象关闭");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
