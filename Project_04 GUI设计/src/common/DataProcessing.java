package common;

import java.sql.*;
import java.util.*;

public class DataProcessing {

	private static boolean connectToDB = false;

	static Hashtable<String, User> users;
	static Hashtable<String, Doc> docs;

	static {
		users = new Hashtable<String, User>();
		users.put("jack", new Operator("jack", "123", "Operator"));
		users.put("rose", new Browser("rose", "123", "Browser"));
		users.put("kate", new Administrator("kate", "123", "Administrator"));
		Init();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		docs = new Hashtable<String, Doc>();
		docs.put("0001", new Doc("0001", "jack", timestamp, "Doc Source Java", "Doc.txt"));
	}

	// 连接数据库
	public static void Init() {
		if (Math.random() > 0.1)
			connectToDB = true;
		else
			connectToDB = false;
	}

	// 查找文件
	public static Doc searchDoc(String ID) throws SQLException {
		
		if (!connectToDB)
			throw new SQLException("未连接到数据库！");

		double ranValue = Math.random();
		if (ranValue > 0.9)
			throw new SQLException("执行查询时出错！");

		if (docs.containsKey(ID)) {
			Doc temp = docs.get(ID);
			return temp;
		}
		return null;
	}

	// 文件列表
	public static Enumeration<Doc> getAllDocs() throws SQLException {
		
		if (!connectToDB)
			throw new SQLException("未连接到数据库！");

		double ranValue = Math.random();
		if (ranValue > 0.9)
			throw new SQLException("执行查询时出错！");

		Enumeration<Doc> e = docs.elements();
		return e;
	}

	// 添加文件
	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename)
			throws SQLException {
		
		if (!connectToDB)
			throw new SQLException("未连接到数据库！");

		double ranValue = Math.random();
		if (ranValue > 0.9)
			throw new SQLException("执行查询时出错！");

		Doc doc;
		if (docs.containsKey(ID))
			return false;
		else {
			doc = new Doc(ID, creator, timestamp, description, filename);
			docs.put(ID, doc);
			return true;
		}
	}

	// 查找用户
	public static User searchUser(String name) throws SQLException {

		if (!connectToDB)
			throw new SQLException("未连接到数据库！");

		double ranValue = Math.random();
		if (ranValue > 0.9)
			throw new SQLException("执行查询时出错！");

		if (users.containsKey(name)) {
			return users.get(name);
		}
		return null;
	}

	// 密码查找用户
	public static User searchUser(String name, String password) throws SQLException {

		if (!connectToDB)
			throw new SQLException("未连接到数据库！");

		double ranValue = Math.random();
		if (ranValue > 0.9)
			throw new SQLException("执行查询时出错！");

		if (users.containsKey(name)) {
			User temp = users.get(name);
			if ((temp.getPassword()).equals(password))
				return temp;
		}
		return null;
	}

	// 用户列表
	public static Enumeration<User> getAllUser() throws SQLException {

		if (!connectToDB)
			throw new SQLException("未连接到数据库！");

		double ranValue = Math.random();
		if (ranValue > 0.9)
			throw new SQLException("执行查询时出错！");

		Enumeration<User> e = users.elements();
		return e;
	}

	// 更新用户
	public static boolean updateUser(String name, String password, String role) throws SQLException {

		if (!connectToDB)
			throw new SQLException("未连接到数据库！");

		double ranValue = Math.random();
		if (ranValue > 0.8)
			throw new SQLException("执行更新时出错！");

		User user;
		if (users.containsKey(name)) {
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name, password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name, password, role);
			else if (role.equalsIgnoreCase("browser"))
				user = new Browser(name, password, role);
			else
				return false;

			users.put(name, user);
			return true;
		} else
			return false;
	}

	// 添加用户
	public static boolean insertUser(String name, String password, String role) throws SQLException {

		if (!connectToDB)
			throw new SQLException("未连接到数据库！");

		double ranValue = Math.random();
		if (ranValue > 0.8)
			throw new SQLException("执行插入时出错！");

		User user;
		if (users.containsKey(name))
			return false;
		else {
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name, password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name, password, role);
			else if (role.equalsIgnoreCase("browser"))
				user = new Browser(name, password, role);
			else
				return false;

			users.put(name, user);
			return true;
		}
	}

	// 删除用户
	public static boolean deleteUser(String name) throws SQLException {

		if (!connectToDB)
			throw new SQLException("未连接到数据库！");

		double ranValue = Math.random();
		if (ranValue > 0.8)
			throw new SQLException("执行删除时出错！");

		if (users.containsKey(name)) {
			users.remove(name);
			return true;
		} else
			return false;
	}

	// 断开数据库连接
	public static void disconnectFromDB() throws SQLException {
		if (connectToDB) {
			if (Math.random() > 0.8) {
				throw new SQLException("断开数据库连接时出错！");
			}
			connectToDB = false;
		}
	}

}