package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.domain.Playerarsalancore;

public interface PlayerarsalancoreRepository {

  Playerarsalancore add(
      String gamePackageName,
      String marketName,
      String env,
      String challengeId,
      String userUuId,
      Playerarsalancore playerarsalancore
  );


  Playerarsalancore get(
      String gamePackageName,
      String marketName,
      String env,
      String challengeId,
      String userUuId
  );

  int incarsalancore(
      String gamePackageName,
      String marketName,
      String env,
      String challengeId,
      String userUuId,
      int score,
      long nowTime
  );

}
