package com.mirbozorgi.core.repository.document.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mirbozorgi.core.domain.LeaderBoardType;
import com.mirbozorgi.core.entity.LeaderBoardReward;
import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.repository.document.LeaderBoardRewardRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mirbozorgi.base.domain.user.WalletChange;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class LeaderBoardRewardRepositoryImpl implements LeaderBoardRewardRepository {

  @Autowired
  private CustomMongoRepository repository;

  @Override
  public LeaderBoardReward add(
      String gamePackageName,
      String env,
      String marketName,
      LeaderBoardType type,
      WalletChange walletChange) {

    if (!exist(gamePackageName, env, marketName)) {
      Map<LeaderBoardType, List<WalletChange>> rewards = new HashMap<>();
      List<WalletChange> rewardList = new ArrayList<>();
      rewardList.add(walletChange);
      rewards.put(type, rewardList);
      return repository.add(new LeaderBoardReward(
          gamePackageName,
          env,
          marketName,
          rewards
      ));
    }

    Update update = new Update();
    update.push("rewards." + type, walletChange);

    repository.update(LeaderBoardReward.class,
        update,
        where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
            .and("marketName").is(marketName)
    );
    return findBy(gamePackageName, env, marketName);
  }

  @Override
  public LeaderBoardReward update(
      String gamePackageName,
      String env,
      String marketName,
      LeaderBoardType type,
      List<WalletChange> walletChanges) {
    Update update = new Update();
    update.set("rewards." + type, walletChanges);

    repository.update(LeaderBoardReward.class,
        update,
        where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
            .and("marketName").is(marketName)
    );
    return findBy(gamePackageName, env, marketName);
  }

  @Override
  public void delete(String id) {

    LeaderBoardReward leaderBoardReward = repository
        .fetchFirst(LeaderBoardReward.class, new ObjectId(id));

    repository.delete(leaderBoardReward);
  }

  @Override
  public LeaderBoardReward findBy(
      String gamePackageName,
      String env,
      String marketName) {
    return repository.fetchFirst(LeaderBoardReward.class,
        new String[]{
            "rewards",
            "id",
            "env",
            "marketName",
            "gamePackageName"
        },
        where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
            .and("marketName").is(marketName));

  }

  //////////////////private///////////////////
  private boolean exist(
      String gamePackageName,
      String env,
      String marketName) {

    LeaderBoardReward leaderBoardReward = repository.fetchFirst(
        LeaderBoardReward.class,
        null,
        where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
            .and("marketName").is(marketName)

    );
    return leaderBoardReward != null;

  }
}
