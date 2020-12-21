package cn.mldn.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class JavaCollectDemo6 {
public static void main(String[] args) {
List<String> all = new ArrayList<String>(); // 为List父接口进行实例化
all.add("小李老师"); // 保存数据
all.add("Java"); // 保存数据
all.add("www.scau.edu.cn"); // 保存数据
ListIterator<String> iter = all.listIterator() ;// 获取ListIterator接口实例
System.out.print("由前向后输出：");
while(iter.hasNext()) { // 由前向后迭代
System.out.print(iter.next() + "、");
}
System.out.print("\n由后向前输出：");
while (iter.hasPrevious()) { // 由后向前迭代
System.out.print(iter.previous() + "、");
}
}
}