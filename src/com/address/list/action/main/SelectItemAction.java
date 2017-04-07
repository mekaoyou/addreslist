package com.address.list.action.main;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.address.list.frame.main.QueryContactPanel;
import com.address.list.frame.main.UserFrame;

/**
 * 工具栏的搜索Action
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class SelectItemAction extends AbstractAction
{
	private UserFrame user;

	public SelectItemAction(UserFrame user)
	{
		this.user = user;
		this.putValue(Action.NAME, "查询联系人");
	}

	public void actionPerformed(ActionEvent e)
	{
		if (!user.getSelectPanel().isVisible())
		{
			user.panelVisible(user.getSelectPanel());
			
			if (user.getSelectPanel()!=null)
			{
				user.getUserFrame().remove(user.getSelectPanel());
			}
			QueryContactPanel selectPanel=new QueryContactPanel(user,user.getUsername());
			user.setSelectPanel(selectPanel);
			user.getUserFrame().add(user.getSelectPanel());		
			
			QueryContactPanel.isMove=true;//启动鼠标移动事件
		}
	}

}
