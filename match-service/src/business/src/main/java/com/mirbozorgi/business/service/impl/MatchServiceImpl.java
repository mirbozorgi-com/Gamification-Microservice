package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.service.MatchService;
import com.mirbozorgi.business.service.TimeService;
import com.mirbozorgi.business.domain.MatchData;
import com.mirbozorgi.business.exception.MarketException;
import com.mirbozorgi.business.mapper.MatchMapper;
import com.mirbozorgi.core.entity.Match;
import com.mirbozorgi.core.repository.document.MatchRepository;
import java.util.ArrayList;
import java.util.List;
import mirbozorgi.base.domain.game.GameInfo;
import mirbozorgi.base.feignService.GameFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImpl implements MatchService {

  @Autowired
  private MatchRepository matchRepository;

  @Autowired
  TimeService timeService;

  @Autowired
  private GameFeignService gameFeignService;


  @Override
  public MatchData save(String gamePackageName,
      String env,
      String marketName,
      String name,
      long startTime,
      long endTime,
      long maxScorePerUpdate,
      long minScorePerUpdate,
      long minSecondBetweenTwoUpdatingScore,
      long maxSecondBetweenTwoUpdatingScore,
      long limitErrorForUpdateRequestPerPeriod,
      int maxPlayerInMatch,
      int minPlayerInMatch,
      int minClientVersion,
      int maxClientVersion,
      Object reward,
      Object config,
      int maxXpForOffline,
      int minXpForOffline,
      int gemCost,
      int goldCost,
      int gemWin,
      int goldWin,
      int winXpLooser,
      int winXpWinner,
      double winnerXpVipCoefficient,
      double looserXpVipCoefficient,
      int gemBonus,
      int goldBonus,
      int xpBonus
  ) {

    GameInfo gameInfo = gameFeignService.getByPackageNameAndEnv(gamePackageName, env);

    if (!gameInfo.getMarketNames().contains(marketName)) {
      throw new MarketException();
    }

    Match match = new Match(
        gamePackageName,
        env,
        marketName,
        name,
        startTime,
        endTime,
        maxScorePerUpdate,
        minScorePerUpdate,
        minSecondBetweenTwoUpdatingScore,
        maxSecondBetweenTwoUpdatingScore,
        limitErrorForUpdateRequestPerPeriod,
        maxPlayerInMatch,
        minPlayerInMatch,
        minClientVersion,
        maxClientVersion,
        reward,
        config,
        maxXpForOffline,
        minXpForOffline,
        gemCost,
        goldCost,
        gemWin,
        goldWin,
        winXpLooser,
        winXpWinner,
        winnerXpVipCoefficient,
        looserXpVipCoefficient,
        gemBonus,
        goldBonus,
        xpBonus
    );

    return MatchMapper.toMatchData(matchRepository.save(match));
  }

  @Override
  public MatchData update(String gamePackageName,
      String env,
      String marketName,
      String name,
      long startTime,
      long endTime,
      long maxScorePerUpdate,
      long minScorePerUpdate,
      long minSecondBetweenTwoUpdatingScore,
      long maxSecondBetweenTwoUpdatingScore,
      long limitErrorForUpdateRequestPerPeriod,
      int maxPlayerInMatch,
      int minPlayerInMatch,
      int minClientVersion,
      int maxClientVersion,
      Object reward,
      Object config,
      int maxXpForJoinOffline,
      int minXpForJoinOffline,
      int gemCost,
      int goldCost,
      int gemWin,
      int goldWin,
      int winXpLooser,
      int winXpWinner,
      double winnerXpVipCoefficient,
      double looserXpVipCoefficient,
      int gemBonus,
      int goldBonus,
      int xpBonus,
      String id) {

    Match match = matchRepository.update(
        gamePackageName,
        env,
        marketName,
        name,
        startTime,
        endTime,
        maxScorePerUpdate,
        minScorePerUpdate,
        minSecondBetweenTwoUpdatingScore,
        maxSecondBetweenTwoUpdatingScore,
        limitErrorForUpdateRequestPerPeriod,
        maxPlayerInMatch,
        minPlayerInMatch,
        minClientVersion,
        maxClientVersion,
        reward,
        config,
        maxXpForJoinOffline,
        minXpForJoinOffline,
        gemCost,
        goldCost,
        gemWin,
        goldWin,
        winXpLooser,
        winXpWinner,
        winnerXpVipCoefficient,
        looserXpVipCoefficient,
        gemBonus,
        goldBonus,
        xpBonus,
        id
    );

    return MatchMapper.toMatchData(match);
  }

  @Override
  public MatchData findById(String id) {
    Match match = matchRepository.findById(id);

    return MatchMapper.toMatchData(match);
  }

  @Override
  public void delete(String id) {

    Match match = matchRepository.findById(id);
    matchRepository.delete(match);


  }

  @Override
  public List<MatchData> findAll(
      String gamePackageName,
      String env,
      String marketName,
      String name,
      boolean active) {

    List<MatchData> matchData = new ArrayList<>();

    List<Match> matches = matchRepository.findAll(
        gamePackageName,
        env,
        marketName,
        name,
        timeService.getNowUnixFromInstantClass(),
        active
    );

    for (Match match : matches) {
      matchData.add(MatchMapper.toMatchData(match));
    }
    if (matchData.size() == 0) {
      return new ArrayList<>();
    }
    return matchData;
  }

  @Override
  public List<MatchData> findAllForPlayers(
      String gamePackageName,
      String env,
      String marketName,
      String name,
      boolean active) {

    List<MatchData> matchData = new ArrayList<>();
    List<Match> matches = matchRepository.findAll(
        gamePackageName,
        env,
        marketName,
        name,
        timeService.getNowUnixFromInstantClass(),
        active
    );

    for (Match match : matches) {
      matchData.add(MatchMapper.toMatchData(match));
    }
    if (matchData.size() == 0) {
      return new ArrayList<>();
    }
    return matchData;
  }


}
