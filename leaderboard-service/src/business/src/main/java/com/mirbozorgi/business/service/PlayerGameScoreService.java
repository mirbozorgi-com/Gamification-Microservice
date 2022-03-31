package com.mirbozorgi.business.service;

import com.mirbozorgi.core.domain.PlayerGameScore;

public interface PlayerGameScoreService {

  PlayerGameScore add(
      String gamePackageName,
      String marketName,
      String env,
      String challengeId,
      String userUuId,
      String username,
      Boolean allMarketInclude);

  int incGameScore(
      String gamePackageName,
      String marketName,
      String env,
      String challengeId,
      String userUuId,
      int score);

  long getPositionInLeaderBoard(
      String gamePackageName,
      String marketName,
      String env,
      String challengeId,
      String userUuId);


}
