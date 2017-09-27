package com.libsys.borrow.pojo;

import com.libsys.book.pojo.Bookinfo;
import com.libsys.reader.pojo.Reader;

public class BorrowBack {

	private int id;
	private Reader reader;
	private Bookinfo book;
	private String borrowTime;
	private String backTime;
	private String realTime;
	private int operator;
	private int ifback;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Reader getReader() {
		return reader;
	}
	public void setReader(Reader reader) {
		this.reader = reader;
	}
	public Bookinfo getBook() {
		return book;
	}
	public void setBook(Bookinfo book) {
		this.book = book;
	}
	public String getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}
	public String getBackTime() {
		return backTime;
	}
	public void setBackTime(String backTime) {
		this.backTime = backTime;
	}
	public String getRealTime() {
		return realTime;
	}
	public void setRealTime(String realTime) {
		this.realTime = realTime;
	}
	public int getOperator() {
		return operator;
	}
	public void setOperator(int operator) {
		this.operator = operator;
	}
	public int getIfback() {
		return ifback;
	}
	public void setIfback(int ifback) {
		this.ifback = ifback;
	}
}
