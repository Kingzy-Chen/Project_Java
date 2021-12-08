import java.util.*;

public class DataProcessing {

	// 哈希表
	static Hashtable<String, User> users;
	static {
		users = new Hashtable<String, User>();
		users.put("rose", new Browser("rose", "123", "browser"));
		users.put("jack", new Operator("jack", "123", "operator"));
		users.put("kate", new Administrator("kate", "123", "administrator"));
	}

	// 查找
	public static User searchUser(String name) {
		if (users.containsKey(name)) {
			return users.get(name);
		}
		return null;
	}

	// 密码查找
	public static User search(String name, String password) {
		if (users.containsKey(name)) {
			User temp = users.get(name);
			if ((temp.getPassword()).equals(password))
				return temp;
		}
		return null;
	}

	// 遍历
	public static Enumeration<User> getAllUser() {
		Enumeration<User> e = users.elements();
		return e;
	}

	// 更新
	public static boolean update(String name, String password, String role) {
		User user;
		if (users.containsKey(name)) {
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name, password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name, password, role);
			else
				user = new Browser(name, password, role);
			users.put(name, user);
			return true;
		} else
			return false;
	}

	// 增添
	public static boolean insert(String name, String password, String role) {
		User user;
		if (users.containsKey(name))
			return false;
		else {
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name, password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name, password, role);
			else
				user = new Browser(name, password, role);
			users.put(name, user);
			return true;
		}
	}

	// 删除
	public static boolean delete(String name) {
		if (users.containsKey(name)) {
			users.remove(name);
			return true;
		} else
			return false;
	}

}