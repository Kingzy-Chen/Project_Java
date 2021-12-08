import java.sql.SQLException;
import java.util.*;

public class DataProcessing {

	private static boolean connectToDB = false;

	static Hashtable<String, User> users;
	static {
		users = new Hashtable<String, User>();
		users.put("jack", new Operator("jack", "123", "operator"));
		users.put("rose", new Browser("rose", "123", "browser"));
		users.put("kate", new Administrator("kate", "123", "administrator"));
		Init();
	}

	// 模拟数据库初始化方法
	public static void Init() {
		if (Math.random() > 0.2)
			connectToDB = true;
		else
			connectToDB = false;
	}

	public static User searchUser(String name) throws SQLException {

		// 未连接数据库抛出异常
		if (!connectToDB)
			throw new SQLException("未连接到数据库！");

		// 通过随机数模拟异常抛出
		double ranValue = Math.random();
		if (ranValue > 0.8)
			throw new SQLException("执行查询时出错！");

		if (users.containsKey(name)) {
			return users.get(name);
		}
		return null;
	}

	public static User search(String name, String password) throws SQLException {

		if (!connectToDB)
			throw new SQLException("未连接到数据库！");

		double ranValue = Math.random();
		if (ranValue > 0.8)
			throw new SQLException("执行查询时出错！");

		if (users.containsKey(name)) {
			User temp = users.get(name);
			if ((temp.getPassword()).equals(password))
				return temp;
		}
		return null;
	}

	public static Enumeration<User> getAllUser() throws SQLException {

		if (!connectToDB)
			throw new SQLException("未连接到数据库！");

		double ranValue = Math.random();
		if (ranValue > 0.8)
			throw new SQLException("执行查询时出错！");

		Enumeration<User> e = users.elements();
		return e;
	}

	public static boolean update(String name, String password, String role) throws SQLException {

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

	public static boolean insert(String name, String password, String role) throws SQLException {

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

	public static boolean delete(String name) throws SQLException {

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