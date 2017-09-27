package com.libsys.book.pojo;

public class Bookinfo {
	private int id;
	private String bookname;
	/**
	 * 关联表信息
	 */
	private BookType bt;
	private String author;
	private String ISBN;
	private double price;
	private int page;
	private String inTime;
	private int operator;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public BookType getBt() {
		return bt;
	}
	public void setBt(BookType bt) {
		this.bt = bt;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public int getOperator() {
		return operator;
	}
	public void setOperator(int operator) {
		this.operator = operator;
	}
	@Override
	public String toString() {
		return bookname;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bookinfo other = (Bookinfo) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
