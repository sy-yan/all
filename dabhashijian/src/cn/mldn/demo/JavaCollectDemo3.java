package cn.mldn.demo;

import java.util.Stack;

public class JavaCollectDemo3 {
	public static void main(String[] args) {
		Stack<String> all = new Stack<String>() ; // ʵ����ջ�ṹ
		all.push("A") ; // ��ջ����
		all.push("B") ; // ��ջ����
		all.push("C") ; // ��ջ����
		System.out.println(all.pop()); // ��ջ����
		System.out.println(all.pop()); // ��ջ����
		System.out.println(all.pop()); // ��ջ����
		System.out.println(all.pop()); // �����ݡ�EmptyStackException
		}
		}
