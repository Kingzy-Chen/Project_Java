package CS;

import java.io.*;
import java.net.*;

public class Server {

	// ��������ServerSocket����
	private static ServerSocket server;
	// �����ֽ����������IO
	private static ObjectOutputStream output;
	private static ObjectInputStream input;
	// ���������û���Socket����connection
	private static Socket connection;
	// ���û����յ���Ϣ
	private static String messageFromClient;
	// �����û�������
	private static int counter = 1;

	// �ȴ��û�����
	public static void WaitForConnection() throws IOException {
		System.out.println("\n�ȴ�����...\n");
		// �����û�
		connection = server.accept();
		System.out.println("Connection " + counter + " ������:" + connection.getInetAddress().getHostName());
	}

	// ����IO��
	public static void GetStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		System.out.println("IO�������\n");
	}

	// ������Ϣ����
	public static void ProcessConnection() throws IOException, ClassNotFoundException {
		do {
			// ������Ϣ
			messageFromClient = (String) input.readObject();
			System.out.println("CLIENT>>> " + messageFromClient);
			// �ش���Ϣ
			output.writeObject(messageFromClient);
			output.flush();
		} while (!messageFromClient.equals("�ǳ�")); // ����Ϣ���ǵǳ�����������

		// ��󴫳��ǳ���Ϣ
		output.writeObject(messageFromClient);
		output.flush();
	}

	// �رշ�����
	public static void CloseConnection() {
		try {
			output.close();
			input.close();
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ������������
	public static void main(String[] args) {

		try {
			// ServerSocket���캯������Ϊ�˿ں�
			server = new ServerSocket(8888);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			// ����ѭ������֤�ǳ����ٵ�¼���ٴ�����
			while (true) {
				WaitForConnection();
				GetStreams();
				ProcessConnection();
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// �����쳣ǿ�ƹرշ�����
			CloseConnection();
		}

	}
}