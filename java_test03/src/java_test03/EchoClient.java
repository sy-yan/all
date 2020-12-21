package java_test03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(
			new InputStreamReader(System.in));
	
	public static String getString(String prompt) throws Exception {  // ������Ϣ����
	    System.out.print(prompt);
		String str = KEYBOARD_INPUT.readLine();
		return str;
	}
	public static void main (String[] args)throws Exception {
		Socket client = new Socket("localhost", 9999); // �������˵�������Ϣ
		// ���ڵĿͻ�����Ҫ������������Ĳ���֧�֣�������ȻҪ׼����Scanner��PrintWriter
		Scanner scan = new Scanner(client.getInputStream()); // ���շ������������
		scan.useDelimiter("\n");
		PrintStream out = new PrintStream(client.getOutputStream()); // ��������˷�������
		boolean flag = true; // ѭ�����
		while (flag) {  // ѭ������
		String input = getString("������Ҫ���͵����ݣ�").trim(); // ��ȡ������������
		out.println(input); // �ӻ���
		if (scan.hasNext()) { // ���������л�Ӧ
		System.out.println(scan.next()); // �����Ӧ��Ϣ
		}
		if ("byebye".equalsIgnoreCase(input)) {  // �����ж�
		flag = false;  // �޸�ѭ�����
		}
		}
		scan.close();  // �ر�������
		out.close(); // �ر��� ����
		client.close(); // �رտͻ���
	}

}
