package test;

import java.net.*;
import java.io.*;

public class ClientTest {

	// ����������ServerTest���������
	public static void main(String[] args) {

		try {
			// 1.�����ͻ��˶���Socket, ���췽���󶨷�������IP��ַ�Ͷ˿ں�
			Socket socket = new Socket("127.0.0.1", 8888);

			// 2.���������ֽ���InputStream��OutputStream�Ķ���
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			// 3.�����˷��Ͳ�����Ϣ
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			bw.write("���Կͻ��˺ͷ�����ͨ�ţ����������յ���Ϣ���ص��ͻ���\n");
			bw.flush();

			// 4.��ȡ���������ص���Ϣ
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String mess = br.readLine();
			System.out.println("������:" + mess);

			// 5.�ͷ���Դ
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}