package com.address.list.action;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.text.JTextComponent;

/**
 *  JTextComponent控件监听器，预设文本，获焦点自动清除
 * @author Alex
 *
 */
public class JTextComponentFocuseLisn implements FocusListener
{
    private JTextComponent textComponent;
    private String text;
    
    private Color tempColor=new Color(120,120,120);//预设字体颜色
	private Color fontColor=new Color(0,0,0);	//正常字体颜色


	public JTextComponentFocuseLisn(JTextComponent textComponent, String text)
	{
		this.textComponent = textComponent;
		this.text = text;
	}

	public void focusGained(FocusEvent e)
	{
		if (textComponent.getText().equals(text))
		{
			textComponent.setText("");
			textComponent.setForeground(fontColor);			
		}
	}

	public void focusLost(FocusEvent e)
	{
		if (textComponent.getText().matches(" *"))
		{
			textComponent.setText(text);
			textComponent.setForeground(tempColor);
		}
	}

}
