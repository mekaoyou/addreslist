package com.address.list.frame.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 逐渐上升的Dialog
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class FlyDialog extends JDialog
{
	private Color bg=new Color(182,177,163);
	private Color fg=new Color(57,105,183);
	private String message;
	private int dialogX,dialogY;

	private Frame owner;
	
	
	public FlyDialog(Frame owner, String message)
	{
		super(owner);
		this.owner=owner;
		this.message=message;
		
		init();
		showDiaog();
	}
	
	/**
	 * 构建窗体
	 */
	private void init()
	{
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);
		JPanel panel=new JPanel();
		panel.setBackground(bg);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		JLabel label=new JLabel(message);
		label.setFont(new Font("SansSerif",Font.BOLD,18));
		label.setForeground(fg);
		panel.add(label);
		add(panel);
		this.pack();
		
		
	}
	/**
	 * 计算坐标并显示
	 */
	private void showDiaog()
	{
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		Dimension ownerSize=owner.getSize();
		Point ownerPiont=owner.getLocationOnScreen();
		
		Dimension dialogSize=this.getSize();
		
		dialogX=ownerPiont.x+(ownerSize.width-dialogSize.width)/2;
		dialogY=ownerPiont.y+(ownerSize.height-dialogSize.height)/2;
		
		dialogX=dialogX<0?0:dialogX;
		dialogX=dialogX+dialogSize.width>screenSize.width?screenSize.width-dialogSize.width:dialogX;
		dialogY=dialogY+dialogSize.height+100>screenSize.height?screenSize.height-dialogSize.height-100:dialogY;
		
		this.setLocation(dialogX, dialogY+100);
		this.setVisible(true);
		
		new flyThreand(this,dialogY).start();
	}
	/**
	 * 线程控制坐标
	 */
	class flyThreand extends Thread
	{
		private FlyDialog flyDialog;
		private int dialogY;

		public flyThreand(FlyDialog flyDialog, int dialogY)
		{
			this.flyDialog = flyDialog;
			this.dialogY = dialogY;
		}

		public void run()
		{
			try
			{
		    	int x=flyDialog.getLocationOnScreen().x;
			    int y=flyDialog.getLocationOnScreen().y;
			    while(y>dialogY)
			    {
				    y-=1;
				    flyDialog.setLocation(x, y);
				    sleep(8);
			    }
			    sleep(800);
			    flyDialog.dispose();
			    interrupt();
			} 
			catch (InterruptedException e)
			{				
			}
		}
	}
}
