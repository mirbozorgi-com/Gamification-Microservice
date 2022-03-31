package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.service.LeaderBoardMemoryXpService;
import com.mirbozorgi.business.service.TimeService;
import com.mirbozorgi.business.service.PlayerXpService;
import com.mirbozorgi.core.repository.document.PlayerXpRepository;
import java.util.List;
import mirbozorgi.base.feignService.GameFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerXpServiceImpl implements PlayerXpService {

  @Autowired
  private PlayerXpRepository repository;
  @Autowired
  private TimeService timeService;

  @Autowired
  private GameFeignService gameFeignService;

  @Autowired
  private LeaderBoardMemoryXpService leaderBoardMemoryXpService;


  @Override
  public void update(
      String uuid,
      String gamepackageName,
      String env,
      String marketName,
      int xpGlobal,
      int xp,
      int level,
      String username,
      List<Integer> avatarActiveIds,
      long endVipTime) {

    repository.upsert(
        uuid,
        gamepackageName,
        env,
        marketName,
        xpGlobal,
        level,
        timeService.getNowUnixFromInstantClass(),
        username,
        avatarActiveIds,
        endVipTime);
    repository.upsertWithTime(
        uuid,
        gamepackageName,
        env,
        marketName,
        xp,
        level,
        timeService.getNowUnixFromInstantClass(),
        username,
        avatarActiveIds,
        endVipTime);

    leaderBoardMemoryXpService.update(
        gamepackageName,
        env,
        marketName,
        xp,
        uuid
    );
  }

  @Override
  public void resetWithTime(String gamepackageName, String env, String marketName) {
    repository.resetXpsWithTime(gamepackageName, env, marketName);
  }
}
