package com.address.list.model.access;

public class MobileDao extends AccessAbstractDao
{
	private static MobileDao instance;
	
	public static MobileDao getInstance()
	{
		if(instance == null)
		{
			instance = new MobileDao();
		}
		return instance;
	}
	
	public MobileEntity getPostByTel(String tel)
	{
		String sql = "select * from Dm_Mobile where AreaCode in (?,?)";
		return obj2Entity(queryUniqueResult(sql, tel.substring(0,3), tel.length() > 3?tel.substring(0, 4):tel.substring(0,3)));
	}
	
	public MobileEntity getPostByMoble(String mobile)
	{
		String sql = "select * from Dm_Mobile where MobileNumber=?";
		return obj2Entity(queryUniqueResult(sql, mobile));
	}

	public MobileEntity getPostByCode(String code)
	{
		String sql = "select * from Dm_Mobile where PostCode=?";
		return obj2Entity(queryUniqueResult(sql, code));
	}
}
