package com.mirbozorgi.business.service;

import com.mirbozorgi.core.domain.Playerarsalancore;

public interface PlayerarsalancoreService {

  Playerarsalancore add(
      String gamePackageName,
      String marketName,
      String env,
      String challengeId,
      String userUuId,
      String username,
      Boolean allMarketInclude);

  int incarsalancore(
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
