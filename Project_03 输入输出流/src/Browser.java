import java.util.Scanner;

public class Browser extends User {

	public Browser(String name, String password, String role) {
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

			// ������ʾ
			System.out.println("========��ӭ���뵵�����Ա�˵�========");
			System.out.println("            1.�����ļ�");
			System.out.println("            2.�ļ��б�");
			System.out.println("            3.�޸�����");
			System.out.println("            4.��    �� ");
			System.out.println("====================================");
			System.out.print("������ѡ�");
			browser_choice = scan1.next();

			if (browser_choice.equals("1")) {

				if (super.downloadFile()) {
					System.out.println("���سɹ���");
				} else {
					System.out.println("����ʧ�ܣ�");
				}

			} else if (browser_choice.equals("2")) {
				super.showFileList();
			} else if (browser_choice.equals("3")) {

				System.out.print("������������:");
				String newpassword = scan2.next();

				if (super.changeSelfInfo(newpassword)) {
					System.out.println("�޸ĳɹ�!");
				} else {
					System.out.println("�޸�ʧ��");
				}

			} else if (browser_choice.equals("4")) {
				browser_isopen = false;
			} else {
				System.out.println("�����ʽ�������������룡");
			}

		}

	}

}