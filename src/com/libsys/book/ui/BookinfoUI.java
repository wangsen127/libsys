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

	private JLabel jl = new JLabel("ͼ����Ϣ", JLabel.CENTER);
	private JTable table = new MyTable();
	private JScrollPane jsp = new JScrollPane(table);
	private Vector<String> head = new Vector<String>();
	private JPanel jp = new JPanel();
	private JButton save = new JButton("����");
	private JButton edit = new JButton("�޸�");
	private JButton del = new JButton("ɾ��");
	
	public BookinfoUI(){
		this.setTitle("��ѯͼ����Ϣ");
		this.setSize(860, 360);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addComponent();
		addEvent();
		setTableData();
		
		this.setVisible(true);
	}
	public void addComponent(){
		jl.setFont(new Font("����_GB2312",Font.BOLD,28));
		this.add(jl, BorderLayout.NORTH);
		
		head.add("ͼ����");
		head.add("ͼ������");
		head.add("ͼ������");
		head.add("����");
		head.add("������");
		head.add("�۸�");
		head.add("ҳ��");
		head.add("¼��ʱ��");
		head.add("����Ա");
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
					JOptionPane.showMessageDialog(null, "��ѡ��һ��Ҫ�޸ĵ�����", "������ʾ", JOptionPane.WARNING_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "��ѡ��һ��Ҫɾ��������", "������ʾ", JOptionPane.WARNING_MESSAGE);
				}else{
					int i = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ������������?", "ɾ����ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(i == JOptionPane.YES_OPTION){
						//�ӱ���л�ȡָ������ָ���е�����
						int id = (Integer)table.getValueAt(row, 0);
						BookinfoDao dao = new BookinfoDao();
						boolean flag = dao.delBookinfo(id);
						if(flag){
							JOptionPane.showMessageDialog(null, "ɾ���ɹ���", "��Ϣ��ʾ", JOptionPane.INFORMATION_MESSAGE);
							setTableData();
						}else{
							JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�", "������ʾ", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
	}
	public void setTableData(){
		Vector<Vector<Object>> body = new Vector<Vector<Object>>();
		//�����ݿ�������ȡ�������뵽�������ļ�����
		BookinfoDao dao = new BookinfoDao();
		List<Bookinfo> list = dao.queryBookinfo();
		//��List�е����ݸ��Ƶ���������Vector�м���
		for (Bookinfo Bookinfo : list) {
			//����СVector���󣬲���POJO���������ݷ���
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
			
			//СV�����V��
			body.add(v);
		}
		DefaultTableModel model = new DefaultTableModel(body, head);
		table.setModel(model);
	}
}
