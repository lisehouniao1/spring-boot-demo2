package com.sm.hello.common.cache.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.sm.hello.common.cache.CacheService;

@Service(value = "cacheServcie")
@Configuration
@EnableAutoConfiguration
@PropertySource(value = "classpath:/application.properties", ignoreResourceNotFound = true)
public class RedisCacheServiceImpl implements CacheService {
	
	@Resource(name = "redisTemplate")
	private RedisTemplate<String, Object> redisTemplate;
	
	@Value("${redis.prefix}")
	private String prefix;
	
	private ValueOperations<String, Object> valueOperations;
	private ListOperations<String, Object> listOperations;
	
	@Override
	public void remove(String key) {
		key = prefix + key;
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	@Override
	public boolean exists(String key) {
		key = prefix + key;
		return redisTemplate.hasKey(key);
	}

	@Override
	public long incr(String key) {
		key = prefix + key;
		return redisTemplate.opsForValue().increment(key, 1);
	}

	@Override
	public long incrBy(String key, long delta) {
		key = prefix + key;
		return redisTemplate.opsForValue().increment(key, delta);
	}

	@Override
	public long decr(String key) {
		key = prefix + key;
		return redisTemplate.opsForValue().increment(key, -1);
	}

	@Override
	public long decrBy(String key, long delta) {
		key = prefix + key;
		return redisTemplate.opsForValue().increment(key, -delta);
	}

	@Override
	public Object get(String key) {
		key = prefix + key;
		valueOperations = redisTemplate.opsForValue();
		return valueOperations.get(key);
	}

	@Override
	public boolean set(String key, Object value, Long expire) {
		try {
			key = prefix + key;
			valueOperations = redisTemplate.opsForValue();
			valueOperations.set(key, value);
			redisTemplate.expire(key, expire, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Long leftPush(String key, Object... values) {
		listOperations = redisTemplate.opsForList();
		return listOperations.leftPushAll(key, values);
	}

}
