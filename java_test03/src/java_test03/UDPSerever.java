package java_test03;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSerever {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DatagramSocket server = new DatagramSocket(9000); // 9000�˿ڼ���
		String str = "www.mldn.cn"; // ������Ϣ
		DatagramPacket packet = new DatagramPacket(str.getBytes(), 0, str.length(),
		InetAddress.getByName("localhost"),9999); // ��������
		server.send(packet); // ������Ϣ
		System.out.println("��Ϣ�������.....");
		server.close(); // �رշ����
	}

}
