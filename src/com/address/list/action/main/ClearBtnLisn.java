package com.address.list.action.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.address.list.frame.main.AddContactPanel;

/**
 * 清空按钮的监听器
 * @author Alex
 *
 */
public class ClearBtnLisn implements ActionListener
{
	private AddContactPanel addPanel;

	public ClearBtnLisn(AddContactPanel addPanel)
	{
		this.addPanel = addPanel;
	}

	public void actionPerformed(ActionEvent e)
	{
		addPanel.getDateField().setText("1990-01-01");
		addPanel.getAddressField().setText("");
		addPanel.getRemarkArea().setText("");
		addPanel.getSumField().setText("");
	}

}
