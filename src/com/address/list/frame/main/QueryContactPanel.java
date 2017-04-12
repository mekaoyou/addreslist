package com.address.list.frame.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.address.list.action.main.DelItemLisn;
import com.address.list.action.main.SelectItemLisn;
import com.address.list.action.main.SelectMouseLisn;
import com.address.list.action.main.ShowItemInforLisn;
import com.address.list.action.main.UpdateItemLisn;
import com.address.list.frame.common.LimitTextField;
import com.address.list.model.ContactDao;

/**
 * 自定义组件，用户实现查找功能的panel
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class QueryContactPanel extends JPanel
{
	private UserFrame user;//用户主界面
	private String username;//用户名
	private JTable table;//加载数据的表格
	private JScrollPane scrol;//用于放置表格
	private JButton selectButton,itemInforBtn,updateBtn,delBtn;//搜索，显示详情，编辑项目，删除项目按钮
	private JPopupMenu pop;
	private LimitTextField queryField;
	private JComboBox typeBox;//选择分组
	
	private static final Object[] columnTitle={"ID","联系人","电话","分组"};
	
	public static boolean isMove=true;//是否启动表格的鼠标移动事件
	
	public QueryContactPanel(UserFrame user,String username)
	{
		this.user=user;
		this.username = username;
		init();
		initTabel(ContactDao.getInstance().queryAll(username));
		initBottomBtn();	
		initpopMenu();
		initTypes();
	}

	public void init()
	{
		this.setLayout(new BorderLayout(5,10));
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"     ------  查询联系人  ------"));
		//顶部panel
		JPanel topPanel=new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,10));
		
		//顶部组件
		typeBox = new JComboBox<>();
		typeBox.addItem("全部");
		typeBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String selectType = (String)typeBox.getSelectedItem();
				initTabel(ContactDao.getInstance().queryByType(username, selectType));
			}
		});
		
		queryField = new LimitTextField(20);
		queryField.setColumns(20);
		selectButton=new JButton("查询");
		
	    //为按钮添加监听器
		selectButton.addActionListener(new SelectItemLisn(username,this));
				
		topPanel.add(typeBox);
		topPanel.add(queryField);
		topPanel.add(selectButton);
		
		this.add(topPanel,BorderLayout.NORTH);
		
		scrol=new JScrollPane();
		this.add(scrol,BorderLayout.CENTER);
	}
	
	private void initTypes()
	{
		List<Object[]> typs = ContactDao.getInstance().queryAllType(username);
		for (Object[] obj : typs)
		{
			typeBox.addItem(obj[0]);
		}
	}
	
	/**
	 * 添加表格
	 */
	public void initTabel(Object[][] obj)
	{
		//Object[][] obj=ContactDao.getInstance().queryAll(username);
		if (obj==null)
		{
			obj=new Object[1][4];
		}
		if(table != null)
		{
			table = null;
		}
		table=new JTable(obj,columnTitle);
		table.setBackground(new Color(214,217,223));
		table.getTableHeader().setReorderingAllowed(false);//设置表格列不能拖动
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//设置一次只能选中一行

		//给table安装鼠标事件
		table.addMouseListener(new SelectMouseLisn(this));
		table.addMouseMotionListener(new SelectMouseLisn(this));
		
		scrol.setViewportView(table);

		QueryContactPanel.isMove=true;//启动鼠标移动事件
	}
	
	/**
	 * 添加底部按钮
	 */
	public void initBottomBtn()
	{
		JPanel bottomPanel=new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,5,10));
		updateBtn=new JButton("编辑联系人");
		delBtn=new JButton("删除联系人");
		itemInforBtn=new JButton("联系人详情");
		itemInforBtn.setMargin(new Insets(0,20,0,20));
		delBtn.setMargin(new Insets(0,20,0,20));
		updateBtn.setMargin(new Insets(0,20,0,20));
		bottomPanel.add(updateBtn);
		bottomPanel.add(delBtn);
		bottomPanel.add(itemInforBtn);
		this.add(bottomPanel,BorderLayout.SOUTH);
		
		//添加监听器
		itemInforBtn.addActionListener(new ShowItemInforLisn(user,this));
		delBtn.addActionListener(new DelItemLisn(user,this));
		updateBtn.addActionListener(new UpdateItemLisn(user,this));
	}
	private void initpopMenu()
	{
		pop=new JPopupMenu();
		JMenuItem itemInforItem=new JMenuItem("联系人详情");
		JMenuItem updateItem=new JMenuItem("编辑联系人");
		JMenuItem delItem=new JMenuItem("删除联系人");
		pop.add(itemInforItem);
		pop.add(updateItem);
		pop.add(delItem);
		table.add(pop);
		//添加菜单项监听器
		itemInforItem.addActionListener(new ShowItemInforLisn(user,this));
		updateItem.addActionListener(new UpdateItemLisn(user,this));
		delItem.addActionListener(new DelItemLisn(user,this));
		
		//给table安装鼠标事件
		table.addMouseListener(new SelectMouseLisn(this));
		table.addMouseMotionListener(new SelectMouseLisn(this));
	}

	public void setUsername(String username){this.username = username;}
	public JTable getTable(){return table;}
	public void setTable(JTable table){this.table = table;}
	public JScrollPane getScrol(){return scrol;}
	public String getUsername(){return username;}
	public JPopupMenu getPop(){return pop;}
	public LimitTextField getQueryField(){return queryField;}
	public JComboBox getTypeBox(){return typeBox;}
}
