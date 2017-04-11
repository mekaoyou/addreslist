package com.address.list.model.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * dao基类
 *
 */
public abstract class AccessAbstractDao
{
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
	/**
	 * 查询
	 * @param sql
	 * @param values
	 * @return
	 */
	protected List<Object[]> query(String sql,Object... values)
	{
		
		List<Object[]> list = new ArrayList<Object[]>();
		try
		{
			conn = AccessC3p0DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			if (values != null)
			{
				for (int i = 0; i < values.length; i++)
				{
					pstmt.setObject(i+1, values[i]);
				}
			}
			rs = pstmt.executeQuery();
			//获取结果集列数
			int columnCount = rs.getMetaData().getColumnCount();
			Object[] row = null;
			while (rs.next())
			{
				row = new Object[columnCount];
				for (int i = 0; i < columnCount; i++)
				{
					row[i] = rs.getObject(i+1);
				}
				list.add(row);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close();
		}
		return list;
	}
	
	/**
	 * 只可能有唯一结果行的查询
	 * @param sql
	 * @param values
	 * @return
	 */
	protected Object[] queryUniqueResult(String sql, Object... values)
	{
		
		Object[] row = null;
		
		try
		{
			conn = AccessC3p0DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			if (values != null)
			{
				for (int i = 0; i < values.length; i++)
				{
					pstmt.setObject(i+1, values[i]);
				}
			}
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				int columnCount = rs.getMetaData().getColumnCount();
				row = new Object[columnCount];
				for (int i = 0; i < columnCount; i++)
				{
					row[i] = rs.getObject(i+1);
				}
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close();
		}
		return row;
	}
	
	/**
	 * 单句dml语句
	 * @param sql
	 * @param values
	 * @return
	 */
	protected boolean dml(String sql, Object...values)
	{
		boolean isSuccess=false;
		try
		{
			conn = AccessC3p0DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			if (values != null)
			{
				for (int i = 0; i < values.length; i++)
				{
					pstmt.setObject(i+1, values[i]);
				}
			}
			int count = pstmt.executeUpdate();
			if(count>0)
			{
			    isSuccess=true;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			close();
		}
		return isSuccess;
	}
	
	/**
	 * 关闭连接 方法
	 */
	public void close()
	{
	    try
        {
            if(conn!=null)
            {
                conn.close();
            }
            if(pstmt!=null)
            {
                pstmt.close();
            }
            if(rs!=null)
            {
                rs.close();
            }
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
	}
	
	/**
	 * 将查询结果转换成Object[][]
	 * @param list 查询结果
	 * @return 
	 */
	protected Object[][] transList(List<Object[]> list)
	{
		int i=list.size();
		if (i==0)
		{
			return null;
		}
		else
		{
			Object[] row=list.get(0);
			int j=row.length;
			Object[][] obj=new Object[i][j];
			for (int y = 0; y < i; y++)
			{
				for (int x = 0; x < j; x++)
				{
					obj[y][x]=list.get(y)[x];
				}
			}
			return obj;			
		}
	}

	protected MobileEntity obj2Entity(Object[] obj)
	{
		if(obj == null)
		{
			return null;
		}
		MobileEntity entity = new MobileEntity();
		entity.setId((int)obj[0]);
		entity.setMobileNum((String)obj[1]);
		entity.setArea((String)obj[2]);
		entity.setMobileType((String)obj[3]);
		entity.setTelArea((String)obj[4]);
		entity.setPostNum((String)obj[5]);
		return entity;
	}
}
