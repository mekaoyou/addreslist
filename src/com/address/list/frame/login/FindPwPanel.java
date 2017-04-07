package com.address.list.frame.login;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.address.list.frame.common.LimitTextField;
import com.address.list.frame.common.limitPasswordField;
import com.address.list.model.UserDao;

/**
 * 自定义组件，找回密码的Panel
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class FindPwPanel extends JPanel
{
	private JPanel centerPanel,bottomPanel;//中间和底部的Panel
	private JPanel firstPanel,secondPanel,thirdPanel,fourthPanel;
	private JButton previousButton,nextButton,cancalButton;//下一步和取消按钮
	private CardLayout cl=new CardLayout(10,20);
	
	private LimitTextField nameField,pwAField;//用户输入用户名,密保答案
	
	private limitPasswordField pwF1,pwF2;//输入密码
	private JLabel nameL,pwL1,pwL2;//提示用户名是否存在，提示密码输入是否正确
	private Font boldFont=new Font("SansSerif",Font.BOLD,14);//符号字体
	private Color fontColor=new Color(2,83,194);//符号颜色
	
	private JLabel pwQLabel,checkpwA;//密码保护问题,密码答案是否真确
	
	private String username,pwQuestion,pwAnswer;//用户名，密保问题,密保答案
	
	private int step=1;
	
	public FindPwPanel()
	{
		firstPanel();
		secondPanel();
		thirdPanel();
		fourthPanel();
		initPanel();
	}
	/**
	 * 顶层Panel布局
	 */
	public void initPanel()
	{
		this.setLayout(new BorderLayout());
		
		//中间操作区
		centerPanel=new JPanel();
		centerPanel.setLayout(cl);
		centerPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		centerPanel.add("first",firstPanel);
		centerPanel.add("second",secondPanel);
		centerPanel.add("third",thirdPanel);
		centerPanel.add("fourth",fourthPanel);
		
		//底部按钮区
		previousButton=new JButton("上一步");
		nextButton=new JButton("下一步");
		cancalButton=new JButton("取    消");
		bottomPanel=new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,15,10));
		bottomPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		bottomPanel.add(previousButton);
		bottomPanel.add(nextButton);
		bottomPanel.add(cancalButton);
		
		this.add(centerPanel);
		this.add(bottomPanel,BorderLayout.SOUTH);
		
		previousButton.setEnabled(false);
		
		//给控件添加监听器
		previousButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				cl.previous(centerPanel);
				step=step-1;
				if (firstPanel.isVisible())
				{
					previousButton.setEnabled(false);
				}
				if (step<3)
				{
					nextButton.setEnabled(true);
				}
			}			
		});
		nextButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				step(step);
			}			
		});
		nameField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				step(step);
			}			
		});
		pwAField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				step(step);
			}			
		});
	}
	/**
	 * 初始化第一个panel
	 */
	private void firstPanel()
	{
		firstPanel=new JPanel();
		firstPanel.setLayout(new GridLayout(6,1));
		
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		JPanel p4=new JPanel();
		JPanel p5=new JPanel();
		JPanel p6=new JPanel();
		firstPanel.add(p1);
		firstPanel.add(p2);
		firstPanel.add(p3);
		firstPanel.add(p4);
		firstPanel.add(p5);
		firstPanel.add(p6);
		
		nameField=new LimitTextField(10);
		nameL=new JLabel();
		nameL.setForeground(Color.RED);
		
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.add(new JLabel("第一步:"));
		p2.setLayout(new BorderLayout());
		p2.add(new JLabel("输入用户名:"),BorderLayout.WEST);
		p2.add(nameField);
		p3.add(nameL);					
	}
	/**
	 * 初始化第二个Panel
	 */
	private void secondPanel()
	{
		secondPanel=new JPanel();
		secondPanel.setLayout(new GridLayout(6,1));
		
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		JPanel p4=new JPanel();
		JPanel p5=new JPanel();
		secondPanel.add(p1);
		secondPanel.add(p2);
		secondPanel.add(p3);
		secondPanel.add(p4);
		secondPanel.add(p5);
		
		pwQLabel=new JLabel();
		pwAField=new LimitTextField(10);
		checkpwA=new JLabel();
		checkpwA.setForeground(Color.RED);
		
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.add(new JLabel("第二步:"));
		p2.setLayout(new BorderLayout());
		p2.add(new JLabel("密保问题:"),BorderLayout.WEST);
		p2.add(pwQLabel);
		p3.setLayout(new BorderLayout());
		p3.add(new JLabel("输入答案:"),BorderLayout.WEST);
		p3.add(pwAField);
		p4.add(checkpwA);		
	}
	/**
	 * 初始化第三个Panel
	 */
	private void thirdPanel()
	{
		thirdPanel=new JPanel();
		thirdPanel.setLayout(new GridLayout(6,1));
		
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		JPanel p4=new JPanel();
		JPanel p5=new JPanel();
		thirdPanel.add(p1);
		thirdPanel.add(p2);
		thirdPanel.add(p3);
		thirdPanel.add(p4);
		thirdPanel.add(p5);
		
		pwF1=new limitPasswordField(16);
		pwF2=new limitPasswordField(16);
		pwL1=new JLabel("  ");
		pwL2=new JLabel("  ");
		pwL1.setFont(boldFont);
		pwL2.setFont(boldFont);
		pwL1.setForeground(fontColor);
		
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.add(new JLabel("第三步:"));
		p2.setLayout(new BorderLayout());
		p2.add(new JLabel("输入新密码:"),BorderLayout.WEST);
		p2.add(pwF1);
		p2.add(pwL1,BorderLayout.EAST);
		p3.setLayout(new BorderLayout());
		p3.add(new JLabel("重复新密码:"),BorderLayout.WEST);
		p3.add(pwF2);
		p3.add(pwL2,BorderLayout.EAST);
		
		//为pwF1,pwF2添加监听器
		pwF1.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e)
			{
				changecheckonePwLabelText();
			}
			public void insertUpdate(DocumentEvent e)
			{
				changecheckonePwLabelText();
			}
			public void removeUpdate(DocumentEvent e)
			{
				changecheckonePwLabelText();
			}			
		});
		pwF2.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e)
			{
				changechecktwoPwLabelText();
			}
			public void insertUpdate(DocumentEvent e)
			{
				changechecktwoPwLabelText();
			}
			public void removeUpdate(DocumentEvent e)
			{
				changechecktwoPwLabelText();
			}			
		});
	}
	/**
	 * 初始化第四个Panel
	 */
	private void fourthPanel()
	{
		fourthPanel=new JPanel();
		fourthPanel.setLayout(new BorderLayout());
		fourthPanel.add(new JLabel("密码已经重置,请使用新密码登陆!"),BorderLayout.NORTH);
	}
	
	/**
	 * 将char[]转化成String
	 * @param c
	 * @return
	 */
	private String charTOstring(char[] c)
	{
		String s="";
		for (int i = 0; i < c.length; i++)
		{
			s=s+c[i];
		}
		return s;
	}
	/**
	 * 密码输入时，改变pwL的文本内容
	 */
	private void changecheckonePwLabelText()
	{
		String s=charTOstring(pwF1.getPassword());
		String s1=charTOstring(pwF2.getPassword());
		if (!s.matches(" *")&&s.length()>5)
		{
			pwL1.setText("√");	
			pwL1.setForeground(fontColor);
			if (s1.equals(s)&&!s1.matches(" *"))
			{
				pwL2.setText("√");
				pwL2.setForeground(fontColor);
				nextButton.setEnabled(true);
			}
			else
			{
				pwL2.setText("×");
				pwL2.setForeground(Color.RED);
				nextButton.setEnabled(false);
			}
		}
		else
		{
			pwL1.setText("×");
			pwL1.setForeground(Color.RED);
		}
	}
	/**
	 * 确认密码时，改变pwL的文本内容
	 */
	private void changechecktwoPwLabelText()
	{
		String s=charTOstring(pwF1.getPassword());
		if (!s.matches(" *"))
		{
			String s1=charTOstring(pwF2.getPassword());
			if (s1.equals(s)&&s1.length()>5)
			{
				pwL2.setText("√");
				pwL2.setForeground(fontColor);
				nextButton.setEnabled(true);
			}else
			{
				pwL2.setText("×");
				pwL2.setForeground(Color.RED);
				nextButton.setEnabled(false);
			}
		}
	}
	/**
	 * 执行找回密码的步骤
	 * @param step 表示执行第几步
	 */
	private void step(int step)
	{
		if (step==1)
		{
			firstStep();
		}
		if (step==2)
		{
			secondStep();
		}
		if (step==3)
		{
			thirdStep();
		}
	}
	/**
	 * 找回密码第一步
	 */
	private void firstStep()
	{
		username=nameField.getText();
		if (!username.matches(" *"))
		{
			boolean b=UserDao.getInstance().addUserCheck(username);
			if (b)
			{
				pwQuestion=UserDao.getInstance().searchPwQ(username);
				if (pwQuestion!=null)
				{
					step=step+1;
					cl.next(centerPanel);
					pwQLabel.setText(pwQuestion);
					previousButton.setEnabled(true);
					pwAField.requestFocus();
				}
				else
				{
					nameL.setText("该用户没有设置密码保护问题!");				
				}
			}
			else
			{
				nameL.setText("用户名不存在!");
				nameField.requestFocus();
				nameField.setSelectionStart(0);
				nameField.setSelectionEnd(nameField.getText().length());
			}
			
		}else
		{
			nameL.setText("请输入用户名!");
			nameField.requestFocus();
			nameField.setText("");
		}
	}
	/**
	 * 找回密码第二步
	 */
	private void secondStep()
	{
		pwAnswer=pwAField.getText();
		if (!pwAnswer.matches(" *"))
		{
			boolean b=UserDao.getInstance().isPwAright(username, pwAnswer);
			if (b)
			{
				step=step+1;
				cl.next(centerPanel);
				pwF1.requestFocus();
				nextButton.setEnabled(false);
			}
			else
			{
				checkpwA.setText("答案不正确！请重新输入。");	
				pwAField.requestFocus();
				pwAField.setSelectionStart(0);
				pwAField.setSelectionEnd(pwAField.getText().length());
			}
			
		}
		else
		{
			checkpwA.setText("请输入密保答案");	
			pwAField.requestFocus();
			pwAField.setText("");
		}
	}
	/**
	 * 找回密码第三步
	 */
	private void thirdStep()
	{
		String password=charTOstring(pwF2.getPassword());
		if (UserDao.getInstance().updatePassword(username, password))
		{
			cl.next(centerPanel);
			previousButton.setVisible(false);
			nextButton.setVisible(false);
			cancalButton.setText("完    成");
			cancalButton.requestFocus();
			this.getRootPane().setDefaultButton(cancalButton);
		}
	}
	public JButton getCancalButton(){return cancalButton;}
	public String getUsername(){return username;}
	
}
