package com.sm.hello.common.cache;

/**
 * 缓存
 * @author shenmiao
 *
 */
public interface CacheService {
	
	/**
	 * 删除缓存
	 * @param key
	 */
	public void remove(String key);
	
	/**
	 * 判断以key为键的缓存是否存在
	 * @param key
	 * @return
	 */
	public boolean exists(String key);
	
	/**
	 * 获取递增值，每次递增1
	 * @param key
	 * @return
	 */
	public long incr(String key);
	
	/**
	 * 获取递增值，delta为递增步长
	 * @param key
	 * @param delta
	 * @return
	 */
	public long incrBy(String key, long delta);
	
	/**
	 * 获取递减值，每次递减1
	 * @param key
	 * @return
	 */
	public long decr(String key);
	
	/**
	 * 获取递减值，delta为递减步长
	 * @param key
	 * @param delta
	 * @return
	 */
	public long decrBy(String key, long delta);
	
	/**
	 * 获取以key为键的value
	 * @param key
	 * @return
	 */
	public Object get(String key);
	
	/**
	 * 插入缓存
	 * @param key
	 * @param value
	 * @param expire	单位：秒
	 * @return
	 */
	public boolean set(String key, Object value, Long expire);
	
	public Long leftPush(String key, Object... values);
}
