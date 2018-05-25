package com.mood.redis;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface CacheService<K, V> {

	V get(K key);

	void set(K key, V member, int exp);

	boolean exists(K key);

	void del(K key);
}
