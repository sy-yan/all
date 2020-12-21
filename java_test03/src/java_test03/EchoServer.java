package java_test03;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(9999); // 设置服务监听端口
		System.out.println("等待客户端连接............."); // 打印提示信息
		Socket client = server.accept(); // 等待客户端连接
		// 首先需要先接收客户端发送来的信息，而后才可以将信息处理之后发送回客户端
		Scanner scan = new Scanner(client.getInputStream()); // 客户端输入流
		scan.useDelimiter("\n"); // 设置分隔符
		PrintStream out = new PrintStream(client.getOutputStream()); // 客户端输出流
		boolean flag = true; // 循环标记
		while (flag) {
		if (scan.hasNext()) { // 有数据接收
		String val = scan.next().trim(); // 接收数据内容
		if ("byebye".equalsIgnoreCase(val)) { // 结束标记
		out.println("ByeByeBye...."); // 回应信息
		flag = false; // 结束循环
		} else {
		out.println("【ECHO】" + val); // Echo信息
		}
		}
		}
		scan.close();  // 关闭输入流
		out.close(); // 关闭输出流
		client.close(); // 关闭客户端
		server.close(); // 关闭服务端
	}

}
