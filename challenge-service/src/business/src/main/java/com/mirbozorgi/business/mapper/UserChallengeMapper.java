package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.UserChallengeData;
import com.mirbozorgi.core.document.UserChallenge;

public class UserChallengeMapper {

  public static UserChallengeData toUserChallengeData(UserChallenge userChallenge) {

    return new UserChallengeData(
        userChallenge.getStringId(),
        userChallenge.getChallengeId(),
        userChallenge.getUserUuId(),
        userChallenge.getName(),
        userChallenge.getGamePackageName(),
        userChallenge.getEnv(),
        userChallenge.getMarketName(),
        userChallenge.getScore(),
        userChallenge.getLastUpdateScoreTime(),
        userChallenge.getCheatUpdateRequest(),
        userChallenge.getClaim(),
        userChallenge.getBanned(),
        userChallenge.getEndTime()

    );

  }
}
