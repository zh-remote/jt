package com.jt.test;

import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class TestRedisConn {

	@Test
	public void test02(){
		Jedis jedis = new Jedis("192.168.174.134",6379);
		jedis.set("hello", "来自god的问候");
		System.out.println(jedis.get("hello"));
	}
	
	@Test
	public void test03(){
		Jedis jedis = new Jedis("192.168.174.134",6379);
		jedis.hset("user", "id","100");
		jedis.hset("user", "name","tom");
		System.out.println(jedis.hgetAll("user"));
	}
	
	@Test
	public void test043(){
		Jedis jedis = new Jedis("192.168.174.134",6379);
 
		jedis.lpush("list1", "1 2 3 4 56","22");
		String lpop = jedis.lpop("list1");
		Set<String> keys = jedis.keys("*");
		for (String key : keys) {
			System.out.println(key);
		}
		System.out.println(lpop);
	}
}
