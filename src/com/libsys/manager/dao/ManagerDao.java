package com.libsys.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.libsys.manager.pojo.Manager;
import com.libsys.util.DBUtil;

public class ManagerDao {
	/**
	 * ����
	 */
	public boolean saveManager(Manager manager){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into tb_manager(username,pwd,role) values(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, manager.getUsername());
			pstmt.setString(2, manager.getPwd());
			pstmt.setString(3, manager.getRole());
			int i = pstmt.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeAll(conn, pstmt, null);
		}
		return false;
	}
	/**
	 * �޸�
	 */
	public boolean editManager(Manager manager){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "update tb_manager set username=?,pwd=?,role=? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, manager.getUsername());
			pstmt.setString(2, manager.getPwd());
			pstmt.setString(3, manager.getRole());
			pstmt.setInt(4, manager.getId());
			int i = pstmt.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeAll(conn, pstmt, null);
		}
		return false;
	}
	/**
	 * ɾ��
	 */
	public boolean delManager(int id){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from tb_manager where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int i = pstmt.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeAll(conn, pstmt, null);
		}
		return false;
	}
	/**
	 * ��ѯȫ������
	 */
	public List<Manager> queryManager(){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "select id,username,pwd,role from tb_manager";
		ResultSet rs = null;
		List<Manager> list = new ArrayList<Manager>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs!=null && rs.next()){
				Manager manager = new Manager();
				manager.setId(rs.getInt("id"));
				manager.setUsername(rs.getString("username"));
				manager.setPwd(rs.getString("pwd"));
				manager.setRole(rs.getString("role"));
				
				list.add(manager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeAll(conn, pstmt, rs);
		}
		return list;
	}
	/**
	 * ��ѯ��������
	 */
	public Manager getManager(int id){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "select id,username,pwd,role from tb_manager where id=?";
		ResultSet rs = null;
		Manager manager = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				manager = new Manager();
				manager.setId(rs.getInt("id"));
				manager.setUsername(rs.getString("username"));
				manager.setPwd(rs.getString("pwd"));
				manager.setRole(rs.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeAll(conn, pstmt, rs);
		}
		return manager;
	}
	/**
	 * �û���¼
	 */
	public Manager login(String username, String pwd){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "select id,username,pwd,role from tb_manager where username=? and pwd=?";
		ResultSet rs = null;
		Manager manager = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				manager = new Manager();
				manager.setId(rs.getInt("id"));
				manager.setUsername(rs.getString("username"));
				manager.setPwd(rs.getString("pwd"));
				manager.setRole(rs.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeAll(conn, pstmt, rs);
		}
		return manager;
	}
	/**
	 * �޸�
	 */
	public boolean changePwd(int id, String pwd){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "update tb_manager set pwd=? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pwd);
			pstmt.setInt(2, id);
			int i = pstmt.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeAll(conn, pstmt, null);
		}
		return false;
	}
}
