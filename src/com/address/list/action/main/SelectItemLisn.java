package com.address.list.action.main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import com.address.list.frame.main.QueryContactPanel;
import com.address.list.model.ContactDao;

/**
 * 查询项目的监听器
 * @author Alex
 *
 */
public class SelectItemLisn implements ActionListener
{
	private String username;
	private QueryContactPanel panel;

	public SelectItemLisn(String username, QueryContactPanel panel)
	{
		this.username = username;
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e)
	{
		String contact = panel.getQueryField().getText();
		if(contact.matches(" *"))
		{
			return;
		}
		
		Object[][] obj=null;
		//执行查询
		obj=ContactDao.getInstance().queryByContact(username, contact);
		//处理结果
		if (obj==null)
		{
			obj=new Object[1][3];
		}
		
		Object[] columTitle={"ID","联系人","电话","分组"};
		//刷新表格，显示查询结果
		if (panel.getScrol()!=null)
		{
			panel.remove(panel.getTable());
		}
		JTable table=new JTable(obj,columTitle);
		table.setBackground(new Color(214,217,223));
		panel.setTable(table);
		panel.getScrol().setViewportView(panel.getTable());
		panel.add(panel.getScrol());
		
		//给table安装鼠标事件
		table.addMouseListener(new SelectMouseLisn(panel));
		table.addMouseMotionListener(new SelectMouseLisn(panel));
		
		QueryContactPanel.isMove=true;//启动鼠标移动事件
	}

}