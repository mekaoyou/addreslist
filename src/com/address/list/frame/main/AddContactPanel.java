package com.address.list.frame.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.function.Function;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
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
import com.address.list.frame.common.Constant;
import com.address.list.frame.common.LimitTextField;
import com.address.list.frame.common.NumberField;
import com.address.list.frame.main.filter.ImageFilter;
import com.address.list.frame.main.interFace.UserEditPanel;
import com.address.list.model.ContactDao;
import com.address.list.model.ContactEntity;
import com.address.list.utils.FileUtils;


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
	private LimitTextField contactNameField,addressField,emailField,unitField;//描述信息，长度限制为20
	private JButton dateBtn,handInBtn,clearBtn,imgBtn;//选择日期的按钮,提交按钮,清空按钮
	private NumberField sumField,qqField,postField;//输入电话号码
	private JTextArea remarkArea;//输入备注信息的textarea
	private String username,img="tx.jpg";
	private UserFrame user;
	private ImageIcon imgIco;
	private JFileChooser filechooser;//文件选择器
	private GridBagConstraints gbc;
	private JDialog infoDialog;
	private boolean edit_able=true;
	private static final int size = 80;
	
	public AddContactPanel(String username,UserFrame user,String type)
	{
		this.username = username;
		this.user = user;
		init(type);
		initcombox();
		initContactType();
		initLisn();
	}
	
	public void init(String type)
	{
		
		GridBagLayout gbl=new GridBagLayout();
		this.setLayout(gbl);
		
		JPanel topPanel = new JPanel();
		JPanel topLeft = new JPanel();
		JPanel topRight = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topLeft.setLayout(gbl);
		topRight.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 20));
		topPanel.add(topLeft, BorderLayout.CENTER);
		topPanel.add(topRight, BorderLayout.EAST);
		
		imgIco = new ImageIcon("img/"+img);
		imgIco.setImage(imgIco.getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
		imgBtn=new JButton(imgIco);
		imgBtn.setMargin(new Insets(-2,-10,-2,-10));
		imgBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(!edit_able)
					return;
				if(filechooser == null)
				{
					filechooser = new JFileChooser();
					filechooser.setFileFilter(new ImageFilter());
				}
				int option = filechooser.showOpenDialog(user.getUserFrame());
				if(option == JFileChooser.APPROVE_OPTION)
				{
					File file = filechooser.getSelectedFile();
					img = FileUtils.fileChannelCopy(file);
					setAvator(img);
				}
			}
		});
		topRight.add(imgBtn);
		
		gbc = new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;	
		
		gbc.weightx=1;
		gbc.insets=new Insets(0,0,0,0);		
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		
		this.add(topPanel, gbc);
		
		gbc.insets=new Insets(0,10,0,10);	
		
		JPanel itemPanel=new JPanel();
		topLeft.add(itemPanel,gbc);
		itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel itemLabel=new JLabel("姓名:*");
		itemPanel.add(itemLabel);		
		
		contactNameField=new LimitTextField(12, "请输入联系人");
		contactNameField.setColumns(14);
		itemPanel.add(contactNameField);		
		
		JLabel genderLabel=new JLabel("性别:");
		itemPanel.add(genderLabel);
		
		genderBox=new JComboBox();
		itemPanel.add(genderBox);		
		
		JPanel sumPanel=new JPanel();
		topLeft.add(sumPanel,gbc);
		sumPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel sumLabel=new JLabel("电话:*");
		sumPanel.add(sumLabel);		
		
		sumField=new NumberField();
		sumField.setColumns(14);
		sumPanel.add(sumField);			
		
		JLabel emLabel=new JLabel("邮箱:");
		sumPanel.add(emLabel);		
		
		emailField=new LimitTextField(30);
		emailField.setColumns(14);
		sumPanel.add(emailField);			
		
		JPanel datePanel=new JPanel();
		topLeft.add(datePanel,gbc);
		datePanel.setLayout(new FlowLayout(FlowLayout.LEFT));		

		JLabel typeLable=new JLabel("分组:*");
		datePanel.add(typeLable);		
		
		typeBox=new JComboBox();
		datePanel.add(typeBox);
		
		JButton plusBtn = new JButton("+");
		datePanel.add(plusBtn);		
		plusBtn.addActionListener(new AddContactTypeLisn(this.user.getUserFrame(), this));
		
		JLabel dateLable=new JLabel("                       生日:");
		datePanel.add(dateLable);				
		
		dateField=new JTextField("1990-01-01");
		dateField.setEditable(false);
		datePanel.add(dateField);		
		
		dateBtn=new JButton("↓");
		dateBtn.setMargin(new Insets(0,-12,0,-12));
		datePanel.add(dateBtn);		
		
		JPanel qpPanel = new JPanel();
		topLeft.add(qpPanel, gbc);
		qpPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel qqLable=new JLabel("QQ:    ");
		qpPanel.add(qqLable);		

		qqField=new NumberField();
		qqField.setColumns(14);
		qpPanel.add(qqField);	
		
		JLabel postLable=new JLabel("邮编:");
		qpPanel.add(postLable);		
		
		postField=new NumberField();
		postField.setColumns(14);
		qpPanel.add(postField);	
		
		JPanel unitPanel = new JPanel();
		this.add(unitPanel, gbc);
		unitPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel unitLable=new JLabel("单位:  ");
		unitPanel.add(unitLable);		

		unitField=new LimitTextField(14);
		unitField.setColumns(45);
		unitPanel.add(unitField);	
		
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		JPanel desPanel=new JPanel();
		this.add(desPanel,gbc);
		desPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel desLabel=new JLabel("地址:  ");
		desPanel.add(desLabel);
		
		addressField=new LimitTextField(30,"请输入地址");
		addressField.setColumns(45);
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
		
		if(type.equals(Constant.ADD))
		{
			setAddBtn();
		}
		else if(type.equals(Constant.INFO))
		{
			setInfoBtn();
		}
		else if(type.equals(Constant.DEL))
		{
			setDelBtn();
		}
		else if(type.equals(Constant.MOD))
		{
			setModBtn();
		}
	}
	
	private void setAddBtn()
	{
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"     ------  新增联系人  ------"));
		gbc.insets=new Insets(5,10,10,10);
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

		handInBtn.addActionListener(new AddItemLisn(this,username,user));//提交按钮
		clearBtn.addActionListener(new ClearBtnLisn(this));//清除按钮
	}
	
	private void setInfoBtn()
	{
		gbc.insets=new Insets(5,10,10,10);
		gbc.weighty=0;
		JPanel bottomPanel=new JPanel();
		this.add(bottomPanel,gbc);
		
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,5,5));
		handInBtn=new JButton("确定");
		handInBtn.setMargin(new Insets(0,20,0,20));
		bottomPanel.add(handInBtn);

		handInBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(infoDialog != null)
				{
					infoDialog.dispose();
				}
			}
		});
		
		setEnable(false);
	}
	
	private void setDelBtn()
	{

		gbc.insets=new Insets(5,10,10,10);
		gbc.weighty=0;
		JPanel bottomPanel=new JPanel();
		this.add(bottomPanel,gbc);

		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,5,5));
		clearBtn=new JButton("取消");
		handInBtn=new JButton("删除");
		clearBtn.setMargin(new Insets(0,20,0,20));
		handInBtn.setMargin(new Insets(0,20,0,20));
		bottomPanel.add(clearBtn);
		bottomPanel.add(handInBtn);

		clearBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(infoDialog != null)
				{
					infoDialog.dispose();
				}
			}
		});
		
		setEnable(false);
	}
	
	private void setModBtn()
	{
		
		gbc.insets=new Insets(5,10,10,10);
		gbc.weighty=0;
		JPanel bottomPanel=new JPanel();
		this.add(bottomPanel,gbc);
		
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,5,5));
		clearBtn=new JButton("取消");
		handInBtn=new JButton("更新");
		clearBtn.setMargin(new Insets(0,20,0,20));
		handInBtn.setMargin(new Insets(0,20,0,20));
		bottomPanel.add(clearBtn);
		bottomPanel.add(handInBtn);
		
		clearBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(infoDialog != null)
				{
					infoDialog.dispose();
				}
			}
		});
		
	}
	
	private void setEnable(boolean b)
	{
		genderBox.setEnabled(b);
		typeBox.setEnabled(b);
		dateField.setEditable(b);
		contactNameField.setEditable(b);
		addressField.setEditable(b);
		emailField.setEditable(b);
		sumField.setEditable(b);
		qqField.setEditable(b);
		postField.setEditable(b);
		remarkArea.setEditable(b);
		unitField.setEditable(b);
		edit_able = b;
	}
	
	public void initData(ContactEntity contact)
	{
		genderBox.setSelectedItem(contact.getGender());
		typeBox.setSelectedItem(contact.getType());
		dateField.setText(contact.getBirthday());
		contactNameField.setText(contact.getContactName());
		addressField.setText(contact.getAddress());
		emailField.setText(contact.getEmail());
		sumField.setText(contact.getMoble());
		qqField.setText(contact.getQq());
		postField.setText(contact.getPost());
		remarkArea.setText(contact.getRemarked());
		unitField.setText(contact.getUnit());
		setAvator(contact.getImg());
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
		List<Object[]> typs = ContactDao.getInstance().queryAllType(username);
		for (Object[] obj : typs)
		{
			typeBox.addItem(obj[0]);
		}
	}
	
	public void setAvator(String img)
	{
		ImageIcon temp = new ImageIcon("img/"+img);
		this.imgIco.setImage(temp.getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
	}
	
	/**
	 * 添加监听器
	 */
	private void initLisn()
	{
		dateBtn.addActionListener(new SelectDateListn(dateBtn,dateField));//选择日期按钮
		remarkArea.addMouseListener(new PopupMenuAction(remarkArea));//备注右键菜单
		remarkArea.addFocusListener(new JTextComponentFocuseLisn(remarkArea,"100个字以内"));
	}
	
	public void clear()
	{
		contactNameField.setText("");
		sumField.setText("");
		postField.setText("");
		dateField.setText("1990-01-01");
		qqField.setText("");
		emailField.setText("");
		unitField.setText("");
		addressField.setText("");
		remarkArea.setText("");
	}
	
	public JTextField getContactName(){return contactNameField;}
	public JComboBox getGenderBox(){return genderBox;}
	public JComboBox getTypeBox(){return typeBox;}
	public JTextField getDateField(){return dateField;}
	public JTextField getAddressField(){return addressField;}
	public NumberField getSumField(){return sumField;}
	public JTextArea getRemarkArea(){return remarkArea;}
	public UserFrame getUserFrame(){return this.user;}
	public String getUserName(){return username;}
	public JTextField getQqField(){return qqField;}
	public JTextField getEmailField(){return emailField;}
	public JTextField getUnitField(){return unitField;}
	public JTextField getPostField(){return postField;}
	public String getImg(){return img;}
	public void setInfoDiaog(JDialog dialog){this.infoDialog = dialog;}
	public JButton getHandInBtn(){return this.handInBtn;}
}
