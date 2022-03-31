package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.ClaimData;
import com.mirbozorgi.business.domain.IncScoreResponse;
import com.mirbozorgi.business.domain.UserChallengeData;
import java.util.List;

public interface UserChallengeService {


  UserChallengeData addOrUpdate(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName);


  UserChallengeData get(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName);

  List<UserChallengeData> getAll(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName,
      Boolean claim,
      Boolean active);


  IncScoreResponse incScore(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName,
      int score);

  void updateBanned(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName,
      boolean banned);


  ClaimData updateClaimed(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName);

}
