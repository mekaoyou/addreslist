package com.address.list.frame.common;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import com.address.list.action.JTextComponentFocuseLisn;
import com.address.list.action.PopupMenuAction;



/**
 * 自定义有长度限制,带右键菜单,可以预设临时文本（获焦点自动清除）的文本框
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class LimitTextField extends JTextField	
{
	private int limit;//要限制的最大长度
	private String text;//要显示的文本
	private Color tempColor=new Color(120,120,120);//预设字体颜色

	/**
	 * 构造器
	 * 只限制长度
	 * @param limit
	 */
	public LimitTextField(int limit)
	{
		super();
		this.limit = limit;
		addkeylisn();
		initpopupmenu();
	}
	/**
	 * 构造器
	 * 限制长度，有预设文本
	 * @param limit
	 * @param text
	 */
	public LimitTextField(int limit, String text)
	{
		super();
		this.limit = limit;
		this.text = text;
		this.setText(text);
		this.setForeground(tempColor);
		addkeylisn();
		initpopupmenu();
		initFocuesLisn();
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
		String s=this.getText();
		return s.length()>=limit;
	}

	@Override
	public void paste()
	{
		super.paste();
		if (islimit(limit))
		{
			this.setText(this.getText().substring(0, limit));
		}
	}
	/**
	 * 添加右键菜单
	 */
	private void initpopupmenu()
	{
		this.addMouseListener(new PopupMenuAction(this));
	}
	/**
	 * 添加焦点监听
	 */
	private void initFocuesLisn()
	{
		this.addFocusListener(new JTextComponentFocuseLisn(this,text));
	}
}
