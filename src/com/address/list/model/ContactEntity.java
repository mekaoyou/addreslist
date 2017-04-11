package com.address.list.model;

/**
 * 联系人实体类
 * @author Geek
 *
 */
public class ContactEntity
{
	private long id;
	private long userId;
	private String contactName;
	private String gender;
	private String birthday;
	private String moble;
	private String address;
	private String remarked;
	private String type;
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public long getUserId()
	{
		return userId;
	}
	public void setUserId(long userId)
	{
		this.userId = userId;
	}
	public String getContactName()
	{
		return contactName;
	}
	public void setContactName(String contactName)
	{
		this.contactName = contactName;
	}
	public String getGender()
	{
		return gender;
	}
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	public String getBirthday()
	{
		return birthday;
	}
	public void setBirthday(String birthday)
	{
		this.birthday = birthday;
	}
	public String getMoble()
	{
		return moble;
	}
	public void setMoble(String moble)
	{
		this.moble = moble;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getRemarked()
	{
		return remarked;
	}
	public void setRemarked(String remarked)
	{
		this.remarked = remarked;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	
}
