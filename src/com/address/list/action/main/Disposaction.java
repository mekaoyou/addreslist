package com.address.list.action.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.address.list.frame.main.ContactInforDialog;
import com.address.list.frame.main.QueryContactPanel;

/**
 * 项目信息窗口上取消按钮的监听器
 * @author Alex
 *
 */
public class Disposaction implements ActionListener
{
	private ContactInforDialog dialog;
	public Disposaction(ContactInforDialog dialog)
	{
		this.dialog = dialog;
	}
	public void actionPerformed(ActionEvent e)
	{
		dialog.getDialog().dispose();
		
		QueryContactPanel.isMove=true;//启动鼠标移动事件
	}	
}