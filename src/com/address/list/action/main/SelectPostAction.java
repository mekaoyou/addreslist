package com.address.list.action.main;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.address.list.frame.main.QueryPostcodePanel;
import com.address.list.frame.main.UserFrame;

/**
 * 工具栏的搜索邮编Action
 * @author Tim
 *
 */
public class SelectPostAction extends AbstractAction
{
	private static final long serialVersionUID = 1L;
	private UserFrame user;

	public SelectPostAction(UserFrame user)
	{
		this.user = user;
		this.putValue(Action.NAME, "查询邮编");
	}

	public void actionPerformed(ActionEvent e)
	{
		if (!user.getQueryPostPanel().isVisible())
		{
			user.panelVisible(user.getQueryPostPanel());

			if (user.getQueryPostPanel()!=null)
			{
				user.getUserFrame().remove(user.getQueryPostPanel());
			}
			QueryPostcodePanel selectPanel=new QueryPostcodePanel(user,user.getUsername());
			user.setQueryPostPanel(selectPanel);
			user.getUserFrame().add(selectPanel);	
		}
	}

}
