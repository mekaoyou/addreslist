package com.address.list.action.main;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.address.list.frame.main.QueryPYPanel;
import com.address.list.frame.main.UserFrame;

/**
 * 工具栏的查询汉字拼音Action
 * @author Tim
 *
 */
public class SelectPYAction extends AbstractAction
{
	private static final long serialVersionUID = 1L;
	private UserFrame user;

	public SelectPYAction(UserFrame user)
	{
		this.user = user;
		this.putValue(Action.NAME, "查询汉字拼音");
	}

	public void actionPerformed(ActionEvent e)
	{
		if (!user.getQueryPYPanel().isVisible())
		{
			user.panelVisible(user.getQueryPYPanel());

			if (user.getQueryPYPanel()!=null)
			{
				user.getUserFrame().remove(user.getQueryPYPanel());
			}
			QueryPYPanel selectPanel=new QueryPYPanel(user,user.getUsername());
			user.setQueryPYPanel(selectPanel);
			user.getUserFrame().add(selectPanel);	
		}
	}

}
