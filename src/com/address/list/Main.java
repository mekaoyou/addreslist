package com.address.list;

import com.address.list.frame.login.LoginBox;
import com.address.list.model.http.HttpRequest;

public class Main
{
	public static void main(String[] args)
	{
		new LoginBox().init();		
		
		//发送 POST 请求
        String sr=HttpRequest.sendPost("http://qqzeng.com/ip/getIpInfo.php", "ip=1.85.221.212");
        System.out.println(sr);
	}
}
