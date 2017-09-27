package com.libsys.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ���ܣ���װ�������ݿ�ķ���
 *
 */
public class DBUtil {
	
	//����ģʽ
	private static DBUtil db = new DBUtil();
	public static DBUtil getInstance() {
		return db;
	}

	//��������
	private static final String className = "com.mysql.jdbc.Driver";
	//�������ݿ���ַ���url
	private static final String url = "jdbc:mysql://localhost:3306/db_lib";
	//���ݿ��û���
	private static final String user = "root";
	//�������ݿ������
	private static final String password = "123";
	
	/**
	 * �ڹ��췽����ע������
	 */
	private DBUtil(){
		try {
			Class.forName(className);
			System.out.println("���������ɹ���");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡConnection����
	 */
	public Connection getConnection(){
		//��������
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("���ӳɹ���");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * �ͷ���Դ
	 */
	public void closeAll(Connection conn,Statement stmt,ResultSet rs){
		try {
			if(rs != null){
				rs.close();
				System.out.println("ResultSet����ر�");
			}
			if(stmt != null){
				stmt.close();
				System.out.println("Statement����ر�");
			}
			if(conn != null){
				conn.close();
				System.out.println("Connection����ر�");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
