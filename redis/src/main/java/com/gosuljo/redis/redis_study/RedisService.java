package com.gosuljo.redis.redis_study;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
	private final RedisTemplate redisTemplate;

	public RedisService(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void setStringOps(String key, String value, long ttl, TimeUnit unit) {
		redisTemplate.opsForValue().set(key, value, ttl, unit);
	}

	public String getStringOps(String key) {
		return (String) redisTemplate.opsForValue().get(key);
	}
}
