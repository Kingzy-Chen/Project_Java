package CS;

import java.io.*;
import java.net.*;

public class Client {

	// �û�Socket����
	private static Socket client;
	// �����ַ����������IO
	private static ObjectInputStream input;
	private static ObjectOutputStream output;
	// �ӷ���˽��յ���Ϣ
	private static String messageFromServer;

	// ���ӷ�����
	public static void ConnectToServer() throws UnknownHostException, IOException {
		System.out.println("\n���ڳ������ӷ�����...\n");
		// Socket���캯������ΪIP��ַ��˿ں�
		client = new Socket("127.0.0.1", 8888);
		System.out.println("��������:" + client.getInetAddress().getHostName());
	}

	// ����IO��
	public static void GetStreams() throws IOException {
		output = new ObjectOutputStream(client.getOutputStream());
		output.flush();
		input = new ObjectInputStream(client.getInputStream());
		System.out.println("IO�������\n");
	}

	// �Ͽ�����
	public static void CloseConnection() throws IOException {
		output.close();
		input.close();
		client.close();
	}

	// �û��������������Ϣ
	public static void SendMessage(String message) throws IOException {
		// д�������
		output.writeObject(message);
		output.flush();
	}

	// ���շ������ش�����Ϣ
	public static void ReceiveMessage() throws ClassNotFoundException, IOException {
		// ����������
		messageFromServer = (String) input.readObject();
		System.out.println("SERVER>>> " + messageFromServer);
	}

}