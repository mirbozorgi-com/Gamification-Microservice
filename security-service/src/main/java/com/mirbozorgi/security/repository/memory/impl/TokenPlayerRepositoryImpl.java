package com.mirbozorgi.security.repository.memory.impl;

import com.mirbozorgi.security.repository.memory.TokenPlayerRepository;
import com.mirbozorgi.security.repository.memory.base.MemoryRepository;
import com.mirbozorgi.security.repository.memory.base.BaseInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TokenPlayerRepositoryImpl implements
    BaseInMemoryRepository, TokenPlayerRepository {

  @Autowired
  MemoryRepository redisRepository;

  @Override
  public void save(String token, String playerId) {
    redisRepository.dictionarySet(getKey(), token, playerId);
  }

  @Override
  public void remove(String token) {
    redisRepository.dictionaryRemove(getKey(), token);
  }

  @Override
  public String get(String token) {
    return redisRepository.dictionaryGet(getKey(), token);
  }

  @Override
  public String getKey() {
    return "TOKEN_PLAYER";
  }
}
