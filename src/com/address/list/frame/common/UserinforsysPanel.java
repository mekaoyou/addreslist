package com.address.list.frame.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.address.list.action.PopupMenuAction;
import com.address.list.model.UserDao;

/**
 * 显示用户详细资料的Panel
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class UserinforsysPanel extends JPanel
{
	private String username,newname;//用户名,新申请的用户名
	private JFrame user;
	
	private LimitTextField nameField,pwAnswerField;//用户名输入，密码提示答案输入
	private limitPasswordField onePwField,twoPwField;//密码输入
	private JComboBox pwQuestionBox;//密码提示问题
	private JTextArea remarkArea;//备注输入
	private JButton clearBtn,handInBtn;//检测用户名，清空，提交按钮
	private JLabel nameLabel,checknameLabel,onePwLabel,twoPwLabel,checkonePwLabel,checktwoPwLabel,pwQLabel,pwALabel,checkpwAlabel,remarkLabel;
	private JPanel p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13;
	private Font commFont=new Font("SansSerif",Font.PLAIN,12);//普通字体
	private Font boldFont=new Font("SansSerif",Font.BOLD,14);//符号字体
	private Color fontColor=new Color(2,83,194);	

	public static final int UPDATE=1;
	public static final int APP=2;
	
	private int handle=UPDATE;
	
	public UserinforsysPanel(String username,JFrame user)
	{
		this.username = username;
		this.user=user;
		init();
		initBox();
		initLisn();
	}	
	
	/**
	 * 布局
	 */
	public void init()
	{		
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"     ------  修改账号资料  ------"));
		//初始化控件
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		p4=new JPanel();
		p5=new JPanel();
		p6=new JPanel();
		p7=new JPanel();
		p8=new JPanel();
		p9=new JPanel();
		p10=new JPanel();
		p11=new JPanel();
		p12=new JPanel();
		p13=new JPanel();
		
		nameLabel=new JLabel("用户名:");
		checknameLabel=new JLabel();
		onePwLabel=new JLabel("输入新密码:");
		checkonePwLabel=new JLabel("请输入6~16位密码");
		twoPwLabel=new JLabel("再次输入新密码:");
		checktwoPwLabel=new JLabel();
		pwQLabel=new JLabel("密码提示问题:");
		pwALabel=new JLabel("密码提示答案:");
		checkpwAlabel=new JLabel("20个字以内");
		remarkLabel=new JLabel("备注:");
		
		clearBtn=new JButton("清空");
		handInBtn=new JButton("提交");
		
		remarkArea=new JTextArea();
		pwQuestionBox=new JComboBox();
		onePwField=new limitPasswordField(16);
		twoPwField=new limitPasswordField(16);
		nameField=new LimitTextField(10);
		nameField.setText(username);
		pwAnswerField=new LimitTextField(20);
		
		JScrollPane scrol=new JScrollPane(remarkArea);		
		
		//设置控件相关属性
		nameField.setEditable(false);
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		checknameLabel.setForeground(fontColor);
		checkonePwLabel.setForeground(fontColor);
		checkonePwLabel.setFont(boldFont);
		onePwLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		twoPwLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		checktwoPwLabel.setFont(boldFont);
		pwQLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		pwALabel.setHorizontalAlignment(SwingConstants.RIGHT);
		remarkLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		remarkArea.setLineWrap(true);
		
		clearBtn.setMargin(new Insets(0,20,0,20));
		handInBtn.setMargin(new Insets(0,20,0,20));
		
		//布局
		this.setLayout(new BorderLayout());
		this.add(p1,BorderLayout.NORTH);
		this.add(p2,BorderLayout.CENTER);
		this.add(p3,BorderLayout.SOUTH);
		
		//north
		GridBagLayout gbl=new GridBagLayout();
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		gbc.weightx=1;
		
		p1.setLayout(new GridLayout(5,3,10,0));
		p1.add(nameLabel);
		p1.add(p4);
		p1.add(p5);
		p1.add(onePwLabel);
		p1.add(p6);
		p1.add(p7);
		p1.add(twoPwLabel);
		p1.add(p8);
		p1.add(p9);
		p1.add(pwQLabel);
		p1.add(p10);
		p1.add(p11);
		p1.add(pwALabel);
		p1.add(p12);
		p1.add(p13);
		
		p4.setLayout(gbl);
		p4.add(nameField,gbc);
		p5.setLayout(new FlowLayout(FlowLayout.LEFT));
		p5.add(checknameLabel);
		p6.setLayout(gbl);
		p6.add(onePwField,gbc);
		p7.setLayout(new FlowLayout(FlowLayout.LEFT));
		p7.add(checkonePwLabel);
		p8.setLayout(gbl);
		p8.add(twoPwField,gbc);
		p9.setLayout(new FlowLayout(FlowLayout.LEFT));
		p9.add(checktwoPwLabel);
		p10.setLayout(gbl);
		p10.add(pwQuestionBox,gbc);
		p12.setLayout(gbl);
		p12.add(pwAnswerField,gbc);	
		p13.setLayout(new FlowLayout(FlowLayout.LEFT));
		p13.add(checkpwAlabel);
		
		//center
		p2.setLayout(new GridLayout(1,3,10,0));
		p2.add(remarkLabel);
		p2.add(scrol);
		p2.add(new JLabel("100个字以内"));
		
		//south
		p3.setLayout(new FlowLayout());
		p3.add(clearBtn);
		p3.add(handInBtn);
	}
	
	/**
	 * 为密码提示问题添加选项
	 */
	private void initBox()
	{
		String[] s={"孩提时最喜欢的玩具是什么?",
				"您从未公开过某个疯狂的念头是?",
				"挚友为您做过哪件事使您感动万分?",
				"最想带父母一起去哪旅游?",
				"您觉得人生的价值是什么?",
				"您觉得该怎样教会孩子加减法？",
				"看望父母希望带什么礼物？"};
		pwQuestionBox.setModel(new DefaultComboBoxModel(s));
	}
	
	/**
	 * 为控件添加监听器
	 */
	private void initLisn()
	{
		//检测nameLabel内容改动的监听器
		nameField.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e)
			{
				changecheckLabelText();
			}
			public void insertUpdate(DocumentEvent e)
			{
				changecheckLabelText();
			}
			public void removeUpdate(DocumentEvent e)
			{
				changecheckLabelText();
			}			
		});
		
		//检测用户名是否可用的监听器
		checknameLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				checkname();
			}
			public void mouseEntered(MouseEvent e)
			{
				setUnderline();
			}
			public void mouseExited(MouseEvent e)
			{
				setCommFont();
			}			
		});
		
		//检测输入密码的监听器
		onePwField.getDocument().addDocumentListener(new DocumentListener(){
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
		//确认密码的监听器
		twoPwField.getDocument().addDocumentListener(new DocumentListener(){
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
		//密码提示答案的监听器
		pwAnswerField.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e)
			{
				changpwAlabelText();
			}
			public void insertUpdate(DocumentEvent e)
			{
				changpwAlabelText();
			}
			public void removeUpdate(DocumentEvent e)
			{
				changpwAlabelText();
			}			
		});
		//清空按钮的监听器
		clearBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				clearBtn(handle);
			}			
		});
		//提交按钮的监听器
		handInBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				handinBtn(handle);
			}			
		});
		//为备注添加右键菜单
		remarkArea.addMouseListener(new PopupMenuAction(remarkArea));
	}
	
	/**
	 * 为checknameLabel添加下划线和改变鼠标样式
	 */
	private void setUnderline()
	{
		checknameLabel.setFont(underLineFont());
		checknameLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	/**
	 * 将checknameLabel的字体设置为普通样式
	 */
	private void setCommFont()
	{
		checknameLabel.setFont(commFont);
	}	
	
	/**
	 * 检查用户名是否可用
	 */
	private void checkname()
	{
		String newname=nameField.getText();
		if (!newname.matches(" *"))
		{
			boolean b=UserDao.getInstance().addUserCheck(newname);
			if (b)
			{
				checknameLabel.setText("该用户已经被占用！");
				checknameLabel.setForeground(Color.RED);
				nameField.requestFocus();
				nameField.setSelectionStart(0);
				nameField.setSelectionEnd(nameField.getText().length());
			}
			else
			{
				checknameLabel.setText("该用户名可以使用！");
				checknameLabel.setForeground(fontColor);
				onePwField.requestFocus();
			}
		}
		else
		{
			checknameLabel.setText("用户名不能为空!");
			checknameLabel.setForeground(Color.RED);
		}
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
	
	/**
	 * namefield内容改动时，改变checknameLabel的文本内容
	 */
	private void changecheckLabelText()
	{
		checknameLabel.setForeground(fontColor);
		checknameLabel.setText("检测用户名是否可用");	
	}
	/**
	 * 密码输入时，改变checkonePwLabel的文本内容
	 */
	private void changecheckonePwLabelText()
	{
		String s=charTOstring(onePwField.getPassword());
		String s1=charTOstring(twoPwField.getPassword());
		if (!s.matches(" *")&&s.length()>5)
		{
			checkonePwLabel.setText("√");			
			checkonePwLabel.setForeground(fontColor);
			if (s1.equals(s)&&!s1.matches(" *"))
			{
				checktwoPwLabel.setText("√");
				checktwoPwLabel.setForeground(fontColor);
			}
			else
			{
				checktwoPwLabel.setText("×");
				checktwoPwLabel.setForeground(Color.RED);
			}
		}
		else
		{
			checkonePwLabel.setText("×");
			checkonePwLabel.setForeground(Color.RED);
		}
	}
	/**
	 * 确认密码时，改变checktwoPwLabel的文本内容
	 */
	private void changechecktwoPwLabelText()
	{
		String s=charTOstring(onePwField.getPassword());
		if (!s.matches(" *"))
		{
			String s1=charTOstring(twoPwField.getPassword());
			if (s1.equals(s)&&s1.length()>5)
			{
				checktwoPwLabel.setText("√");
				checktwoPwLabel.setForeground(fontColor);
			}else
			{
				checktwoPwLabel.setText("×");
				checktwoPwLabel.setForeground(Color.RED);
			}
		}
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
	 * 密码问题答案改变时，改变pwAlabel文本内容
	 */
	private void changpwAlabelText()
	{
		String s=pwAnswerField.getText();
		if (!s.matches(" *"))
		{
			checkpwAlabel.setText("√");
			checkpwAlabel.setForeground(fontColor);
			checkpwAlabel.setFont(boldFont);
		}
		else
		{
			checkpwAlabel.setText("×");
			checkpwAlabel.setForeground(Color.RED);
			checkpwAlabel.setFont(boldFont);
		}
	}
	/**
	 * 点击清空按钮的操作
	 */
	private void clearBtn(int handle)
	{
		if (handle==UPDATE)
		{
			onePwField.requestFocus();			
		}
		if (handle==APP)
		{
			nameField.setText("");
			nameField.requestFocus();		
		}
		onePwField.setText("");
		twoPwField.setText("");
		pwAnswerField.setText("");
		remarkArea.setText("");
		checkonePwLabel.setText("请输入6~16位密码");
		checktwoPwLabel.setText("");
		pwQuestionBox.setSelectedIndex(0);
		checkpwAlabel.setText("20个字以内");
		checkpwAlabel.setFont(commFont);
		checkpwAlabel.setForeground(Color.black);
	}	
	/**
	 * 点击申请账号时的界面改变
	 */
	public void app()
	{
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"     ------  申请新账号  ------"));
		nameLabel.setText("输入用户名:");
		nameField.setText("");
		checknameLabel.setText("");
		nameField.setEditable(true);
		onePwLabel.setText("请输入密码:");
		twoPwLabel.setText("请确认密码:");
		clearBtn(APP);
		
		handle=APP;
	}
	/**
	 * 点击修改账号资料时界面改变
	 */
	public void update()
	{
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"     ------  修改账号资料  ------"));
		nameLabel.setText("用户名:");
		nameField.setText(username);
		checknameLabel.setText("");
		nameField.setEditable(false);
		onePwLabel.setText("输入新密码:");
		twoPwLabel.setText("再次输入新密码:");
		clearBtn(UPDATE);
		
		handle=UPDATE;
	}
	/**
	 * 为修改用户资料提供数据
	 * @return
	 */
	private Object[] updateData()
	{
		String password=charTOstring(onePwField.getPassword());
		Object pwQuestion=pwQuestionBox.getSelectedItem();
		String pwAnswer=pwAnswerField.getText();
		String remarked=remarkArea.getText();
		if (remarked.length()>100)
		{
			remarked=remarked.substring(0, 100);
		}
		Object[] obj={password,pwQuestion,pwAnswer,remarked,username};
		return obj;
	}
	/**
	 * 为申请账号提供数据
	 * @return
	 */
	public Object[] appData()
	{
		String newname=nameField.getText();
		String password=charTOstring(onePwField.getPassword());
		Object pwQuestion=pwQuestionBox.getSelectedItem();
		String pwAnswer=pwAnswerField.getText();
		String remarked=remarkArea.getText();
		if (remarked.length()>100)
		{
			remarked=remarked.substring(0, 100);
		}
		Object[] obj={newname,password,pwQuestion,pwAnswer,remarked};
		return obj;
	}

	/**
	 * 点击提交按钮的操作
	 * @param handle
	 */
	private void handinBtn(int handle)
	{
		if (handle==UPDATE)
		{
			updateHandin(true);
		}
		if (handle==APP)
		{
			appHandin();
		}
	}
	/**
	 * 修改或申请账号的操作
	 * @param b true为修改 false为申请
	 */
	private void updateHandin(boolean b)
	{
		if (!charTOstring(twoPwField.getPassword()).matches(" *")&&!charTOstring(onePwField.getPassword()).matches(" *"))
		{
			if (checktwoPwLabel.getText().equals("√"))
			{
				if (checkpwAlabel.getText().equals("√"))
				{
					if (b)
					{
						if (UserDao.getInstance().updateUser(updateData()))
						{
							new FlyDialog(user,"修改成功!");
						}
					}
					else
					{
						if (UserDao.getInstance().appUser(appData()))
						{
							new FlyDialog(user,"申请成功!");
						}
					}
				}
				else
				{
					new FlyDialog(user,"密码提示问题不能为空!");
				}
			}
			else
			{
				new FlyDialog(user,"两次密码输入不一致!");
			}			
		}
		else
		{
			new FlyDialog(user,"密码不能为空!");
		}
	}
	/**
	 * 申请账号时，点击提交按钮的操作
	 */
	public void appHandin()
	{
		newname=nameField.getText();
		if (!newname.matches(" *"))
		{
			boolean b=UserDao.getInstance().addUserCheck(newname);
			if (b)
			{
				new FlyDialog(user,"用户名重复!");
			}
			else
			{
				updateHandin(false);
			}
		}
		else
		{
			new FlyDialog(user,"用户名不能为空!");
		}
	}

	public JTextField getNameField(){return nameField;}
	public JLabel getChecktwoPwLabel(){return checktwoPwLabel;}
	public JLabel getCheckpwAlabel(){return checkpwAlabel;}
	public JButton getHandInBtn(){return handInBtn;}
	public void setHandInBtn(JButton handInBtn){this.handInBtn = handInBtn;}
	public String getNewname(){return newname;}
	
}
