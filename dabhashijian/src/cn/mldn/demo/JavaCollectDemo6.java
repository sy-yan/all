package cn.mldn.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class JavaCollectDemo6 {
public static void main(String[] args) {
List<String> all = new ArrayList<String>(); // ΪList���ӿڽ���ʵ����
all.add("С����ʦ"); // ��������
all.add("Java"); // ��������
all.add("www.scau.edu.cn"); // ��������
ListIterator<String> iter = all.listIterator() ;// ��ȡListIterator�ӿ�ʵ��
System.out.print("��ǰ��������");
while(iter.hasNext()) { // ��ǰ������
System.out.print(iter.next() + "��");
}
System.out.print("\n�ɺ���ǰ�����");
while (iter.hasPrevious()) { // �ɺ���ǰ����
System.out.print(iter.previous() + "��");
}
}
}