package com.address.list.action.main;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.address.list.frame.main.QueryTelPanel;
import com.address.list.frame.main.UserFrame;

/**
 * 工具栏的搜索座机号Action
 * @author Tim
 *
 */
public class SelectTelAction extends AbstractAction
{
	private static final long serialVersionUID = 1L;
	private UserFrame user;

	public SelectTelAction(UserFrame user)
	{
		this.user = user;
		this.putValue(Action.NAME, "查询座机号");
	}

	public void actionPerformed(ActionEvent e)
	{
		if (!user.getQueryTelPanel().isVisible())
		{
			user.panelVisible(user.getQueryTelPanel());

			if (user.getQueryTelPanel()!=null)
			{
				user.getUserFrame().remove(user.getQueryTelPanel());
			}
			QueryTelPanel selectPanel=new QueryTelPanel(user,user.getUsername());
			user.setQueryTelPanel(selectPanel);
			user.getUserFrame().add(selectPanel);	
		}
	}

}
