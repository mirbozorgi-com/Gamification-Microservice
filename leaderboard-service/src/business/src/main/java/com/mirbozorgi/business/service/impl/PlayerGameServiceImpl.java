package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.service.PlayerarsalancoreService;
import com.mirbozorgi.business.service.TimeService;
import com.mirbozorgi.core.domain.Playerarsalancore;
import com.mirbozorgi.core.repository.document.PlayerarsalancoreRepository;
import com.mirbozorgi.core.repository.memory.LeaderBoardMemoryRepository;
import java.util.LinkedHashMap;
import mirbozorgi.base.feignService.ChallengeFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PlayerarsalanerviceImpl implements PlayerarsalancoreService {

  @Autowired
  private PlayerarsalancoreRepository playerarsalancoreRepository;

  @Autowired
  private TimeService timeService;

  @Autowired
  private LeaderBoardMemoryRepository leaderBoardMemoryRepository;

  @Value("${market.default-name}")
  String defaultMarket;


  @Autowired
  private ChallengeFeignService challengeFeignService;


  @Override
  public Playerarsalancore add(
      String gamePackageName,
      String marketName,
      String env,
      String challengeId,
      String userUuId,
      String username,
      Boolean allMarketInclude) {

    if (allMarketInclude) {
      marketName = defaultMarket;
    }
    Playerarsalancore playerarsalancore = new Playerarsalancore(0,
        timeService.getNowUnixFromInstantClass(),
        username);

    leaderBoardMemoryRepository.add(
        gamePackageName,
        env,
        marketName,
        challengeId,
        0,
        userUuId
    );

    return playerarsalancoreRepository.add(
        gamePackageName,
        marketName,
        env,
        challengeId,
        userUuId,
        playerarsalancore
    );
  }


  @Override
  public int incarsalancore(
      String gamePackageName,
      String marketName,
      String env,
      String challengeId,
      String userUuId,
      int score) {

    marketName = findMarketName(challengeId, marketName);

    leaderBoardMemoryRepository.update(
        gamePackageName,
        env,
        marketName,
        challengeId,
        score,
        userUuId);

    return playerarsalancoreRepository.incarsalancore(
        gamePackageName,
        marketName,
        env,
        challengeId,
        userUuId,
        score,
        timeService.getNowUnixFromInstantClass()
    );
  }

  @Override
  public long getPositionInLeaderBoard(
      String gamePackageName,
      String marketName,
      String env,
      String challengeId,
      String userUuId) {
    if (marketName == null) {
      marketName = "all-markets";
    }
    marketName = findMarketName(challengeId, marketName);

    Long position = leaderBoardMemoryRepository.getPosition(
        gamePackageName,
        env,
        marketName,
        challengeId,
        userUuId
    );
    if (position == null) {
      return -1;
    }

    return position + 1;
  }


  private String findMarketName(String challengeId, String marketName) {

    Object o = challengeFeignService.isIncludeAllMarket(challengeId);

    if (((LinkedHashMap) o).get("data").equals(true)) {
      marketName = defaultMarket;
    }

    return marketName;
  }
}
