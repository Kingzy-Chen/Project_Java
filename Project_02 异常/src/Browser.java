import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Browser extends User {

	Browser(String name, String password, String role) {
		super(name, password, role);
	}

	@SuppressWarnings("resource")
	@Override
	public void showMenu() {

		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);

		// ���ƽ��濪��
		boolean browser_isopen = true;
		// ��¼�û�ѡ��
		String browser_choice;

		while (browser_isopen) {

			// ��ʾҳ��
			System.out.println("========��ӭ���뵵�����Ա�˵�========");
			System.out.println("            1.�����ļ�");
			System.out.println("            2.�ļ��б�");
			System.out.println("            3.�޸�����");
			System.out.println("            4.��    �� ");
			System.out.println("====================================");
			System.out.print("������ѡ�");
			browser_choice = scan1.next();

			if (browser_choice.equals("1")) {

				System.out.print("�������ļ�����");
				String filename = scan2.next();

				try {
					super.downloadFile(filename);
				} catch (IOException e) {
					System.out.println(e.getLocalizedMessage());
				}

			} else if (browser_choice.equals("2")) {

				System.out.println("�ļ��б�");
				try {
					super.showFileList();
				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());
				}

			} else if (browser_choice.equals("3")) {

				System.out.print("������������:");
				String newpassword = scan2.next();

				try {
					if (this.changeSelfInfo(newpassword)) {
						System.out.println("�޸ĳɹ�!");
					} else {
						System.out.println("�޸�ʧ��");
					}
				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());
				}

			} else if (browser_choice.equals("4")) {
				browser_isopen = false;
			} else {
				System.out.println("�����ʽ�������������룡");
			}

		}
	}
}