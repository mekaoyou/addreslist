package com.address.list.frame.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.address.list.frame.common.FlyDialog;
import com.address.list.frame.common.NumberField;
import com.address.list.model.KdDao;
import com.address.list.model.kd.KdniaoTrackQueryAPI;

/**
 * 自定义组件，用户实现查询快递信息的功能
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class QueryKDPanel extends JPanel
{
	private UserFrame user;//用户主界面
	private String username;//用户名
	private JScrollPane scrol;//用于放置查询结果
	private JComboBox kdTypeBox;//选择快递公司
	private JButton selectButton;//搜索
	private NumberField queryField;
	
	public QueryKDPanel(UserFrame user,String username)
	{
		this.user=user;
		this.username = username;
		init();
		initKdData();
	}

	public void init()
	{
		this.setLayout(new BorderLayout(5,10));
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"     ------  查询物流信息  ------"));
		//顶部panel
		JPanel topPanel=new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,10));
		
		//顶部组件
		kdTypeBox=new JComboBox();
		
		queryField = new NumberField();
		queryField.setColumns(20);
		selectButton=new JButton("查询");
		
		JTextArea result = new JTextArea();
		result.setColumns(50);
		result.setRows(17);
		scrol=new JScrollPane(result);
		
	    //为按钮添加监听器
		selectButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String kdType = (String)kdTypeBox.getSelectedItem();
				String code = queryField.getText();
				String msg="";
				try
				{
					String kdcode = KdDao.getInstance().getCodeByName(kdType);
					msg = KdniaoTrackQueryAPI.getInstance().getOrderTracesByJson(kdcode,code);
				} catch (Exception e1)
				{
					new FlyDialog(user.getUserFrame(), "查询出错，请重试");
				}
				result.setText(msg);
			}
		});

		topPanel.add(kdTypeBox);		
		topPanel.add(queryField);
		topPanel.add(selectButton);
		topPanel.add(scrol);
		
		this.add(topPanel,BorderLayout.CENTER);
	}
	
	private void initKdData()
	{
		List<Object[]> kds = KdDao.getInstance().queryAllKD();
		for (Object[] kd : kds)
		{
			kdTypeBox.addItem(kd[0]);
		}
	}

	public void setUsername(String username){this.username = username;}
	public String getUsername(){return username;}
	public NumberField getQueryField(){return queryField;}
}
