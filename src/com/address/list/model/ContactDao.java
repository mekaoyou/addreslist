package com.address.list.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 联系人的数据库操作
 * @author Geek
 *
 */
public class ContactDao extends AbstractDao
{
	private static final String COLUMNS = " id,user_id,contactname,moble,gender,birthday,address,remarked ";
	
	private static ContactDao instance;
	
	public static ContactDao getInstance()
	{
		if(instance == null)
		{
			instance = new ContactDao();
		}
		return instance;
	}
	
	/**
	 * 增加联系人
	 * @param username
	 * @param contactName
	 * @param moble
	 * @param birthday
	 * @param address
	 * @param gender
	 * @param remarked
	 * @return
	 */
	public boolean addContact(String username, String contactName, String moble, String birthday, String address, String gender, String remarked)
	{
		String sql = "insert into tbl_contact (user_id, contactname, moble, gender, birthday, address, remarked) values ((select id from tbl_user where username=?),?,?,?,?,?,?)";
		return dml(sql, username, contactName, moble, gender, birthday, address, remarked);
	}
	/**
	 * 查询用户名下的所有联系人
	 * @param username
	 * @return
	 */
	public Object[][] queryAll(String username)
	{
		String sql = "select a.id,a.contactname,a.moble from tbl_contact a, tbl_user b where b.username=? and b.id=a.user_id";
		return transList(query(sql, username));
	}

	/**
	 * 根据ID查询联系人详情
	 * @param id
	 * @return
	 */
	public ContactEntity queryById(long id)
	{
		String sql = "select "+COLUMNS+" from tbl_contact where id=?";
		return obj2Contact(queryUniqueResult(sql, id));
	}
	/**
	 * 删除联系人
	 * @param id
	 * @return
	 */
	public boolean delete(long id)
	{
		String sql = "delete from tbl_contact where id=?";
		return dml(sql, id);
	}
	
	/**
	 * 根据关键字模糊查询联系人
	 * @param username
	 * @param contact
	 */
	public Object[][] queryByContact(String username, String contact)
	{
		String sql = "select  a.id,a.contactname,a.moble from tbl_contact a, tbl_user b where b.username=? and b.id=a.user_id and a.contactname like ?";
		return transList(query(sql, username, "%"+contact+"%"));
	}
	
	public boolean update(long id, String moble, String gender, String birthday, String address, String remark)
	{
		String sql ="update tbl_contact set moble=?,gender=?,birthday=?,address=?,remarked=? where id=?";
		return dml(sql, moble, gender, birthday, address, remark, id);
	}
	
	private ContactEntity obj2Contact(Object[] obj)
	{
		//id,user_id,contactname,moble,gender,birthday,address,remarked
		ContactEntity contact = new ContactEntity();
		contact.setId((long)obj[0]);
		contact.setUserId((long)obj[1]);
		contact.setContactName((String)obj[2]);
		contact.setMoble((String)obj[3]);
		contact.setGender((String)obj[4]);
		contact.setBirthday(new SimpleDateFormat("yyyy-MM-dd").format((Date)obj[5]));
		contact.setAddress((String)obj[6]);
		contact.setRemarked((String)obj[7]);
		return contact;
	}
}
