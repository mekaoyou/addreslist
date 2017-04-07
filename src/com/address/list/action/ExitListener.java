package com.address.list.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

/**
 * 退出的监听器
 * @author Alex
 *
 */
public class ExitListener implements ActionListener,WindowListener
{
	private JFrame jframe;

	/**
	 * @param client
	 */
	public ExitListener(JFrame jframe)
	{
		this.jframe = jframe;
	}

	public void actionPerformed(ActionEvent e)
	{
		jframe.setVisible(false);
	}

	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}
	public void windowIconified(WindowEvent e)
	{
		jframe.setVisible(false);
	}	
	
	public void windowActivated(WindowEvent e){}	
	public void windowClosed(WindowEvent e){}	
	public void windowDeactivated(WindowEvent e){}	
	public void windowDeiconified(WindowEvent e){}	
	public void windowOpened(WindowEvent e){}
}
