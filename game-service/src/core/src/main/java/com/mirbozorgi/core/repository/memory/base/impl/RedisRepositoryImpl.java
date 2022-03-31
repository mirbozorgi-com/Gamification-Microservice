package com.mirbozorgi.core.repository.memory.base.impl;

import com.mirbozorgi.core.repository.memory.base.MemoryRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@Repository
public class RedisRepositoryImpl implements MemoryRepository {

  @Autowired
  private JedisPool jedisPool;


  @Override
  public String getItem(String key) {
    Jedis jedis = jedisPool.getResource();
    jedis.close();
    return jedis.get(key);
  }

  @Override
  public String setItem(String key, String item) {
    Jedis jedis = jedisPool.getResource();
    String result = jedis.set(key, item);
    jedis.close();
    return result;
  }

  @Override
  public Boolean setItemNx(String key, String item) {
    Jedis jedis = jedisPool.getResource();
    Boolean result = jedis.setnx(key, item) > 0;
    jedis.close();
    return result;
  }


  @Override
  public void removeItem(String key) {
    Jedis jedis = jedisPool.getResource();
    jedis.del(key);
    jedis.close();
  }

  @Override
  public void dictionarySet(String key, String dicKey, String dicValue) {
    Jedis jedis = jedisPool.getResource();
    jedis.hset(key, dicKey, dicValue);
    jedis.close();
  }

  @Override
  public String dictionaryGet(String key, String dicKey) {
    Jedis jedis = jedisPool.getResource();
    String result = jedis.hget(key, dicKey);
    jedis.close();
    return result;
  }

  @Override
  public Map<String, String> dictionaryGet(String key) {
    Jedis jedis = jedisPool.getResource();
    Map<String, String> result = jedis.hgetAll(key);
    jedis.close();
    return result;
  }

  @Override
  public Boolean dictionaryExists(String key, String dicKey) {
    Jedis jedis = jedisPool.getResource();
    Boolean result = jedis.hexists(key, dicKey);
    jedis.close();
    return result;
  }

  @Override
  public void dictionaryRemove(String key, String dicKey) {
    Jedis jedis = jedisPool.getResource();
    jedis.hdel(key, dicKey);
    jedis.close();
  }

  @Override
  public long dictionaryIncrement(String key, String dicKey, int value) {
    Jedis jedis = jedisPool.getResource();
    Long result = jedis.hincrBy(key, dicKey, value);
    jedis.close();
    return result;
  }

  @Override
  public void queueLPush(String key, String item, Integer size) {
    Jedis jedis = jedisPool.getResource();

    jedis.lpush(key, item);
    if (size > 0) {
      jedis.ltrim(key, 0, size);
    }

    jedis.close();
  }

  @Override
  public void queueRPush(String key, String item, Integer size) {
    Jedis jedis = jedisPool.getResource();

    jedis.rpush(key, item);
    if (size > 0) {
      jedis.ltrim(key, 0, size);
    }

    jedis.close();
  }

  @Override
  public String queueLPop(String key) {
    Jedis jedis = jedisPool.getResource();
    String result = jedis.lpop(key);
    jedis.close();
    return result;
  }

  @Override
  public String queueRPop(String key) {
    Jedis jedis = jedisPool.getResource();
    String result = jedis.rpop(key);
    jedis.close();
    return result;
  }

  @Override
  public List<String> queueLRange(String key, Integer start, Integer end) {
    Jedis jedis = jedisPool.getResource();
    List<String> result = jedis.lrange(key, start, end);
    jedis.close();
    return result;
  }

  @Override
  public List<String> queueRRange(String key, Integer start, Integer end) {
    Jedis jedis = jedisPool.getResource();
    List<String> result = jedis.lrange(key, start, end);
    jedis.close();
    return result;
  }

  @Override
  public void queueRemove(String key, String item) {
  }

  @Override
  public List<String> queueGet(String key) {
    Jedis jedis = jedisPool.getResource();
    List<String> result = jedis.mget(key);
    jedis.close();
    return result;
  }

  @Override
  public void arrayAdd(String key, String item) {
    Jedis jedis = jedisPool.getResource();
    jedis.mset(key, item);
    jedis.close();
  }

  @Override
  public void arrayRemove(String key, String item) {

  }

  @Override
  public String arrayGetRandom(String key) {
    return null;
  }

  @Override
  public long incrementItem(String key, Integer count) {
    return 0;
  }

  @Override
  public void setExpiry(String key, Integer sec) {

  }

  @Override
  public boolean check() {
    Jedis jedis = jedisPool.getResource();
    String result = jedis.info();
    jedis.close();
    return !StringUtils.isEmpty(result);
  }
}