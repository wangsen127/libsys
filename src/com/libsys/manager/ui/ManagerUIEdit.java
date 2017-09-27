package com.libsys.manager.ui;

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

import com.libsys.manager.dao.ManagerDao;
import com.libsys.manager.pojo.Manager;

public class ManagerUIEdit extends JFrame{

	private JLabel jl1 = new JLabel("用户名称");
	private JLabel jl2 = new JLabel("密码");
	private JLabel jl3 = new JLabel("权限");
	private JTextField jt1 = new JTextField(10);
	private JTextField jt2 = new JTextField(10);
	private Vector<String> data = new Vector<String>();
	private JComboBox<String> jc3 = new JComboBox<String>();
	private JButton ok = new JButton("确定");
	private JButton cancel = new JButton("取消");
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	private JPanel jp4 = new JPanel();
	private ManagerUI ui;
	private int id;
	
	public ManagerUIEdit(ManagerUI ui, int id){
		this.ui = ui;
		this.id = id;
		this.setTitle("修改用户信息");
		this.setSize(280, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addComponent();
		addEvent();
		showData();
		
		this.setVisible(true);
	}
	public void addComponent(){
		this.setLayout(new GridLayout(4, 1));
		jp1.add(jl1);
		jp1.add(jt1);
		jp2.add(jl2);
		jp2.add(jt2);
		jp3.add(jl3);
		data.addAll(Arrays.asList("管理员","操作员","读者"));
		jc3.setModel(new DefaultComboBoxModel<String>(data));
		jp3.add(jc3);
		jp4.add(ok);
		jp4.add(cancel);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
	}
	public void addEvent(){
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//1.获取用户在组件中录入的数据，并进行校验(校验为选作内容)
				String username = jt1.getText().trim();
				String pwd = jt2.getText().trim();
				String role = (String) jc3.getSelectedItem();
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
				//2.将数据保存到POJO对象中
				Manager manager = new Manager();
				manager.setId(id);
				manager.setUsername(username);
				manager.setPwd(pwd);
				manager.setRole(role);
				//3.通过封装好Dao将数据保存到数据库中
				ManagerDao dao = new ManagerDao();
				boolean flag = dao.editManager(manager);
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
		ManagerDao dao = new ManagerDao();
		Manager manager = dao.getManager(id);
		jt1.setText(manager.getUsername());
		jt2.setText(manager.getPwd());
		jc3.setSelectedItem(manager.getRole());
	}
}
