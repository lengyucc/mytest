package com.antbean.mytest.a;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 一个key值可以重复的map
 * @author: lmh
 * @version 1.0
 * @date: 2017年4月25日
 */
public class RepeatableKeyMap<K, V> implements Map<K, V> {
	private List<K> keys = new ArrayList<K>();
	private List<V> values = new ArrayList<V>();

	@Override
	public int size() {
		return keys.size();
	}
	@Override
	public boolean isEmpty() {
		return keys.isEmpty();
	}
	@Override
	public boolean containsKey(Object key) {
		return keys.contains(key);
	}
	@Override
	public boolean containsValue(Object value) {
		return values.contains(value);
	}
	@Override
	public V get(Object key) {
		int index = 0;
		for (K k : keys) {
			if (k == key) {
				return values.get(index);
			}
			index++;
		}
		return null;
	}
	@Override
	public V put(K key, V value) {
		keys.add(key);
		values.add(value);
		return value;
	}
	@Override
	public V remove(Object key) {
		boolean find = false;
		int index = 0;
		for (K k : keys) {
			if (k == key) {
				find = true;
				break;
			}
			index++;
		}
		if (find) {
			keys.remove(index);
			return values.remove(index);
		}
		return null;
	}
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		keys.addAll(m.keySet());
		values.addAll(m.values());
	}
	@Override
	public void clear() {
		keys.clear();
		values.clear();
	}
	@Override
	public Set<K> keySet() {
		return new LinkedHashSet<K>(keys);
	}
	@Override
	public Collection<V> values() {
		return values;
	}
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		Set<java.util.Map.Entry<K, V>> set = new LinkedHashSet<java.util.Map.Entry<K, V>>();
		for (int i = 0; i < keys.size(); i++) {
			K k = keys.get(i);
			V v = values.get(i);
			Entry<K, V> entry = new MyEntry(k, v);
			set.add(entry);
		}
		return set;
	}

	class MyEntry implements Entry<K, V> {
		private K k;
		private V v;

		@Override
		public K getKey() {
			return k;
		}
		@Override
		public V getValue() {
			return v;
		}
		@Override
		public V setValue(V value) {
			this.v = value;
			return value;
		}
		public MyEntry() {
			super();
		}
		public MyEntry(K k, V v) {
			super();
			this.k = k;
			this.v = v;
		}
	}
}
