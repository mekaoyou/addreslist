package com.address.list.action.main;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.address.list.frame.main.AddContactPanel;
import com.address.list.frame.main.UserFrame;

/**
 * 新增收支项目Action
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class AddItemAction extends AbstractAction
{
	private UserFrame user;

	public AddItemAction(UserFrame user)
	{
		this.user = user;
		this.putValue(Action.NAME, "新增联系人");
	}

	public void actionPerformed(ActionEvent e)
	{
		if (!user.getAddPanel().isVisible())
		{
			user.panelVisible(user.getAddPanel());
			
			if (user.getAddPanel()!=null)
			{
				user.getUserFrame().remove(user.getAddPanel());
			}
			AddContactPanel addPanel=new AddContactPanel(user.getUsername(),user);
			user.setAddPanel(addPanel);
			user.getUserFrame().add(user.getAddPanel());
		}
	}
}
