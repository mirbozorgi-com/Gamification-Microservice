package com.mirbozorgi.core.repository.docuemnt.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mirbozorgi.core.repository.docuemnt.CustomMongoRepository;
import com.mirbozorgi.core.repository.docuemnt.DailyRewardContinuesRepository;
import com.mirbozorgi.core.document.DailyRewardContinues;
import com.mirbozorgi.core.domain.WalletChangeModelDailyReward;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class DailyRewardContinuesRepositoryImpl implements DailyRewardContinuesRepository {

  @Autowired
  private CustomMongoRepository repository;

  @Override
  public DailyRewardContinues update(
      List<WalletChangeModelDailyReward> walletChangeModels,
      String name,
      int gameId,
      String gamePackageName,
      String env) {
    DailyRewardContinues byGameId = findByGameId(gameId);
    if (byGameId == null) {

      Map<String, List<WalletChangeModelDailyReward>> map = new HashMap<>();
      map.put(name, walletChangeModels);
      return repository.add(new DailyRewardContinues(
          gameId,
          gamePackageName,
          env,
          map
      ));
    }

    Update update = new Update();
    update.set("rewardFlows." + name, walletChangeModels);

    repository.update(DailyRewardContinues.class, update,
        Criteria.where("gameId").is(gameId));

    return findByGameId(gameId);
  }

  @Override
  public List<WalletChangeModelDailyReward> getAllDailyFlows(
      String gamePackageName,
      String env,
      String name) {
    return repository.fetchFirst(
        DailyRewardContinues.class,
        new String[]{
            "gameId",
            "env",
            "gamePackageName",
            "rewardFlows"
        },
        where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
            .and("rewardFlows." + name).exists(true)
    ).getRewardFlows().get(name);

  }


  ////////////////////////////////////////////////
  ////////////private///////////////////////////////
  ///////////////////////////////////////////////
  private DailyRewardContinues findByGameId(int gameId) {
    return repository.fetchFirst(
        DailyRewardContinues.class,
        new String[]{
            "gameId",
            "env",
            "gamePackageName",
            "rewardFlows"
        },
        where("gameId").is(gameId)
    );

  }
}
