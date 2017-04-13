package com.address.list.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils
{
	/**
	 * 
	 * 使用文件通道的方式复制文件
	 * @param s
	 *            源文件
	 * @param t
	 *            复制到的新文件
	 */
	public static String fileChannelCopy(File s)
	{
        String extension = s.getName().substring(s.getName().lastIndexOf(".")).toLowerCase();
		String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) + Math.random()*1000 + extension;
		File t = new File("img/"+fileName);
		
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;

		try
		{
			fi = new FileInputStream(s);
			fo = new FileOutputStream(t);
			in = fi.getChannel();// 得到对应的文件通道
			out = fo.getChannel();// 得到对应的文件通道
			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			return "tx.jpg";
		}
		finally
		{
			try
			{
				fi.close();
				in.close();
				fo.close();
				out.close();

			} catch (IOException e)
			{
				e.printStackTrace();
				return "tx.jpg";
			}
		}
		
		return fileName;
	}
}
