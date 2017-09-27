package com.libsys.manager.ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.libsys.manager.dao.LibraryDao;
import com.libsys.manager.pojo.Library;

public class LibraryUIEdit extends JFrame{

	private JLabel jl1 = new JLabel("图书馆名称：");
	private JLabel jl2 = new JLabel("馆            长：");
	private JLabel jl3 = new JLabel("联 系 电 话： ");
	private JLabel jl4 = new JLabel("联 系 地 址： ");
	private JLabel jl5 = new JLabel("联 系 邮 箱： ");
	private JLabel jl6 = new JLabel("图书馆网址：");
	private JLabel jl7 = new JLabel("建 馆 时 间： ");
	private JLabel jl8 = new JLabel("简            介：");
	private JTextField jt1 = new JTextField(10);
	private JTextField jt2 = new JTextField(10);
	private JTextField jt3 = new JTextField(10);
	private JTextField jt4 = new JTextField(10);
	private JTextField jt5 = new JTextField(10);
	private JTextField jt6 = new JTextField(10);
	private JTextField jt7 = new JTextField(10);
	private JTextField jt8 = new JTextField(10);
	private JButton ok = new JButton("确定");
	private JButton cancel = new JButton("取消");
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	private JPanel jp4 = new JPanel();
	private JPanel jp5 = new JPanel();
	private JPanel jp6 = new JPanel();
	private JPanel jp7 = new JPanel();
	private JPanel jp8 = new JPanel();
	private JPanel jp9 = new JPanel();
	private LibraryUI ui;
	private int id;
	
	public LibraryUIEdit(LibraryUI ui, int id){
		this.ui = ui;
		this.id = id;
		this.setTitle("修改图书馆信息");
		this.setSize(280, 440);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addComponent();
		addEvent();
		showData();
		
		this.setVisible(true);
	}
	public void addComponent(){
		this.setLayout(new GridLayout(9, 1));
		jp1.setLayout(new FlowLayout(FlowLayout.LEFT,15,5));
		jp2.setLayout(new FlowLayout(FlowLayout.LEFT,15,5));
		jp3.setLayout(new FlowLayout(FlowLayout.LEFT,15,5));
		jp4.setLayout(new FlowLayout(FlowLayout.LEFT,15,5));
		jp5.setLayout(new FlowLayout(FlowLayout.LEFT,15,5));
		jp6.setLayout(new FlowLayout(FlowLayout.LEFT,15,5));
		jp7.setLayout(new FlowLayout(FlowLayout.LEFT,15,5));
		jp8.setLayout(new FlowLayout(FlowLayout.LEFT,15,5));
		jp1.add(jl1);
		jp1.add(jt1);
		jp2.add(jl2);
		jp2.add(jt2);
		jp3.add(jl3);
		jp3.add(jt3);
		jp4.add(jl4);
		jp4.add(jt4);
		jp5.add(jl5);
		jp5.add(jt5);
		jp6.add(jl6);
		jp6.add(jt6);
		jp7.add(jl7);
		jp7.add(jt7);
		jp8.add(jl8);
		jp8.add(jt8);
		jp9.add(ok);
		jp9.add(cancel);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jp6);
		this.add(jp7);
		this.add(jp8);
		this.add(jp9);
	}
	public void addEvent(){
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//1.获取用户在组件中录入的数据，并进行校验(校验为选作内容)
				String libraryname = jt1.getText().trim();
				String curator = jt2.getText().trim();
				String tel = jt3.getText().trim();
				String address = jt4.getText().trim();
				String email = jt5.getText().trim();
				String url = jt6.getText().trim();
				String createDate = jt7.getText().trim();
				String introduce = jt8.getText().trim();
				/**
				 * 非空校验
				 */
				if(libraryname.equals("")){
					JOptionPane.showMessageDialog(null, "图书馆名称不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(curator.equals("")){
					JOptionPane.showMessageDialog(null, "馆长不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(tel.equals("")){
					JOptionPane.showMessageDialog(null, "联系电话不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(address.equals("")){
					JOptionPane.showMessageDialog(null, "联系地址不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(email.equals("")){
					JOptionPane.showMessageDialog(null, "联系邮箱不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(url.equals("")){
					JOptionPane.showMessageDialog(null, "图书馆网址不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(createDate.equals("")){
					JOptionPane.showMessageDialog(null, "建馆时间不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(introduce.equals("")){
					JOptionPane.showMessageDialog(null, "简介不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				//2.将数据保存到POJO对象中
				Library lib = new Library();
				lib.setId(id);
				lib.setLibraryname(libraryname);
				lib.setCurator(curator);
				lib.setTel(tel);
				lib.setAddress(address);
				
				//3.通过封装好Dao将数据保存到数据库中
				LibraryDao dao = new LibraryDao();
				boolean flag = dao.editLibrary(lib);
				if(flag){
					JOptionPane.showMessageDialog(null, "修改成功！", "信息提示", JOptionPane.INFORMATION_MESSAGE);
					//关闭添加窗口
					dispose();
					ui.showData();
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
		LibraryDao dao = new LibraryDao();
		Library lib = dao.getLibrary(id);
		jt1.setText(lib.getLibraryname());
		jt2.setText(lib.getCurator());
		jt3.setText(lib.getTel());
		jt4.setText(lib.getAddress());
		jt5.setText(lib.getEmail());
		jt6.setText(lib.getUrl());
		jt7.setText(lib.getCreateDate());
		jt8.setText(lib.getIntroduce());
	}
}
