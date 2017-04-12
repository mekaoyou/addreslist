package com.address.list.action.main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.address.list.frame.common.FlyDialog;
import com.address.list.frame.common.LimitTextField;
import com.address.list.frame.main.interFace.UserEditPanel;
import com.address.list.model.ContactDao;

public class AddContactTypeLisn implements ActionListener
{
	private JFrame parentFrame;
	private UserEditPanel editPanel;
	
	public AddContactTypeLisn(JFrame parentFrame, UserEditPanel editPanel)
	{
		this.parentFrame = parentFrame;
		this.editPanel = editPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		init();
	}

	private void init()
	{
		JDialog addDialog = new JDialog(parentFrame, "增加联系人分组", true);
		addDialog.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		LimitTextField typeName = new LimitTextField(6);
		typeName.setColumns(15);
		addDialog.add(typeName);
		
		JButton addBtn = new JButton("添加");
		addDialog.add(addBtn);
		addBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				String name = typeName.getText();
				if(name.matches(" *"))
				{
					new FlyDialog(parentFrame, "联系人分组名不能为空!");
					return;
				}
				if(ContactDao.getInstance().addContactType(editPanel.getUserName(), name))
				{
					editPanel.initContactType();
					addDialog.dispose();
				}
				else
				{
					new FlyDialog(parentFrame, "添加失败，分组名重复!");
				}
			}
		});
		
		showDialg(addDialog);
	}
	

	/**显示设置窗口*/
	private void showDialg(JDialog addDialog)
	{
		Point p=parentFrame.getLocationOnScreen();
		
		//这里要考虑到弹出的子窗体位置会不会跑出屏幕边缘的问题
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		//横坐标
		int x=(p.x+400)>d.width?d.width-300:p.x+100;
		x=x<0?0:x;
		//纵坐标
		int y=(p.y+300)>d.height?d.height-220:p.y+100;
		y=y<0?0:y;
		
		addDialog.setLocation(x,y);
		addDialog.setSize(220,130);
		addDialog.setVisible(true);
	}
}
