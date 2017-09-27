package com.libsys.book.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.libsys.book.dao.BookTypeDao;
import com.libsys.book.dao.BookinfoDao;
import com.libsys.book.pojo.BookType;
import com.libsys.book.pojo.Bookinfo;

public class BookinfoUISave extends JFrame{

	private JLabel jl1 = new JLabel("ͼ������");
	private JLabel jl2 = new JLabel("ͼ������");
	private JLabel jl3 = new JLabel("����");
	private JLabel jl4 = new JLabel("������");
	private JLabel jl5 = new JLabel("�۸�");
	private JLabel jl6 = new JLabel("ҳ��");
	private JTextField jt1 = new JTextField(10);
	private Vector<BookType> data = new Vector<BookType>();
	private JComboBox<BookType> jc2 = new JComboBox<BookType>();
	private JTextField jt3 = new JTextField(10);
	private JTextField jt4 = new JTextField(10);
	private JTextField jt5 = new JTextField(10);
	private JTextField jt6 = new JTextField(10);
	private JButton ok = new JButton("ȷ��");
	private JButton cancel = new JButton("ȡ��");
	private JPanel jp1 = new JPanel();
	private JPanel jp2 = new JPanel();
	private JPanel jp3 = new JPanel();
	private JPanel jp4 = new JPanel();
	private JPanel jp5 = new JPanel();
	private JPanel jp6 = new JPanel();
	private JPanel jp7 = new JPanel();
	private BookinfoUI ui;
	
	public BookinfoUISave(BookinfoUI ui){
		this.ui = ui;
		this.setTitle("���ͼ����Ϣ");
		this.setSize(280, 360);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addComponent();
		addEvent();
		
		this.setVisible(true);
	}
	public void addComponent(){
		this.setLayout(new GridLayout(7, 1));
		jp1.add(jl1);
		jp1.add(jt1);
		jp2.add(jl2);
		BookTypeDao dao = new BookTypeDao();
		data.addAll(dao.queryBookType());
		jc2.setModel(new DefaultComboBoxModel<BookType>(data));
		jp2.add(jc2);
		jp3.add(jl3);
		jp3.add(jt3);
		jp4.add(jl4);
		jp4.add(jt4);
		jp5.add(jl5);
		jp5.add(jt5);
		jp6.add(jl6);
		jp6.add(jt6);
		jp7.add(ok);
		jp7.add(cancel);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jp6);
		this.add(jp7);
	}
	public void addEvent(){
		ok.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//1.��ȡ�û��������¼������ݣ�������У��(У��Ϊѡ������)
				String bookname = jt1.getText().trim();
				BookType bt = (BookType) jc2.getSelectedItem();
				String author = jt3.getText().trim();
				String ISBN = jt4.getText().trim();
				String price = jt5.getText().trim();
				String page = jt6.getText().trim();
				/**
				 * �ǿ�У��
				 */
				if(bookname.equals("")){
					JOptionPane.showMessageDialog(null, "ͼ�����Ʋ���Ϊ�գ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(author.equals("")){
					JOptionPane.showMessageDialog(null, "���߲���Ϊ�գ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(ISBN.equals("")){
					JOptionPane.showMessageDialog(null, "�����粻��Ϊ�գ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(price.equals("")){
					JOptionPane.showMessageDialog(null, "�۸���Ϊ�գ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(price.matches("^[0-9]+\\.{0,1}[0-9]{0,2}$")){
					JOptionPane.showMessageDialog(null, "�۸����Ϊ������С����", "������ʾ", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(page.equals("")){
					JOptionPane.showMessageDialog(null, "ҳ������Ϊ�գ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!page.matches("^[0-9]+$")){
					JOptionPane.showMessageDialog(null, "ҳ������Ϊ������", "������ʾ", JOptionPane.WARNING_MESSAGE);
					return;
				}
				//2.�����ݱ��浽POJO������
				Bookinfo book = new Bookinfo();
				book.setBookname(bookname);
				book.setBt(bt);
				book.setAuthor(author);
				book.setISBN(ISBN);
				book.setPrice(Double.parseDouble(price));
				book.setPage(Integer.parseInt(page));
				//3.ͨ����װ��Dao�����ݱ��浽���ݿ���
				BookinfoDao dao = new BookinfoDao();
				boolean flag = dao.saveBookinfo(book);
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
				jt1.setText("");
				jc2.setSelectedIndex(0);
				jt3.setText("");
				jt4.setText("");
				jt5.setText("");
				jt6.setText("");
			}
		});
	}
}
