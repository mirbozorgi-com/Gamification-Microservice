package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.DailyRewardContinuesInfo;
import com.mirbozorgi.core.document.DailyRewardContinues;

public class DailyRewardContinuesMapper {

  public static DailyRewardContinuesInfo toInfo(DailyRewardContinues dailyRewardContinues,
      String name) {
    return new DailyRewardContinuesInfo(
        dailyRewardContinues.getStringId(),
        dailyRewardContinues.getGameId(),
        name,
        dailyRewardContinues.getRewardFlows().get(name),
        dailyRewardContinues.getGamePackageName(),
        dailyRewardContinues.getEnv()
    );
  }

}
