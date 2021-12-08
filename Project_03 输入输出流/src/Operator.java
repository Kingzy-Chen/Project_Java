import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Operator extends User {

	public Operator(String name, String password, String role) {
		super(name, password, role);
	}

	@SuppressWarnings("resource")
	public void uploadFile() {

		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		Scanner scan3 = new Scanner(System.in);

		System.out.print("请输入文件名(路径)：");
		String filename = scan1.next();
		System.out.print("请输入档案号：");
		String filenumber = scan2.next();

		try {
			if (DataProcessing.searchDoc(filenumber) != null) {
				System.out.println("档案号重复！");
			} else {

				System.out.print("请输入档案描述：");
				String fileDescription = scan3.next();

				// 输入文件对象
				File input_file = new File(filename);
				// 输入过滤器流,建立在文件流上
				BufferedInputStream input = new BufferedInputStream(new FileInputStream(input_file));

				// 输出文件对象
				File output_file = new File(uploadpath + input_file.getName());
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

				DataProcessing.docs.put(filenumber, new Doc(filenumber, this.getName(),
						new Timestamp(System.currentTimeMillis()), fileDescription, input_file.getName()));

				System.out.println("上传成功！");
			}
		} catch (SQLException | IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	@SuppressWarnings("resource")
	@Override
	public void showMenu() {

		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);

		// 控制界面开关
		boolean operator_isopen = true;
		// 记录用户选择
		String operator_choice;

		while (operator_isopen) {

			// 界面显示
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
				this.uploadFile();
			} else if (operator_choice.equals("2")) {

				if (super.downloadFile()) {
					System.out.println("下载成功！");
				} else {
					System.out.println("下载失败！");
				}

			} else if (operator_choice.equals("3")) {
				super.showFileList();
			} else if (operator_choice.equals("4")) {

				System.out.print("请输入新密码:");
				String newpassword = scan2.next();

				if (super.changeSelfInfo(newpassword)) {
					System.out.println("修改成功!");
				} else {
					System.out.println("修改失败");
				}

			} else if (operator_choice.equals("5")) {
				operator_isopen = false;
			} else {
				System.out.println("输入格式有误！请重新输入！");
			}

		}

	}

}