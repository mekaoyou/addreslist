package com.address.list.action.main;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.address.list.frame.main.QueryIPPanel;
import com.address.list.frame.main.UserFrame;

/**
 * 工具栏的查询IPAction
 * @author Tim
 *
 */
public class SelectIPAction extends AbstractAction
{
	private static final long serialVersionUID = 1L;
	private UserFrame user;

	public SelectIPAction(UserFrame user)
	{
		this.user = user;
		this.putValue(Action.NAME, "查询IP");
	}

	public void actionPerformed(ActionEvent e)
	{
		if (!user.getQueryIPPanel().isVisible())
		{
			user.panelVisible(user.getQueryIPPanel());

			if (user.getQueryIPPanel()!=null)
			{
				user.getUserFrame().remove(user.getQueryIPPanel());
			}
			QueryIPPanel selectPanel=new QueryIPPanel(user,user.getUsername());
			user.setQueryIPPanel(selectPanel);
			user.getUserFrame().add(selectPanel);	
		}
	}

}
