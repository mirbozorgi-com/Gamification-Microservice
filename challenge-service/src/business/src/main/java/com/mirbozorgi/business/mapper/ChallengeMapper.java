package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.ChallengeData;
import com.mirbozorgi.core.document.Challenge;

public class ChallengeMapper {


  public static ChallengeData toChallengeData(Challenge challenge) {

    return new ChallengeData(
        challenge.getStringId(),
        challenge.getName(),
        challenge.getStartTime(),
        challenge.getEndTime(),
        challenge.getMaxScorePerUpdate(),
        challenge.getMinScorePerUpdate(),
        challenge.getSecondBetweenTwoUpdatingScore(),
        challenge.getLimitForUpdateRequestPerPeriod(),
        challenge.getReward(),
        challenge.getType(),
        challenge.getGamePackageName(),
        challenge.getEnv(),
        challenge.getMarketName(),
        challenge.getAllMarketInclude(),
        challenge.getMinClientVersion(),
        challenge.getMaxClientVersion()

    );
  }

}
