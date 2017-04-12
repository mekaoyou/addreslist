package com.address.list.frame.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.address.list.action.PopupMenuAction;
import com.address.list.action.main.AddContactTypeLisn;
import com.address.list.action.main.Disposaction;
import com.address.list.action.main.SelectDateListn;
import com.address.list.frame.common.FlyDialog;
import com.address.list.frame.common.LimitTextField;
import com.address.list.frame.common.NumberField;
import com.address.list.frame.main.interFace.UserEditPanel;
import com.address.list.model.ContactDao;
import com.address.list.model.ContactEntity;


/**
 * 显示项目详细信息的Diallog,编辑，删除，显示详情时调用
 * @author Alex
 *
 */
public class ContactInforDialog implements UserEditPanel
{
	private UserFrame user;//用户界面主窗体
	private QueryContactPanel panel;
	private JDialog dialog;//容器
	private JTextField contactNameField;
	private LimitTextField addressField;//地址
	private JTextField dateField;//日期
	private NumberField mobleField;//电话
	private JTextArea remarkArea;//显示备注
	private JCheckBox maleBox,femaleBox;//性别
	private JButton dateBtn,cancalBtn,handinBtn,plusBtn;//日期按钮，底部按钮
	private JComboBox typeBox;

	public ContactInforDialog(UserFrame user,QueryContactPanel panel)
	{
		this.user = user;
		this.panel=panel;
		init();
		initLisn();
		initData();
	}

	/**
	 * 构建窗体
	 */
	public void init()
	{
		dialog=new JDialog(user.getUserFrame(),"联系人详细信息",true);
		dialog.setLayout(new BorderLayout(5,10));
		
		//顶部+++++++++++++++++++++++++++++
		JPanel northPanel=new JPanel();
		GridBagLayout gbl=new GridBagLayout();
		northPanel.setLayout(gbl);
		
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		gbc.insets=new Insets(10,10,0,10);
		gbc.weightx=1;
		
		JPanel cotactPanel=new JPanel();
		JPanel genderPanel=new JPanel();
		JPanel datePanel=new JPanel();
		JPanel moblePanel=new JPanel();
		JPanel addressPanel=new JPanel();
		JLabel remarkLabel=new JLabel("备注:");
				
		cotactPanel.setLayout(new BorderLayout(5,0));
		cotactPanel.add(new JLabel("姓名:*"),BorderLayout.WEST);
		contactNameField=new JTextField();
		cotactPanel.add(contactNameField);
		
		genderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		maleBox=new JCheckBox("男");
		femaleBox=new JCheckBox("女");
		maleBox.setSelected(true);
		genderPanel.add(maleBox);
		genderPanel.add(femaleBox);
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(maleBox);
		bg.add(femaleBox);
		
		JLabel typeLable=new JLabel("     分组:*");
		genderPanel.add(typeLable);		
		
		typeBox=new JComboBox();
		genderPanel.add(typeBox);
		
		plusBtn = new JButton("+");
		genderPanel.add(plusBtn);		
		plusBtn.addActionListener(new AddContactTypeLisn(this.user.getUserFrame(), this));
		
		moblePanel.setLayout(new BorderLayout(5,0));
		moblePanel.add(new JLabel("电话:*"),BorderLayout.WEST);
		mobleField=new NumberField();
		moblePanel.add(mobleField);
		
		datePanel.setLayout(new BorderLayout(5,0));
		datePanel.add(new JLabel("生日:"),BorderLayout.WEST);
		dateField=new JTextField();
		dateField.setEditable(false);
		dateBtn=new JButton("↓");
		dateBtn.setMargin(new Insets(0,-12,0,-12));
		datePanel.add(dateField);
		datePanel.add(dateBtn,BorderLayout.EAST);
		
		addressPanel.setLayout(new BorderLayout(5,0));
		addressPanel.add(new JLabel("地址:  "),BorderLayout.WEST);
		addressField=new LimitTextField(20);
		addressPanel.add(addressField);
		
		northPanel.add(cotactPanel,gbc);
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		northPanel.add(genderPanel,gbc);
		gbc.insets=new Insets(5,10,0,10);
		gbc.gridwidth=1;
		northPanel.add(moblePanel,gbc);
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		northPanel.add(datePanel,gbc);
		northPanel.add(addressPanel,gbc);
		northPanel.add(remarkLabel,gbc);
		//中部++++++++++++++++++++++++++++++
		remarkArea=new JTextArea();
		remarkArea.setLineWrap(true);		
		JScrollPane scrol=new JScrollPane(remarkArea);
		
		//底部++++++++++++++++++++++++++++++
		JPanel bottomPanel=new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,5,10));
		
		cancalBtn=new JButton("取消");
		handinBtn=new JButton("提交");
		cancalBtn.setMargin(new Insets(0,20,0,20));
		handinBtn.setMargin(new Insets(0,20,0,20));
		bottomPanel.add(cancalBtn);
		bottomPanel.add(handinBtn);
		
		dialog.add(northPanel,BorderLayout.NORTH);
		dialog.add(scrol);
		dialog.add(bottomPanel,BorderLayout.SOUTH);
		
		//计算坐标
		Point p=user.getUserFrame().getLocationOnScreen();
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		int x=(p.x+650)>d.width?d.width-550:p.x+100;
		int y=(p.y+450)>d.height?d.height-300:p.y+150;
		x=x<0?0:x;		
		dialog.setBounds(x,y,550,300);
		
		//设置焦点按钮
		dialog.getRootPane().setDefaultButton(handinBtn);
	}
	
	/**
	 * 添加监听器
	 */
	public void initLisn()
	{		
		//取消按钮的监听器
		cancalBtn.addActionListener(new Disposaction(this));	
		//日期按钮的监听器
		dateBtn.addActionListener(new SelectDateListn(dateBtn,dateField,dialog));
		//备注右键菜单
		remarkArea.addMouseListener(new PopupMenuAction(remarkArea));
	}
	
	/**
	 * 加载数据
	 */
	private void initData()
	{
		int row=panel.getTable().getSelectedRow();
		//准备数据并加载
		long contact_id=(long)panel.getTable().getValueAt(row, 0);
		ContactEntity contact = ContactDao.getInstance().queryById(contact_id);
		if(contact != null)
		{
			if (contact.getGender().equals("女"))
			{
				femaleBox.setSelected(true);
			}
			contactNameField.setText(contact.getContactName());
			dateField.setText(contact.getBirthday());
			addressField.setText(contact.getAddress());
			mobleField.setText(contact.getMoble());
			remarkArea.setText(contact.getRemarked());			
		}
		else
		{
			new FlyDialog(user.getUserFrame(), "数据有误");
		}
		
		initContactType();
		typeBox.setSelectedItem(contact.getType());
		
		//设置控件不可操作
		setEnabled(false);		
		
	}
	
	public void initContactType()
	{
		typeBox.removeAllItems();
		List<Object[]> typs = ContactDao.getInstance().queryAllType(panel.getUsername());
		for (Object[] obj : typs)
		{
			typeBox.addItem(obj[0]);
		}
	}
	
	/**
	 * 设置控件是否可操作
	 * @param b
	 */
	public void setEnabled(boolean b)
	{
		contactNameField.setEditable(b);
		maleBox.setEnabled(b);
		femaleBox.setEnabled(b);
		dateBtn.setEnabled(b);
		mobleField.setEditable(b);
		addressField.setEditable(b);
		remarkArea.setEditable(b);
		typeBox.setEnabled(b);
		plusBtn.setEnabled(b);
	}

	public QueryContactPanel getPanel(){return panel;}
	public JDialog getDialog(){return dialog;}
	public JTextField getItemField(){return contactNameField;}
	public JTextField getDateField(){return dateField;}
	public JTextField getAddressField(){return addressField;}
	public NumberField getMobleField(){return mobleField;}
	public JTextArea getRemarkArea(){return remarkArea;}
	public JCheckBox getMaleBox(){return maleBox;}
	public JCheckBox getFemaleBox(){return femaleBox;}
	public JButton getDateBtn(){return dateBtn;}
	public JButton getCancalBtn(){return cancalBtn;}
	public JButton getHandinBtn(){return handinBtn;}	
	public JComboBox getTypeBox(){return typeBox;}
	public String getUserName(){return panel.getUsername();}
}
