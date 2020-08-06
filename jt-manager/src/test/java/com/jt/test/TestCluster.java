package com.jt.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class TestCluster {

	@Test
	public void TestCluster() {
		JedisPoolConfig poolConfig=new JedisPoolConfig();
		poolConfig.setMaxTotal(1200);
		Set<HostAndPort> nodes=new HashSet<HostAndPort>();
		String host="192.168.174.134";
		nodes.add(new  HostAndPort(host, 7000));
		nodes.add(new  HostAndPort(host, 7001));
		nodes.add(new  HostAndPort(host, 7002));
		nodes.add(new  HostAndPort(host, 7003));
		nodes.add(new  HostAndPort(host, 7004));
		nodes.add(new  HostAndPort(host, 7005));
		nodes.add(new  HostAndPort(host, 7006));
		nodes.add(new  HostAndPort(host, 7007));
		nodes.add(new  HostAndPort(host, 7008));
		JedisCluster jedisCluster=new JedisCluster(nodes, poolConfig);
		jedisCluster.set("name", "tom");
		String name = jedisCluster.get("name");
		System.out.println(name);		
	}
}
