package cn.mldn.demo;

import java.util.Properties;

public class JavaCollectDemo4 {
public static void main(String[] args) {
Properties prop = new Properties(); // ���Դ洢
prop.setProperty("scau", " www.scau.edu.cn "); // ������������
prop.setProperty("scaujava", "www.scau.edu.cn "); // ������������
System.out.println(prop.getProperty("scau")); // ����key��������
System.out.println(prop.getProperty("yootk", "NoFound"));// ����key��������
System.out.println(prop.getProperty("yootk")); // ����key��������
}
}