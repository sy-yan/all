package cn.mldn.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaCollectDemo10 {
public static void main(String[] args) throws Exception {
List<String> all = new ArrayList<String>(); // 实例化List集合
Collections.addAll(all, "Java", "JavaScript", "JSP",
"Json", "Python", "Ruby", "Go"); // 集合数据保存
Stream<String> stream = all.stream(); // 获取Stream接口对象
// 获取元素中包含有字母“j”的数据，利用skip()跳过2个数据，利用limit()取出2个数据
List<String> result = stream.filter((ele) -> ele.toLowerCase().contains("j"))
.skip(2).limit(2).collect(Collectors.toList()); // 获取处理后的数据
System.out.println(result);
}
}