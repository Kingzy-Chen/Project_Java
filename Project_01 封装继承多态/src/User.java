
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

	public abstract void showMenu();

	public boolean downloadFile(String fileID) {
		System.out.println("�����ļ�... ...");
		System.out.println("���سɹ���");
		return true;
	}

	public void showFileList() {
		System.out.println("�б�... ...");
	}

	public boolean changeSelfInfo(String password) {
		if (DataProcessing.update(name, password, role)) {
			this.password = password;
			return true;
		} else
			return false;
	}

	public void exitSystem() {
		System.out.println("ϵͳ�˳�, ллʹ�� ! ");
		System.exit(0);
	}

}