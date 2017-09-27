package com.libsys.book.ui;

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

import com.libsys.book.dao.BookinfoDao;
import com.libsys.book.pojo.Bookinfo;
import com.libsys.util.MyTable;

public class BookinfoUI extends JFrame{

	private JLabel jl = new JLabel("图书信息", JLabel.CENTER);
	private JTable table = new MyTable();
	private JScrollPane jsp = new JScrollPane(table);
	private Vector<String> head = new Vector<String>();
	private JPanel jp = new JPanel();
	private JButton save = new JButton("新增");
	private JButton edit = new JButton("修改");
	private JButton del = new JButton("删除");
	
	public BookinfoUI(){
		this.setTitle("查询图书信息");
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
		
		head.add("图书编号");
		head.add("图书名称");
		head.add("图书类型");
		head.add("作者");
		head.add("出版社");
		head.add("价格");
		head.add("页数");
		head.add("录入时间");
		head.add("操作员");
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
				new BookinfoUISave(BookinfoUI.this);
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
					new BookinfoUIEdit(BookinfoUI.this, id);
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
						BookinfoDao dao = new BookinfoDao();
						boolean flag = dao.delBookinfo(id);
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
		BookinfoDao dao = new BookinfoDao();
		List<Bookinfo> list = dao.queryBookinfo();
		//将List中的数据复制到表格所需的Vector中即可
		for (Bookinfo Bookinfo : list) {
			//创建小Vector对象，并把POJO对象中数据放入
			Vector<Object> v = new Vector<Object>();
			v.add(Bookinfo.getId());
			v.add(Bookinfo.getBookname());
			v.add(Bookinfo.getBt().getTypename());
			v.add(Bookinfo.getAuthor());
			v.add(Bookinfo.getISBN());
			v.add(Bookinfo.getPrice());
			v.add(Bookinfo.getPage());
			v.add(Bookinfo.getInTime());
			v.add(Bookinfo.getOperator());
			
			//小V放入大V中
			body.add(v);
		}
		DefaultTableModel model = new DefaultTableModel(body, head);
		table.setModel(model);
	}
}
