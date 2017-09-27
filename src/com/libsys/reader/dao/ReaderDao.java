package com.libsys.reader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.libsys.reader.pojo.Reader;
import com.libsys.reader.pojo.Readertype;
import com.libsys.util.DBUtil;

public class ReaderDao {
	/**
	 * 新增
	 */
	public boolean saveReader(Reader reader){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into tb_reader(name,typeid,sex,vocation,birthday,paperType,paperNO,tel,"
				+ "email,createDate,operator,remark) values(?,?,?,?,?,?,?,?,?,now(),?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reader.getName());
			/**
			 * 从读者对象中取出读者类型属性对象，再从读者类型对象中获取其id属性
			 */
			pstmt.setInt(2, reader.getRt().getId());
			pstmt.setString(3, reader.getSex());
			pstmt.setString(4, reader.getVocation());
			pstmt.setString(5, reader.getBirthday());
			pstmt.setString(6, reader.getPaperType());
			pstmt.setString(7, reader.getPaperNO());
			pstmt.setString(8, reader.getTel());
			pstmt.setString(9, reader.getEmail());
			pstmt.setInt(10, reader.getOperator());
			pstmt.setString(11, reader.getRemark());
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
	public boolean editReader(Reader reader){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "update tb_reader set name=?,typeid=?,sex=?,vocation=?,birthday=?,paperType=?,paperNO=?,"
				+ "tel=?,email=?,createDate=now(),operator=?,remark=? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reader.getName());
			pstmt.setInt(2, reader.getRt().getId());
			pstmt.setString(3, reader.getSex());
			pstmt.setString(4, reader.getVocation());
			pstmt.setString(5, reader.getBirthday());
			pstmt.setString(6, reader.getPaperType());
			pstmt.setString(7, reader.getPaperNO());
			pstmt.setString(8, reader.getTel());
			pstmt.setString(9, reader.getEmail());
			pstmt.setInt(10, reader.getOperator());
			pstmt.setString(11, reader.getRemark());
			pstmt.setInt(12, reader.getId());
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
	public boolean delReader(int id){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from tb_reader where id=?";
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
	public List<Reader> queryReader(){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "select a.id,a.name,typeid,b.name typename,sex,vocation,birthday,paperType,paperNO,tel,email,"
				+ "createDate,operator,remark from tb_reader a,tb_readertype b where a.typeid=b.id";
		ResultSet rs = null;
		List<Reader> list = new ArrayList<Reader>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs!=null && rs.next()){
				Reader reader = new Reader();
				reader.setId(rs.getInt("id"));
				reader.setName(rs.getString("name"));
				/**
				 * 将查询出来读者类型数据，存放在一个读者类型的对象中
				 * 然后再将读者类型对象设置到读者对象的属性中
				 */
				Readertype rt = new Readertype();
				rt.setId(rs.getInt("typeid"));
				rt.setName(rs.getString("typename"));
				reader.setRt(rt);
				reader.setSex(rs.getString("sex"));
				reader.setVocation(rs.getString("vocation"));
				reader.setBirthday(rs.getString("birthday"));
				reader.setPaperType(rs.getString("paperType"));
				reader.setPaperNO(rs.getString("paperNO"));
				reader.setTel(rs.getString("tel"));
				reader.setEmail(rs.getString("email"));
				reader.setCreateDate(rs.getString("createDate"));
				reader.setOperator(rs.getInt("operator"));
				reader.setRemark(rs.getString("remark"));
				
				list.add(reader);
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
	public Reader getReader(int id){
		DBUtil db = DBUtil.getInstance();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "select a.id,a.name,typeid,b.name typename,sex,vocation,birthday,paperType,paperNO,tel,email,"
				+ "createDate,operator,remark from tb_reader a,tb_readertype b where a.typeid=b.id and a.id=?";
		ResultSet rs = null;
		Reader reader = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs!=null && rs.next()){
				reader = new Reader();
				reader.setId(rs.getInt("id"));
				reader.setName(rs.getString("name"));
				Readertype rt = new Readertype();
				rt.setId(rs.getInt("typeid"));
				rt.setName(rs.getString("typename"));
				reader.setRt(rt);
				reader.setSex(rs.getString("sex"));
				reader.setVocation(rs.getString("vocation"));
				reader.setBirthday(rs.getString("birthday"));
				reader.setPaperType(rs.getString("paperType"));
				reader.setPaperNO(rs.getString("paperNO"));
				reader.setTel(rs.getString("tel"));
				reader.setEmail(rs.getString("email"));
				reader.setCreateDate(rs.getString("createDate"));
				reader.setOperator(rs.getInt("operator"));
				reader.setRemark(rs.getString("remark"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeAll(conn, pstmt, rs);
		}
		return reader;
	}
}
