package com.libsys.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.libsys.book.ui.BookTypeUI;
import com.libsys.book.ui.BookinfoUI;
import com.libsys.borrow.ui.BorrowBackUI;
import com.libsys.manager.pojo.Manager;
import com.libsys.manager.ui.ManagerUI;
import com.libsys.reader.ui.ReaderUI;
import com.libsys.reader.ui.ReadertypeUI;

/**
 * 主菜单
 * @author Administrator
 *
 */
public class MainUI extends JFrame{

	/**
	 * 将窗体中组件定义为该窗体类属性
	 */
	//菜单栏
	private JMenuBar bar = new JMenuBar();
	//菜单
	private JMenu m1 = new JMenu("读者管理");
	private JMenu m2 = new JMenu("图书管理");
	private JMenu m3 = new JMenu("图书借还");
	private JMenu m4 = new JMenu("系统查询");
	private JMenu m5 = new JMenu("排行榜");
	private JMenu m6 = new JMenu("系统设置");
	//菜单项
	private JMenuItem i11 = new JMenuItem("读者类型管理");
	private JMenuItem i12 = new JMenuItem("读者档案管理");
	private JMenuItem i21 = new JMenuItem("图书类型管理");
	private JMenuItem i22 = new JMenuItem("图书档案管理");
	private JMenuItem i3 = new JMenuItem("图书借还管理");
	private JMenuItem i41 = new JMenuItem("图书档案查询");
	private JMenu i42 = new JMenu("图书借阅查询");
	private JMenuItem i421 = new JMenuItem("已借查询");
	private JMenuItem i422 = new JMenuItem("逾期查询");
	private JMenuItem i51 = new JMenuItem("图书排行榜");
	private JMenuItem i52 = new JMenuItem("读者排行榜");
	private JMenuItem i61 = new JMenuItem("用户管理");
	private JMenuItem i62 = new JMenuItem("图书馆信息");
	private JMenuItem i63 = new JMenuItem("修改密码");
	private JMenuItem i64 = new JMenuItem("退出系统");
	private JLabel jl1 = new JLabel("欢迎使用图书管理系统");
	private JLabel jl2 = new JLabel();
	private JPanel jp = new JPanel();
	//当前登录的用户
	private Manager manager;
	
	public MainUI(Manager manager){
		this.manager = manager;
		this.setTitle("图书馆管理系统");
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addComponent();
		addListener();
		
		this.setVisible(true);
	}
	public void addComponent(){
		//权限判断
		if(manager.getRole().equals("管理员")){
			adminRole();
		} else if(manager.getRole().equals("操作员")){
			operatorRole();
		}else{
			readerRole();
		}
		//将菜单栏加入到窗体中
		this.setJMenuBar(bar);
		
		jp.setLayout(new GridLayout(1,2));
		jp.add(jl1);
		jp.add(jl2);
		jl2.setText(manager.getRole() + ":" + manager.getUsername());
		this.add(jp, BorderLayout.SOUTH);
	}
	/**
	 * 在组件中加入事件
	 */
	public void addListener(){
		i11.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ReadertypeUI();
			}
		});
		i12.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ReaderUI();
			}
		});
		i21.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new BookTypeUI();
			}
		});
		i22.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new BookinfoUI();
			}
		});
		i3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new BorrowBackUI();
			}
		});
		i61.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ManagerUI();
			}
		});
		i63.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ChangePwdUI(manager);
			}
		});
		i64.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "您是否要退出系统呢?", "退出提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(n == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});
	}
	public void adminRole(){
		//先将菜单项加入到相应的菜单
		m1.add(i11);
		m1.add(i12);
		m2.add(i21);
		m2.add(i22);
		m3.add(i3);
		m4.add(i41);
		m4.add(i42);
		i42.add(i421);
		i42.add(i422);
		m5.add(i51);
		m5.add(i52);
		m6.add(i61);
		m6.add(i62);
		m6.add(i63);
		m6.add(i64);
		//再将所有的菜单加入到菜单栏中
		bar.add(m1);
		bar.add(m2);
		bar.add(m3);
		bar.add(m4);
		bar.add(m5);
		bar.add(m6);
	}
	public void operatorRole(){
		m1.add(i11);
		m1.add(i12);
		m2.add(i21);
		m2.add(i22);
		m3.add(i3);
		m4.add(i41);
		m4.add(i42);
		i42.add(i421);
		i42.add(i422);
		m5.add(i51);
		m5.add(i52);
		m6.add(i62);
		m6.add(i63);
		m6.add(i64);
		
		bar.add(m1);
		bar.add(m2);
		bar.add(m3);
		bar.add(m4);
		bar.add(m5);
		bar.add(m6);
	}
	public void readerRole(){
		m4.add(i41);
		m4.add(i42);
		i42.add(i421);
		i42.add(i422);
		m5.add(i51);
		m5.add(i52);
		m6.add(i62);
		m6.add(i63);
		m6.add(i64);
		
		bar.add(m4);
		bar.add(m5);
		bar.add(m6);		
	}
	
}
