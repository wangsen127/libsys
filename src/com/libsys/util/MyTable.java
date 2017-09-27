package com.libsys.util;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
/**
 * �Զ��������
 * �޸�JTable����е�bug����
 */
public class MyTable extends JTable{
	private boolean cellEditable = false;
	private boolean tableHeader = false;
	private boolean center = false;
	public MyTable() {}
	public MyTable(DefaultTableModel tableModel) {
		super(tableModel);
	}
	public MyTable(boolean cellEditable,boolean tableHeader,boolean center) {
		this.cellEditable = cellEditable;
		this.tableHeader = tableHeader;
		this.center = center;
	}
	/**
	 * ��дJTable���isCellEditable(int row, int column)����
	 * ���ã�ʹ��񲻿ɱ༭
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return cellEditable;
	}
	/**
	 * ��дJTable���getTableHeader()����
	 * ���ã����ñ���в������ţ���������������ʾ
	 */
	@Override
	public JTableHeader getTableHeader() {
		//��ñ��ͷ����
		JTableHeader th = super.getTableHeader();
		//���ñ���в�������
        th.setReorderingAllowed(tableHeader);
        //��ñ��ͷ�ĵ�Ԫ�����
        DefaultTableCellRenderer hr = (DefaultTableCellRenderer) th.getDefaultRenderer(); 
        //��������������ʾ
        if(center)
        	hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);     
        return th;
	}
	/**
	 * ��дJTable���getDefaultRenderer(Class<?> columnClass)����
	 * ���ã����õ�Ԫ�����ݾ�����ʾ
	 */
	@Override
	public TableCellRenderer getDefaultRenderer(Class<?> columnClass) {
		//��ñ��ĵ�Ԫ�����
		DefaultTableCellRenderer cr = (DefaultTableCellRenderer) super.getDefaultRenderer(columnClass);                   
		//���õ�Ԫ�����ݾ�����ʾ
		if(center)
			cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);     
        return cr;
	}
}
