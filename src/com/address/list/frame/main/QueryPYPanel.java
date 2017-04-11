package com.address.list.frame.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.address.list.frame.common.FlyDialog;
import com.address.list.frame.common.LimitTextField;
import com.address.list.frame.common.NumberField;
import com.address.list.model.access.MobileDao;
import com.address.list.model.access.MobileEntity;
import com.address.list.model.http.HttpRequest;

/**
 * 自定义组件，用户实现查询座机号码归属地的功能
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class QueryPYPanel extends JPanel
{
	private UserFrame user;//用户主界面
	private String username;//用户名
	private JButton selectButton;//搜索
	private LimitTextField queryField;
	private JScrollPane scrol;//用于放置查询结果
	
	private static final String pyURL = "http://51pinyin.com/GetData.ashx?url=index";
	
	public QueryPYPanel(UserFrame user,String username)
	{
		
		this.user=user;
		this.username = username;
		init();
	}

	public void init()
	{
		this.setLayout(new BorderLayout(5,10));
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"     ------  查询拼音  ------"));
		//顶部panel
		JPanel topPanel=new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,10));
		
		//顶部组件
		queryField = new LimitTextField(100,"请输入要查询的汉字");
		queryField.setColumns(45);
		selectButton=new JButton("查询");
		
		JTextArea result = new JTextArea();
		result.setColumns(50);
		result.setRows(17);
		result.setLineWrap(true);
		scrol = new JScrollPane(result);
		
	    //为按钮添加监听器
		selectButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String code = queryField.getText();
				String py = HttpRequest.sendPost(pyURL, "name="+code);
				result.setText(py);
			}
		});
				
		topPanel.add(queryField);
		topPanel.add(selectButton);
		topPanel.add(scrol);
		
		this.add(topPanel,BorderLayout.CENTER);
	}

	public void setUsername(String username){this.username = username;}
	public String getUsername(){return username;}
	public LimitTextField getQueryField(){return queryField;}
}
