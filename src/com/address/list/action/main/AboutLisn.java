package com.address.list.action.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 关于按钮监听器
 * @author Alex
 *
 */
public class AboutLisn implements ActionListener
{
	private JFrame frame;

	public AboutLisn(JFrame frame)
	{
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e)
	{
		JOptionPane.showMessageDialog(frame, "通讯录(R)\n作者:Alex\n版本:1.0");
	}

}
