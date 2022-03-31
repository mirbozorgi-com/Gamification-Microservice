package com.mirbozorgi.security.repository.memory.base;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisConfig {

  static final JedisPoolConfig poolConfig = buildPoolConfig();

  private static JedisPool jedisPool = null;

  @Value("${core.redis-server}")
  private String redisServer;

  @Value("${core.redis-port}")
  private Integer redisPort;

  private static JedisPoolConfig buildPoolConfig() {
    final JedisPoolConfig poolConfig = new JedisPoolConfig();
    poolConfig.setMaxTotal(128);
    poolConfig.setMaxIdle(128);
    poolConfig.setMinIdle(16);
    poolConfig.setTestOnBorrow(true);
    poolConfig.setTestOnReturn(true);
    poolConfig.setTestWhileIdle(true);
    poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
    poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
    poolConfig.setNumTestsPerEvictionRun(3);
    poolConfig.setBlockWhenExhausted(true);
    return poolConfig;
  }

  @Bean
  JedisPool jedis() {

    if (jedisPool == null) {
      jedisPool = new JedisPool(poolConfig, redisServer, redisPort);
    }
    return jedisPool;
  }
}
