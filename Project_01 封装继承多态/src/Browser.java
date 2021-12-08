import java.util.Scanner;

public class Browser extends User {

	public Browser(String name, String password, String role) {
		super(name, password, role);
	}

	@SuppressWarnings("resource")
	public void showMenu() {

		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);

		// ���ƽ���Ŀ���
		boolean browser_isopen = true;
		// ��¼�û���ѡ��
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

				System.out.print("�����뵵���ţ�");
				String fileID = scan2.next();

				// �����ļ�
				super.downloadFile(fileID);

			} else if (browser_choice.equals("2")) {

				// �г��ļ�
				System.out.println("�ļ��б�");
				super.showFileList();

			} else if (browser_choice.equals("3")) {

				System.out.print("������������:");
				String newpassword = scan2.next();

				// �޸�����
				if (this.changeSelfInfo(newpassword)) {
					System.out.println("�޸ĳɹ�!");
				} else {
					System.out.println("�޸�ʧ��");
				}

			} else if (browser_choice.equals("4")) {
				// �ر�ҳ��
				browser_isopen = false;
			} else {
				System.out.println("�����ʽ�������������룡");
			}

		}
	}

}