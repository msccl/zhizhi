package com.qkzz.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * Title: LRUMap: Last Recent Used HashMap<br/> Description: 实现最近最少使用原则的Map<br/>
 * 1.在集合较大、访问频繁时，最大容量可能失效，实际应用的时候请注意测试检查<br/>
 * 2.在调用get()，put()方法的时候，影响到的元素会被放置在Iterator的首部<br/>
 * 3.遍历时不能进行put、get操作，否则会抛出ConcurrentModificationException异常<br/>
 * 4.如果需要同步，使用createSyncMap<br/>
 * </p>
 * @version 1.0
 */
public class LRUMap<K,V> extends LinkedHashMap<K,V> {

	private static final long serialVersionUID = -7259184077288777601L;


	int maxsize;

	/**
	 * @param initCapacity
	 *            int 此map的初始化大小
	 * @param maxSize
	 *            int 此map最大容量，如map插入的数值超过此大小，最早被访问的元素将会被抛弃
	 */
	public LRUMap(int initCapacity, int maxSize) {
		super(initCapacity, 0.75f, true);
		this.maxsize = maxSize;
	}

	/**
	 * @param maxSize
	 *            此map的最大容量，初始大小为最大容量的1/4
	 */
	public LRUMap(int maxSize) {
		super(maxSize >> 2, 0.75f, true);
		this.maxsize = maxSize;
	}

	protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
		return this.size() >= maxsize;
	}
}