package com.address.list.frame.login;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.address.list.frame.common.DateAndTime;
import com.address.list.frame.common.UserinforsysPanel;

/**
 * 登陆界面注册新账号,找回密码的监听器
 * @author Alex
 *
 */
public class EnrolAndFindPwLisn implements MouseListener
{
	private LoginBox login;
	private JLabel label;
	private Font commFont=new Font("SansSerif",Font.PLAIN,12);
	private UserinforsysPanel userPanel;//申请账号Panel
	private FindPwPanel findPanel;//找回密码Panel

	public EnrolAndFindPwLisn(LoginBox login,JLabel label)
	{
		this.login = login;
		this.label=label;
	}
	
	/**
	 * 定义一个有下划线的字体
	 */
	private Font underLineFont()
	{
		HashMap<TextAttribute, Object> hm = new HashMap<TextAttribute, Object>();
		hm.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);  // 定义是否有下划线
		hm.put(TextAttribute.SIZE, 12);    // 定义字号
		hm.put(TextAttribute.FAMILY, "SansSerif");    // 定义字体名
		Font underLineFont = new Font(hm);    // 生成字号为12，字体为SansSerif，字形带有下划线的字体 
		return underLineFont;
	}

	public void mouseClicked(MouseEvent e)
	{
		if (label==login.getFindPwLabel())
		{
			initFindPwFrame();
		}
		else
		{
			initAppFrame();
			userPanel.app();
			userPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		}
	}

	public void mouseEntered(MouseEvent e)
	{
		label.setFont(underLineFont());
		label.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public void mouseExited(MouseEvent e)
	{
		label.setFont(commFont);
	}

	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	
	/**
	 * 构建注册账号窗体
	 */
	@SuppressWarnings("serial")
	private void initAppFrame()
	{
		final JFrame appFrame=new JFrame("注册新账号");
		Image logoImg=Toolkit.getDefaultToolkit().getImage("img/logo.png");
		appFrame.setIconImage(logoImg);
		appFrame.setResizable(false);
		appFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//上
		JLabel topLabel=new JLabel("~~WELL COME~~");
		topLabel.setFont(new Font("Dialog",Font.BOLD,20));
		
		final Image img=Toolkit.getDefaultToolkit().createImage("img/bg.png");
		JPanel panel=new JPanel()
		{
			protected void paintChildren(Graphics g)
			{
				g.drawImage(img, 0, 0, this);
				super.paintChildren(g);
			}
		};
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT,20,21));
		panel.add(topLabel);
		
		appFrame.add(panel,BorderLayout.NORTH);
		
		//中
		String username="";
		userPanel=new UserinforsysPanel(username,appFrame);
		appFrame.add(userPanel);
		
		//下
		DateAndTime dateBar=new DateAndTime();
		appFrame.add(dateBar,BorderLayout.SOUTH);
		
		
		appFrame.pack();
		Dimension appFSize=appFrame.getSize();
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		int x=(d.width-appFSize.width)/2;
		int y=(d.height-appFSize.height)/2;
		appFrame.setLocation(x, y);
		
		//添加窗口监听器
		appFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				login.getUserField().setText(userPanel.getNewname());
				login.getPasswordField().setText(null);
				showLoginbox(appFrame, userPanel);
			}			
		});
		
		appFrame.setVisible(true);
		login.setAppOrFind(true);
		login.getLoginFrame().setVisible(false);
	}
	
	/**
	 * 构建找回密码窗体
	 */
	@SuppressWarnings("serial")
	private void initFindPwFrame()
	{
		final JFrame findFrame=new JFrame("找回密码");
		Image logoImg=Toolkit.getDefaultToolkit().getImage("img/logo.png");
		findFrame.setIconImage(logoImg);
		findFrame.setResizable(false);
		findFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//左
		final Image img=Toolkit.getDefaultToolkit().createImage("img/findimg.png");
		JPanel leftPanel=new JPanel()
		{
			protected void paintChildren(Graphics g)
			{
				g.drawImage(img, 0, 0, this);
				super.paintChildren(g);
			}
		};
		leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER,80,20));
		leftPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		JLabel lab=new JLabel("      ");
		leftPanel.add(lab);
		findFrame.add(leftPanel,BorderLayout.WEST);
		
		//右
		findPanel=new FindPwPanel();
		findFrame.add(findPanel);
		
		//添加取消按钮监听
		findPanel.getCancalButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				login.getUserField().setText(findPanel.getUsername());
				login.getPasswordField().setText(null);
				showLoginbox(findFrame,findPanel);
			}			
		});
		
		findFrame.pack();
		Dimension appFSize=findFrame.getSize();
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		int x=(d.width-appFSize.width)/2;
		int y=(d.height-appFSize.height)/2;
		findFrame.setLocation(x, y);
		//添加窗口监听器
		findFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				login.getUserField().setText(findPanel.getUsername());
				login.getPasswordField().setText(null);
				showLoginbox(findFrame,findPanel);
			}			
		});
		findFrame.setVisible(true);
		login.setAppOrFind(true);
		login.getLoginFrame().setVisible(false);
	}
	/**
	 * 关闭申请账号或找回密码窗口，显示登录窗口
	 */
	private void showLoginbox(JFrame frame,JPanel panel)
	{
		frame.dispose();
		login.setAppOrFind(false);
		login.getLoginFrame().setVisible(true);
		login.getLoginFrame().requestFocus();
		login.getUserField().requestFocus();
		
		login.getUserField().setSelectionStart(0);
		login.getUserField().setSelectionEnd(login.getUserField().getText().length());
	}
}
