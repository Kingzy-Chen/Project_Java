import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		Scanner scan3 = new Scanner(System.in);

		// 控制界面开关
		boolean main_isopen = true;
		// 记录用户选择
		String main_choice;

		while (main_isopen) {

			// 界面显示
			System.out.println("========欢迎进入档案系统========");
			System.out.println("           1.登   录 ");
			System.out.println("           2.退   出 ");
			System.out.println("==============================");
			System.out.print("请输入选项：");
			main_choice = scan1.next();

			if (main_choice.equals("1")) {

				System.out.print("请输入用户名：");
				String input_name = scan2.next();
				System.out.print("请输入密码：");
				String input_password = scan3.next();

				User user;
				try {
					user = DataProcessing.searchUser(input_name, input_password);
					if (user == null) {
						System.out.println("用户名与密码不符！");
					} else {
						user.showMenu();
					}
				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());
				}

			} else if (main_choice.equals("2")) {

				try {
					// 断开连接
					DataProcessing.disconnectFromDB();
					main_isopen = false;
				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());
				}

			} else {
				System.out.println("输入格式有误！请重新输入！");
			}

		}
		System.out.println("系统退出，感谢使用！");
	}

}