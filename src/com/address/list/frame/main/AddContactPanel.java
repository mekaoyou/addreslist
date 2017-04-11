package com.address.list.frame.main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.address.list.action.JTextComponentFocuseLisn;
import com.address.list.action.PopupMenuAction;
import com.address.list.action.main.AddContactTypeLisn;
import com.address.list.action.main.AddItemLisn;
import com.address.list.action.main.ClearBtnLisn;
import com.address.list.action.main.SelectDateListn;
import com.address.list.frame.common.LimitTextField;
import com.address.list.frame.common.NumberField;
import com.address.list.frame.main.interFace.UserEditPanel;
import com.address.list.model.ContactDao;


/**
 * 增加联系人的组件
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class AddContactPanel extends JPanel implements UserEditPanel
{
	private JComboBox genderBox, typeBox;//选择项目类型，收入或支出的下拉列表
	private JTextField dateField;//输入日期
	private LimitTextField contactNameField,addressField;//描述信息，长度限制为20
	private JButton dateBtn,handInBtn,clearBtn;//选择日期的按钮,提交按钮,清空按钮
	private NumberField sumField;//输入电话号码
	private JTextArea remarkArea;//输入备注信息的textarea
	
	private String username;
	private UserFrame user;
	
	public AddContactPanel(String username,UserFrame user)
	{
		this.username = username;
		this.user = user;
		init();
		initcombox();
		initContactType();
		initLisn();
	}
	
	public void init()
	{
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"     ------  新增联系人  ------"));
		GridBagLayout gbl=new GridBagLayout();
		this.setLayout(gbl);
		
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;	
		
		gbc.weightx=1;
		gbc.insets=new Insets(0,10,0,10);		
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		
		JPanel itemPanel=new JPanel();
		this.add(itemPanel,gbc);
		itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel itemLabel=new JLabel("姓名:*");
		itemPanel.add(itemLabel);		
		
		contactNameField=new LimitTextField(12, "请输入联系人");
		contactNameField.setColumns(12);
		itemPanel.add(contactNameField);		
		
		JLabel genderLabel=new JLabel("性别:");
		itemPanel.add(genderLabel);
		
		genderBox=new JComboBox();
		itemPanel.add(genderBox);		
		
		JPanel sumPanel=new JPanel();
		this.add(sumPanel,gbc);
		sumPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel sumLabel=new JLabel("电话:*");
		sumPanel.add(sumLabel);		
		
		sumField=new NumberField();
		sumField.setColumns(11);
		sumPanel.add(sumField);			

		JLabel typeLable=new JLabel("分组:*");
		sumPanel.add(typeLable);		
		
		typeBox=new JComboBox();
		sumPanel.add(typeBox);
		
		JButton plusBtn = new JButton("+");
		sumPanel.add(plusBtn);		
		plusBtn.addActionListener(new AddContactTypeLisn(this.user.getUserFrame(), this));
		
		JPanel datePanel=new JPanel();
		this.add(datePanel,gbc);
		datePanel.setLayout(new FlowLayout(FlowLayout.LEFT));		
		
		JLabel dateLable=new JLabel("生日:  ");
		datePanel.add(dateLable);				
		
		dateField=new JTextField("1990-01-01");
		dateField.setEditable(false);
		datePanel.add(dateField);		
		
		dateBtn=new JButton("↓");
		dateBtn.setMargin(new Insets(0,-12,0,-12));
		datePanel.add(dateBtn);		
			
		
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		JPanel desPanel=new JPanel();
		this.add(desPanel,gbc);
		desPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel desLabel=new JLabel("地址:  ");
		desPanel.add(desLabel);
		
		addressField=new LimitTextField(30,"请输入地址");
		addressField.setColumns(30);
		desPanel.add(addressField);
		
		JPanel p=new JPanel();
		JLabel remarkLabel=new JLabel("备注:");
		p.setLayout(new FlowLayout(FlowLayout.LEFT));
		p.add(remarkLabel);
		this.add(p,gbc);
		
		gbc.weighty=1;		
		remarkArea=new JTextArea("100个字以内");
		remarkArea.setLineWrap(true);
		remarkArea.setForeground(new Color(120,120,120));
		JScrollPane scrol=new JScrollPane(remarkArea);
		this.add(scrol,gbc);
		
		gbc.insets=new Insets(5,10,20,10);
		gbc.weighty=0;
		JPanel bottomPanel=new JPanel();
		this.add(bottomPanel,gbc);
		
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,5,5));
		clearBtn=new JButton("清空");
		handInBtn=new JButton("提交");
		clearBtn.setMargin(new Insets(0,20,0,20));
		handInBtn.setMargin(new Insets(0,20,0,20));
		bottomPanel.add(clearBtn);
		bottomPanel.add(handInBtn);
	}
	
	/**
	 * 加载项目类型和收支类型
	 */
	private void initcombox()
	{
		genderBox.addItem("男");
		genderBox.addItem("女");
	}
	
	public void initContactType()
	{
		typeBox.removeAllItems();
		List<Object[]> typs = ContactDao.getInstance().queryAllType();
		for (Object[] obj : typs)
		{
			typeBox.addItem(obj[0]);
		}
	}
	
	/**
	 * 添加监听器
	 */
	private void initLisn()
	{
		dateBtn.addActionListener(new SelectDateListn(dateBtn,dateField));//选择日期按钮
		handInBtn.addActionListener(new AddItemLisn(this,username,user));//提交按钮
		clearBtn.addActionListener(new ClearBtnLisn(this));//清除按钮
		
		remarkArea.addMouseListener(new PopupMenuAction(remarkArea));//备注右键菜单
		remarkArea.addFocusListener(new JTextComponentFocuseLisn(remarkArea,"100个字以内"));
	}
	
	public JTextField getContactName(){return contactNameField;}
	public JComboBox getGenderBox(){return genderBox;}
	public JComboBox getTypeBox(){return typeBox;}
	public JTextField getDateField(){return dateField;}
	public JTextField getAddressField(){return addressField;}
	public NumberField getSumField(){return sumField;}
	public JTextArea getRemarkArea(){return remarkArea;}
	public UserFrame getUserFrame(){return this.user;}
	
}
