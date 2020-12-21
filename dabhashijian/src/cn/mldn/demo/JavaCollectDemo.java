package cn.mldn.demo;
import java.util.ArrayList;
import java.util.List;
public class JavaCollectDemo {
public static void main(String[] args) {
List<String> all = new ArrayList<String>(); // 为List父接口进行实例化
all.add("Java"); // 保存数据
all.add("Java"); // 保存重复数据
all.add("www.scau.edu.cn"); // 保存数据
all.add("张老师"); // 保存数据
all.forEach((str) -> { // 集合输出
System.out.print(str + "、");
});
}
}