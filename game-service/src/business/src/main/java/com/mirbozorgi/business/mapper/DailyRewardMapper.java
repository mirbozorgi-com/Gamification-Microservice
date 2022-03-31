package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.DailyRewardInfo;
import com.mirbozorgi.core.entity.DailyReward;

public class DailyRewardMapper {

  public static DailyRewardInfo toInfo(DailyReward dailyReward) {

    return new DailyRewardInfo(
        dailyReward.getId(),
        dailyReward.getGame().getId(),
        dailyReward.getGem(),
        dailyReward.getLevel(),
        dailyReward.getGold(),
        dailyReward.getXp(),
        dailyReward.getAvatarId(),
        dailyReward.getVipPeriodTime(),
        dailyReward.getAddHami()
    );
  }

}
