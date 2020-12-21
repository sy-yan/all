package cn.mldn.demo;

import java.util.Enumeration;
import java.util.Vector;

public class JavaCollectDemo7 {
public static void main(String[] args) {
Vector<String> all = new Vector<String>(); // 实例化Vector
all.add("小李老师"); // 保存数据
all.add("ScauJava"); // 保存数据
all.add("www. scau.edu.cn "); // 保存数据
Enumeration<String> enu = all.elements() ; // 获取Enumeration实例
while (enu.hasMoreElements()) {
String str = enu.nextElement() ;
System.out.print(str + "、");
}
}
}