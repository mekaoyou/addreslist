package com.address.list.action.main;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.address.list.frame.main.UserFrame;

@SuppressWarnings("serial")
public class UpdateUserInforsysAction extends AbstractAction
{
	private UserFrame user;
	public UpdateUserInforsysAction(UserFrame user)
	{
		this.user = user;
		this.putValue(Action.NAME, "修改账号资料");
	}

	public void actionPerformed(ActionEvent e)
	{
		user.getUserPanel().update();		
	}
}
