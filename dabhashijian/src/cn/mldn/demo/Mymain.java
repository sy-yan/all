package cn.mldn.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Mymain {
public static void main0(String [] args) {
	List<String> all=new ArrayList<String>();
	all.add("java");
	all.add("Java");
	all.add("Õ¢Íß");
	all.add("×¦ÍÛ");
	all.forEach((Str)->{
		System.out.println(Str);
	});
}
public static void main1(String[] args) {
	Set<String> all=new TreeSet<String>();
	all.add("java");
	all.add("jvja");
	all.add("java");
	all.add("Java");
	all.add("Hello");
	System.out.println(all);
}
public static void main2(String[] args) {
	Map<String,Integer > all=new HashMap<String,Integer>();
	all.put("1",1);
	all.put("to", 2);
	all.put(null, 0);
	all.put("zero", null);
	System.out.println(all.get("1"));
	System.out.println(all.get("ten"));
	System.out.println(all.get(null));
	System.out.println(all.get("zero"));
}
public static void main3(String[] args) {
	
}
}
