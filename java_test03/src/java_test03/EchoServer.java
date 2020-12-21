package java_test03;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(9999); // ���÷�������˿�
		System.out.println("�ȴ��ͻ�������............."); // ��ӡ��ʾ��Ϣ
		Socket client = server.accept(); // �ȴ��ͻ�������
		// ������Ҫ�Ƚ��տͻ��˷���������Ϣ������ſ��Խ���Ϣ����֮���ͻؿͻ���
		Scanner scan = new Scanner(client.getInputStream()); // �ͻ���������
		scan.useDelimiter("\n"); // ���÷ָ���
		PrintStream out = new PrintStream(client.getOutputStream()); // �ͻ��������
		boolean flag = true; // ѭ�����
		while (flag) {
		if (scan.hasNext()) { // �����ݽ���
		String val = scan.next().trim(); // ������������
		if ("byebye".equalsIgnoreCase(val)) { // �������
		out.println("ByeByeBye...."); // ��Ӧ��Ϣ
		flag = false; // ����ѭ��
		} else {
		out.println("��ECHO��" + val); // Echo��Ϣ
		}
		}
		}
		scan.close();  // �ر�������
		out.close(); // �ر������
		client.close(); // �رտͻ���
		server.close(); // �رշ����
	}

}
