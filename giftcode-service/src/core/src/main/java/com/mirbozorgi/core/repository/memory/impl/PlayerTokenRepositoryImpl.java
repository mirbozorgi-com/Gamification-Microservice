package com.mirbozorgi.core.repository.memory.impl;

import com.mirbozorgi.core.repository.memory.PlayerTokenRepository;
import com.mirbozorgi.core.repository.memory.base.BaseInMemoryRepository;
import com.mirbozorgi.core.repository.memory.base.MemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class PlayerTokenRepositoryImpl implements
    BaseInMemoryRepository, PlayerTokenRepository {

  @Autowired
  MemoryRepository redisRepository;


  @Override
  public void save(String playerId, String token) {
    redisRepository.dictionarySet(getKey(), playerId, token);
  }

  @Override
  public String get(String playerId) {
    return redisRepository.dictionaryGet(getKey(), playerId);
  }

  @Override
  public void remove(String playerId) {
    redisRepository.dictionaryRemove(getKey(), playerId);
  }


  @Override
  public String getKey() {
    return "PLAYER_TOKEN";
  }
}
