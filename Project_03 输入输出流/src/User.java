import java.io.*;
import java.sql.SQLException;
import java.util.*;

public abstract class User {

	private String name;
	private String password;
	private String role;

	// �ϴ�������·��
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

	public abstract void showMenu();

	public String toString() {
		return "Name: " + this.name + " Password: " + this.password + " Role: " + this.role;
	}

	@SuppressWarnings("resource")
	public boolean downloadFile() {

		Scanner scan = new Scanner(System.in);

		System.out.print("�����뵵���ţ�");
		String download_id = scan.next();

		Doc download_doc = null;
		try {
			download_doc = DataProcessing.searchDoc(download_id);
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}

		if (download_doc == null) {
			return false;
		} else {

			// �����ļ�����
			File input_file = new File(uploadpath + download_doc.getFilename());

			try {
				// �����������,�������ļ�����
				BufferedInputStream input = new BufferedInputStream(new FileInputStream(input_file));
				// ����ļ�����
				File output_file = new File(downloadpath + download_doc.getFilename());
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

			} catch (FileNotFoundException e) {
				System.out.println(e.getLocalizedMessage());
			} catch (IOException e) {
				System.out.println(e.getLocalizedMessage());
			}

			return false;
		}
	}

	public void showFileList() {
		try {
			Enumeration<Doc> e = DataProcessing.getAllDocs();
			while (e.hasMoreElements()) {
				System.out.println(e.nextElement());
			}
		} catch (SQLException s) {
			System.out.println(s.getLocalizedMessage());
		}
	}

	public boolean changeSelfInfo(String password) {
		try {
			if (DataProcessing.updateUser(name, password, role)) {
				this.password = password;
				return true;
			} else
				return false;
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return false;
	}

	public void exitSystem() {
		System.out.println("ϵͳ�˳�, ллʹ�� ! ");
		System.exit(0);
	}

}