package com.libsys.borrow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.libsys.book.pojo.Bookinfo;
import com.libsys.borrow.pojo.BorrowBack;
import com.libsys.reader.pojo.Reader;
import com.libsys.util.DBUtil;

public class BorrowBackDao {
	/**
	 * 新增借书记录
	 */
	public boolean saveBorrowBack(BorrowBack bb){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into tb_borrow_back(readerid,bookid,borrowTime,backTime,operator) values(?,?,now(),?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bb.getReader().getId());
			pstmt.setInt(2, bb.getBook().getId());
			pstmt.setString(3, bb.getBackTime());
			pstmt.setInt(4, bb.getOperator());
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
	 * 修改借书记录
	 */
	public boolean editBorrowBack(BorrowBack bb){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "update tb_borrow_back set readerid=?,bookid=?,borrowTime=now(),backTime=?,operator=? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bb.getBook().getId());
			pstmt.setInt(2, bb.getBook().getId());
			pstmt.setString(3, bb.getBackTime());
			pstmt.setInt(4, bb.getOperator());
			pstmt.setInt(5, bb.getId());
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
	 * 删除借书记录
	 */
	public boolean delBorrowBack(int id){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from tb_borrow_back where id=?";
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
	public List<BorrowBack> queryBorrowBack(){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "select a.id,readerid,b.name readername,bookid,c.bookname,borrowTime,"
				+ "backTime,realTime,a.operator,ifback from tb_borrow_back a,tb_reader b,tb_bookinfo c "
				+ "where a.readerid=b.id and a.bookid=c.id and ifback=0";
		ResultSet rs = null;
		List<BorrowBack> list = new ArrayList<BorrowBack>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs!=null && rs.next()){
				BorrowBack bb = new BorrowBack();
				bb.setId(rs.getInt("id"));
				Reader reader = new Reader();
				reader.setId(rs.getInt("readerid"));
				reader.setName(rs.getString("readername"));
				bb.setReader(reader);
				Bookinfo book = new Bookinfo();
				book.setId(rs.getInt("bookid"));
				book.setBookname(rs.getString("bookname"));
				bb.setBook(book);
				bb.setBorrowTime(rs.getString("borrowTime"));
				bb.setBackTime(rs.getString("backTime"));
				bb.setRealTime(rs.getString("realTime"));
				bb.setOperator(rs.getInt("operator"));
				bb.setIfback(rs.getInt("ifback"));
				
				list.add(bb);
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
	 */
	public BorrowBack getBorrowBack(int id){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "select a.id,readerid,b.name readername,bookid,c.bookname,borrowTime,"
				+ "backTime,realTime,a.operator,ifback from tb_borrow_back a,tb_reader b,tb_bookinfo c "
				+ "where a.readerid=b.id and a.bookid=c.id and ifback=0 and a.id=?";
		ResultSet rs = null;
		BorrowBack bb = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				bb = new BorrowBack();
				bb.setId(rs.getInt("id"));
				Reader reader = new Reader();
				reader.setId(rs.getInt("readerid"));
				reader.setName(rs.getString("readername"));
				bb.setReader(reader);
				Bookinfo book = new Bookinfo();
				book.setId(rs.getInt("bookid"));
				book.setBookname(rs.getString("bookname"));
				bb.setBook(book);
				bb.setBorrowTime(rs.getString("borrowTime"));
				bb.setBackTime(rs.getString("backTime"));
				bb.setRealTime(rs.getString("realTime"));
				bb.setOperator(rs.getInt("operator"));
				bb.setIfback(rs.getInt("ifback"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeAll(conn, pstmt, rs);
		}
		return bb;
	}
	/**
	 * 还书：修改借书记录中的真实还书时间以及是否还书字段
	 */
	public boolean editBorrowBackForBack(int id){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "update tb_borrow_back set realTime=now(),ifback=1 where id=?";
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
}
