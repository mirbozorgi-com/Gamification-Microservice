package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.service.PlayerGameScoreService;
import com.mirbozorgi.business.service.TimeService;
import com.mirbozorgi.core.domain.PlayerGameScore;
import com.mirbozorgi.core.repository.document.PlayerGameScoreRepository;
import com.mirbozorgi.core.repository.memory.LeaderBoardMemoryRepository;
import java.util.LinkedHashMap;
import mirbozorgi.base.feignService.ChallengeFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PlayerGameServiceImpl implements PlayerGameScoreService {

  @Autowired
  private PlayerGameScoreRepository playerGameScoreRepository;

  @Autowired
  private TimeService timeService;

  @Autowired
  private LeaderBoardMemoryRepository leaderBoardMemoryRepository;

  @Value("${market.default-name}")
  String defaultMarket;


  @Autowired
  private ChallengeFeignService challengeFeignService;


  @Override
  public PlayerGameScore add(
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
    PlayerGameScore playerGameScore = new PlayerGameScore(0,
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

    return playerGameScoreRepository.add(
        gamePackageName,
        marketName,
        env,
        challengeId,
        userUuId,
        playerGameScore
    );
  }


  @Override
  public int incGameScore(
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

    return playerGameScoreRepository.incarsalancore(
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
