package cn.mldn.demo;

import java.util.HashMap;
import java.util.Map;

public class JavaCollectDemo2 {
public static void main(String[] args) {
Map<String, Integer> map = new HashMap<String, Integer>(); // ����Map����
map.put("one", 1); // ��������
map.put("two", 2); // ��������
map.put("one", 101); // key�ظ�����������
map.put(null, 0); // keyΪnull
map.put("zero", null); // valueΪnull
System.out.println(map.get("one")); // key����
System.out.println(map.get(null)); // key����
System.out.println(map.get("ten")); // key������
}
}