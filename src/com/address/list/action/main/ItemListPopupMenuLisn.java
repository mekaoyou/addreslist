package com.address.list.action.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * 联系人类型列表JList的鼠标右键菜单监听器
 * @author Alex
 *
 */
public class ItemListPopupMenuLisn implements MouseListener
{
	private JList list;
	private JPopupMenu pop;
	private JMenuItem popItem;

	public ItemListPopupMenuLisn(JList list)
	{
		this.list = list;
		initPopmenu();
	}

	public void mouseReleased(MouseEvent e)
	{
		list.requestFocus();
		if (e.isPopupTrigger())
		{
			pop.show(list, e.getX(), e.getY());	//显示右键菜单
			
			//获取鼠标焦点
			list.setSelectedIndex(row(e));
		}
	}
	
	/**
	 * 添加右键菜单
	 */
	private void initPopmenu()
	{
		pop=new JPopupMenu();
		popItem=new JMenuItem("删除");
		pop.add(popItem);
		list.add(pop);
	}
	
	/**
	 * 通过高度计算鼠标点击事件所在的行
	 * @return
	 */
	private int row(MouseEvent e)
	{
		return e.getY()/list.getFixedCellHeight();
	}

	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	
	public JMenuItem getPopItem(){return popItem;}
}

