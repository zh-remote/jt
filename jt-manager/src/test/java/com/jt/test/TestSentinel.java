package com.jt.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class TestSentinel {

	@Test
	public void test01() {
		Set<String> sentinels=new HashSet<String>();
		sentinels.add("192.168.174.134:26379");
		JedisSentinelPool sentinelPool=new JedisSentinelPool("mymaster", sentinels);
		Jedis resource = sentinelPool.getResource();
		resource.set("name","tocat");
		System.out.println(resource.get("name"));
	}
}
