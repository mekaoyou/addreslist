package com.address.list.action.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.address.list.frame.common.FlyDialog;
import com.address.list.frame.main.ContactInforDialog;
import com.address.list.frame.main.QueryContactPanel;
import com.address.list.frame.main.UserFrame;
import com.address.list.model.ContactDao;


/**
 * 删除项目按钮的监听器
 * @author Alex
 *
 */
public class DelItemLisn implements ActionListener
{
	private UserFrame user;
	private QueryContactPanel panel;
	
	private int row;
	private Object itemid;

	public DelItemLisn(UserFrame user, QueryContactPanel panel)
	{
		this.user = user;
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e)
	{
		row=panel.getTable().getSelectedRow();
		itemid=null;
		if (row>=0)
		{
			itemid=panel.getTable().getValueAt(row, 0);			
		}
		if (row!=-1&&itemid!=null)
		{
			ContactInforDialog dialog=new ContactInforDialog(user,panel);
			
			dialog.getDialog().setTitle("确认删除该联系人?");
			dialog.getHandinBtn().setText("删除");
			
			//为删除按钮添加监听器
			dialog.getHandinBtn().addActionListener(new delAction(itemid,panel,row,dialog,user));
			
			dialog.getDialog().setVisible(true);			
		}
	}
	
	class delAction implements ActionListener
	{
		private Object itemid;
		private QueryContactPanel panel;
		private int row;
		private ContactInforDialog dialog;
		private UserFrame user;

		public delAction(Object itemid, QueryContactPanel panel,int row,ContactInforDialog dialog,UserFrame user)
		{
			this.itemid = itemid;
			this.panel = panel;
			this.row=row;
			this.dialog=dialog;
			this.user=user;
		}

		public void actionPerformed(ActionEvent e)
		{
			if (ContactDao.getInstance().delete((long)itemid))
			{
				//移除界面数据
				for (int j = 0; j < 3; j++)
				{
					panel.getTable().setValueAt(null, row, j);
				}
				dialog.getDialog().dispose();
				
				//显示提示信息
				new FlyDialog(user.getUserFrame(),"删除成功!");
				
				QueryContactPanel.isMove=true;//启动鼠标移动事件
			}
		}		
	}
}

