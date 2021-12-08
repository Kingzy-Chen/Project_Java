import java.util.Scanner;

public class Operator extends User {

	public Operator(String name, String password, String role) {
		super(name, password, role);
	}

	// �ϴ��ļ�
	@SuppressWarnings({ "resource", "unused" })
	public void uploadFile() {

		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		Scanner scan3 = new Scanner(System.in);

		System.out.println("�ϴ��ļ�");

		System.out.print("�������ļ�����");
		String filename = scan1.next();
		System.out.print("�����뵵���ţ�");
		String fileID = scan2.next();
		System.out.print("�����뵵��������");
		String fileDescrption = scan3.next();
		System.out.println("�ϴ��ɹ���");

	}

	@SuppressWarnings({ "resource" })
	public void showMenu() {

		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);

		// ����ҳ��Ŀ���
		boolean operator_isopen = true;
		// ��¼�û���ѡ��
		String operator_choice;

		while (operator_isopen) {

			// ��ʾҳ��
			System.out.println("========��ӭ���뵵��¼��Ա�˵�========");
			System.out.println("            1.�ϴ��ļ�");
			System.out.println("            2.�����ļ�");
			System.out.println("            3.�ļ��б�");
			System.out.println("            4.�޸�����");
			System.out.println("            5.��    �� ");
			System.out.println("====================================");
			System.out.print("������ѡ�");
			operator_choice = scan1.next();

			if (operator_choice.equals("1")) {

				// �ϴ��ļ�
				this.uploadFile();

			} else if (operator_choice.equals("2")) {

				System.out.print("�������ļ�����");
				String filename = scan2.next();

				// �����ļ�
				super.downloadFile(filename);

			} else if (operator_choice.equals("3")) {

				// �г��ļ�
				System.out.println("�ļ��б�");
				super.showFileList();

			} else if (operator_choice.equals("4")) {

				System.out.print("������������:");
				String newpassword = scan2.next();

				// �޸�����
				if (this.changeSelfInfo(newpassword)) {
					System.out.println("�޸ĳɹ�!");
				} else {
					System.out.println("�޸�ʧ��");
				}

			} else if (operator_choice.equals("5")) {
				// �رս���
				operator_isopen = false;
			} else {
				System.out.println("�����ʽ�������������룡");
			}
		}
	}
}