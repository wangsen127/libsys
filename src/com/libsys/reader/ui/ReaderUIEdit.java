package com.libsys.reader.ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.libsys.reader.dao.ReaderDao;
import com.libsys.reader.dao.ReadertypeDao;
import com.libsys.reader.pojo.Reader;
import com.libsys.reader.pojo.Readertype;
import com.libsys.util.MyCalendar;

public class ReaderUIEdit extends JFrame{

	private JLabel jl1 = new JLabel("读者姓名");
	private JLabel jl2 = new JLabel("读者类型");
	private JLabel jl3 = new JLabel("性别");
	private JLabel jl4 = new JLabel("职业");
	private JLabel jl5 = new JLabel("出生日期");
	private JLabel jl6 = new JLabel("有效证件");
	private JLabel jl7 = new JLabel("证件号码");
	private JLabel jl8 = new JLabel("电话");
	private JLabel jl9 = new JLabel("电子邮件");
	private JLabel jl10 = new JLabel("备注");
	private JTextField jt1 = new JTextField(10);
	private Vector<Readertype> data = new Vector<Readertype>();
	private JComboBox<Readertype> jc2 = new JComboBox<Readertype>();
	private JRadioButton jr31 = new JRadioButton("男",true);
	private JRadioButton jr32 = new JRadioButton("女");
	private JTextField jt4 = new JTextField(10);
	private JTextField jt5 = new JTextField(10);
	private JTextField jt6 = new JTextField(10);
	private JTextField jt7 = new JTextField(10);
	private JTextField jt8 = new JTextField(10);
	private JTextField jt9 = new JTextField(10);
	private JTextField jt10 = new JTextField(10);
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
	private JPanel jp10 = new JPanel();
	private JPanel jp11 = new JPanel();
	private ReaderUI ui;
	private int id;
	
	public ReaderUIEdit(ReaderUI ui, int id){
		this.ui = ui;
		this.id = id;
		this.setTitle("修改读者信息");
		this.setSize(280, 480);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addComponent();
		addEvent();
		showData();
		
		this.setVisible(true);
	}
	public void addComponent(){
		this.setLayout(new GridLayout(11, 1));
		jp1.add(jl1);
		jp1.add(jt1);
		jp2.add(jl2);
		/**
		 * 类型下拉列表中的数据是从类型表中动态查询出来的
		 * 所以数据需要连接数据库去查询获得
		 */
		ReadertypeDao dao = new ReadertypeDao();
		data.addAll(dao.queryReadertype());
		jc2.setModel(new DefaultComboBoxModel<Readertype>(data));
		jp2.add(jc2);
		jp3.add(jl3);
		/**
		 * 单选按钮需要加入按钮组中
		 */
		ButtonGroup bg = new ButtonGroup();
		bg.add(jr31);
		bg.add(jr32);
		jp3.add(jr31);
		jp3.add(jr32);
		jp4.add(jl4);
		jp4.add(jt4);
		jp5.add(jl5);
		//注册日历控件
		MyCalendar.getInstance().register(jt5);
		//设置不可编辑
		jt5.setEditable(false);
		//设置背景颜色
		jt5.setBackground(Color.WHITE);
		jp5.add(jt5);
		jp6.add(jl6);
		jp6.add(jt6);
		jp7.add(jl7);
		jp7.add(jt7);
		jp8.add(jl8);
		jp8.add(jt8);
		jp9.add(jl9);
		jp9.add(jt9);
		jp10.add(jl10);
		jp10.add(jt10);
		jp11.add(ok);
		jp11.add(cancel);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jp6);
		this.add(jp7);
		this.add(jp8);
		this.add(jp9);
		this.add(jp10);
		this.add(jp11);
	}
	public void addEvent(){
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//1.获取用户在组件中录入的数据，并进行校验(校验为选作内容)
				String name = jt1.getText().trim();
				Readertype rt = (Readertype) jc2.getSelectedItem();
				String sex = "";
				if(jr31.isSelected()){
					sex = jr31.getText();
				}else{
					sex = jr32.getText();
				}
				String vocation = jt4.getText().trim();
				String birthday = jt5.getText().trim();
				String paperType = jt6.getText().trim();
				String paperNO = jt7.getText().trim();
				String tel = jt8.getText().trim();
				String email = jt9.getText().trim();
				String remark = jt10.getText().trim();
				/**
				 * 非空校验
				 */
				if(name.equals("")){
					JOptionPane.showMessageDialog(null, "读者姓名不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(vocation.equals("")){
					JOptionPane.showMessageDialog(null, "职业不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(birthday.equals("")){
					JOptionPane.showMessageDialog(null, "出生日期不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(paperType.equals("")){
					JOptionPane.showMessageDialog(null, "有效证件不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(paperNO.equals("")){
					JOptionPane.showMessageDialog(null, "证件号码不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(tel.equals("")){
					JOptionPane.showMessageDialog(null, "电话不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(email.equals("")){
					JOptionPane.showMessageDialog(null, "电子邮件不能为空！", "警告提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				//2.将数据保存到POJO对象中
				Reader reader = new Reader();
				reader.setId(id);
				reader.setName(name);
				reader.setRt(rt);
				reader.setSex(sex);
				reader.setVocation(vocation);
				reader.setBirthday(birthday);
				reader.setPaperType(paperType);
				reader.setPaperNO(paperNO);
				reader.setTel(tel);
				reader.setEmail(email);
				reader.setRemark(remark);
				//3.通过封装好Dao将数据保存到数据库中
				ReaderDao dao = new ReaderDao();
				boolean flag = dao.editReader(reader);
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
		ReaderDao dao = new ReaderDao();
		Reader reader = dao.getReader(id);
		jt1.setText(reader.getName());
		jc2.setSelectedItem(reader.getRt());
		if(reader.getSex().equals(jr31.getText())){
			jr31.setSelected(true);
		}else{
			jr32.setSelected(true);
		}
		jt4.setText(reader.getVocation());
		jt5.setText(reader.getBirthday());
		jt6.setText(reader.getPaperType());
		jt7.setText(reader.getPaperNO());
		jt8.setText(reader.getTel());
		jt9.setText(reader.getEmail());
		jt10.setText(reader.getRemark());
	}
}
