import java.util.Map.Entry;
import java.util.Scanner;

public class Administrator extends User {

	public Administrator(String name, String password, String role) {
		super(name, password, role);
	}

	// ɾ���û�
	public void delUser(String input_name) {
		if (DataProcessing.delete(input_name)) {
			System.out.println("ɾ���ɹ���");
		} else {
			System.out.println("ɾ��ʧ�ܣ����Ҳ����û�����");
		}
	}

	// ����û�
	public void addUser(String input_name, String input_password, String input_role) {
		if (DataProcessing.insert(input_name, input_password, input_role)) {
			System.out.println("��ӳɹ���");
		} else {
			System.out.println("ɾ��ʧ�ܣ��û����Ѵ��ڣ�");
		}
	}

	// �г��û�
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

		// ���ƽ���Ŀ���
		boolean administrator_isopen = true;
		// ��¼�û���ѡ��
		String administrator_choice;

		while (administrator_isopen) {

			// ������ʾ
			System.out.println("========��ӭ���뵵������Ա�˵�========");
			System.out.println("           1.�޸��û�");
			System.out.println("           2.ɾ���û�");
			System.out.println("           3.�����û�");
			System.out.println("           4.�г��û�");
			System.out.println("           5.�����ļ�");
			System.out.println("           6.�ļ��б�");
			System.out.println("           7.�޸�����");
			System.out.println("           8.��    ��");
			System.out.println("====================================");
			System.out.print("������ѡ�");
			administrator_choice = scan1.next();

			if (administrator_choice.equals("1")) {

				// �����û���Ϣ
				System.out.print("�������û�����");
				String input_name = scan2.next();
				System.out.print("���������룺");
				String input_password = scan3.next();

				// ��������û�
				if (DataProcessing.search(input_name, input_password) != null) {

					System.out.print("��������ݣ�");
					String input_role = scan4.next();

					// �޸��û�
					if (DataProcessing.update(input_name, input_password, input_role)) {
						System.out.println("�޸ĳɹ���");
					} else {
						System.out.println("�޸�ʧ�ܣ����Ҳ����û�����");
					}

				} else {
					System.out.println("�û��������벻����");
				}

			} else if (administrator_choice.equals("2")) {

				System.out.print("�������û�����");
				String input_name = scan2.next();

				// ɾ���û�
				this.delUser(input_name);

			} else if (administrator_choice.equals("3")) {

				System.out.print("�������û�����");
				String input_name = scan2.next();
				System.out.print("���������룺");
				String input_password = scan3.next();
				System.out.print("��������ݣ�");
				String input_role = scan4.next();

				// ����û�
				this.addUser(input_name, input_password, input_role);

			} else if (administrator_choice.equals("4")) {

				// �г��û�
				System.out.println("�û��б�");
				this.listUser();

			} else if (administrator_choice.equals("5")) {

				System.out.print("�������ļ�����");
				String filename = scan2.next();

				// �����ļ�
				super.downloadFile(filename);

			} else if (administrator_choice.equals("6")) {

				// �г��ļ�
				System.out.println("�ļ��б�");
				super.showFileList();

			} else if (administrator_choice.equals("7")) {

				System.out.print("������������:");
				String newpassword = scan2.next();

				// �޸�����
				if (this.changeSelfInfo(newpassword)) {
					System.out.println("�޸ĳɹ�!");
				} else {
					System.out.println("�޸�ʧ��");
				}

			} else if (administrator_choice.equals("8")) {
				// ����ر�
				administrator_isopen = false;
			} else {
				System.out.println("�����ʽ�������������룡");
			}
		}
	}
}