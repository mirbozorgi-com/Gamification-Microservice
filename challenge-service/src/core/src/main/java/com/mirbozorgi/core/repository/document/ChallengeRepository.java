package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.constant.ChallengeType;
import com.mirbozorgi.core.document.Challenge;
import java.util.List;

public interface ChallengeRepository {

  Challenge save(Challenge challenge);

  Challenge findById(String id);

  Challenge update(String id,
      String name,
      long startTime,
      long endTime,
      long maxScorePerUpdate,
      long minScorePerUpdate,
      long secondBetweenTwoUpdatingScore,
      long limitForUpdateRequestPerPeriod,
      Object reward,
      ChallengeType type,
      String gamePackageName,
      String env,
      String marketName,
      Boolean allMarketInclude,
      int minClientVersion,
      int maxClientVersion);

  Challenge findBy(
      String gamePackageName,
      String env,
      String marketName,
      String name);

  void delete(Challenge challenge);

  List<Challenge> findAll(
      String gamePackageName,
      String env,
      String marketName,
      String name,
      Boolean active,
      long nowTime);

  boolean isIncludeAllMarket(String id);

}



