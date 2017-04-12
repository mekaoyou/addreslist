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
		String type = (String)panel.getTypeBox().getSelectedItem();
		Object[][] obj= ContactDao.getInstance().queryByContact(username, contact, type);
		//处理结果
		panel.initTabel(obj);
	}

}