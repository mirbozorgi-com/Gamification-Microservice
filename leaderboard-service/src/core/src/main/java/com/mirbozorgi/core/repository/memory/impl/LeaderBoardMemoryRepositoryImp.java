package com.mirbozorgi.core.repository.memory.impl;

import com.mirbozorgi.core.repository.memory.base.MemoryRepository;
import com.mirbozorgi.core.repository.memory.LeaderBoardMemoryRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LeaderBoardMemoryRepositoryImp implements LeaderBoardMemoryRepository {

  @Autowired
  private MemoryRepository redisRepository;

  @Override
  public Long add(
      String packageName,
      String env,
      String market,
      String challengeId,
      long score,
      String userUuId) {
    String key = String
        .format("position-%s-%s-%s-%s", packageName, env, market, challengeId);

    return redisRepository.zAdd(key, score, userUuId);
  }

  @Override
  public Long update(
      String packageName,
      String env,
      String market,
      String challengeId,
      long score,
      String userUuId) {
    String key = String
        .format("position-%s-%s-%s-%s", packageName, env, market, challengeId);
    Double update = redisRepository.zUpdate(key, score, userUuId);
    return update.longValue();
  }

  @Override
  public Long getPosition(
      String packageName,
      String env,
      String market,
      String challengeId,
      String userUuId) {
    String key = String
        .format("position-%s-%s-%s-%s", packageName, env, market, challengeId);

    return redisRepository.zReverseRank(key, userUuId);
  }

  @Override
  public Set<String> getCashLeaderBoard(
      String packageName,
      String env,
      String market,
      String challengeId,
      long start,
      long stop) {
    String key = String
        .format("position-%s-%s-%s-%s", packageName, env, market, challengeId);

    return redisRepository.zRange(key, start, stop);
  }

  @Override
  public void deleteSpecificKey(String packageName, String env, String market,
      String challengeId) {
    String key = String
        .format("position-%s-%s-%s-%s", packageName, env, market, challengeId);

    redisRepository.deleteAllWithSpecificKey(key);

  }

  private String fix(String packageName) {
    return packageName.replace(".", "_");
  }

}
