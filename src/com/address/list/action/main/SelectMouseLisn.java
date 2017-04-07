package com.address.list.action.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.address.list.frame.main.QueryContactPanel;

/**
 * 数据表的鼠标监听
 * @author Alex
 *
 */
public class SelectMouseLisn implements MouseListener,MouseMotionListener
{
	private QueryContactPanel selectPanel;
	private int rowHeight;
	private int row;

	public SelectMouseLisn(QueryContactPanel selectPanel)
	{
		this.selectPanel = selectPanel;
		rowHeight=selectPanel.getTable().getRowHeight();
	}

	/**
	 * 鼠标右键事件
	 */
	public void mouseReleased(MouseEvent e)
	{
		if (e.isPopupTrigger())
		{
			//弹出右键
			selectPanel.getPop().show(selectPanel.getTable(), e.getX(), e.getY());
			//所在行获得焦点
			getFocues(e);
		}
	}
	/**
	 * 鼠标移动事件
	 */
	public void mouseMoved(MouseEvent e)
	{
		if (QueryContactPanel.isMove)
		{
			getFocues(e);				
		}
	}
	/**
	 * 鼠标单击事件
	 */
	public void mouseClicked(MouseEvent e)
	{
		QueryContactPanel.isMove=false;
		getFocues(e);
	}
	
	private void getFocues(MouseEvent e)
	{
		row=selectPanel.getTable().rowAtPoint(e.getPoint());
		selectPanel.getTable().setRowSelectionInterval(row, row);
		//调整行高
		selectPanel.getTable().setRowHeight(rowHeight);
		selectPanel.getTable().setRowHeight(row, rowHeight+10);		
	}
	
	public void mouseExited(MouseEvent e){}
	public void mouseDragged(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mousePressed(MouseEvent e){}

}
