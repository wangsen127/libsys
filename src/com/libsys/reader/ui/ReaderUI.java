package com.libsys.reader.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.libsys.reader.dao.ReaderDao;
import com.libsys.reader.pojo.Reader;
import com.libsys.util.MyTable;

public class ReaderUI extends JFrame{

	private JLabel jl = new JLabel("读者信息", JLabel.CENTER);
	private JTable table = new MyTable();
	private JScrollPane jsp = new JScrollPane(table);
	private Vector<String> head = new Vector<String>();
	private JPanel jp = new JPanel();
	private JButton save = new JButton("新增");
	private JButton edit = new JButton("修改");
	private JButton del = new JButton("删除");
	
	public ReaderUI(){
		this.setTitle("查询读者信息");
		this.setSize(860, 360);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addComponent();
		addEvent();
		setTableData();
		
		this.setVisible(true);
	}
	public void addComponent(){
		jl.setFont(new Font("楷体_GB2312",Font.BOLD,28));
		this.add(jl, BorderLayout.NORTH);
		
		head.add("读者编号");
		head.add("读者姓名");
		head.add("读者类型");
		head.add("性别");
		head.add("职业");
		head.add("出生日期");
		head.add("有效证件");
		head.add("证件号码");
		head.add("电话");
		head.add("电子邮件");
		head.add("登记日期");
		head.add("操作员");
		head.add("备注");
		this.add(jsp);
		
		jp.add(save);
		jp.add(edit);
		jp.add(del);
		this.add(jp, BorderLayout.SOUTH);
	}
	public void addEvent(){
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new ReaderUISave(ReaderUI.this);
			}
		});
		edit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row == -1){
					JOptionPane.showMessageDialog(null, "请选择一条要修改的数据", "警告提示", JOptionPane.WARNING_MESSAGE);
				}else{
					int id = (Integer)table.getValueAt(row, 0);
					new ReaderUIEdit(ReaderUI.this, id);
				}
			}
		});
		del.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row == -1){
					JOptionPane.showMessageDialog(null, "请选择一条要删除的数据", "警告提示", JOptionPane.WARNING_MESSAGE);
				}else{
					int i = JOptionPane.showConfirmDialog(null, "你确定要删除该条数据吗?", "删除提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(i == JOptionPane.YES_OPTION){
						//从表格中获取指定行与指定列的数据
						int id = (Integer)table.getValueAt(row, 0);
						ReaderDao dao = new ReaderDao();
						boolean flag = dao.delReader(id);
						if(flag){
							JOptionPane.showMessageDialog(null, "删除成功！", "信息提示", JOptionPane.INFORMATION_MESSAGE);
							setTableData();
						}else{
							JOptionPane.showMessageDialog(null, "删除失败！", "错误提示", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
	}
	public void setTableData(){
		Vector<Vector<Object>> body = new Vector<Vector<Object>>();
		//将数据库中数据取出，放入到表格所需的集合中
		ReaderDao dao = new ReaderDao();
		List<Reader> list = dao.queryReader();
		//将List中的数据复制到表格所需的Vector中即可
		for (Reader reader : list) {
			//创建小Vector对象，并把POJO对象中数据放入
			Vector<Object> v = new Vector<Object>();
			v.add(reader.getId());
			v.add(reader.getName());
			v.add(reader.getRt().getName());
			v.add(reader.getSex());
			v.add(reader.getVocation());
			v.add(reader.getBirthday());
			v.add(reader.getPaperType());
			v.add(reader.getPaperNO());
			v.add(reader.getTel());
			v.add(reader.getEmail());
			v.add(reader.getCreateDate());
			v.add(reader.getOperator());
			v.add(reader.getRemark());
			
			//小V放入大V中
			body.add(v);
		}
		DefaultTableModel model = new DefaultTableModel(body, head);
		table.setModel(model);
	}
}
