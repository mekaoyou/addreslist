package com.address.list.frame.common;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPasswordField;

/**
 * 自定义组件，限制长度和禁止粘贴的密码输入框
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class limitPasswordField extends JPasswordField
{
	private int limit;//要限制的最大长度

	/**
	 * 构造器
	 * @param limit 最大长度
	 */
	public limitPasswordField(int limit)
	{
		super();
		this.limit = limit;
		addkeylisn();
	}
	
	private void addkeylisn()
	{
		this.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e)
			{
				if (islimit(limit))
				{
					e.consume();
				}
			}			
		});
	}
	
	private boolean islimit(int limit)
	{		
		char[] c=this.getPassword();
		String s="";
		for (int i = 0; i < c.length; i++)
		{
			s=s+c[i];
		}
		return s.length()>=limit;
	}

	//禁止粘贴
	@Override
	public void paste()
	{	
	}	
}
