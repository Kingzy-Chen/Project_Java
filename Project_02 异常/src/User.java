import java.io.IOException;
import java.sql.SQLException;

public abstract class User {

	private String name;
	private String password;
	private String role;

	User(String name, String password, String role) {
		this.name = name;
		this.password = password;
		this.role = role;
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

	// 重写 toString 方法
	public String toString() {
		return "Name: " + this.name + " Password: " + this.password + " Role: " + this.role;
	}

	public abstract void showMenu();

	// 模拟抛出异常
	public boolean downloadFile(String filename) throws IOException {

		double ranValue = Math.random();
		if (ranValue > 0.8)
			throw new IOException("访问文件时出错!");

		System.out.println("下载文件...");
		return true;
	}

	public void showFileList() throws SQLException {

		double ranValue = Math.random();
		if (ranValue > 0.8)
			throw new SQLException("访问文件数据库时出错!");

		System.out.println("列表...");
	}

	public boolean changeSelfInfo(String password) throws SQLException {
		if (DataProcessing.update(name, password, role)) {
			this.password = password;
			return true;
		} else
			return false;
	}

	public void exitSystem() {
		System.out.println("系统退出, 谢谢使用 ! ");
		System.exit(0);
	}

}