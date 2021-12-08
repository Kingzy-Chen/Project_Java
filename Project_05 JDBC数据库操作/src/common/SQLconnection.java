package common;

import java.sql.SQLException;

public class SQLconnection {

	// �������ݿ�������
	static String DriverName = "com.mysql.cj.jdbc.Driver"; // �°汾д��cj.
	
	// �������ݿ��URL
	static String Url = "jdbc:mysql://localhost:3306/document?useSSL=false&serverTimezone=UTC"; // �°汾��ʽ�ر�useSSL�����ʱ��
	
	// ���ݿ��û�
	static String User = "root";
	static String Password = "123456";

	// �������ݿ⺯��
	public static void Connect() throws ClassNotFoundException, SQLException {
		DataProcessing.connectToDatabase(DriverName, Url, User, Password);
	}

	// �Ͽ����ݿ⺯��
	public static void Disconnect() throws SQLException {
		DataProcessing.disconnectFromDatabase();
	}

}
