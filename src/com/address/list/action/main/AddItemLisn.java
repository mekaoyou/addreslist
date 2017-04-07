package com.address.list.action.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.address.list.frame.common.FlyDialog;
import com.address.list.frame.main.AddContactPanel;
import com.address.list.frame.main.UserFrame;
import com.address.list.model.ContactDao;

/**
 * 增加新收支项目的监听器
 * @author Alex
 *
 */
public class AddItemLisn implements ActionListener
{
	private AddContactPanel addPanel;
	private String username;
	private UserFrame user;
	private String contactName,date,address,gender,remarked, moble;
	
	public AddItemLisn(AddContactPanel addPanel,String username,UserFrame user)
	{
		this.addPanel = addPanel;
		this.username=username;
		this.user=user;
	}

	public void actionPerformed(ActionEvent e)
	{
		//获取用户输入
		contactName=(String) addPanel.getContactName().getText();
		date=addPanel.getDateField().getText();
		address=addPanel.getAddressField().getText();
		address = address.equals("请输入地址")?"":address;
		gender=(String) addPanel.getGenderBox().getSelectedItem();
		remarked=addPanel.getRemarkArea().getText();
		moble=addPanel.getSumField().getText();
		//处理数据
		if (moble.length()<=0 || contactName.equals("请输入联系人") || contactName.matches(" *"))
		{
			new FlyDialog(user.getUserFrame(),"请填写正确完整信息!");
		}
		else
		{			
			if (remarked==null||remarked.equals("100个字以内"))
			{
				remarked="";
			}
			if (remarked.length()>100)
			{
				remarked=remarked.substring(0, 100);
			}
			if (address.length()>20)
			{
				address=address.substring(0, 20);
			}
			if (ContactDao.getInstance().addContact(username, contactName, moble, date, address, gender, remarked))
			{
				//添加提示信息
				new FlyDialog(user.getUserFrame(),"添加成功!");
				
				addPanel.getDateField().setText("1990-01-01");
				addPanel.getAddressField().setText("");
				addPanel.getRemarkArea().setText("");
				addPanel.getSumField().setText("");				
			}
		}
	}
}
