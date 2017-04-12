package com.address.list.frame.main;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JDialog;

import com.address.list.frame.main.interFace.UserEditPanel;
import com.address.list.model.ContactDao;
import com.address.list.model.ContactEntity;


/**
 * 显示项目详细信息的Dialog,编辑，删除，显示详情时调用
 * @author Alex
 *
 */
public class ContactInforDialog implements UserEditPanel
{
	private UserFrame user;//用户界面主窗体
	private QueryContactPanel panel;
	private JDialog dialog;//容器
	private AddContactPanel addPanel;

	public ContactInforDialog(UserFrame user,QueryContactPanel panel, String type)
	{
		this.user = user;
		this.panel=panel;
		init(type);
	}

	/**
	 * 构建窗体
	 */
	public void init(String type)
	{
		dialog=new JDialog(user.getUserFrame(),"联系人详细信息",true);
		dialog.setResizable(false);
		addPanel = new AddContactPanel(this.panel.getUsername(), user, type);
		addPanel.setInfoDiaog(dialog);
		
		int row=panel.getTable().getSelectedRow();
		//准备数据并加载
		long contact_id=(long)panel.getTable().getValueAt(row, 0);
		ContactEntity contact = ContactDao.getInstance().queryById(contact_id);
		addPanel.initData(contact);
		
		dialog.add(addPanel);

		//计算坐标
		Point p=user.getUserFrame().getLocationOnScreen();
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		int x=(p.x+650)>d.width?d.width-620:p.x+50;
		int y=(p.y+450)>d.height?d.height-450:p.y+50;
		x=x<0?0:x;		
		dialog.setBounds(x,y,620,450);
		
		//设置焦点按钮
		//dialog.getRootPane().setDefaultButton(handinBtn);
	}
	public void initContactType(){}
	public QueryContactPanel getPanel(){return panel;}
	public JDialog getDialog(){return dialog;}
	
	public String getUserName(){return panel.getUsername();}
	public AddContactPanel getAddPanel(){return this.addPanel;}
}
