package com.jt.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class RedisService {
	
	@Autowired(required = false)//通用工具类关闭 启动装载用时才装载
	//private ShardedJedisPool shardedJedisPool;
	private JedisCluster jedisCluster;
	public String set(String key,String value) {
		String status = jedisCluster.set(key, value);
		return status;
	}
	/**
	 * 向缓存区存储数据
	 * @param key 键
	 * @param seconds 超时时间 单位：秒
	 * @param value 值
	 * @return
	 */
	public String set(String key,int seconds,String value) {
		String status = jedisCluster.setex(key, seconds, value) ;
		return status;
	}
	
	public String get(String key) {
		String result = jedisCluster.get(key);
		return result;
	}
	
}
