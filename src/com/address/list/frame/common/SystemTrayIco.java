package com.address.list.frame.common;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import com.address.list.frame.login.LoginBox;
import com.address.list.frame.main.UserFrame;

/**
 * 系统托盘图标
 * 
 * @author Geek
 *
 */
public class SystemTrayIco
{
	private TrayIcon trayicon;
	
	private LoginBox loginBox;
	private UserFrame userFrame;

	public SystemTrayIco(LoginBox loginBox)
	{
		this.loginBox = loginBox;
		
		init();
	}
	
	/**
	 * 添加系统托盘
	 */
	private void init()
	{
		if (SystemTray.isSupported())
		{
			SystemTray tray = SystemTray.getSystemTray();
			Image icon = loginBox.getLoginFrame().getToolkit().getImage("img/logo.png");
			trayicon = new TrayIcon(icon, "通讯录");
			trayicon.setImageAutoSize(true);
			trayicon.addMouseListener(new MouseListener()
			{
				public void mouseClicked(MouseEvent e)
				{
					if (e.getClickCount() == 2 && !loginBox.isAppOrFind())
					{
						if(userFrame != null)
						{
							userFrame.getUserFrame().setVisible(true);
							userFrame.getUserFrame().setExtendedState(JFrame.NORMAL);
							userFrame.getUserFrame().toFront();
						}
						else
						{
							loginBox.getLoginFrame().setVisible(true);
							loginBox.getLoginFrame().setExtendedState(JFrame.NORMAL);
							loginBox.getLoginFrame().toFront();
						}
					}
				}

				public void mouseEntered(MouseEvent e){}
				public void mouseExited(MouseEvent e){}
				public void mousePressed(MouseEvent e){}
				public void mouseReleased(MouseEvent e){}
			});
			try
			{
				tray.add(trayicon);
			} catch (AWTException e1)
			{
				e1.printStackTrace();
			}
		}
	}
	
	public void setUserFrame(UserFrame userFrame)
	{
		this.userFrame = userFrame;
	}
}
