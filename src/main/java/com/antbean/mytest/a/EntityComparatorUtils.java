package com.antbean.mytest.a;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;

public class EntityComparatorUtils {
	@SuppressWarnings("rawtypes")
	public static <T> List<String> compare(T t, T other)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (null == t || null == other) {
			throw new IllegalArgumentException("对象不能为null");
		}
		Class tClass = t.getClass();
		Class otherClass = other.getClass();
		if (!tClass.equals(otherClass)) {
			throw new IllegalArgumentException("类型不匹配");
		}
		Map<String, String> names = new RepeatableKeyMap<String, String>();
		Field[] fields = tClass.getDeclaredFields();
		for (Field filed : fields) {
			FieldDesc fieldDesc = filed.getAnnotation(FieldDesc.class);
			if (null != fieldDesc) {
				names.put(filed.getName(), fieldDesc.value());
			}
		}
		if (!names.isEmpty()) {
			List<String> modifyList = new ArrayList<String>();
			for (Entry<String, String> entry : names.entrySet()) {
				String fieldName = entry.getKey();
				String fieldDesc = entry.getValue();
				Object tValue = BeanUtils.getProperty(t, fieldName);
				Object otherValue = BeanUtils.getProperty(other, fieldName);
				if (null == tValue && null == otherValue) {
					continue;
				}
				if (null == tValue || null == otherValue || !tValue.equals(otherValue)) {
					modifyList.add(fieldDesc + "由'" + tValue + "'修改为'" + otherValue + "'");
				}
			}
			if (!modifyList.isEmpty()) {
				return modifyList;
			}
		}
		return null;
	}
}
