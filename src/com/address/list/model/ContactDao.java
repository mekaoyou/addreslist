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
	private static final String COLUMNS = " a.id,a.user_id,contactname,moble,gender,birthday,address,remarked,type_name,qq,email,post,unit,img ";
	
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
	public boolean addContact(String username, String contactName, String moble, 
			String birthday, String address, String gender, String remarked, String type,
			String qq, String email, String unit, String post, String img)
	{
		String sql = "insert into tbl_contact "
				+ "("
				+ "user_id, "
				+ "contactname, "
				+ "moble, "
				+ "gender, "
				+ "birthday, "
				+ "address, "
				+ "remarked, "
				+ "qq, "
				+ "email, "
				+ "unit, "
				+ "post, "
				+ "img, "
				+ "contact_type"
				+ ") values (("
				+ "select id from tbl_user where username=?),"
				+ "?,"
				+ "?,"
				+ "?,"
				+ "?,"
				+ "?,"
				+ "?,"
				+ "?,"
				+ "?,"
				+ "?,"
				+ "?,"
				+ "?,"
				+ "(select a.id from tbl_contact_type a,tbl_user b where a.user_id=b.id and b.username=? and type_name=?))";
		return dml(sql, username, contactName, moble, gender, 
				birthday, address, remarked, 
				qq, email, unit, post, img, 
				username, type);
	}
	/**
	 * 查询用户名下的所有联系人
	 * @param username
	 * @return
	 */
	public Object[][] queryAll(String username)
	{
		String sql = "select a.id,a.contactname,a.moble,c.type_name,a.gender,a.qq,a.email,a.unit from tbl_contact a, tbl_user b, tbl_contact_type c where b.username=? and b.id=a.user_id and a.contact_type=c.id order by a.id";
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
			sql = "select  a.id,a.contactname,a.moble,c.type_name,a.gender,a.qq,a.email,a.unit from tbl_contact a, tbl_user b, tbl_contact_type c where b.username=? and b.id=a.user_id and a.contact_type=c.id and a.contactname like ? order by a.id";
			return transList(query(sql, username, "%"+contact+"%"));
		}
		else
		{
			sql = "select  a.id,a.contactname,a.moble,c.type_name,a.gender,a.qq,a.email,a.unit from tbl_contact a, tbl_user b, tbl_contact_type c where b.username=? and b.id=a.user_id and a.contact_type=c.id and a.contactname like ? and c.type_name=? order by a.id";
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
		String sql  = "select  a.id,a.contactname,a.moble,c.type_name,a.gender,a.qq,a.email,a.unit from tbl_contact a, tbl_user b, tbl_contact_type c where b.username=? and b.id=a.user_id and a.contact_type=c.id and c.type_name=?  order by a.id";
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
	public boolean update(long id, String moble, String gender, String birthday, String address, String remark, String type, String name,
											String qq, String email, String post, String unit, String img, String username)
	{
		String sql ="update tbl_contact set contactname=?,moble=?,gender=?,birthday=?,address=?,remarked=?,"
				+ "contact_type=(select type.id from tbl_user u,tbl_contact_type type where u.id=type.user_id and u.username=? and type.type_name=?),"
				+ "qq=?,email=?,post=?,unit=?,img=? where id=?";
		return dml(sql, name, moble, gender, birthday, address, remark, username, type, qq, email, post, unit, img, id);
	}
	/**
	 * 增加联系人分组
	 * @param name
	 * @return
	 */
	public boolean addContactType(String username, String type)
	{
		//check
		String sql = "select * from tbl_user a, tbl_contact_type b where a.id=b.user_id and a.username=? and b.type_name=?";
		if(query(sql, username, type).size() > 0)
		{
			return false;
		}
		sql = "insert into tbl_contact_type (user_id,type_name) values ((select id from tbl_user where username=?),?)";
		return dml(sql, username, type);
	}
	
	/**
	 * 插叙联系人所有分组
	 * @param username
	 * @return
	 */
	public List<Object[]> queryAllType(String username)
	{
		String sql = "select type_name from tbl_contact_type a, tbl_user b where a.user_id=b.id and b.username=?";
		return query(sql, username);
	}
	
	private ContactEntity obj2Contact(Object[] obj)
	{
		//id,user_id,contactname,moble,gender,birthday,address,remarked,qq,email,post,unit,img
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
		contact.setQq((String)obj[9]);
		contact.setEmail((String)obj[10]);
		contact.setPost((String)obj[11]);
		contact.setUnit((String)obj[12]);
		contact.setImg((String)obj[13]);
		return contact;
	}
}
