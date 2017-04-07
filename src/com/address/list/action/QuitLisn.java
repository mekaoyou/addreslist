package com.address.list.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 退出监听器
 * @author Alex
 *
 */
public class QuitLisn implements ActionListener
{
	private JFrame frame;

	public QuitLisn(JFrame frame)
	{
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e)
	{
		int option=JOptionPane.showConfirmDialog(frame, "确定要退出?", "退出", JOptionPane.YES_NO_OPTION);
		if (option==JOptionPane.YES_OPTION)
		{
			System.exit(0);
		}
	}

}
