package com.libsys.manager.ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.libsys.manager.dao.LibraryDao;
import com.libsys.manager.pojo.Library;

public class LibraryUIEdit extends JFrame{

	private JLabel jl1 = new JLabel("ͼ������ƣ�");
	private JLabel jl2 = new JLabel("��            ����");
	private JLabel jl3 = new JLabel("�� ϵ �� ���� ");
	private JLabel jl4 = new JLabel("�� ϵ �� ַ�� ");
	private JLabel jl5 = new JLabel("�� ϵ �� �䣺 ");
	private JLabel jl6 = new JLabel("ͼ�����ַ��");
	private JLabel jl7 = new JLabel("�� �� ʱ �䣺 ");
	private JLabel jl8 = new JLabel("��            �飺");
	private JTextField jt1 = new JTextField(10);
	private JTextField jt2 = new JTextField(10);
	private JTextField jt3 = new JTextField(10);
	private JTextField jt4 = new JTextField(10);
	private JTextField jt5 = new JTextField(10);
	private JTextField jt6 = new JTextField(10);
	private JTextField jt7 = new JTextField(10);
	private JTextField jt8 = new JTextField(10);
	private JButton ok = new JButton("ȷ��");
	private JButton cancel = new JButton("ȡ��");
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	private JPanel jp4 = new JPanel();
	private JPanel jp5 = new JPanel();
	private JPanel jp6 = new JPanel();
	private JPanel jp7 = new JPanel();
	private JPanel jp8 = new JPanel();
	private JPanel jp9 = new JPanel();
	private LibraryUI ui;
	private int id;
	
	public LibraryUIEdit(LibraryUI ui, int id){
		this.ui = ui;
		this.id = id;
		this.setTitle("�޸�ͼ�����Ϣ");
		this.setSize(280, 440);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addComponent();
		addEvent();
		showData();
		
		this.setVisible(true);
	}
	public void addComponent(){
		this.setLayout(new GridLayout(9, 1));
		jp1.setLayout(new FlowLayout(FlowLayout.LEFT,15,5));
		jp2.setLayout(new FlowLayout(FlowLayout.LEFT,15,5));
		jp3.setLayout(new FlowLayout(FlowLayout.LEFT,15,5));
		jp4.setLayout(new FlowLayout(FlowLayout.LEFT,15,5));
		jp5.setLayout(new FlowLayout(FlowLayout.LEFT,15,5));
		jp6.setLayout(new FlowLayout(FlowLayout.LEFT,15,5));
		jp7.setLayout(new FlowLayout(FlowLayout.LEFT,15,5));
		jp8.setLayout(new FlowLayout(FlowLayout.LEFT,15,5));
		jp1.add(jl1);
		jp1.add(jt1);
		jp2.add(jl2);
		jp2.add(jt2);
		jp3.add(jl3);
		jp3.add(jt3);
		jp4.add(jl4);
		jp4.add(jt4);
		jp5.add(jl5);
		jp5.add(jt5);
		jp6.add(jl6);
		jp6.add(jt6);
		jp7.add(jl7);
		jp7.add(jt7);
		jp8.add(jl8);
		jp8.add(jt8);
		jp9.add(ok);
		jp9.add(cancel);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jp6);
		this.add(jp7);
		this.add(jp8);
		this.add(jp9);
	}
	public void addEvent(){
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//1.��ȡ�û��������¼������ݣ�������У��(У��Ϊѡ������)
				String libraryname = jt1.getText().trim();
				String curator = jt2.getText().trim();
				String tel = jt3.getText().trim();
				String address = jt4.getText().trim();
				String email = jt5.getText().trim();
				String url = jt6.getText().trim();
				String createDate = jt7.getText().trim();
				String introduce = jt8.getText().trim();
				/**
				 * �ǿ�У��
				 */
				if(libraryname.equals("")){
					JOptionPane.showMessageDialog(null, "ͼ������Ʋ���Ϊ�գ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(curator.equals("")){
					JOptionPane.showMessageDialog(null, "�ݳ�����Ϊ�գ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(tel.equals("")){
					JOptionPane.showMessageDialog(null, "��ϵ�绰����Ϊ�գ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(address.equals("")){
					JOptionPane.showMessageDialog(null, "��ϵ��ַ����Ϊ�գ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(email.equals("")){
					JOptionPane.showMessageDialog(null, "��ϵ���䲻��Ϊ�գ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(url.equals("")){
					JOptionPane.showMessageDialog(null, "ͼ�����ַ����Ϊ�գ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(createDate.equals("")){
					JOptionPane.showMessageDialog(null, "����ʱ�䲻��Ϊ�գ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(introduce.equals("")){
					JOptionPane.showMessageDialog(null, "��鲻��Ϊ�գ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
					return;
				}
				//2.�����ݱ��浽POJO������
				Library lib = new Library();
				lib.setId(id);
				lib.setLibraryname(libraryname);
				lib.setCurator(curator);
				lib.setTel(tel);
				lib.setAddress(address);
				
				//3.ͨ����װ��Dao�����ݱ��浽���ݿ���
				LibraryDao dao = new LibraryDao();
				boolean flag = dao.editLibrary(lib);
				if(flag){
					JOptionPane.showMessageDialog(null, "�޸ĳɹ���", "��Ϣ��ʾ", JOptionPane.INFORMATION_MESSAGE);
					//�ر���Ӵ���
					dispose();
					ui.showData();
				}else{
					JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�", "������ʾ", JOptionPane.ERROR_MESSAGE);
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
		LibraryDao dao = new LibraryDao();
		Library lib = dao.getLibrary(id);
		jt1.setText(lib.getLibraryname());
		jt2.setText(lib.getCurator());
		jt3.setText(lib.getTel());
		jt4.setText(lib.getAddress());
		jt5.setText(lib.getEmail());
		jt6.setText(lib.getUrl());
		jt7.setText(lib.getCreateDate());
		jt8.setText(lib.getIntroduce());
	}
}
