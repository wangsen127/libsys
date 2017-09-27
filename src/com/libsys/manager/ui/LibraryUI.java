package com.libsys.manager.ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.libsys.manager.dao.LibraryDao;
import com.libsys.manager.pojo.Library;

public class LibraryUI extends JFrame{

	private JLabel jl1 = new JLabel("图书馆名称：");
	private JLabel jl2 = new JLabel("馆            长：");
	private JLabel jl3 = new JLabel("联 系 电 话： ");
	private JLabel jl4 = new JLabel("联 系 地 址： ");
	private JLabel jl5 = new JLabel("联 系 邮 箱： ");
	private JLabel jl6 = new JLabel("图书馆网址：");
	private JLabel jl7 = new JLabel("建 馆 时 间： ");
	private JLabel jl8 = new JLabel("简            介：");
	private JLabel jt1 = new JLabel();
	private JLabel jt2 = new JLabel();
	private JLabel jt3 = new JLabel();
	private JLabel jt4 = new JLabel();
	private JLabel jt5 = new JLabel();
	private JLabel jt6 = new JLabel();
	private JLabel jt7 = new JLabel();
	private JLabel jt8 = new JLabel();
	private JButton ok = new JButton("修改信息");
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	private JPanel jp4 = new JPanel();
	private JPanel jp5 = new JPanel();
	private JPanel jp6 = new JPanel();
	private JPanel jp7 = new JPanel();
	private JPanel jp8 = new JPanel();
	private JPanel jp9 = new JPanel();
	private static final int ID = 1;
	
	public LibraryUI(){
		this.setTitle("图书馆信息");
		this.setSize(440, 440);
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
				new LibraryUIEdit(LibraryUI.this, ID);
			}
		});
	}
	public void showData(){
		LibraryDao dao = new LibraryDao();
		Library lib = dao.getLibrary(ID);
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
