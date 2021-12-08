package common;

import java.io.*;
import java.sql.*;
import javax.swing.JOptionPane;

public class User {

	private String name;
	private String password;
	private String role;

	String uploadpath = "E:\\uploadfile\\";
	String downloadpath = "E:\\downloadfile\\";

	User(String name, String password, String role) {
		this.setName(name);
		this.setPassword(password);
		this.setRole(role);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String toString() {
		return "Name: " + this.name + " Password: " + this.password + " Role: " + this.role;
	}

	public boolean downloadFile(String fileID) {

		Doc doc;

		try {

			// ��ȡ��ϣ����Ϣ
			doc = DataProcessing.searchDoc(fileID);
			// �����ļ�����
			File input_file = new File(uploadpath + doc.getFilename());
			// �����������,�������ļ�����
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(input_file));

			// ����ļ�����
			File output_file = new File(downloadpath + doc.getFilename());
			// �����ļ�
			output_file.createNewFile();
			// �����������,�������ļ�����
			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(output_file));

			// ���ֽ������ȡ����
			byte[] bytes = new byte[1024];
			// �ļ�д�����
			int length = 0;
			while ((length = input.read(bytes)) != -1) {
				output.write(bytes, 0, length);
			}

			// �ر���
			input.close();
			output.close();
			
			return true;

		} catch (SQLException | IOException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		}

		return false;
	}

	public boolean uploadFile(String fileID, String filepath, String filedescription) {

		// �����ļ�����
		File input_file = new File(filepath);
		// ��ȡ�ļ���
		String filename = input_file.getName();
		// ��ȡ��ǰʱ��
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		try {

			if (DataProcessing.insertDoc(fileID, this.getName(), timestamp, filedescription, filename)) {

				// �����������,�������ļ�����
				BufferedInputStream input = new BufferedInputStream(new FileInputStream(input_file));

				// ����ļ�����
				File output_file = new File(uploadpath + input_file.getName());
				// �����ļ�
				output_file.createNewFile();
				// �����������,�������ļ�����
				BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(output_file));

				// ���ֽ������ȡ����
				byte[] bytes = new byte[1024];
				// �ļ�д�����
				int length = 0;
				while ((length = input.read(bytes)) != -1) {
					output.write(bytes, 0, length);
				}

				// �ر���
				input.close();
				output.close();

				return true;

			} else
				return false;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		}

		return false;
	}

	// �޸�����
	public boolean changeSelfInfo(String password) {

		try {
			if (DataProcessing.updateUser(name, password, role)) {
				this.password = password;
				return true;
			} else
				return false;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		}

		return false;
	}

}