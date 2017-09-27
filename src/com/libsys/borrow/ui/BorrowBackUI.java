package com.libsys.borrow.ui;

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

import com.libsys.borrow.dao.BorrowBackDao;
import com.libsys.borrow.pojo.BorrowBack;
import com.libsys.util.MyTable;

public class BorrowBackUI extends JFrame{

	private JLabel jl = new JLabel("图书借还信息", JLabel.CENTER);
	private JTable table = new MyTable();
	private JScrollPane jsp = new JScrollPane(table);
	private Vector<String> head = new Vector<String>();
	private JPanel jp = new JPanel();
	private JButton save = new JButton("新增");
	private JButton edit = new JButton("修改");
	private JButton del = new JButton("删除");
	private JButton back = new JButton("还书");
	
	public BorrowBackUI(){
		this.setTitle("查询图书借还信息");
		this.setSize(600, 360);
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
		
		head.add("借还编号");
		head.add("读者姓名");
		head.add("图书名称");
		head.add("借阅时间");
		head.add("应还时间");
		head.add("操作员");
		this.add(jsp);
		
		jp.add(save);
		jp.add(edit);
		jp.add(del);
		jp.add(back);
		this.add(jp, BorderLayout.SOUTH);
	}
	public void addEvent(){
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new BorrowBackUISave(BorrowBackUI.this);
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
					new BorrowBackUIEdit(BorrowBackUI.this, id);
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
						BorrowBackDao dao = new BorrowBackDao();
						boolean flag = dao.delBorrowBack(id);
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
		back.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row == -1){
					JOptionPane.showMessageDialog(null, "请选择一条要还书的数据", "警告提示", JOptionPane.WARNING_MESSAGE);
				}else{
					int i = JOptionPane.showConfirmDialog(null, "你确定要还书吗?", "信息提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(i == JOptionPane.YES_OPTION){
						//从表格中获取指定行与指定列的数据
						int id = (Integer)table.getValueAt(row, 0);
						BorrowBackDao dao = new BorrowBackDao();
						boolean flag = dao.editBorrowBackForBack(id);
						if(flag){
							JOptionPane.showMessageDialog(null, "还书成功！", "信息提示", JOptionPane.INFORMATION_MESSAGE);
							setTableData();
						}else{
							JOptionPane.showMessageDialog(null, "还书失败！", "错误提示", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
	}
	public void setTableData(){
		Vector<Vector<Object>> body = new Vector<Vector<Object>>();
		//将数据库中数据取出，放入到表格所需的集合中
		BorrowBackDao dao = new BorrowBackDao();
		List<BorrowBack> list = dao.queryBorrowBack();
		//将List中的数据复制到表格所需的Vector中即可
		for (BorrowBack bb : list) {
			//创建小Vector对象，并把POJO对象中数据放入
			Vector<Object> v = new Vector<Object>();
			v.add(bb.getId());
			v.add(bb.getReader().getName());
			v.add(bb.getBook().getBookname());
			v.add(bb.getBorrowTime());
			v.add(bb.getBackTime());
			v.add(bb.getOperator());
			
			//小V放入大V中
			body.add(v);
		}
		DefaultTableModel model = new DefaultTableModel(body, head);
		table.setModel(model);
	}
}
