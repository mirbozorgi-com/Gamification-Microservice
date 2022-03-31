package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.SocialNetWorksFollowRewardInfo;
import com.mirbozorgi.core.document.SocialNetWorksFollowReward;

public class SocialNetWorksFollowRewardMapper {

  public static SocialNetWorksFollowRewardInfo toInfo(
      SocialNetWorksFollowReward socialNetWorksFollowReward) {
    return new SocialNetWorksFollowRewardInfo(
        socialNetWorksFollowReward.getStringId(),
        socialNetWorksFollowReward.getGameId(),
        socialNetWorksFollowReward.getGamePackageName(),
        socialNetWorksFollowReward.getEnv(),
        socialNetWorksFollowReward.getRewards()
    );
  }

}
