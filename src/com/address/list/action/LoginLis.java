package com.address.list.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.address.list.frame.login.LoginBox;
import com.address.list.frame.main.UserFrame;
import com.address.list.model.UserDao;

/**
 * 登陆监听器
 * 
 * @author Alex
 * 
 */
public class LoginLis implements ActionListener
{
	private LoginBox login;

	public LoginLis(LoginBox login)
	{
		this.login = login;
	}

	public void actionPerformed(ActionEvent e)
	{
		//准备参数
		String username=login.getUserField().getText();
		char[] charpassword = login.getPasswordField().getPassword();
		String password="";
		for (int i = 0; i < charpassword.length; i++)
		{
			password=password+charpassword[i];
		}
		//执行SQL操作
		boolean b = UserDao.getInstance().login(username, password);
		//根据结果做出相应处理
		if (b)
		{
			login.getSysIco().setUserFrame(new UserFrame(username));
			login.getLoginFrame().dispose();
			if(login.getRemember().isSelected())
			{
				setAutoLogin(username);
			}
		} 
		else
		{
			JOptionPane.showMessageDialog(login.getLoginFrame(),
					"登录失败，原因可能有以下几种：\n" +
					"0.用户名空;\n"+
					"1.用户名不存在;\n" +
					"2.密码错误;\n" );
			login.getUserField().requestFocus();
		}
	}
	
	private void setAutoLogin(String username)
	{
		UserDao.getInstance().setDefaultLogin(username);
	}
}
