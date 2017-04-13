package com.address.list.action.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.address.list.frame.common.Constant;
import com.address.list.frame.common.FlyDialog;
import com.address.list.frame.main.UserFrame;
import com.address.list.model.UserDao;

/**
 * 注销Action
 * @author Tim
 *
 */
public class LogoutBtnLisn implements ActionListener
{
	private UserFrame user;
	private String type;

	public LogoutBtnLisn(UserFrame user, String type)
	{
		this.user = user;
		this.type = type;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(this.type.equals(Constant.LOG_OUT))
		{
			this.user.getLoginBox().getLoginFrame().setVisible(true);
			this.user.getUserFrame().dispose();
		}
		else if(this.type.equals(Constant.DEL_OUT))
		{
			int option=JOptionPane.showConfirmDialog(user.getUserFrame(), "此操作将删除当前用户及所有联系人信息并退出，确认执行?", "确认", JOptionPane.YES_NO_OPTION);
			if (option==JOptionPane.YES_OPTION)
			{
				if(UserDao.getInstance().delUser(user.getUsername()))
				{
					this.user.getLoginBox().clear();
					this.user.getLoginBox().getLoginFrame().setVisible(true);
					this.user.getUserFrame().dispose();
				}
				else
				{
					new FlyDialog(user.getUserFrame(), "执行失败，请重试");
				}
			}
			
		}
	}

}
