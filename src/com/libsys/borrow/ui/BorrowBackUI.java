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

	private JLabel jl = new JLabel("ͼ��軹��Ϣ", JLabel.CENTER);
	private JTable table = new MyTable();
	private JScrollPane jsp = new JScrollPane(table);
	private Vector<String> head = new Vector<String>();
	private JPanel jp = new JPanel();
	private JButton save = new JButton("����");
	private JButton edit = new JButton("�޸�");
	private JButton del = new JButton("ɾ��");
	private JButton back = new JButton("����");
	
	public BorrowBackUI(){
		this.setTitle("��ѯͼ��軹��Ϣ");
		this.setSize(600, 360);
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
		
		head.add("�軹���");
		head.add("��������");
		head.add("ͼ������");
		head.add("����ʱ��");
		head.add("Ӧ��ʱ��");
		head.add("����Ա");
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
					JOptionPane.showMessageDialog(null, "��ѡ��һ��Ҫ�޸ĵ�����", "������ʾ", JOptionPane.WARNING_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "��ѡ��һ��Ҫɾ��������", "������ʾ", JOptionPane.WARNING_MESSAGE);
				}else{
					int i = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ������������?", "ɾ����ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(i == JOptionPane.YES_OPTION){
						//�ӱ���л�ȡָ������ָ���е�����
						int id = (Integer)table.getValueAt(row, 0);
						BorrowBackDao dao = new BorrowBackDao();
						boolean flag = dao.delBorrowBack(id);
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
		back.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row == -1){
					JOptionPane.showMessageDialog(null, "��ѡ��һ��Ҫ���������", "������ʾ", JOptionPane.WARNING_MESSAGE);
				}else{
					int i = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫ������?", "��Ϣ��ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(i == JOptionPane.YES_OPTION){
						//�ӱ���л�ȡָ������ָ���е�����
						int id = (Integer)table.getValueAt(row, 0);
						BorrowBackDao dao = new BorrowBackDao();
						boolean flag = dao.editBorrowBackForBack(id);
						if(flag){
							JOptionPane.showMessageDialog(null, "����ɹ���", "��Ϣ��ʾ", JOptionPane.INFORMATION_MESSAGE);
							setTableData();
						}else{
							JOptionPane.showMessageDialog(null, "����ʧ�ܣ�", "������ʾ", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
	}
	public void setTableData(){
		Vector<Vector<Object>> body = new Vector<Vector<Object>>();
		//�����ݿ�������ȡ�������뵽�������ļ�����
		BorrowBackDao dao = new BorrowBackDao();
		List<BorrowBack> list = dao.queryBorrowBack();
		//��List�е����ݸ��Ƶ���������Vector�м���
		for (BorrowBack bb : list) {
			//����СVector���󣬲���POJO���������ݷ���
			Vector<Object> v = new Vector<Object>();
			v.add(bb.getId());
			v.add(bb.getReader().getName());
			v.add(bb.getBook().getBookname());
			v.add(bb.getBorrowTime());
			v.add(bb.getBackTime());
			v.add(bb.getOperator());
			
			//СV�����V��
			body.add(v);
		}
		DefaultTableModel model = new DefaultTableModel(body, head);
		table.setModel(model);
	}
}
