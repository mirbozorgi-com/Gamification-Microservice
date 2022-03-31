package com.mirbozorgi.core.repository.memory.impl;

import com.mirbozorgi.core.repository.memory.base.MemoryRepository;
import com.mirbozorgi.core.repository.memory.DefaultMarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultMarketRepositoryImpl implements DefaultMarketRepository {

  @Autowired
  MemoryRepository redisRepository;

  @Override
  public void add(String gamePackageName, String env, String marketName) {
    redisRepository.setItem(getKey(gamePackageName, env), marketName);

  }

  @Override
  public String get(String gamePackageName, String env) {
    return redisRepository.getItem(getKey(gamePackageName, env));
  }

  private String getKey(String gamePackageName, String env) {
    return "DEFAULT_MARKET_GAME_ENV_" + gamePackageName + "_" + env;
  }


}
