package com.libsys.main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.libsys.manager.dao.ManagerDao;
import com.libsys.manager.pojo.Manager;

public class LoginUI extends JFrame{

	private JLabel jl1 = new JLabel("用户名称");
	private JLabel jl2 = new JLabel("密        码");
	private JTextField jt1 = new JTextField(10);
	private JPasswordField jt2 = new JPasswordField(10);
	private JButton ok = new JButton("登录");
	private JButton cancel = new JButton("取消");
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	
	public LoginUI(){
		this.setTitle("用户登录");
		this.setSize(280, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addComponent();
		addEvent();
		
		this.setVisible(true);
	}
	public void addComponent(){
		this.setLayout(new GridLayout(3, 1));
		jp1.add(jl1);
		jp1.add(jt1);
		jp2.add(jl2);
		jp2.add(jt2);
		jp3.add(ok);
		jp3.add(cancel);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
	}
	public void addEvent(){
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		jt1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					jt2.requestFocus();
				}
			}
		});
		jt2.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					doLogin();
				}
			}
		});
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				jt1.setText("");
				jt2.setText("");
			}
		});
	}
	public void doLogin(){
		//1.获取录入的数据，并进行校验(校验为选作内容)
		String username = jt1.getText().trim();
		String pwd = new String(jt2.getPassword());
		/**
		 * 非空校验
		 */
		if(username.equals("")){
			JOptionPane.showMessageDialog(null, "用户名称不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(pwd.equals("")){
			JOptionPane.showMessageDialog(null, "密码不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
			return;
		}
		//2.通过封装好Dao，进行登陆操作
		ManagerDao dao = new ManagerDao();
		Manager manager = dao.login(username, pwd);
		if(manager!=null){
			JOptionPane.showMessageDialog(null, "欢迎，用户：" + manager.getUsername(), "信息提示", JOptionPane.INFORMATION_MESSAGE);
			dispose();
			new MainUI(manager);
		}else{
			JOptionPane.showMessageDialog(null, "登录失败，用户名或密码错误！", "错误提示", JOptionPane.ERROR_MESSAGE);
		}
	}
}
