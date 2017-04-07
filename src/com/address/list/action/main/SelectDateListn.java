package com.address.list.action.main;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import hysun.util.DateChooser;


/**
 * 弹出日期选择框的监听器
 * @author Alex
 *
 */
public class SelectDateListn implements ActionListener
{
	private JButton button;
	private JTextField textfield;
	private JDialog dialog;
	
	private DateChooser datechooser;

	/**
	 * 构造器
	 * @param button
	 * @param textfield
	 * @param dialog
	 */
	public SelectDateListn(JButton button, JTextField textfield, JDialog dialog)
	{
		this.button = button;
		this.textfield = textfield;
		this.dialog = dialog;
	}

	/**
	 * 构造器
	 * @param button
	 * @param textfield
	 */
	public SelectDateListn(JButton button,JTextField textfield)
	{
		this.button = button;
		this.textfield = textfield;
	}

	public void actionPerformed(ActionEvent e)
	{	
		if (dialog!=null)
		{
			datechooser=new DateChooser(dialog);
		}
		else
		{
			datechooser=new DateChooser();
		}
		init();
	}
	
	/**
	 * 初始化日期选择框
	 */
	private void init()
	{		
		Point p=button.getLocationOnScreen();//按钮位置
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();//屏幕尺寸
		Dimension bd=datechooser.getSize();//日期框尺寸
		Dimension btns=button.getSize();//按钮尺寸
		//日期框横坐标
		int x=(p.x-bd.width/2)<0?0:p.x-bd.width/2;
		x=(p.x+bd.width/2)>d.width?d.width-bd.width:x;
		//日期框纵坐标
		int y=(p.y+bd.height)>d.height?d.height-bd.height:p.y+btns.height;
		datechooser.setLocation(x,y);
		datechooser.setVisible(true);
		
		//为日期框添加监听器获取选择时间
		datechooser.addWindowListener(new WindowAdapter()
		{
			public void windowClosed(WindowEvent e)
			{
				Calendar calendar=datechooser.getSelectedDate();
				if (calendar!=null)
				{
					Date date=calendar.getTime();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					String nowdate=sdf.format(date);
					textfield.setText(nowdate);					
				}
			}
			public void windowDeactivated(WindowEvent e)
			{
				datechooser.dispose();
			}			
		});
	}
}