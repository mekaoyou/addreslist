package com.address.list.action.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.address.list.frame.common.Constant;
import com.address.list.frame.main.ContactInforDialog;
import com.address.list.frame.main.QueryContactPanel;
import com.address.list.frame.main.UserFrame;

/**
 * 显示项目详细信息按钮的监听器
 * @author Alex
 *
 */
public class ShowItemInforLisn implements ActionListener
{
	private UserFrame user;
	private QueryContactPanel panel;
	
	public ShowItemInforLisn(UserFrame user,QueryContactPanel panel)
	{
		this.user = user;
		this.panel=panel;
	}
	public void actionPerformed(ActionEvent e)
	{
		int row=user.getSelectPanel().getTable().getSelectedRow();
		Object itemid=null;
		if (row>=0)
		{
			itemid=panel.getTable().getValueAt(row, 0);			
		}
		if (row!=-1&&itemid!=null)
		{
			ContactInforDialog dialog=new ContactInforDialog(user,panel,Constant.INFO);
			dialog.getDialog().setVisible(true);
		}
	}	
}