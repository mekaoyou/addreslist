package com.address.list.action;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent;

/**
 * 鼠标右键监听器
 * 带右键菜单
 * @author Alex
 *
 */
public class PopupMenuAction implements MouseListener
{
	private JTextComponent component;

	private JPopupMenu pop;
	private JMenuItem copy,cut,paste;

	public PopupMenuAction(JTextComponent component)
	{
		this.component = component;
		popupmenu();
	}
	public void mouseReleased(MouseEvent e)
	{
		if (e.isPopupTrigger())
		{
	        pop.show(component, e.getX(), e.getY());//弹出菜单
	        component.requestFocus();//对象组件获得焦点
	        //调整菜单项可用性
	        copy.setEnabled(iscanCopy());
	        cut.setEnabled(iscanCopy());
	        paste.setEnabled(isClipboardString());
		}
	}
	private void popupmenu()
	{
		pop=new JPopupMenu();
		copy=new JMenuItem("复制");
		cut=new JMenuItem("剪切");
		paste=new JMenuItem("粘贴");
		copy.setAccelerator(KeyStroke.getKeyStroke('C',InputEvent.CTRL_DOWN_MASK));
		cut.setAccelerator(KeyStroke.getKeyStroke('X',InputEvent.CTRL_DOWN_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke('V',InputEvent.CTRL_DOWN_MASK));
		
		pop.add(copy);
		pop.add(cut);
		pop.add(paste);
		
		component.add(pop);
		
		//为菜单项添加事件
		copy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				component.copy();
			}			
		});
		cut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				component.cut();
			}
		});
		paste.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				component.paste();
			}
		});
	}
	
	/**
	 * 判断是否满足复制条件
	 * @return
	 */
	private boolean iscanCopy()
	{
		boolean b=false;
		int start=component.getSelectionStart();
		int end=component.getSelectionEnd();
		if (start!=end)
		{
			b=true;
		}
		return b;
	}
	/**
	 * 判断剪切板是否有数据可供粘贴
	 * @return
	 */
	private boolean isClipboardString()
	{
		boolean b=false;
		Clipboard clipboard=component.getToolkit().getSystemClipboard();
		Transferable content=clipboard.getContents(component);
		try
		{
			if (content.getTransferData(DataFlavor.stringFlavor) instanceof String)
			{
				b=true;
			}
		}
		catch (UnsupportedFlavorException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return b;
	}

	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
}
