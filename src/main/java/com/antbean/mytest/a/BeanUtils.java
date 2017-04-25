package com.antbean.mytest.a;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BeanUtils {
	@SuppressWarnings("rawtypes")
	public static <T> List<String> difference2ListDesc(T t, T other)
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
				Object tValue = org.apache.commons.beanutils.BeanUtils.getProperty(t, fieldName);
				Object otherValue = org.apache.commons.beanutils.BeanUtils.getProperty(other, fieldName);
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
	@SuppressWarnings({ "rawtypes" })
	public static <T> T difference2NewEntity(T t, T other)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
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
			T newT = null;
			for (Entry<String, String> entry : names.entrySet()) {
				String fieldName = entry.getKey();
				String fieldDesc = entry.getValue();
				Object tValue = org.apache.commons.beanutils.BeanUtils.getProperty(t, fieldName);
				Object otherValue = org.apache.commons.beanutils.BeanUtils.getProperty(other, fieldName);
				if (null == tValue && null == otherValue) {
					continue;
				}
				if (null == tValue || null == otherValue || !tValue.equals(otherValue)) {
					if (null == newT) {
						newT = (T) tClass.newInstance();
					}
					org.apache.commons.beanutils.BeanUtils.setProperty(newT, fieldName, otherValue);
				}
			}
			return newT;
		}
		return null;
	}
	@SuppressWarnings("rawtypes")
	public static <T, O> O bean2OtherBean(T t, Class<O> clazz) throws InstantiationException, IllegalAccessException,
			NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException {
		if (null == t || null == clazz) {
			throw new IllegalArgumentException("对象不能为null");
		}
		O o = clazz.newInstance();
		Class tClazz = t.getClass();
		Field[] fields = tClazz.getDeclaredFields();
		for (Field field : fields) {
			Field tField = null;
			try {
				tField = clazz.getDeclaredField(field.getName());
			} catch (NoSuchFieldException e) {
			}
			if (tField != null) {
				Object value = org.apache.commons.beanutils.BeanUtils.getProperty(t, field.getName());
				org.apache.commons.beanutils.BeanUtils.setProperty(o, field.getName(), value);
			}
		}
		return o;
	}
}
