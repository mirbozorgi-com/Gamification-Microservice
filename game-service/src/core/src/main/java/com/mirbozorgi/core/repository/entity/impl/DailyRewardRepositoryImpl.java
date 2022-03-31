package com.mirbozorgi.core.repository.entity.impl;

import com.mirbozorgi.core.entity.DailyReward;
import com.mirbozorgi.core.repository.entity.DailyRewardRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DailyRewardRepositoryImpl extends CustomRepository implements DailyRewardRepository {

  @Override
  public DailyReward add(DailyReward dailyReward) {
    return save(DailyReward.class, dailyReward);
  }

  @Override
  public DailyReward findById(int id) {
    return findById(DailyReward.class, id);
  }

  @Override
  public DailyReward findBy(String gamePackageName, String env) {
    return findQueryWrapper(
        entityManager
            .createQuery(
                "select s from DailyReward s where s.game.packageName = :gamePackageName and s.game.env=:env",
                DailyReward.class)
            .setParameter("env", env)
            .setParameter("gamePackageName", gamePackageName)
    );
  }

  @Override
  public void delete(int id) {
    DailyReward byId = findById(id);
    delete(DailyReward.class, byId);
  }
}
