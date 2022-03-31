package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.ChallengeData;
import com.mirbozorgi.core.constant.ChallengeType;
import java.util.List;

public interface ChallengeService {


  ChallengeData save(
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

  ChallengeData findById(String id);

  ChallengeData update(
      String id,
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

  ChallengeData findBy(
      String gamePackageName,
      String env,
      String marketName,
      String name);

  void delete(String id);

  List<ChallengeData> findAll(
      String gamePackageName,
      String env,
      String marketName,
      String name,
      Boolean active
  );

  boolean isIncludeAllMarket(String id);


}
