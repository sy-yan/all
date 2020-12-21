package cn.mldn.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaCollectDemo10 {
public static void main(String[] args) throws Exception {
List<String> all = new ArrayList<String>(); // ʵ����List����
Collections.addAll(all, "Java", "JavaScript", "JSP",
"Json", "Python", "Ruby", "Go"); // �������ݱ���
Stream<String> stream = all.stream(); // ��ȡStream�ӿڶ���
// ��ȡԪ���а�������ĸ��j�������ݣ�����skip()����2�����ݣ�����limit()ȡ��2������
List<String> result = stream.filter((ele) -> ele.toLowerCase().contains("j"))
.skip(2).limit(2).collect(Collectors.toList()); // ��ȡ����������
System.out.println(result);
}
}