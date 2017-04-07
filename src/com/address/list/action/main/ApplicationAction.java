package com.address.list.action.main;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.address.list.frame.common.UserinforsysPanel;

/**
 * 用户界面申请账号的Action
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class ApplicationAction extends AbstractAction
{
	private UserinforsysPanel userPanel;

	public ApplicationAction(UserinforsysPanel userPanel)
	{
		this.userPanel = userPanel;
		this.putValue(Action.NAME, "申请新账号");
	}

	public void actionPerformed(ActionEvent e)
	{
		userPanel.app();
	}

}