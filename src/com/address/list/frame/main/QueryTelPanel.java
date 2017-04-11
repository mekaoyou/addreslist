package com.address.list.frame.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.address.list.frame.common.FlyDialog;
import com.address.list.frame.common.NumberField;
import com.address.list.model.access.MobileDao;
import com.address.list.model.access.MobileEntity;

/**
 * 自定义组件，用户实现查询座机号码归属地的功能
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class QueryTelPanel extends JPanel
{
	private UserFrame user;//用户主界面
	private String username;//用户名
	private JButton selectButton;//搜索
	private NumberField queryField;
	
	public QueryTelPanel(UserFrame user,String username)
	{
		
		this.user=user;
		this.username = username;
		init();
	}

	public void init()
	{
		this.setLayout(new BorderLayout(5,10));
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"     ------  查询座机号码归属地  ------"));
		//顶部panel
		JPanel topPanel=new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,10));
		
		//顶部组件
		queryField = new NumberField();
		queryField.setColumns(20);
		selectButton=new JButton("查询");
		
		JTextArea result = new JTextArea();
		result.setColumns(50);
		result.setRows(10);
		
	    //为按钮添加监听器
		selectButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String code = queryField.getText();
				if(code.length()<3 || code.length()>11)
				{
					new FlyDialog(user.getUserFrame(), "请输入正确的座机号码");
					return;
				}
				MobileEntity obj = MobileDao.getInstance().getPostByTel(code);
				if(obj == null)
				{
					new FlyDialog(user.getUserFrame(), "座机号码不存在");
					return;
				}
				result.setText(obj.getArea());
			}
		});
				
		topPanel.add(queryField);
		topPanel.add(selectButton);
		topPanel.add(result);
		
		this.add(topPanel,BorderLayout.NORTH);
	}

	public void setUsername(String username){this.username = username;}
	public String getUsername(){return username;}
	public NumberField getQueryField(){return queryField;}
}
