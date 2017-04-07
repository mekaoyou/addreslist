package com.address.list.frame.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.address.list.action.ExitListener;
import com.address.list.action.LoginLis;
import com.address.list.action.PopupMenuAction;
import com.address.list.frame.common.LimitTextField;
import com.address.list.frame.common.SystemTrayIco;
import com.address.list.frame.common.limitPasswordField;
import com.address.list.model.UserDao;

/**
 * 系统登录框
 * 可选择登录身份，管理员或普通用户
 * @author Alex
 *
 */
public class LoginBox
{
	private JFrame loginFrame;
	
	private LimitTextField userField;
	
	private limitPasswordField passwordField;
	
	private JLabel enrolLabel,findPwLabel;
	
	private Color linkFontColor=new Color(2,83,194);
	
	private boolean appOrFind;//是否正在执行申请账户或找回密码
	
	private SystemTrayIco sysIco;
	
	private JCheckBox remember;
	
	private LoginLis loginLis;

	/**
	 * 初始化登录框
	 */
	@SuppressWarnings("serial")
	public void init()
	{
		// 设置外观样式
		try
		{
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (InstantiationException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
		
		loginFrame=new JFrame("通讯录");
		Image logoImg=Toolkit.getDefaultToolkit().getImage("img/logo.png");
		loginFrame.setIconImage(logoImg);
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		int x=((d.width-340)/2);
		int y=((d.height-250)/2);
		loginFrame.setBounds(x, y, 340, 250);
		loginFrame.setResizable(false);//大小不可更改
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.addWindowListener(new ExitListener(loginFrame));
		
		//在窗体顶部绘制图片
		final Image img=Toolkit.getDefaultToolkit().createImage("img/loginimg.png");
		JPanel imgPanel=new JPanel()
		{
			protected void paintChildren(Graphics g)
			{
				g.drawImage(img, 0, 0, this);
				super.paintChildren(g);
			}
		};
		
		loginLis = new LoginLis(this);
		
		JLabel label=new JLabel("   ");//用于将顶部panel撑高
		imgPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,23));
		imgPanel.add(label);
		//输入账号区
		JPanel centerPanel=new JPanel();
		centerPanel.setBackground(new Color(182,177,163));
		centerPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		gbc.insets=new Insets(15,30,0,5);
				
		JLabel userLabel=new JLabel("账号:");
		centerPanel.add(userLabel,gbc);
		
		gbc.insets=new Insets(15,0,0,5);
		gbc.weightx=1;
		userField=new LimitTextField(10);
		userField.setColumns(15);
		userField.addActionListener(loginLis);//添加键盘事件
		userField.addMouseListener(new PopupMenuAction(userField));//添加右键菜单
		centerPanel.add(userField,gbc);
		
		gbc.insets=new Insets(15,0,0,20);
		gbc.weightx=0;
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		enrolLabel=new JLabel("注册新账号");
		enrolLabel.setForeground(linkFontColor);
		enrolLabel.addMouseListener(new EnrolAndFindPwLisn(this,enrolLabel));
		centerPanel.add(enrolLabel,gbc);
		
		gbc.insets=new Insets(10,30,0,5);
		gbc.gridwidth=1;
		JLabel passwordLable=new JLabel("密码:");
		centerPanel.add(passwordLable,gbc);
		
		gbc.insets=new Insets(10,0,0,5);
		gbc.weightx=1;
		passwordField=new limitPasswordField(16);
		passwordField.setColumns(15);
		passwordField.addActionListener(loginLis);
		centerPanel.add(passwordField,gbc);
		
		gbc.insets=new Insets(10,0,0,20);
		gbc.weightx=0;
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		findPwLabel=new JLabel("找回密码");
		findPwLabel.setForeground(linkFontColor);
		findPwLabel.addMouseListener(new EnrolAndFindPwLisn(this,findPwLabel));
		centerPanel.add(findPwLabel,gbc);
		
		gbc.insets=new Insets(0,30,0,0);
		gbc.gridwidth=1;
		centerPanel.add(new JLabel(),gbc);
		
		JPanel autoPanel=new JPanel();
		autoPanel.setOpaque(false);
		centerPanel.add(autoPanel,gbc);
		
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		centerPanel.add(new JLabel(),gbc);
		//选择登录身份
		autoPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		remember=new JCheckBox("记住密码");
		autoPanel.add(remember);
		
		//底部按钮区
		JPanel bottomPanel=new JPanel();
		bottomPanel.setBackground(new Color(191,189,174));
		bottomPanel.setLayout(new BorderLayout());
		
		JPanel bottomLeft=new JPanel();
		bottomPanel.add(bottomLeft,BorderLayout.WEST);
		JPanel bottomRight=new JPanel();
		bottomPanel.add(bottomRight,BorderLayout.EAST);		
		bottomLeft.setLayout(new FlowLayout(FlowLayout.CENTER,10,3));
		bottomRight.setLayout(new FlowLayout(FlowLayout.CENTER,10,3));
		bottomLeft.setOpaque(false);
		bottomRight.setOpaque(false);
		
		JButton loginButton=new JButton("登录");
		loginButton.setBounds(240, 3, 80, 25);
		loginButton.addActionListener(loginLis);
		
		loginButton.setMargin(new Insets(-2,11,-2,11));
		
		bottomRight.add(loginButton);

		loginFrame.add(imgPanel,BorderLayout.NORTH);
		loginFrame.add(centerPanel);
		loginFrame.add(bottomPanel,BorderLayout.SOUTH);
		
		loginFrame.getRootPane().setDefaultButton(loginButton);//设置焦点按钮
		
		loginFrame.pack();
		loginFrame.setVisible(true);
		
		//添加系统托盘图标
		sysIco = new SystemTrayIco(this);
		
		autoLogin();
	}
	
	private void autoLogin()
	{
		Object[] defaultUser = UserDao.getInstance().getDefaultUser();
		if(defaultUser != null)
		{
			String type = (String)defaultUser[2];
			userField.setText((String)defaultUser[0]);
			passwordField.setText((String)defaultUser[1]);
			if(type.equals("1"))
			{
				remember.setSelected(true);
			}
		}
	}

	public JFrame getLoginFrame(){return loginFrame;}
	public LimitTextField getUserField(){return userField;}
	public limitPasswordField getPasswordField(){return passwordField;}
	public JLabel getEnrolLabel(){return enrolLabel;}
	public JLabel getFindPwLabel(){return findPwLabel;}

	public boolean isAppOrFind()
	{
		return appOrFind;
	}

	public void setAppOrFind(boolean appOrFind)
	{
		this.appOrFind = appOrFind;
	}
	
	public SystemTrayIco getSysIco()
	{
		return this.sysIco;
	}
	public JCheckBox getRemember()
	{
		return remember;
	}
	
}
