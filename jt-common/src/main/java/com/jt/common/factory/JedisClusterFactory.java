package com.jt.common.factory;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;


import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.Resource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class JedisClusterFactory implements FactoryBean<JedisCluster> {

	private JedisPoolConfig poolConfig;
	private String redisNodePrefix;
	private Resource propertySource;
	@Override
	public JedisCluster getObject() throws Exception {
		Set<HostAndPort> nodes=getNodes();
		JedisCluster jedisCluster=new JedisCluster(nodes,  poolConfig);
		return jedisCluster;
	}

	/**
	 * 通过配置文件获取redis集群各个节点信息
	 * @return 节点集合
	 */
	public Set<HostAndPort> getNodes(){
		Set<HostAndPort> set=new HashSet<HostAndPort>();
		Properties properties=new Properties();
		try {
			properties.load(propertySource.getInputStream());
			for (Object key : properties.keySet()) {
				String keyString=(String) key ;
				if(keyString.startsWith(redisNodePrefix)) {
					String hostAndPort = (String) properties.get(keyString);
					String host=hostAndPort.split("\\:")[0];
					int port=Integer.parseInt(hostAndPort.split("\\:")[1]);
					set.add(new HostAndPort(host, port));
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return set;
	}
	
	@Override
	public Class<?> getObjectType() {
		return JedisCluster.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public JedisPoolConfig getPoolConfig() {
		return poolConfig;
	}

	public void setPoolConfig(JedisPoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}

	public String getRedisNodePrefix() {
		return redisNodePrefix;
	}

	public void setRedisNodePrefix(String redisNodePrefix) {
		this.redisNodePrefix = redisNodePrefix;
	}

	public Resource getPropertySource() {
		return propertySource;
	}

	public void setPropertySource(Resource propertySource) {
		this.propertySource = propertySource;
	}

	
	
	
}
