package com.address.list.model.ip;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.address.list.model.http.HttpRequest;

/**
 * 查询IP对应地理位置API
 * @author Tim
 *
 */
public class QueryIPUtils
{
	private static final String ipURL = "http://ip.cn/index.php";
	private static final Pattern pat=Pattern.compile("<code>(.*?)</code>");
	private static final String code = "<code>";
	private static final String code_end = "</code>";
	private static final String IP = "((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))";
	
	public static String getIPLocal(String ip)
	{
		if(!ip.matches(IP))
		{
			return "IP地址不合法";
		}
		
		String ipHtml = HttpRequest.sendGet(ipURL, "ip="+ip);
		
		Matcher mat=pat.matcher(ipHtml);
		List<String> rs = new ArrayList<String>();
		while(mat.find())
		{
			String temp = mat.group().replace(code, "").replace(code_end, "");
			rs.add(temp);
		}
		StringBuilder sb = new StringBuilder();
		if(rs.size() > 0)
		{
			sb.append("您查询的 IP：").append(rs.get(0)).append("\r\n")
				.append("所在地理位置：").append(rs.get(1));
		}
		else
		{
			sb.append("查询失败");
		}
		
		return sb.toString();
	}
}
