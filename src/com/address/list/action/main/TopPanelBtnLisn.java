package com.address.list.action.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.address.list.frame.main.UserFrame;

/**
 * 用户主窗体顶部按钮的监听器
 * @author Alex
 *
 */
public class TopPanelBtnLisn implements ActionListener
{
	private UserFrame user;
	private JButton btn;

	public TopPanelBtnLisn(UserFrame user, JButton btn)
	{
		this.user = user;
		this.btn = btn;
	}

	public void actionPerformed(ActionEvent e)
	{
		user.itemVisible(btn);
	}

}
