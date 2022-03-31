package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.DailyRewardInfo;

public interface DailyRewardService {

  DailyRewardInfo add(
      int gameId,
      int gem,
      Short level,
      int gold,
      int xp,
      long avatarId,
      long vipPeriodTime,
      Boolean addHami);


  DailyRewardInfo update(
      int id,
      int gameId,
      int gem,
      Short level,
      int gold,
      int xp,
      long avatarId,
      long vipPeriodTime,
      Boolean addHami);


  DailyRewardInfo findById(int id);

  DailyRewardInfo findBy(String gamePackageName, String env);

  void delete(int id);

}
