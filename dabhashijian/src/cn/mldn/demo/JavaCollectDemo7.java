package cn.mldn.demo;

import java.util.Enumeration;
import java.util.Vector;

public class JavaCollectDemo7 {
public static void main(String[] args) {
Vector<String> all = new Vector<String>(); // ʵ����Vector
all.add("С����ʦ"); // ��������
all.add("ScauJava"); // ��������
all.add("www. scau.edu.cn "); // ��������
Enumeration<String> enu = all.elements() ; // ��ȡEnumerationʵ��
while (enu.hasMoreElements()) {
String str = enu.nextElement() ;
System.out.print(str + "��");
}
}
}