package cn.mldn.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class JavaCollectDemo9 {
public static void main(String[] args) throws Exception {
List<String> all = new ArrayList<String>(); // 实例化List集合
Collections.addAll(all, "Java", "JavaScript", "JSP",
"Json", "Python", "Ruby", "Go"); // 集合数据保存
Stream<String> stream = all.stream(); // 获取Stream接口对象
// 将每一个元素全部变为小写字母，而后查询是否存在有字母“j”，如果存在则进行个数统计
System.out.println(stream.filter((ele) -> ele.toLowerCase().contains("j")).count());
}
}
