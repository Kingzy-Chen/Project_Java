package test;

import java.io.*;
import java.net.*;

public class ServerTest {

	// ����������ClientTest���������
	@SuppressWarnings("static-access")
	public static void main(String[] args) {

		try {
			// 1.��������˶���serverSocket��ϵͳָ���˿ں�
			ServerSocket serversocket = new ServerSocket(8888);
			System.out.println("����������....");

			// 2.ʹ��ServerSocket�����еķ���accept, ��ȡ�յ�����Ŀͻ��˶���Socket
			Socket socket = serversocket.accept();
			System.out.println("�ͻ���:" + socket.getInetAddress().getLocalHost() + "�����ӵ�������");

			// 3.ʹ�������ֽ���������ȡ�ͻ��˷���������Ϣ
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String mess = br.readLine();
			System.out.println("�ͻ���:" + mess);

			// 4.ʹ�������ֽ��������ͻ��˻�д��Ϣ
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bw.write(mess + "\n");
			bw.flush();

			// 5.�ͷ���Դ
			serversocket.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}