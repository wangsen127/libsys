package com.libsys.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.libsys.book.ui.BookTypeUI;
import com.libsys.book.ui.BookinfoUI;
import com.libsys.borrow.ui.BorrowBackUI;
import com.libsys.manager.pojo.Manager;
import com.libsys.manager.ui.ManagerUI;
import com.libsys.reader.ui.ReaderUI;
import com.libsys.reader.ui.ReadertypeUI;

/**
 * ���˵�
 * @author Administrator
 *
 */
public class MainUI extends JFrame{

	/**
	 * ���������������Ϊ�ô���������
	 */
	//�˵���
	private JMenuBar bar = new JMenuBar();
	//�˵�
	private JMenu m1 = new JMenu("���߹���");
	private JMenu m2 = new JMenu("ͼ�����");
	private JMenu m3 = new JMenu("ͼ��軹");
	private JMenu m4 = new JMenu("ϵͳ��ѯ");
	private JMenu m5 = new JMenu("���а�");
	private JMenu m6 = new JMenu("ϵͳ����");
	//�˵���
	private JMenuItem i11 = new JMenuItem("�������͹���");
	private JMenuItem i12 = new JMenuItem("���ߵ�������");
	private JMenuItem i21 = new JMenuItem("ͼ�����͹���");
	private JMenuItem i22 = new JMenuItem("ͼ�鵵������");
	private JMenuItem i3 = new JMenuItem("ͼ��軹����");
	private JMenuItem i41 = new JMenuItem("ͼ�鵵����ѯ");
	private JMenu i42 = new JMenu("ͼ����Ĳ�ѯ");
	private JMenuItem i421 = new JMenuItem("�ѽ��ѯ");
	private JMenuItem i422 = new JMenuItem("���ڲ�ѯ");
	private JMenuItem i51 = new JMenuItem("ͼ�����а�");
	private JMenuItem i52 = new JMenuItem("�������а�");
	private JMenuItem i61 = new JMenuItem("�û�����");
	private JMenuItem i62 = new JMenuItem("ͼ�����Ϣ");
	private JMenuItem i63 = new JMenuItem("�޸�����");
	private JMenuItem i64 = new JMenuItem("�˳�ϵͳ");
	private JLabel jl1 = new JLabel("��ӭʹ��ͼ�����ϵͳ");
	private JLabel jl2 = new JLabel();
	private JPanel jp = new JPanel();
	//��ǰ��¼���û�
	private Manager manager;
	
	public MainUI(Manager manager){
		this.manager = manager;
		this.setTitle("ͼ��ݹ���ϵͳ");
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addComponent();
		addListener();
		
		this.setVisible(true);
	}
	public void addComponent(){
		//Ȩ���ж�
		if(manager.getRole().equals("����Ա")){
			adminRole();
		} else if(manager.getRole().equals("����Ա")){
			operatorRole();
		}else{
			readerRole();
		}
		//���˵������뵽������
		this.setJMenuBar(bar);
		
		jp.setLayout(new GridLayout(1,2));
		jp.add(jl1);
		jp.add(jl2);
		jl2.setText(manager.getRole() + ":" + manager.getUsername());
		this.add(jp, BorderLayout.SOUTH);
	}
	/**
	 * ������м����¼�
	 */
	public void addListener(){
		i11.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ReadertypeUI();
			}
		});
		i12.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ReaderUI();
			}
		});
		i21.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new BookTypeUI();
			}
		});
		i22.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new BookinfoUI();
			}
		});
		i3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new BorrowBackUI();
			}
		});
		i61.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ManagerUI();
			}
		});
		i63.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ChangePwdUI(manager);
			}
		});
		i64.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "���Ƿ�Ҫ�˳�ϵͳ��?", "�˳���ʾ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(n == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});
	}
	public void adminRole(){
		//�Ƚ��˵�����뵽��Ӧ�Ĳ˵�
		m1.add(i11);
		m1.add(i12);
		m2.add(i21);
		m2.add(i22);
		m3.add(i3);
		m4.add(i41);
		m4.add(i42);
		i42.add(i421);
		i42.add(i422);
		m5.add(i51);
		m5.add(i52);
		m6.add(i61);
		m6.add(i62);
		m6.add(i63);
		m6.add(i64);
		//�ٽ����еĲ˵����뵽�˵�����
		bar.add(m1);
		bar.add(m2);
		bar.add(m3);
		bar.add(m4);
		bar.add(m5);
		bar.add(m6);
	}
	public void operatorRole(){
		m1.add(i11);
		m1.add(i12);
		m2.add(i21);
		m2.add(i22);
		m3.add(i3);
		m4.add(i41);
		m4.add(i42);
		i42.add(i421);
		i42.add(i422);
		m5.add(i51);
		m5.add(i52);
		m6.add(i62);
		m6.add(i63);
		m6.add(i64);
		
		bar.add(m1);
		bar.add(m2);
		bar.add(m3);
		bar.add(m4);
		bar.add(m5);
		bar.add(m6);
	}
	public void readerRole(){
		m4.add(i41);
		m4.add(i42);
		i42.add(i421);
		i42.add(i422);
		m5.add(i51);
		m5.add(i52);
		m6.add(i62);
		m6.add(i63);
		m6.add(i64);
		
		bar.add(m4);
		bar.add(m5);
		bar.add(m6);		
	}
	
}
