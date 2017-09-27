package com.libsys.book.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.libsys.book.dao.BookTypeDao;
import com.libsys.book.dao.BookinfoDao;
import com.libsys.book.pojo.BookType;
import com.libsys.book.pojo.Bookinfo;

public class BookinfoUIEdit extends JFrame{

	private JLabel jl1 = new JLabel("图书名称");
	private JLabel jl2 = new JLabel("图书类型");
	private JLabel jl3 = new JLabel("作者");
	private JLabel jl4 = new JLabel("出版社");
	private JLabel jl5 = new JLabel("价格");
	private JLabel jl6 = new JLabel("页数");
	private JTextField jt1 = new JTextField(10);
	private Vector<BookType> data = new Vector<BookType>();
	private JComboBox<BookType> jc2 = new JComboBox<BookType>();
	private JTextField jt3 = new JTextField(10);
	private JTextField jt4 = new JTextField(10);
	private JTextField jt5 = new JTextField(10);
	private JTextField jt6 = new JTextField(10);
	private JButton ok = new JButton("确定");
	private JButton cancel = new JButton("取消");
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	private JPanel jp4 = new JPanel();
	private JPanel jp5 = new JPanel();
	private JPanel jp6 = new JPanel();
	private JPanel jp7 = new JPanel();
	private BookinfoUI ui;
	private int id;
	
	public BookinfoUIEdit(BookinfoUI ui, int id){
		this.ui = ui;
		this.id = id;
		this.setTitle("修改图书信息");
		this.setSize(280, 360);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addComponent();
		addEvent();
		showData();
		
		this.setVisible(true);
	}
	public void addComponent(){
		this.setLayout(new GridLayout(7, 1));
		jp1.add(jl1);
		jp1.add(jt1);
		jp2.add(jl2);
		BookTypeDao dao = new BookTypeDao();
		data.addAll(dao.queryBookType());
		jc2.setModel(new DefaultComboBoxModel<BookType>(data));
		jp2.add(jc2);
		jp3.add(jl3);
		jp3.add(jt3);
		jp4.add(jl4);
		jp4.add(jt4);
		jp5.add(jl5);
		jp5.add(jt5);
		jp6.add(jl6);
		jp6.add(jt6);
		jp7.add(ok);
		jp7.add(cancel);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jp6);
		this.add(jp7);
	}
	public void addEvent(){
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//1.获取用户在组件中录入的数据，并进行校验(校验为选作内容)
				String bookname = jt1.getText().trim();
				BookType bt = (BookType) jc2.getSelectedItem();
				String author = jt3.getText().trim();
				String ISBN = jt4.getText().trim();
				String price = jt5.getText().trim();
				String page = jt6.getText().trim();
				/**
				 * 非空校验
				 */
				if(bookname.equals("")){
					JOptionPane.showMessageDialog(null, "图书名称不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(author.equals("")){
					JOptionPane.showMessageDialog(null, "作者不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(ISBN.equals("")){
					JOptionPane.showMessageDialog(null, "出版社不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(price.equals("")){
					JOptionPane.showMessageDialog(null, "价格不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(price.matches("^[0-9]+\\.{0,1}[0-9]{0,2}$")){
					JOptionPane.showMessageDialog(null, "价格必须为整数或小数！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(page.equals("")){
					JOptionPane.showMessageDialog(null, "页数不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!page.matches("^[0-9]+$")){
					JOptionPane.showMessageDialog(null, "页数必须为整数！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				//2.将数据保存到POJO对象中
				Bookinfo book = new Bookinfo();
				book.setId(id);
				book.setBookname(bookname);
				book.setBt(bt);
				book.setAuthor(author);
				book.setISBN(ISBN);
				book.setPrice(Double.parseDouble(price));
				book.setPage(Integer.parseInt(page));
				//3.通过封装好Dao将数据保存到数据库中
				BookinfoDao dao = new BookinfoDao();
				boolean flag = dao.editBookinfo(book);
				if(flag){
					JOptionPane.showMessageDialog(null, "修改成功！", "信息提示", JOptionPane.INFORMATION_MESSAGE);
					//关闭添加窗口
					dispose();
					ui.setTableData();
				}else{
					JOptionPane.showMessageDialog(null, "修改失败！", "错误提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				showData();
			}
		});
	}
	public void showData(){
		BookinfoDao dao = new BookinfoDao();
		Bookinfo book = dao.getBookinfo(id);
		jt1.setText(book.getBookname());
		jc2.setSelectedItem(book.getBt());
		jt3.setText(book.getAuthor());
		jt4.setText(book.getISBN());
		jt5.setText(book.getPrice()+"");
		jt6.setText(book.getPage()+"");
	}
}
