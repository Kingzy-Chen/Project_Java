package common;

import java.sql.*;
import java.util.Enumeration;
import java.util.Hashtable;

public class DataProcessing {

	private static boolean connectedToDatabase = false;

	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;

	public static void connectToDatabase(String driverName, String url, String user, String password)
			throws SQLException, ClassNotFoundException {
		// ��������
		Class.forName(driverName);
		// �������ݿ�����
		connection = DriverManager.getConnection(url, user, password);
		connectedToDatabase = true;
	}

	public static void disconnectFromDatabase() throws SQLException {
		if (connectedToDatabase) {
			// �ͷ���Դ
			resultSet.close();
			statement.close();
			connection.close();
			connectedToDatabase = false;
		}
	}

	public static Doc searchDoc(String ID) throws SQLException {

		Doc doc = null;

		if (!connectedToDatabase)
			throw new SQLException("���ݿ�δ���ӣ�");

		// ����������
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// ִ��SQL���
		String sql = "select * from doc_info where FileID='" + ID + "'"; // ��ȡFileID����ֵΪID�����ݣ�����ǰ����+��'"��"'����

		// �������
		resultSet = statement.executeQuery(sql);
		// ��ȡ���ݿ���Ϣ
		if (resultSet.next()) {

			String FileID = resultSet.getString("FileID");
			String Creator = resultSet.getString("Creator");
			Timestamp timestamp = Timestamp.valueOf(resultSet.getString("Timestamp")); // stringתTimestamp
			String Description = resultSet.getString("Description");
			String FileName = resultSet.getString("FileName");

			doc = new Doc(FileID, Creator, timestamp, Description, FileName);
		}

		return doc;
	}

	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename)
			throws SQLException {

		if (!connectedToDatabase)
			throw new SQLException("���ݿ�δ���ӣ�");

		// ����������
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// ִ��SQL���
		String sql = "INSERT INTO doc_info VALUES('" + ID + "','" + creator + "','" + timestamp + "','" + description
				+ "','" + filename + "')";
		// �����б�
		statement.executeUpdate(sql);

		return true;
	}

	public static Enumeration<Doc> getAllDocs() throws SQLException {

		if (!connectedToDatabase)
			throw new SQLException("���ݿ�δ���ӣ�");

		// ������ϣ��
		Hashtable<String, Doc> docs = new Hashtable<String, Doc>();

		// ����������
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// ִ��SQL���
		String sql = "select * from doc_info "; // ��ȡdoc_info����ֵ

		// �������
		resultSet = statement.executeQuery(sql);
		// ��ȡ���ݿ���Ϣ
		while (resultSet.next()) {

			// �������
			String FileID = resultSet.getString("FileID");
			String Creator = resultSet.getString("Creator");
			Timestamp timestamp = Timestamp.valueOf(resultSet.getString("Timestamp")); // stringתTimestamp
			String Description = resultSet.getString("Description");
			String FileName = resultSet.getString("FileName");

			docs.put(FileID, new Doc(FileID, Creator, timestamp, Description, FileName));
		}

		// ����ö������
		Enumeration<Doc> e = docs.elements();
		return e;
	}

	public static User searchUser(String name) throws SQLException {

		User user = null;

		if (!connectedToDatabase)
			throw new SQLException("δ���ӵ����ݿ⣡");

		// ����������
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// ִ��SQL���
		String sql = "select * from user_info where UserName='" + name + "'"; // ��ȡUserName����ֵΪname������,����ǰ����+��'"��"'����

		// �������
		resultSet = statement.executeQuery(sql);
		// ��ȡ���ݿ���Ϣ
		if (resultSet.next()) {

			String UserName = resultSet.getString("UserName");
			String Password = resultSet.getString("Password");
			String Role = resultSet.getString("Role");

			user = new User(UserName, Password, Role);
		}

		return user;
	}

	public static User searchUser(String name, String password) throws SQLException {

		User user = null;

		if (!connectedToDatabase)
			throw new SQLException("δ���ӵ����ݿ⣡");

		// ����������
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// ִ��SQL���
		String sql = "select * from user_info where UserName='" + name + "'"; // ��ȡUserName����ֵΪname������,����ǰ����+��'"��"'����

		// �������
		resultSet = statement.executeQuery(sql);
		// ��ȡ���ݿ���Ϣ
		if (resultSet.next()) {

			String UserName = resultSet.getString("UserName");
			String Password = resultSet.getString("Password");
			String Role = resultSet.getString("Role");

			user = new User(UserName, Password, Role);

			if (Password.equals(password)) {
				return user;
			} else {
				return null;
			}
		}

		return null;
	}

	public static Enumeration<User> getAllUser() throws SQLException {

		if (!connectedToDatabase)
			throw new SQLException("δ���ӵ����ݿ⣡");

		// ������ϣ��
		Hashtable<String, User> users = new Hashtable<String, User>();

		// ����������
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// ִ��SQL���
		String sql = "select * from user_info "; // ��ȡuser_info����ֵ

		// �������
		resultSet = statement.executeQuery(sql);
		// ��ȡ���ݿ���Ϣ
		while (resultSet.next()) {

			// �������
			String UserName = resultSet.getString("UserName");
			String Password = resultSet.getString("Password");
			String Role = resultSet.getString("Role");

			users.put(UserName, new User(UserName, Password, Role));
		}

		// ����ö������
		Enumeration<User> e = users.elements();
		return e;
	}

	public static boolean updateUser(String name, String password, String role) throws SQLException {

		if (!connectedToDatabase)
			throw new SQLException("δ���ӵ����ݿ⣡");

		// ����������
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// ִ��SQL���
		String sql = "UPDATE user_info SET Password='" + password + "',Role='" + role + "'WHERE UserName='" + name
				+ "'"; // ����user_info�������
		// �����б�
		statement.executeUpdate(sql);

		return true;
	}

	public static boolean insertUser(String name, String password, String role) throws SQLException {

		if (!connectedToDatabase)
			throw new SQLException("δ���ӵ����ݿ⣡");

		// ����������
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// ִ��SQL���
		String sql = "INSERT INTO user_info VALUES('" + name + "','" + password + "','" + role + "')";
		// �����б�
		statement.executeUpdate(sql);

		return true;
	}

	public static boolean deleteUser(String name) throws SQLException {

		if (!connectedToDatabase)
			throw new SQLException("δ���ӵ����ݿ⣡");

		// ����������
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		// ִ��SQL���
		String sql = "DELETE FROM user_info WHERE UserName='" + name + "'";
		// �����б�
		statement.executeUpdate(sql);

		return true;
	}
}