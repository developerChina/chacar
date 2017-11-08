package org.core.util;

import java.util.*;

/**
 * 自定义一对多map,value  “多值不重复”
 */
@SuppressWarnings({ "rawtypes", "unchecked" })

public class O2MoreOnlyMap<K, V> {
	private List<K> mkey;
	private List<Set <V>> mvlaue;

	public O2MoreOnlyMap() {
		mkey = new ArrayList<K>();
		mvlaue = new ArrayList<Set<V>>();

	}

	/*
	 ** 添加元素
	 */
	public void put(K key, V value) {
		Set set  =   new  HashSet();
		set.add(value);
		if (containsKey(key)) {
			mvlaue.get(mkey.indexOf(key)).add(value);
		} else {
			mkey.add(key);
			mvlaue.add(set);
		}
	}

	/*
	 * 通过index获取key
	 */
	public K getkey(int i) {
		return mkey.get(i);
	}

	/*
	 * 通过index获取values
	 */
	public Set<V> getvalue(int i) {
		return mvlaue.get(i);
	}

	/*
	 * 通过index获取元素
	 */
	public Map<K, Set<V>> get(int i) {
		Map<K, Set<V>> map = new HashMap<>();
		map.put(mkey.get(i), mvlaue.get(i));
		return map;
	}

	/*
	 * 获取全部元素
	 */
	public Map<K, Set<V>> getAll() {
		Map<K, Set<V>> map = new HashMap<>();
		for (int i = 0; i < mkey.size(); i++) {
			map.put(mkey.get(i), mvlaue.get(i));
		}
		return map;
	}

	// 查看key是否重复
	public boolean containsKey(K key) {
		if (mkey.contains(key)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 大小
	 */
	public long getSize() {
		return mkey.size();
	}

	/*
	 * 移除
	 */
	public boolean removeAll() {
		mkey.clear();
		mvlaue.clear();
		if (mkey.isEmpty() && mvlaue.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
