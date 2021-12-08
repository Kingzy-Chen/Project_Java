package CS;

import java.io.*;
import java.net.*;

public class Server {

	// 服务器的ServerSocket对象
	private static ServerSocket server;
	// 网络字节输入输出流IO
	private static ObjectOutputStream output;
	private static ObjectInputStream input;
	// 接收连接用户的Socket对象connection
	private static Socket connection;
	// 从用户接收的信息
	private static String messageFromClient;
	// 连接用户的数量
	private static int counter = 1;

	// 等待用户连接
	public static void WaitForConnection() throws IOException {
		System.out.println("\n等待连接...\n");
		// 接收用户
		connection = server.accept();
		System.out.println("Connection " + counter + " 已连接:" + connection.getInetAddress().getHostName());
	}

	// 构造IO流
	public static void GetStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		System.out.println("IO构造完成\n");
	}

	// 保持消息监听
	public static void ProcessConnection() throws IOException, ClassNotFoundException {
		do {
			// 读入消息
			messageFromClient = (String) input.readObject();
			System.out.println("CLIENT>>> " + messageFromClient);
			// 回传消息
			output.writeObject(messageFromClient);
			output.flush();
		} while (!messageFromClient.equals("登出")); // 若消息不是登出，保持连接

		// 最后传出登出消息
		output.writeObject(messageFromClient);
		output.flush();
	}

	// 关闭服务器
	public static void CloseConnection() {
		try {
			output.close();
			input.close();
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 服务器主函数
	public static void main(String[] args) {

		try {
			// ServerSocket构造函数参数为端口号
			server = new ServerSocket(8888);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			// 保持循环，保证登出后再登录可再次连接
			while (true) {
				WaitForConnection();
				GetStreams();
				ProcessConnection();
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 出现异常强制关闭服务器
			CloseConnection();
		}

	}
}