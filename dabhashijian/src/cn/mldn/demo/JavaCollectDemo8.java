package cn.mldn.demo;

import java.util.HashSet;
import java.util.Set;

public class JavaCollectDemo8 {
public static void main(String[] args) {
Set<String> all = new HashSet<String>(); // ʵ����Set
all.add("��ʦ"); // ��������
all.add("Java"); // ��������
all.add("www.scau.edu.cn "); // ��������
for (String str : all) {
System.out.print(str + "��");
}
}
}