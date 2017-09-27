package com.libsys.util;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
/**
 * 自定义表格对象
 * 修改JTable表格中的bug问题
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
	 * 重写JTable类的isCellEditable(int row, int column)方法
	 * 作用：使表格不可编辑
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return cellEditable;
	}
	/**
	 * 重写JTable类的getTableHeader()方法
	 * 作用：设置表格列不可重排，设置列名居中显示
	 */
	@Override
	public JTableHeader getTableHeader() {
		//获得表格头对象
		JTableHeader th = super.getTableHeader();
		//设置表格列不可重排
        th.setReorderingAllowed(tableHeader);
        //获得表格头的单元格对象
        DefaultTableCellRenderer hr = (DefaultTableCellRenderer) th.getDefaultRenderer(); 
        //设置列名居中显示
        if(center)
        	hr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);     
        return th;
	}
	/**
	 * 重写JTable类的getDefaultRenderer(Class<?> columnClass)方法
	 * 作用：设置单元格内容居中显示
	 */
	@Override
	public TableCellRenderer getDefaultRenderer(Class<?> columnClass) {
		//获得表格的单元格对象
		DefaultTableCellRenderer cr = (DefaultTableCellRenderer) super.getDefaultRenderer(columnClass);                   
		//设置单元格内容居中显示
		if(center)
			cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);     
        return cr;
	}
}
