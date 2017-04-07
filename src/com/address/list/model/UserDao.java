package com.address.list.model;

/**
 * 用户账号数据库操作
 * @author Alex
 *
 */
public class UserDao extends AbstractDao
{		

	private static UserDao instance;
	
	public static UserDao getInstance()
	{
		if(instance == null)
		{
			instance = new UserDao();
		}
		return instance;
	}
	/**
	 * use
	 * 用户登录时验证身份
	 * @param usrname 用户名
	 * @param password 密码
	 * @return 若找到该用户记录，登录成功，否则登录失败
	 */
	public boolean login(String usrname,String password)
	{
		String sql="select id from tbl_user where username=? and password = ?";
		return query(sql, usrname,password).size() > 0;
	}
	
	/**
	 * use
	 * 申请账号时测试用户名是否可用
	 * @param username 输入的用户名
	 * @return 如果返回TRUE 表示已经占用，不可用
	 */
	public boolean addUserCheck(String username)
	{
		String sql="select id from tbl_user where username=?";
		return query(sql, username).size()>0;
	}
	
	/**
	 * use
	 * 查找用户的密码提示问题
	 * @param username
	 * @return
	 */
	public String searchPwQ(String username)
	{
		String sql="select pwquestion from tbl_user where username = ?";
		return (String)transList(query(sql, username))[0][0];
	}
	/**
	 * use
	 * 验证密保问题是否真确
	 * @param username 用户名
	 * @param pwAwswer 密保答案
	 * @return
	 */
	public boolean isPwAright(String username,String pwAnswer)
	{
		String sql="select * from tbl_user where username = ? and pwanswer = ?";
		return query(sql, username,pwAnswer).size()>0;
	}
	/**
	 * use
	 * 用户修改密码
	 * @param username 用户名	
	 * @param password 新密码
	 * @return
	 */
	public boolean updatePassword(String username,String password)
	{
		String sql="update tbl_user set password = ? where username = ?";
		return dml(sql, password,username);
	}
	/**
	 * use
	 * 用户修改自己的账户资料
	 * @param values 账户资料相关数据
	 * @return 若返回1，修改成功
	 */
	public boolean updateUser(Object[] values)
	{
		String sql="update tbl_user set password=?,pwquestion=?,pwanswer=?,remarked=? where username=?";
		return dml(sql, values);
	}
	
	/**
	 * use
	 * 用户申请账号
	 * @param values 用户提供的相关数据
	 * @return 若返回1，申请成功
	 */
	public boolean appUser(Object[] values)
	{
		String sql="insert into tbl_user (username,password,pwquestion,pwanswer,remarked) values (?,?,?,?,?)";
		return dml(sql, values);
	}
	
	/**
	 * 设置默认账号
	 * @param username
	 * @param type
	 */
	public void setDefaultLogin(String username)
	{
		String sql = "update tbl_user t set t.default_login='0' where t.default_login!='0'";
		dml(sql);
		sql = "update tbl_user t set t.default_login='1' where t.username=?";
		dml(sql, username);
	}
	
	/**
	 * 获取默认账号
	 * @return
	 */
	public Object[] getDefaultUser()
	{
		String sql = "select t.username,t.password,t.default_login from tbl_user t where t.default_login != '0' limit 0,1";
		return queryUniqueResult(sql);
	}
}
