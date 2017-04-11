package com.address.list.action.main;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.address.list.frame.main.QueryMobilePanel;
import com.address.list.frame.main.UserFrame;

/**
 * 工具栏的搜索手机号Action
 * @author Tim
 *
 */
public class SelectMobleAction extends AbstractAction
{

	private static final long serialVersionUID = 1L;
	private UserFrame user;

	public SelectMobleAction(UserFrame user)
	{
		this.user = user;
		this.putValue(Action.NAME, "查询手机号");
	}

	public void actionPerformed(ActionEvent e)
	{
		if (!user.getQueryMoblePanel().isVisible())
		{
			user.panelVisible(user.getQueryMoblePanel());

			if (user.getQueryMoblePanel()!=null)
			{
				user.getUserFrame().remove(user.getQueryMoblePanel());
			}
			QueryMobilePanel selectPanel=new QueryMobilePanel(user,user.getUsername());
			user.setMoblePanel(selectPanel);
			user.getUserFrame().add(selectPanel);	
		}
	}

}
