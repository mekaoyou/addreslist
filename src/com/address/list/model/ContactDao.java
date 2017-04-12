package com.address.list.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 联系人的数据库操作
 * @author Geek
 *
 */
public class ContactDao extends AbstractDao
{
	private static final String COLUMNS = " a.id,user_id,contactname,moble,gender,birthday,address,remarked,type_name ";
	
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
	public boolean addContact(String username, String contactName, String moble, String birthday, String address, String gender, String remarked, String type)
	{
		String sql = "insert into tbl_contact (user_id, contactname, moble, gender, birthday, address, remarked, contact_type) values ((select id from tbl_user where username=?),?,?,?,?,?,?,(select id from tbl_contact_type where type_name=?))";
		return dml(sql, username, contactName, moble, gender, birthday, address, remarked, type);
	}
	/**
	 * 查询用户名下的所有联系人
	 * @param username
	 * @return
	 */
	public Object[][] queryAll(String username)
	{
		String sql = "select a.id,a.contactname,a.moble,c.type_name from tbl_contact a, tbl_user b, tbl_contact_type c where b.username=? and b.id=a.user_id and a.contact_type=c.id";
		return transList(query(sql, username));
	}

	/**
	 * 根据ID查询联系人详情
	 * @param id
	 * @return
	 */
	public ContactEntity queryById(long id)
	{
		String sql = "select "+COLUMNS+" from tbl_contact a, tbl_contact_type b where a.id=? and a.contact_type=b.id";
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
	public Object[][] queryByContact(String username, String contact, String type)
	{
		String sql = null;
		if(type.equals("全部"))
		{
			sql = "select  a.id,a.contactname,a.moble,c.type_name from tbl_contact a, tbl_user b, tbl_contact_type c where b.username=? and b.id=a.user_id and a.contact_type=c.id and a.contactname like ?";
			return transList(query(sql, username, "%"+contact+"%"));
		}
		else
		{
			sql = "select  a.id,a.contactname,a.moble,c.type_name from tbl_contact a, tbl_user b, tbl_contact_type c where b.username=? and b.id=a.user_id and a.contact_type=c.id and a.contactname like ? and c.type_name=?";
			return transList(query(sql, username, "%"+contact+"%", type));
		}
	}
	/**
	 * 根据类型查询客户名下联系人
	 * @param username
	 * @param type
	 * @return
	 */
	public Object[][] queryByType(String username, String type)
	{
		if(type.equals("全部"))
		{
			return queryAll(username);
		}
		String sql  = "select  a.id,a.contactname,a.moble,c.type_name from tbl_contact a, tbl_user b, tbl_contact_type c where b.username=? and b.id=a.user_id and a.contact_type=c.id and c.type_name=?";
		return transList(query(sql, username, type));
	}
	
	/**
	 * 更新联系人信息
	 * @param id
	 * @param moble
	 * @param gender
	 * @param birthday
	 * @param address
	 * @param remark
	 * @return
	 */
	public boolean update(long id, String moble, String gender, String birthday, String address, String remark, String type, String name)
	{
		String sql ="update tbl_contact set contactname=?,moble=?,gender=?,birthday=?,address=?,remarked=?,contact_type=(select id from tbl_contact_type where type_name=?) where id=?";
		return dml(sql, name, moble, gender, birthday, address, remark, type, id);
	}
	/**
	 * 增加联系人分组
	 * @param name
	 * @return
	 */
	public boolean addContactType(String name)
	{
		String sql = "insert into tbl_contact_type (type_name) values (?)";
		return dml(sql, name);
	}
	
	public List<Object[]> queryAllType()
	{
		String sql = "select type_name from tbl_contact_type";
		return query(sql);
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
		contact.setType((String)obj[8]);
		return contact;
	}
}
