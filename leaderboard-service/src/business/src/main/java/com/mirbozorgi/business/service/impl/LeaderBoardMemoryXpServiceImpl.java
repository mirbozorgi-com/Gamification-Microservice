package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.service.LeaderBoardMemoryXpService;
import com.mirbozorgi.core.repository.memory.LeaderBoardMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaderBoardMemoryXpServiceImpl implements LeaderBoardMemoryXpService {

  @Autowired
  private LeaderBoardMemoryRepository repository;

  @Override
  public void add(
      String packageName,
      String env,
      String market,
      long score,
      String uuid) {

    repository.add(
        packageName,
        env,
        market,
        "weekly_xp",
        score,
        uuid);

    repository.add(
        packageName,
        env,
        market,
        "xp",
        score,
        uuid);

  }

  @Override
  public void update(
      String packageName,
      String env,
      String market,
      long score,
      String uuid) {

    repository.update(
        packageName,
        env,
        market,
        "weekly_xp",
        score,
        uuid);

  }

  @Override
  public Long getPosition(
      String packageName,
      String env,
      String market,
      String uuid) {
    Long position = -2L;

    position = repository.getPosition(
        packageName,
        env,
        market,
        "weekly_xp",
        uuid
    );

    if (position == null) {
      return -1L;
    }

    return position + 1;
  }
}
