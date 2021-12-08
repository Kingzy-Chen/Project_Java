import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Administrator extends User {

	Administrator(String name, String password, String role) {
		super(name, password, role);
	}

	// ������ try ��䲶���쳣
	public void updateUser(String input_name, String input_password, String input_role) {
		try {
			if (DataProcessing.update(input_name, input_password, input_role)) {
				System.out.println("�޸ĳɹ���");
			} else {
				System.out.println("�޸�ʧ�ܣ������ɫ����ȷ��");
			}
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

	public void delUser(String input_name) {
		try {
			if (DataProcessing.delete(input_name)) {
				System.out.println("ɾ���ɹ���");
			} else {
				System.out.println("ɾ��ʧ�ܣ����Ҳ����û�����");
			}
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	public void addUser(String input_name, String input_password, String input_role) {
		try {
			if (DataProcessing.insert(input_name, input_password, input_role)) {
				System.out.println("��ӳɹ���");
			} else {
				System.out.println("���ʧ�ܣ��û����Ѵ���/��ɫ������ȷ��");
			}
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	public void listUser() {
		try {
			// ͨ�� toString ��д����ö�������
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

		// ���ƽ���Ŀ���
		boolean administrator_isopen = true;
		// ��¼�û���ѡ��
		String administrator_choice;

		while (administrator_isopen) {

			// ������ʾ
			System.out.println("========��ӭ���뵵������Ա�˵�========");
			System.out.println("            1.�޸��û�");
			System.out.println("            2.ɾ���û�");
			System.out.println("            3.�����û�");
			System.out.println("            4.�г��û�");
			System.out.println("            5.�����ļ�");
			System.out.println("            6.�ļ��б�");
			System.out.println("            7.�޸�����");
			System.out.println("            8.��    ��");
			System.out.println("====================================");
			System.out.print("������ѡ�");
			administrator_choice = scan1.next();

			if (administrator_choice.equals("1")) {

				System.out.print("�������û�����");
				String input_name = scan2.next();
				System.out.print("���������룺");
				String input_password = scan3.next();

				// ���� try ��䲶���쳣
				try {
					if (DataProcessing.search(input_name, input_password) != null) {

						System.out.print("��������ݣ�");
						String input_role = scan4.next();

						this.updateUser(input_name, input_password, input_role);

					} else {
						System.out.println("�û��������벻����");
					}

				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());
				}

			} else if (administrator_choice.equals("2")) {

				System.out.print("�������û�����");
				String input_name = scan2.next();

				this.delUser(input_name);

			} else if (administrator_choice.equals("3")) {

				System.out.print("�������û�����");
				String input_name = scan2.next();
				System.out.print("���������룺");
				String input_password = scan3.next();
				System.out.print("��������ݣ�");
				String input_role = scan4.next();

				this.addUser(input_name, input_password, input_role);

			} else if (administrator_choice.equals("4")) {
				this.listUser();
			} else if (administrator_choice.equals("5")) {

				System.out.print("�������ļ�����");
				String filename = scan2.next();

				try {
					super.downloadFile(filename);
				} catch (IOException e) {
					System.out.println(e.getLocalizedMessage());
				}

			} else if (administrator_choice.equals("6")) {

				System.out.println("�ļ��б�");
				try {
					super.showFileList();
				} catch (SQLException e) {
					System.out.println(e.getLocalizedMessage());
				}

			} else if (administrator_choice.equals("7")) {

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

			} else if (administrator_choice.equals("8")) {
				administrator_isopen = false;
			} else {
				System.out.println("�����ʽ�������������룡");
			}

		}

	}
}