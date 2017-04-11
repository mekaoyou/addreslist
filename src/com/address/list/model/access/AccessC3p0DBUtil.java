package com.address.list.model.access;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 使用c3p0连接池连接jdbc代码
 * 
 */
public class AccessC3p0DBUtil
{
	/**
	 * c3p0连接池对象
	 */
	private static ComboPooledDataSource dataSource;

	/**
	 * c3p0连接池的创建和初始化
	 */
	public static void init(String db)
	{
		if (dataSource == null)
		{
			dataSource = new ComboPooledDataSource();
			try
			{
				dataSource = new ComboPooledDataSource();
				// 1、设置连接数据库的驱动
				dataSource.setDriverClass("com.hxtt.sql.access.AccessDriver");
				// 2、设置URL
				dataSource.setJdbcUrl("jdbc:Access:///db/"+db+".mdb");
				// 3、设置user
				dataSource.setUser("");
				// 4、设置密码
				dataSource.setPassword("");
				// 5、设置连接池最小连接数
				dataSource.setMinPoolSize(3);
				// 6、设置连接池最大连接数
				dataSource.setMaxPoolSize(5);
				// 7、设置初始连接数
				dataSource.setInitialPoolSize(3);
				// 8、设置每次增加连接时一次增加多少个连接
				dataSource.setAcquireIncrement(2);
				// 9、设置PreparedStatement的缓存数量
				dataSource.setMaxStatements(10);
				// 10、设置连接池关闭时，提交所有尚未提交的事务
				dataSource.setAutoCommitOnClose(true);
				// 11、检查连接池内连接有效性的时间间隔
				dataSource.setIdleConnectionTestPeriod(60);
				// 12、连接最大闲置时间
				dataSource.setMaxIdleTime(60000);
			} catch (PropertyVetoException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * 从连接池获得连接
	 * 
	 * @return
	 */
	public static Connection getConnection()
	{
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭连接池
	 */
	public static void close()
	{
		if (dataSource != null)
		{
			dataSource.close();
			dataSource = null;
		}
	}
}
