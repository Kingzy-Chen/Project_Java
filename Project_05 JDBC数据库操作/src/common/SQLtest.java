package common;

import java.sql.*;

public class SQLtest { // �������ڲ������ݿ�����

	public static void main(String[] args) {

		Connection connection;
		Statement statement;
		ResultSet resultSet;

		// �������ݿ�������
		// String driverName = "com.mysql.jdbc.Driver"; // �ɰ汾д��
		String driverName = "com.mysql.cj.jdbc.Driver"; // �°汾д��cj.
		
		// �������ݿ��URL
		// String url = "jdbc:mysql://localhost:3306/document"; // �ɰ汾д��
		String url = "jdbc:mysql://localhost:3306/document?useSSL=false&serverTimezone=UTC"; // �°汾��ʽ�ر�useSSL�����ʱ��
		
		// ���ݿ��û�
		String user = "root";
		String password = "123456";

		try {

			// 1. ��������
			Class.forName(driverName);
			// 2. �������ݿ�����
			connection = DriverManager.getConnection(url, user, password);
			// 3. ����������
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// 4. ִ��SQL���
			String sql = "select * from user_info ";
			// 5. �������
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String username = resultSet.getString("UserName");
				String pwd = resultSet.getString("Password");
				String role = resultSet.getString("Role");
				System.out.println(username + ";" + pwd + ";" + role);
			}
			// 6. �ͷ���Դ
			resultSet.close();
			statement.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			System.out.println("������������");
		} catch (SQLException e) {
			System.out.println("���ݿ����");
		}

	}

}