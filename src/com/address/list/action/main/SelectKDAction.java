package com.address.list.action.main;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.address.list.frame.main.QueryKDPanel;
import com.address.list.frame.main.UserFrame;

/**
 * 工具栏的查询快递信息Action
 * @author Tim
 *
 */
public class SelectKDAction extends AbstractAction
{
	private static final long serialVersionUID = 1L;
	private UserFrame user;

	public SelectKDAction(UserFrame user)
	{
		this.user = user;
		this.putValue(Action.NAME, "查询物流信息");
	}

	public void actionPerformed(ActionEvent e)
	{
		if (!user.getQueryKDPanel().isVisible())
		{
			user.panelVisible(user.getQueryKDPanel());

			if (user.getQueryKDPanel()!=null)
			{
				user.getUserFrame().remove(user.getQueryKDPanel());
			}
			QueryKDPanel selectPanel=new QueryKDPanel(user,user.getUsername());
			user.setQueryKDPanel(selectPanel);
			user.getUserFrame().add(selectPanel);	
		}
	}

}
