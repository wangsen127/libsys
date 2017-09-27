package com.libsys.book.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.libsys.book.dao.BookTypeDao;
import com.libsys.book.pojo.BookType;

public class BookTypeUIEdit extends JFrame{

	private JLabel jl = new JLabel("类型名称");
	private JTextField jt = new JTextField(10);
	private JButton ok = new JButton("确定");
	private JButton cancel = new JButton("取消");
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private BookTypeUI ui;
	private int id;
	
	public BookTypeUIEdit(BookTypeUI ui,int id){
		this.ui = ui;
		this.id = id;
		this.setTitle("修改图书类型");
		this.setSize(280, 120);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addComponent();
		addEvent();
		showData();
		
		this.setVisible(true);
	}
	public void addComponent(){
		this.setLayout(new GridLayout(2, 1));
		jp1.add(jl);
		jp1.add(jt);
		jp2.add(ok);
		jp2.add(cancel);
		this.add(jp1);
		this.add(jp2);
	}
	public void addEvent(){
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//1.获取用户在组件中录入的数据，并进行校验(校验为选作内容)
				String typename = jt.getText().trim();
				/**
				 * 非空校验
				 */
				if(typename.equals("")){
					JOptionPane.showMessageDialog(null, "图书类型名称不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				//2.将数据保存到POJO对象中
				BookType bt = new BookType();
				bt.setId(id);
				bt.setTypename(typename);
				//3.通过封装好Dao将数据修改到数据库中
				BookTypeDao dao = new BookTypeDao();
				boolean flag = dao.editBookType(bt);
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
		BookTypeDao dao = new BookTypeDao();
		BookType bt = dao.getBookType(id);
		jt.setText(bt.getTypename());
	}
}
