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

import com.libsys.reader.dao.ReadertypeDao;
import com.libsys.reader.pojo.Readertype;
import com.libsys.util.MyTable;

public class ReadertypeUI extends JFrame{

	private JLabel jl = new JLabel("����������Ϣ", JLabel.CENTER);
	private JTable table = new MyTable();
	private JScrollPane jsp = new JScrollPane(table);
	private Vector<String> head = new Vector<String>();
	private JPanel jp = new JPanel();
	private JButton save = new JButton("����");
	private JButton edit = new JButton("�޸�");
	private JButton del = new JButton("ɾ��");
	
	public ReadertypeUI(){
		this.setTitle("��ѯ��������");
		this.setSize(400, 360);
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
		
		head.add("���ͱ��");
		head.add("��������");
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
				new ReadertypeUISave(ReadertypeUI.this);
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
					new ReadertypeUIEdit(ReadertypeUI.this, id);
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
						ReadertypeDao dao = new ReadertypeDao();
						boolean flag = dao.delReadertype(id);
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
		ReadertypeDao dao = new ReadertypeDao();
		List<Readertype> list = dao.queryReadertype();
		//��List�е����ݸ��Ƶ���������Vector�м���
		for (Readertype rt : list) {
			//����СVector���󣬲���POJO���������ݷ���
			Vector<Object> v = new Vector<Object>();
			v.add(rt.getId());
			v.add(rt.getName());
			
			//СV�����V��
			body.add(v);
		}
		DefaultTableModel model = new DefaultTableModel(body, head);
		table.setModel(model);
	}
}
