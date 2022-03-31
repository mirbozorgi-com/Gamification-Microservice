package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.document.UserChallenge;
import java.util.List;

public interface UserChallengeRepository {


  UserChallenge addOrUpdate(UserChallenge userChallenge);


  UserChallenge get(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName);

  List<UserChallenge> getAll(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName,
      Boolean claim,
      Boolean active,
      long nowTime);


  int incScore(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName,
      int score,
      long nowTime);

  void incCheatInt(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName);

  void updateBanned(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName,
      boolean banned);


  void updateClaimed(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName,
      boolean claim);


}
