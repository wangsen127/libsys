package com.libsys.reader.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.libsys.reader.dao.ReadertypeDao;
import com.libsys.reader.pojo.Readertype;

public class ReadertypeUISave extends JFrame{

	private JLabel jl = new JLabel("��������");
	private JTextField jt = new JTextField(10);
	private JButton ok = new JButton("ȷ��");
	private JButton cancel = new JButton("ȡ��");
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private ReadertypeUI ui;
	
	public ReadertypeUISave(ReadertypeUI ui){
		this.ui = ui;
		this.setTitle("��Ӷ�������");
		this.setSize(280, 120);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addComponent();
		addEvent();
		
		this.setVisible(true);
	}
	public void addComponent(){
		this.setLayout(new GridLayout(2, 1));
		jp1.add(jl);
		jp1.add(jt);
		jp2.add(ok);
		jp2.add(cancel);
		this.add(jp1);
		this.add(jp2);
	}
	public void addEvent(){
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//1.��ȡ�û��������¼������ݣ�������У��(У��Ϊѡ������)
				String name = jt.getText().trim();
				/**
				 * �ǿ�У��
				 */
				if(name.equals("")){
					JOptionPane.showMessageDialog(null, "�����������Ʋ���Ϊ�գ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
					return;
				}
				//2.�����ݱ��浽POJO������
				Readertype rt = new Readertype();
				rt.setName(name);
				//3.ͨ����װ��Dao�����ݱ��浽���ݿ���
				ReadertypeDao dao = new ReadertypeDao();
				boolean flag = dao.saveReadertype(rt);
				if(flag){
					JOptionPane.showMessageDialog(null, "��ӳɹ���", "��Ϣ��ʾ", JOptionPane.INFORMATION_MESSAGE);
					//�ر���Ӵ���
					dispose();
					ui.setTableData();
				}else{
					JOptionPane.showMessageDialog(null, "���ʧ�ܣ�", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				jt.setText("");
			}
		});
	}
}
