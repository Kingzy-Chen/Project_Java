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

	// �������ݿ�
	public static void Init() {
		if (Math.random() > 0.1)
			connectToDB = true;
		else
			connectToDB = false;
	}

	// �����ļ�
	public static Doc searchDoc(String ID) throws SQLException {
		
		if (!connectToDB)
			throw new SQLException("δ���ӵ����ݿ⣡");

		double ranValue = Math.random();
		if (ranValue > 0.9)
			throw new SQLException("ִ�в�ѯʱ����");

		if (docs.containsKey(ID)) {
			Doc temp = docs.get(ID);
			return temp;
		}
		return null;
	}

	// �ļ��б�
	public static Enumeration<Doc> getAllDocs() throws SQLException {
		
		if (!connectToDB)
			throw new SQLException("δ���ӵ����ݿ⣡");

		double ranValue = Math.random();
		if (ranValue > 0.9)
			throw new SQLException("ִ�в�ѯʱ����");

		Enumeration<Doc> e = docs.elements();
		return e;
	}

	// ����ļ�
	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename)
			throws SQLException {
		
		if (!connectToDB)
			throw new SQLException("δ���ӵ����ݿ⣡");

		double ranValue = Math.random();
		if (ranValue > 0.9)
			throw new SQLException("ִ�в�ѯʱ����");

		Doc doc;
		if (docs.containsKey(ID))
			return false;
		else {
			doc = new Doc(ID, creator, timestamp, description, filename);
			docs.put(ID, doc);
			return true;
		}
	}

	// �����û�
	public static User searchUser(String name) throws SQLException {

		if (!connectToDB)
			throw new SQLException("δ���ӵ����ݿ⣡");

		double ranValue = Math.random();
		if (ranValue > 0.9)
			throw new SQLException("ִ�в�ѯʱ����");

		if (users.containsKey(name)) {
			return users.get(name);
		}
		return null;
	}

	// ��������û�
	public static User searchUser(String name, String password) throws SQLException {

		if (!connectToDB)
			throw new SQLException("δ���ӵ����ݿ⣡");

		double ranValue = Math.random();
		if (ranValue > 0.9)
			throw new SQLException("ִ�в�ѯʱ����");

		if (users.containsKey(name)) {
			User temp = users.get(name);
			if ((temp.getPassword()).equals(password))
				return temp;
		}
		return null;
	}

	// �û��б�
	public static Enumeration<User> getAllUser() throws SQLException {

		if (!connectToDB)
			throw new SQLException("δ���ӵ����ݿ⣡");

		double ranValue = Math.random();
		if (ranValue > 0.9)
			throw new SQLException("ִ�в�ѯʱ����");

		Enumeration<User> e = users.elements();
		return e;
	}

	// �����û�
	public static boolean updateUser(String name, String password, String role) throws SQLException {

		if (!connectToDB)
			throw new SQLException("δ���ӵ����ݿ⣡");

		double ranValue = Math.random();
		if (ranValue > 0.8)
			throw new SQLException("ִ�и���ʱ����");

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

	// ����û�
	public static boolean insertUser(String name, String password, String role) throws SQLException {

		if (!connectToDB)
			throw new SQLException("δ���ӵ����ݿ⣡");

		double ranValue = Math.random();
		if (ranValue > 0.8)
			throw new SQLException("ִ�в���ʱ����");

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

	// ɾ���û�
	public static boolean deleteUser(String name) throws SQLException {

		if (!connectToDB)
			throw new SQLException("δ���ӵ����ݿ⣡");

		double ranValue = Math.random();
		if (ranValue > 0.8)
			throw new SQLException("ִ��ɾ��ʱ����");

		if (users.containsKey(name)) {
			users.remove(name);
			return true;
		} else
			return false;
	}

	// �Ͽ����ݿ�����
	public static void disconnectFromDB() throws SQLException {
		if (connectToDB) {
			if (Math.random() > 0.8) {
				throw new SQLException("�Ͽ����ݿ�����ʱ����");
			}
			connectToDB = false;
		}
	}

}