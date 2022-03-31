package com.mirbozorgi.reactor.security.repository.memory.impl;


import com.mirbozorgi.reactor.security.repository.memory.PlayerSessionRepository;
import com.mirbozorgi.reactor.security.repository.memory.base.MemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerSessionRepositoryImpl implements PlayerSessionRepository {

  @Autowired
  MemoryRepository redisRepository;

  @Override
  public void add(String uuid, String sessionId) {
    String key = "SESSION_ID ";
    redisRepository.setItem(key + uuid, sessionId);
  }

  @Override
  public String get(String uuid) {
    String key = "SESSION_ID ";
    return redisRepository.getItem(key + uuid);
  }

  @Override
  public void remove(String uuid) {
    String key = "SESSION_ID ";
    redisRepository.removeItem(key + uuid);
  }
}
