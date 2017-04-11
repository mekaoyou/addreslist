package com.address.list.model;

import java.util.List;

public class KdDao extends AbstractDao
{
	private static KdDao instance;
	
	public static KdDao getInstance()
	{
		if(instance == null)
		{
			instance = new KdDao();
		}
		return instance;
	}
	
	public List<Object[]> queryAllKD()
	{
		String sql = "select kd_name from tbl_kd_code";
		return query(sql);
	}
	
	public String getCodeByName(String name)
	{
		String sql = "select code from tbl_kd_code t where t.kd_name=?";
		return (String)queryUniqueResult(sql, name)[0];
	}
}
