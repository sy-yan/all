package cn.mldn.demo;

import java.util.Iterator;
import java.util.Set;

public class JavaCollectDemo5 {
public static void main(String[] args) {
Set<String> all = Set.of("Hello", "ScauJava", "Scau"); // ����Set����
Iterator<String> iter = all.iterator(); // ʵ����Iterator�ӿڶ���
while (iter.hasNext()) { // �����Ƿ�������
String str = iter.next(); // ��ȡÿһ������
System.out.print(str + "��"); // �������
}
}
}