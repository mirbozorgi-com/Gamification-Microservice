package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.LeaderBoardRewardInfo;
import com.mirbozorgi.core.entity.LeaderBoardReward;

public class LeaderBoardRewardMapper {

  public static LeaderBoardRewardInfo toInfo(LeaderBoardReward leaderBoardReward) {
    return new LeaderBoardRewardInfo(
        leaderBoardReward.getStringId(),
        leaderBoardReward.getGamePackageName(),
        leaderBoardReward.getEnv(),
        leaderBoardReward.getMarketName(),
        leaderBoardReward.getRewards()
    );
  }

}
