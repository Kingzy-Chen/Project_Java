import java.util.Scanner;

public class Main {

	@SuppressWarnings({ "resource" })
	public static void main(String[] args) {

		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		Scanner scan3 = new Scanner(System.in);

		// ���ƽ���Ŀ���
		boolean main_isopen = true;
		// ���ڼ�¼�û���ѡ��
		String main_choice;

		while (main_isopen) {

			// ������ʾ
			System.out.println("========��ӭ���뵵��ϵͳ========");
			System.out.println("           1.��   ¼ ");
			System.out.println("           2.��   �� ");
			System.out.println("==============================");
			System.out.print("������ѡ�");
			main_choice = scan1.next();

			if (main_choice.equals("1")) {

				// �����û���Ϣ
				System.out.print("�������û�����");
				String input_name = scan2.next();
				System.out.print("���������룺");
				String input_password = scan3.next();

				// ��������ȷ�Եļ��
				User user = DataProcessing.search(input_name, input_password);
				if (user == null) {
					System.out.println("�û��������벻����");
				} else {
					// �򿪶�Ӧ��ݵĽ���
					user.showMenu();
				}

			} else if (main_choice.equals("2")) {
				// �˳������������ر�
				main_isopen = false;
			} else {
				// ��ʽ�����������
				System.out.println("�����ʽ�������������룡");
			}

		}
		System.out.println("ϵͳ�˳�����лʹ�ã�");
	}

}