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

	private JLabel jl = new JLabel("������Ϣ", JLabel.CENTER);
	private JTable table = new MyTable();
	private JScrollPane jsp = new JScrollPane(table);
	private Vector<String> head = new Vector<String>();
	private JPanel jp = new JPanel();
	private JButton save = new JButton("����");
	private JButton edit = new JButton("�޸�");
	private JButton del = new JButton("ɾ��");
	
	public ReaderUI(){
		this.setTitle("��ѯ������Ϣ");
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
		
		head.add("���߱��");
		head.add("��������");
		head.add("��������");
		head.add("�Ա�");
		head.add("ְҵ");
		head.add("��������");
		head.add("��Ч֤��");
		head.add("֤������");
		head.add("�绰");
		head.add("�����ʼ�");
		head.add("�Ǽ�����");
		head.add("����Ա");
		head.add("��ע");
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
					JOptionPane.showMessageDialog(null, "��ѡ��һ��Ҫ�޸ĵ�����", "������ʾ", JOptionPane.WARNING_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "��ѡ��һ��Ҫɾ��������", "������ʾ", JOptionPane.WARNING_MESSAGE);
				}else{
					int i = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ������������?", "ɾ����ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(i == JOptionPane.YES_OPTION){
						//�ӱ���л�ȡָ������ָ���е�����
						int id = (Integer)table.getValueAt(row, 0);
						ReaderDao dao = new ReaderDao();
						boolean flag = dao.delReader(id);
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
		ReaderDao dao = new ReaderDao();
		List<Reader> list = dao.queryReader();
		//��List�е����ݸ��Ƶ���������Vector�м���
		for (Reader reader : list) {
			//����СVector���󣬲���POJO���������ݷ���
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
			
			//СV�����V��
			body.add(v);
		}
		DefaultTableModel model = new DefaultTableModel(body, head);
		table.setModel(model);
	}
}
