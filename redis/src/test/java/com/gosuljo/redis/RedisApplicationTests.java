package com.gosuljo.redis;

import com.gosuljo.redis.redis_study.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisApplicationTests {

	@Autowired
	private RedisService redisService;

	@Test
	void contextLoads() {
	}

	@Test
	void string_redis() {
		String key = "string_redis";
		String value = "hi~ my string";
		redisService.setStringOps(key, value, 10, TimeUnit.SECONDS);
		System.out.println(String.format("### Redis Key => %s | value => %s", key, redisService.getStringOps(key)));
	}
}
