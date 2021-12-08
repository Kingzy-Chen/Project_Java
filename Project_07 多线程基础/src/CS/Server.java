package CS;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server extends Thread { // ��Server�̳���Thread�࣬ʵ�ֶ��߳�

	// �����ֽ������(�������ͬ�û�)
	private static ArrayList<ObjectOutputStream> outputToClients;
	// �����ֽ�������
	private ObjectInputStream input;

	// ��������ServerSocket����
	private static ServerSocket server;
	// ���������û���Socket����connection
	private Socket connection;

	// �̱߳��
	private static int counter = 0;
	// �߳�����
	private String name;

	// ���췽��
	public Server(Socket connection, String name) {
		this.connection = connection;
		this.name = name;
		try {
			// ����input��
			input = new ObjectInputStream(connection.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ����̬����
	public void run() {
		try {
			// �����û�����Ϣ
			String messageFromClient;

			// ����output��
			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			outputToClients.add(output);
			System.out.println("IO�������\n");

			do {
				// ��ȡ�û���Ϣ
				messageFromClient = (String) input.readObject();
				System.out.println(this.name + ":" + messageFromClient);

				// �ش���Ϣ
				output.writeObject(messageFromClient);
				output.flush();

			} while (!messageFromClient.equals("�ǳ�")); // ֻҪδ�ǳ�����ѭ������

			// �ش��ǳ���Ϣ
			output.writeObject(messageFromClient);
			output.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		try {
			// ��������������
			server = new ServerSocket(8888);
			outputToClients = new ArrayList<ObjectOutputStream>();

			// ����ѭ��ʵ�ֲ��Ͻ����û���Ч��
			while (true) {
				// �����ȴ�
				Socket connection = server.accept();
				System.out.println("\n�ȴ�����...\n");
				// �������
				counter++;
				System.out.println("Thread " + counter + ":������" + connection.getInetAddress().getHostName());
				// �����߳�
				Thread t = new Server(connection, "Thread " + counter);
				// ʹ�߳̽������̬��һ����CPU��Դ����ֱ�ӽ���run()����
				t.start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}