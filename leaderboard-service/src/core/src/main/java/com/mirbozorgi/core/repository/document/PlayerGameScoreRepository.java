package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.domain.PlayerGameScore;

public interface PlayerGameScoreRepository {

  PlayerGameScore add(
      String gamePackageName,
      String marketName,
      String env,
      String challengeId,
      String userUuId,
      PlayerGameScore playerGameScore
  );


  PlayerGameScore get(
      String gamePackageName,
      String marketName,
      String env,
      String challengeId,
      String userUuId
  );

  int incGameScore(
      String gamePackageName,
      String marketName,
      String env,
      String challengeId,
      String userUuId,
      int score,
      long nowTime
  );

}
