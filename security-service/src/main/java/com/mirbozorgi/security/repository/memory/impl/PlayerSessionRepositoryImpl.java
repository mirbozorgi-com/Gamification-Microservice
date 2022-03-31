package com.mirbozorgi.security.repository.memory.impl;

import com.mirbozorgi.security.repository.memory.base.MemoryRepository;
import com.mirbozorgi.security.repository.memory.PlayerSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerSessionRepositoryImpl implements PlayerSessionRepository {

  @Autowired
  MemoryRepository redisRepository;

  @Override
  public void add(
      String uuid,
      String sessionId,
      String gamePackageName,
      String marketName,
      String env) {
    String key = "SESSION_ID " + "_" + gamePackageName + "_" + env + "_" + marketName + "_";
    redisRepository.setItem(key + uuid, sessionId);
  }

  @Override
  public String get(
      String uuid,
      String gamePackageName,
      String marketName,
      String env) {
    String key = "SESSION_ID " + "_" + gamePackageName + "_" + env + "_" + marketName + "_";
    return redisRepository.getItem(key + uuid);
  }

  @Override
  public void remove(String uuid,
      String gamePackageName,
      String marketName,
      String env) {
    String key = "SESSION_ID " + "_" + gamePackageName + "_" + env + "_" + marketName + "_";
    redisRepository.removeItem(key + uuid);
  }
}
