import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Administrator extends User {

	Administrator(String name, String password, String role) {
		super(name, password, role);
	}

	// 均增加 try 语句捕获异常
	public void updateUser(String input_name, String input_password, String input_role) {
		try {
			if (DataProcessing.update(input_name, input_password, input_role)) {
				System.out.println("修改成功！");
			} else {
				System.out.println("修改失败！输入角色不正确！");
			}
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

	public void delUser(String input_name) {
		try {
			if (DataProcessing.delete(input_name)) {
				System.out.println("删除成功！");
			} else {
				System.out.println("删除失败！查找不到用户名！");
			}
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	public void addUser(String input_name, String input_password, String input_role) {
		try {
			if (DataProcessing.insert(input_name, input_password, input_role)) {
				System.out.println("添加成功！");
			} else {
				System.out.println("添加失败！用户名已存在/角色名不正确！");
			}
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	public void listUser() {
		try {
			// 通过 toString 重写利用枚举类遍历
			Enumeration<User> s = DataProcessing.getAllUser();
			while (s.hasMoreElements()) {
				System.out.println(s.nextElement());
			}
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	@SuppressWarnings({ "resource" })
	@Override
	public void showMenu() {

		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		Scanner scan3 = new Scanner(System.in);
		Scanner scan4 = new Scanner(System.in);

		// 控制界面的开闭
		boolean administrator_isopen = true;
		// 记录用户的选择
		String administrator_choice;

		while (administrator_isopen) {

			// 界面显示
			System.out.println("========欢迎进入档案管理员菜单========");
			System.out.println("            1.修改用户");
			System.out.println("            2.删除用户");
			System.out.println("            3.新增用户");
			System.out.println("            4.列出用户");
			System.out.println("            5.下载文件");
			System.out.println("            6.文件列表");
			System.out.println("            7.修改密码");
			System.out.println("            8.退    出");
			System.out.println("====================================");
			System.out.print("请输入选项：");
			administrator_choice = scan1.next();

			if (administrator_choice.equals("1")) {

				System.out.print("请输入用户名：");
				String input_name = scan2.next();
				System.out.print("请输入密码：");
				String input_password = scan3.next();

				// 增加 try 语句捕获异常
				try {
					if (DataProcessing.search(input_name, input_password) != null) {

						System.out.print("请输入身份：");
						String input_role = scan4.next();

						this.updateUser(input_name, input_password, input_role);

					} else {
						System.out.println("用户名与密码不符！");
					}

				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());
				}

			} else if (administrator_choice.equals("2")) {

				System.out.print("请输入用户名：");
				String input_name = scan2.next();

				this.delUser(input_name);

			} else if (administrator_choice.equals("3")) {

				System.out.print("请输入用户名：");
				String input_name = scan2.next();
				System.out.print("请输入密码：");
				String input_password = scan3.next();
				System.out.print("请输入身份：");
				String input_role = scan4.next();

				this.addUser(input_name, input_password, input_role);

			} else if (administrator_choice.equals("4")) {
				this.listUser();
			} else if (administrator_choice.equals("5")) {

				System.out.print("请输入文件名：");
				String filename = scan2.next();

				try {
					super.downloadFile(filename);
				} catch (IOException e) {
					System.out.println(e.getLocalizedMessage());
				}

			} else if (administrator_choice.equals("6")) {

				System.out.println("文件列表");
				try {
					super.showFileList();
				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());
				}

			} else if (administrator_choice.equals("7")) {

				System.out.print("请输入新密码:");
				String newpassword = scan2.next();

				try {
					if (this.changeSelfInfo(newpassword)) {
						System.out.println("修改成功!");
					} else {
						System.out.println("修改失败");
					}
				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());
				}

			} else if (administrator_choice.equals("8")) {
				administrator_isopen = false;
			} else {
				System.out.println("输入格式有误！请重新输入！");
			}

		}

	}
}