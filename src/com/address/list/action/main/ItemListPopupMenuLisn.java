package com.address.list.action.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * ��Ŀ�����б�JList������Ҽ��˵�������
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
			pop.show(list, e.getX(), e.getY());	//��ʾ�Ҽ��˵�
			
			//��ȡ��꽹��
			list.setSelectedIndex(row(e));
		}
	}
	
	/**
	 * ����Ҽ��˵�
	 */
	private void initPopmenu()
	{
		pop=new JPopupMenu();
		popItem=new JMenuItem("ɾ��");
		pop.add(popItem);
		list.add(pop);
	}
	
	/**
	 * ͨ���߶ȼ���������¼����ڵ���
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
