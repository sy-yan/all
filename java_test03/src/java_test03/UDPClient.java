package java_test03;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPClient {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		DatagramSocket client = new DatagramSocket(9999); // 9999�˿ڼ���
		byte data[] = new byte[1024]; // �����������
			DatagramPacket packet = new DatagramPacket(data, data.length);// �������ݱ�
		System.out.println("�ͻ��˵ȴ����շ��͵���Ϣ........"); // ��ʾ��Ϣ
		client.receive(packet); // ������Ϣ����
		System.out.println("���յ�����Ϣ����Ϊ��" + new String(data, 0,
		packet.getLength()));
		client.close(); // �ر�����
	}

}
