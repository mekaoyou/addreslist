package com.address.list.action.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.address.list.frame.common.FlyDialog;
import com.address.list.frame.main.ContactInforDialog;
import com.address.list.frame.main.QueryContactPanel;
import com.address.list.frame.main.UserFrame;
import com.address.list.model.ContactDao;

/**
 * 编辑项目按钮的监听器
 * @author Alex
 *
 */
public class UpdateItemLisn implements ActionListener
{
	private UserFrame user;
	private QueryContactPanel panel;
	private ContactInforDialog dialog;
	private String date,address,gender,remarked,type,name;
	private int row;
	private String moble;
	private Object itemid;

	public UpdateItemLisn(UserFrame user, QueryContactPanel panel)
	{
		this.user = user;
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e)
	{
		row=user.getSelectPanel().getTable().getSelectedRow();
		itemid=null;
		if (row>=0)
		{
			itemid=panel.getTable().getValueAt(row, 0);
		}
		if (row!=-1&&itemid!=null)
		{
			dialog=new ContactInforDialog(user,panel);
			dialog.getDialog().setTitle("编辑联系人信息");
						
			//设置窗口控件可操作
			dialog.setEnabled(true);
			
			//添加提交按钮监听器
			dialog.getHandinBtn().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					handin();
				}				
			});
			
			dialog.getDialog().setVisible(true);
		}
	}
	
	/**
	 * 提交修改
	 */
	private void handin()
	{
		//准备数据
		date=dialog.getDateField().getText();
		address=dialog.getAddressField().getText();
		remarked=dialog.getRemarkArea().getText();
		moble=dialog.getMobleField().getText();
		if (dialog.getMaleBox().isSelected())
		{
			gender="男";
		}else
		{
			gender="女";
		}
		type = (String)dialog.getTypeBox().getSelectedItem();
		name = dialog.getItemField().getText();
		//处理数据
		if (moble.matches(" *"))
		{
			JOptionPane.showMessageDialog(dialog.getDialog(), "电话不能为空!");
		}
		else if(name.matches(" *"))
		{
			JOptionPane.showMessageDialog(dialog.getDialog(), "联系人姓名不能为空!");
		}
		else
		{
			if (remarked==null)
			{
				remarked="";
			}
			if (remarked.length()>100)
			{
				remarked=remarked.substring(0,100);
			}
			if (address.length()>20)
			{
				address=address.substring(0, 20);
			}
			//执行数据库操作
			if (ContactDao.getInstance().update((long)itemid, moble, gender, date, address, remarked, type, name))
			{
				//更新界面信息
				panel.getTable().setValueAt(name, row, 1);
				panel.getTable().setValueAt(moble, row, 2);
				panel.getTable().setValueAt(type, row, 3);
				//隐藏dialog
				dialog.getDialog().dispose();
				
				//显示提示信息
				new FlyDialog(user.getUserFrame(),"修改成功!");
				
				QueryContactPanel.isMove=true;//启动鼠标移动事件
			}
		}
	}

}
