package com.address.list.frame.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import com.address.list.action.ExitListener;
import com.address.list.action.QuitLisn;
import com.address.list.action.main.AboutLisn;
import com.address.list.action.main.AddItemAction;
import com.address.list.action.main.ApplicationAction;
import com.address.list.action.main.SelectItemAction;
import com.address.list.action.main.TopPanelBtnLisn;
import com.address.list.action.main.UpdateUserInforsysAction;
import com.address.list.frame.common.DateAndTime;
import com.address.list.frame.common.UserinforsysPanel;

/**
 * 通讯录主界面
 * @author Alex
 *
 */
public class UserFrame
{
	private JFrame userFrame;//主窗体
	private String username;//用户名
	private JButton itemBtn,userBtn;
	private JToolBar toolbar,toolbar2;//工具条
	private QueryContactPanel selectPanel;//查询panel
	private AddContactPanel addPanel;//增加项目panel
	private UserinforsysPanel userPanel;//管理用户账户Panel
	private DateAndTime dateandtime;//时间状态栏
	
	
	public UserFrame(String username)
	{
		this.username = username;
		init();
		inituserAdmin();
	}

	/**
	 * 初始化用户界面
	 */
	@SuppressWarnings("serial")
	private void init()
	{
		userFrame=new JFrame(username+" - 通讯录");
		Image logoImg=Toolkit.getDefaultToolkit().getImage("img/logo.png");
		userFrame.setIconImage(logoImg);
		userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		userFrame.setMinimumSize(new Dimension(720,500));
		userFrame.setResizable(false);//大小不可更改
		userFrame.addWindowListener(new ExitListener(userFrame));
		
		//创建顶部panel
		final Image img=Toolkit.getDefaultToolkit().createImage("img/bg.png");
		JPanel topPanel=new JPanel()
		{
			protected void paintChildren(Graphics g)
			{
				g.drawImage(img, 0, 0, this);
				super.paintChildren(g);
			}
		};
		topPanel.setLayout(new BorderLayout());
		
		JPanel topLeft=new JPanel();
		JPanel topRight=new JPanel();
		topPanel.add(topLeft,BorderLayout.WEST);
		topPanel.add(topRight,BorderLayout.EAST);
		topLeft.setOpaque(false);
		topRight.setOpaque(false);		
		topRight.setLayout(new FlowLayout(FlowLayout.CENTER,20,16));
		topLeft.setLayout(new FlowLayout(FlowLayout.CENTER,20,16));
		//添加顶部按钮
		itemBtn=new JButton();
		itemBtn.setIcon(new ImageIcon("img/item.png"));
		itemBtn.setMargin(new Insets(0,-5,0,-5));
		userBtn=new JButton();
		userBtn.setIcon(new ImageIcon("img/user.png"));
		userBtn.setMargin(new Insets(0,-5,0,-5));
		itemBtn.setToolTipText("联系人管理");
		userBtn.setToolTipText("账号管理");
		topLeft.add(itemBtn);
		topLeft.add(userBtn);		
		
		//创建菜单条和工具条
		JMenuBar menubar=new JMenuBar();
		toolbar=new JToolBar("通讯录管理",1);	
		toolbar.setMargin(new Insets(20,10,0,10));
		toolbar.setFloatable(false);
				
		//创建菜单
		JMenu sysmenu = new JMenu("系统(S)");
		JMenu runmenu = new JMenu("运行(R)");
		JMenu aboutmenu = new JMenu("帮助(H)");
		sysmenu.setMnemonic(KeyEvent.VK_S);
		runmenu.setMnemonic(KeyEvent.VK_R);
		aboutmenu.setMnemonic(KeyEvent.VK_A);
		
		//创建工具栏按钮
		SelectItemAction selectAction=new SelectItemAction(this);
		AddItemAction addAction=new AddItemAction(this);
		
		//创建菜单项
		JMenuItem exitItem = new JMenuItem("退出(Q)");
		JMenuItem itemItem=new JMenuItem("管理联系人(I)");
		JMenuItem userItem=new JMenuItem("管理账户信息(U)");
		JMenuItem helpItem = new JMenuItem("帮助(H)");
		JMenuItem aboutItem = new JMenuItem("关于(A)");
		exitItem.setMnemonic(KeyEvent.VK_Q);
		exitItem.setAccelerator(KeyStroke.getKeyStroke('Q',InputEvent.CTRL_DOWN_MASK));
		itemItem.setMnemonic(KeyEvent.VK_I);
		userItem.setMnemonic(KeyEvent.VK_U);
		helpItem.setMnemonic(KeyEvent.VK_H);
		aboutItem.setMnemonic(KeyEvent.VK_A);
		
		//创建时间栏
		dateandtime=new DateAndTime();
		
		//添加菜单项
		sysmenu.add(exitItem);
		runmenu.add(itemItem);
		runmenu.add(userItem);
		aboutmenu.add(helpItem);
		aboutmenu.add(aboutItem);
		
		//添加菜单
		menubar.add(sysmenu);
		menubar.add(runmenu);
		menubar.add(aboutmenu);
		
		//添加工具栏按钮
		toolbar.add(selectAction);
		toolbar.add(addAction);
		toolbar.addSeparator(new Dimension(5,30));
		
		//添加菜单条和工具条
		userFrame.setJMenuBar(menubar);
		userFrame.add(toolbar,BorderLayout.WEST);
				
		//添加时间栏
		userFrame.add(dateandtime,BorderLayout.SOUTH);
		
		//添加顶部panel
		userFrame.add(topPanel,BorderLayout.NORTH);		
		
		//数据显示区
		addPanel=new AddContactPanel(username,this);
		userFrame.add(addPanel);
		addPanel.setVisible(false);
		
		selectPanel=new QueryContactPanel(this,username);
		userFrame.add(selectPanel);		
		
		userFrame.setSize(715,530);
		Dimension userFrameSize=userFrame.getSize();
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		int x=((screenSize.width-userFrameSize.width)/2);
		int y=((screenSize.height-userFrameSize.height)/2);
		userFrame.setLocation(x, y);
		userFrame.setVisible(true);	
		
		/**
		 * 添加监听器++++++++++++++++++
		 */
		//添加顶部按钮监听器
		itemBtn.addActionListener(new TopPanelBtnLisn(this,itemBtn));//显示联系人管理页面
		userBtn.addActionListener(new TopPanelBtnLisn(this,userBtn));//显示账号管理页面
		
		//添加菜单项监听器
		itemItem.addActionListener(new TopPanelBtnLisn(this,itemBtn));//显示联系人管理页面
		userItem.addActionListener(new TopPanelBtnLisn(this,userBtn));//显示账号管理页面
		
		exitItem.addActionListener(new QuitLisn(userFrame));//退出监听器
		
		aboutItem.addActionListener(new AboutLisn(userFrame));//关于
	}
	
	/**
	 * 初始化账号管理界面
	 */
	private void inituserAdmin()
	{
		//创建工具条
		toolbar2=new JToolBar("账号管理",1);
		toolbar2.setMargin(new Insets(20,10,0,10));
		toolbar2.setFloatable(false);
		
		//创建账号管理panel
		userPanel=new UserinforsysPanel(username,userFrame);
		
		//创建工具条按钮
		UpdateUserInforsysAction updateUserAction=new UpdateUserInforsysAction(this);
		ApplicationAction applicationAction=new ApplicationAction(userPanel);
		
		
		//添加工具栏按钮
		toolbar2.add(updateUserAction);
		toolbar2.add(applicationAction);
		
		//添加账号管理Panel和工具栏
		userFrame.add(userPanel);
		userFrame.add(toolbar2,BorderLayout.EAST);
		
		toolbar2.setVisible(false);
		userPanel.setVisible(false);
	}
	
	/**
	 * 调整项目管理页面和账号管理页面的显隐
	 * @param btn
	 */
	public void itemVisible(JButton btn)
	{
		boolean b=false;
		
		if (btn==itemBtn)
		{
			if (!selectPanel.isVisible()&&!addPanel.isVisible())
			{
				toolbar2.setVisible(b);
				userPanel.setVisible(b);
				if (selectPanel==null)
				{
					userFrame.add(selectPanel);
				}
				selectPanel.setVisible(!b);
				toolbar.setVisible(!b);
			}			
		}
		if (btn==userBtn)
		{
			if (!userPanel.isVisible())
			{
				toolbar.setVisible(b);
				if (selectPanel.isVisible())
				{
					selectPanel.setVisible(b);					
				}
				if (addPanel.isVisible())
				{
					addPanel.setVisible(b);					
				}
				userFrame.add(userPanel);
				toolbar2.setVisible(!b);
				userPanel.setVisible(!b);
			}
		}
	}
	
	/**
	 * 项目管理页面调整中间Panel的显隐
	 * @param panel
	 */
	public void panelVisible(JPanel panel)
	{
		boolean b=false;
		selectPanel.setVisible(b);
		addPanel.setVisible(b);
		
		panel.setVisible(true);
	}

	public JFrame getUserFrame(){return userFrame;}
	public QueryContactPanel getSelectPanel(){return selectPanel;}
	public void setSelectPanel(QueryContactPanel selectPanel){this.selectPanel = selectPanel;}
	public String getUsername(){return username;}
	public AddContactPanel getAddPanel(){return addPanel;}
	public void setAddPanel(AddContactPanel addPanel){this.addPanel = addPanel;}
	public JButton getItemBtn(){return itemBtn;}
	public JButton getUserBtn(){return userBtn;}
	public UserinforsysPanel getUserPanel(){return userPanel;}
}
