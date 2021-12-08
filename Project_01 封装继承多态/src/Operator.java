import java.util.Scanner;

public class Operator extends User {

	public Operator(String name, String password, String role) {
		super(name, password, role);
	}

	// 上传文件
	@SuppressWarnings({ "resource", "unused" })
	public void uploadFile() {

		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		Scanner scan3 = new Scanner(System.in);

		System.out.println("上传文件");

		System.out.print("请输入文件名：");
		String filename = scan1.next();
		System.out.print("请输入档案号：");
		String fileID = scan2.next();
		System.out.print("请输入档案描述：");
		String fileDescrption = scan3.next();
		System.out.println("上传成功！");

	}

	@SuppressWarnings({ "resource" })
	public void showMenu() {

		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);

		// 控制页面的开关
		boolean operator_isopen = true;
		// 记录用户的选择
		String operator_choice;

		while (operator_isopen) {

			// 显示页面
			System.out.println("========欢迎进入档案录入员菜单========");
			System.out.println("            1.上传文件");
			System.out.println("            2.下载文件");
			System.out.println("            3.文件列表");
			System.out.println("            4.修改密码");
			System.out.println("            5.退    出 ");
			System.out.println("====================================");
			System.out.print("请输入选项：");
			operator_choice = scan1.next();

			if (operator_choice.equals("1")) {

				// 上传文件
				this.uploadFile();

			} else if (operator_choice.equals("2")) {

				System.out.print("请输入文件名：");
				String filename = scan2.next();

				// 下载文件
				super.downloadFile(filename);

			} else if (operator_choice.equals("3")) {

				// 列出文件
				System.out.println("文件列表");
				super.showFileList();

			} else if (operator_choice.equals("4")) {

				System.out.print("请输入新密码:");
				String newpassword = scan2.next();

				// 修改密码
				if (this.changeSelfInfo(newpassword)) {
					System.out.println("修改成功!");
				} else {
					System.out.println("修改失败");
				}

			} else if (operator_choice.equals("5")) {
				// 关闭界面
				operator_isopen = false;
			} else {
				System.out.println("输入格式有误！请重新输入！");
			}
		}
	}
}