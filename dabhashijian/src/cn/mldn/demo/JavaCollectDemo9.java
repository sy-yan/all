package cn.mldn.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class JavaCollectDemo9 {
public static void main(String[] args) throws Exception {
List<String> all = new ArrayList<String>(); // ʵ����List����
Collections.addAll(all, "Java", "JavaScript", "JSP",
"Json", "Python", "Ruby", "Go"); // �������ݱ���
Stream<String> stream = all.stream(); // ��ȡStream�ӿڶ���
// ��ÿһ��Ԫ��ȫ����ΪСд��ĸ�������ѯ�Ƿ��������ĸ��j���������������и���ͳ��
System.out.println(stream.filter((ele) -> ele.toLowerCase().contains("j")).count());
}
}
