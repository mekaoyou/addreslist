package com.address.list.frame.common;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * 自定义组件，获取当前系统时间
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class DateAndTime extends JPanel
{
	private JLabel timeLabel;
	private Timer timer;
	
	/**
	 * 构造器
	 */
	public DateAndTime()
	{
		setBorder(BorderFactory.createRaisedBevelBorder());
		setLayout(new BorderLayout(10,0));
		
		JPanel rightPanel=new JPanel();
		rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
		timeLabel=new JLabel(dateAndTime());
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ActionListener action=new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				timeLabel.setText(dateAndTime());
			}			
		};
		timer=new Timer(1000,action);
		timer.start();
		rightPanel.add(timeLabel);
		add(rightPanel,BorderLayout.EAST);
	}
	
	/**
	 * 获得系统时间
	 * @return
	 */
	public String dateAndTime()
	{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now=sdf.format(date);
		return now;
	}	
	
}
