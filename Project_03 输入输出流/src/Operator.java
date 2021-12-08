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

		System.out.print("�������ļ���(·��)��");
		String filename = scan1.next();
		System.out.print("�����뵵���ţ�");
		String filenumber = scan2.next();

		try {
			if (DataProcessing.searchDoc(filenumber) != null) {
				System.out.println("�������ظ���");
			} else {

				System.out.print("�����뵵��������");
				String fileDescription = scan3.next();

				// �����ļ�����
				File input_file = new File(filename);
				// �����������,�������ļ�����
				BufferedInputStream input = new BufferedInputStream(new FileInputStream(input_file));

				// ����ļ�����
				File output_file = new File(uploadpath + input_file.getName());
				// �����ļ�
				output_file.createNewFile();
				// �����������,�������ļ�����
				BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(output_file));

				// ���ֽ������ȡ����
				byte[] bytes = new byte[1024];
				// �ļ�д�����
				int length = 0;
				while ((length = input.read(bytes)) != -1) {
					output.write(bytes, 0, length);
				}

				// �ر���
				input.close();
				output.close();

				DataProcessing.docs.put(filenumber, new Doc(filenumber, this.getName(),
						new Timestamp(System.currentTimeMillis()), fileDescription, input_file.getName()));

				System.out.println("�ϴ��ɹ���");
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

		// ���ƽ��濪��
		boolean operator_isopen = true;
		// ��¼�û�ѡ��
		String operator_choice;

		while (operator_isopen) {

			// ������ʾ
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
				this.uploadFile();
			} else if (operator_choice.equals("2")) {

				if (super.downloadFile()) {
					System.out.println("���سɹ���");
				} else {
					System.out.println("����ʧ�ܣ�");
				}

			} else if (operator_choice.equals("3")) {
				super.showFileList();
			} else if (operator_choice.equals("4")) {

				System.out.print("������������:");
				String newpassword = scan2.next();

				if (super.changeSelfInfo(newpassword)) {
					System.out.println("�޸ĳɹ�!");
				} else {
					System.out.println("�޸�ʧ��");
				}

			} else if (operator_choice.equals("5")) {
				operator_isopen = false;
			} else {
				System.out.println("�����ʽ�������������룡");
			}

		}

	}

}