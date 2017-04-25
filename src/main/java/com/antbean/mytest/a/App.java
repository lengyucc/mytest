package com.antbean.mytest.a;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		User user = new User(12, "zhangsan", "111222", "张三", 24, '男', "北京");
		User otherUser = new User(12, "zhangsan", "123456", "张三", 28, '女', "上海");
		List<String> result = EntityComparatorUtils.compare(user, otherUser);
		System.out.println("用户名(12):" + result);
	}
}
