package cn.mldn.demo;
import java.util.ArrayList;
import java.util.List;
public class JavaCollectDemo {
public static void main(String[] args) {
List<String> all = new ArrayList<String>(); // ΪList���ӿڽ���ʵ����
all.add("Java"); // ��������
all.add("Java"); // �����ظ�����
all.add("www.scau.edu.cn"); // ��������
all.add("����ʦ"); // ��������
all.forEach((str) -> { // �������
System.out.print(str + "��");
});
}
}