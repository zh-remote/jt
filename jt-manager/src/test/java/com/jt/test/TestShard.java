package com.jt.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class TestShard {

	@Test
	public void test1() {
		JedisPoolConfig poolConfig=new JedisPoolConfig();
		poolConfig.setMaxTotal(1000);
		poolConfig.setMinIdle(100);
		poolConfig.setTestOnBorrow(true);
		List<JedisShardInfo> shards=new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo("192.168.174.134", 6379));
		shards.add(new JedisShardInfo("192.168.174.134", 6380));
		shards.add(new JedisShardInfo("192.168.174.134", 6381));
		
	 
		ShardedJedisPool pool = new ShardedJedisPool(poolConfig, shards);	
		ShardedJedis shardedJedis = pool.getResource();
		  shardedJedis.set("tit","测试分片");
    	String fenpian = shardedJedis.get("fenpian");
	    System.out.println(fenpian);
	    shardedJedis.close();
	}
}
