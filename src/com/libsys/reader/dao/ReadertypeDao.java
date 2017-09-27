package com.libsys.reader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.libsys.reader.pojo.Readertype;
import com.libsys.util.DBUtil;

/**
 * ReadertypeDao类是用来操作tb_readertype表增删改查
 */
public class ReadertypeDao {

	/**
	 * 新增
	 */
	public boolean saveReadertype(Readertype rt){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into tb_readertype(name) values(?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rt.getName());
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
	 * 修改
	 */
	public boolean editReadertype(Readertype rt){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "update tb_readertype set name=? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rt.getName());
			pstmt.setInt(2, rt.getId());
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
	 * 删除
	 */
	public boolean delReadertype(int id){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from tb_readertype where id=?";
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
	 * 查询全部数据
	 */
	public List<Readertype> queryReadertype(){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "select id,name from tb_readertype";
		ResultSet rs = null;
		List<Readertype> list = new ArrayList<Readertype>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs!=null && rs.next()){
				Readertype rt = new Readertype();
				rt.setId(rs.getInt("id"));
				rt.setName(rs.getString("name"));
				
				list.add(rt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeAll(conn, pstmt, rs);
		}
		return list;
	}
	/**
	 * 查询单条数据
	 * 注意：查询到数据，那么方法返回pojo对象
	 *     没有查询到数据，那么方法返回null值
	 */
	public Readertype getReadertype(int id){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "select id,name from tb_readertype where id=?";
		ResultSet rs = null;
		Readertype rt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				rt = new Readertype();
				rt.setId(rs.getInt("id"));
				rt.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeAll(conn, pstmt, rs);
		}
		return rt;
	}
}
