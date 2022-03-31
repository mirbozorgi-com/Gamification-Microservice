package com.mirbozorgi.core.repository.entity;

import com.mirbozorgi.core.entity.DailyReward;

public interface DailyRewardRepository {

  DailyReward add(DailyReward dailyReward);

  DailyReward findById(int id);

  DailyReward findBy(String gamePackageName, String env);

  void delete(int id);
}
