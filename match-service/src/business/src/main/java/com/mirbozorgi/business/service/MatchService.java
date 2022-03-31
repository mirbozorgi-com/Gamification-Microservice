package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.MatchData;
import java.util.List;

public interface MatchService {

  MatchData save(String gamePackageName,
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
  );


  MatchData update(String gamePackageName,
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
      String id);

  MatchData findById(String id);

  void delete(String id);

  List<MatchData> findAll(
      String gamePackageName,
      String env,
      String marketName,
      String name,
      boolean active);

  List<MatchData> findAllForPlayers(
      String gamePackageName,
      String env,
      String marketName,
      String name,
      boolean active);
}
