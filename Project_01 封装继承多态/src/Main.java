import java.util.Scanner;

public class Main {

	@SuppressWarnings({ "resource" })
	public static void main(String[] args) {

		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		Scanner scan3 = new Scanner(System.in);

		// 控制界面的开闭
		boolean main_isopen = true;
		// 用于记录用户的选择
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

				// 输入用户信息
				System.out.print("请输入用户名：");
				String input_name = scan2.next();
				System.out.print("请输入密码：");
				String input_password = scan3.next();

				// 对密码正确性的检查
				User user = DataProcessing.search(input_name, input_password);
				if (user == null) {
					System.out.println("用户名与密码不符！");
				} else {
					// 打开对应身份的界面
					user.showMenu();
				}

			} else if (main_choice.equals("2")) {
				// 退出情况，将界面关闭
				main_isopen = false;
			} else {
				// 格式输入错误的情况
				System.out.println("输入格式有误！请重新输入！");
			}

		}
		System.out.println("系统退出，感谢使用！");
	}

}