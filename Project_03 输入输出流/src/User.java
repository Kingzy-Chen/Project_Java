import java.io.*;
import java.sql.SQLException;
import java.util.*;

public abstract class User {

	private String name;
	private String password;
	private String role;

	// 上传与下载路径
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

		System.out.print("请输入档案号：");
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

			// 输入文件对象
			File input_file = new File(uploadpath + download_doc.getFilename());

			try {
				// 输入过滤器流,建立在文件流上
				BufferedInputStream input = new BufferedInputStream(new FileInputStream(input_file));
				// 输出文件对象
				File output_file = new File(downloadpath + download_doc.getFilename());
				// 创建文件
				output_file.createNewFile();
				// 输出过滤器流,建立在文件流上
				BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(output_file));

				// 用字节数组存取数据
				byte[] bytes = new byte[1024];
				// 文件写入操作
				int length = 0;
				while ((length = input.read(bytes)) != -1) {
					output.write(bytes, 0, length);
				}

				// 关闭流
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
		System.out.println("系统退出, 谢谢使用 ! ");
		System.exit(0);
	}

}