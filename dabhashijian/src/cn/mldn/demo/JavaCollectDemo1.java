package cn.mldn.demo;
import java.util.Set;
import java.util.TreeSet;
public class JavaCollectDemo1 {
public static void main(String[] args) {
Set<String> all = new TreeSet<String>(); // ΪSet���ӿڽ���ʵ����
all.add("java"); // ��������
all.add("Java"); // �����ظ�����
all.add("Java"); // �����ظ�����
all.add("Hello"); // ��������
System.out.println(all); // ֱ��������϶���
}
}