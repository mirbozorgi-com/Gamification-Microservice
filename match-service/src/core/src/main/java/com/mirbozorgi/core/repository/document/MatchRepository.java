package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.entity.Match;
import java.util.List;

public interface MatchRepository {

  Match save(Match match);

  Match findById(String id);

  void delete(Match match);

  List<Match> findAll(
      String gamePackageName,
      String env,
      String marketName,
      String name,
      long nowTime,
      boolean active);

  Match update(String gamePackageName,
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


}
