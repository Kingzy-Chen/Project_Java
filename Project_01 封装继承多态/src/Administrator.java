import java.util.Map.Entry;
import java.util.Scanner;

public class Administrator extends User {

	public Administrator(String name, String password, String role) {
		super(name, password, role);
	}

	// 删除用户
	public void delUser(String input_name) {
		if (DataProcessing.delete(input_name)) {
			System.out.println("删除成功！");
		} else {
			System.out.println("删除失败！查找不到用户名！");
		}
	}

	// 添加用户
	public void addUser(String input_name, String input_password, String input_role) {
		if (DataProcessing.insert(input_name, input_password, input_role)) {
			System.out.println("添加成功！");
		} else {
			System.out.println("删除失败！用户名已存在！");
		}
	}

	// 列出用户
	public void listUser() {
		for (Entry<String, User> u : DataProcessing.users.entrySet()) {
			String print_name = u.getValue().getName();
			String print_password = u.getValue().getPassword();
			String print_role = u.getValue().getRole();
			System.out.println("Name:" + print_name + " Password:" + print_password + " Role:" + print_role);
		}
	}

	@SuppressWarnings("resource")
	public void showMenu() {

		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		Scanner scan3 = new Scanner(System.in);
		Scanner scan4 = new Scanner(System.in);

		// 控制界面的开关
		boolean administrator_isopen = true;
		// 记录用户的选择
		String administrator_choice;

		while (administrator_isopen) {

			// 界面显示
			System.out.println("========欢迎进入档案管理员菜单========");
			System.out.println("           1.修改用户");
			System.out.println("           2.删除用户");
			System.out.println("           3.新增用户");
			System.out.println("           4.列出用户");
			System.out.println("           5.下载文件");
			System.out.println("           6.文件列表");
			System.out.println("           7.修改密码");
			System.out.println("           8.退    出");
			System.out.println("====================================");
			System.out.print("请输入选项：");
			administrator_choice = scan1.next();

			if (administrator_choice.equals("1")) {

				// 输入用户信息
				System.out.print("请输入用户名：");
				String input_name = scan2.next();
				System.out.print("请输入密码：");
				String input_password = scan3.next();

				// 密码查找用户
				if (DataProcessing.search(input_name, input_password) != null) {

					System.out.print("请输入身份：");
					String input_role = scan4.next();

					// 修改用户
					if (DataProcessing.update(input_name, input_password, input_role)) {
						System.out.println("修改成功！");
					} else {
						System.out.println("修改失败！查找不到用户名！");
					}

				} else {
					System.out.println("用户名与密码不符！");
				}

			} else if (administrator_choice.equals("2")) {

				System.out.print("请输入用户名：");
				String input_name = scan2.next();

				// 删除用户
				this.delUser(input_name);

			} else if (administrator_choice.equals("3")) {

				System.out.print("请输入用户名：");
				String input_name = scan2.next();
				System.out.print("请输入密码：");
				String input_password = scan3.next();
				System.out.print("请输入身份：");
				String input_role = scan4.next();

				// 添加用户
				this.addUser(input_name, input_password, input_role);

			} else if (administrator_choice.equals("4")) {

				// 列出用户
				System.out.println("用户列表");
				this.listUser();

			} else if (administrator_choice.equals("5")) {

				System.out.print("请输入文件名：");
				String filename = scan2.next();

				// 下载文件
				super.downloadFile(filename);

			} else if (administrator_choice.equals("6")) {

				// 列出文件
				System.out.println("文件列表");
				super.showFileList();

			} else if (administrator_choice.equals("7")) {

				System.out.print("请输入新密码:");
				String newpassword = scan2.next();

				// 修改密码
				if (this.changeSelfInfo(newpassword)) {
					System.out.println("修改成功!");
				} else {
					System.out.println("修改失败");
				}

			} else if (administrator_choice.equals("8")) {
				// 界面关闭
				administrator_isopen = false;
			} else {
				System.out.println("输入格式有误！请重新输入！");
			}
		}
	}
}