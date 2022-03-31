package com.mirbozorgi.core.repository.docuemnt.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mirbozorgi.core.repository.docuemnt.CustomMongoRepository;
import com.mirbozorgi.core.repository.docuemnt.SocialNetWorksFollowRewardRepository;
import com.mirbozorgi.core.document.SocialNetWorksFollowReward;
import com.mirbozorgi.core.domain.SocialNetWorkFollowData;
import java.util.HashMap;
import java.util.Map;
import mirbozorgi.base.domain.user.WalletChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class SocialNetWorksFollowRewardRepositoryImpl implements
    SocialNetWorksFollowRewardRepository {

  @Autowired
  private CustomMongoRepository repository;

  @Override
  public SocialNetWorksFollowReward update(
      String url,
      WalletChange walletChange,
      String nameOfSocialNet,
      int gameId,
      String gamePackageName,
      String env) {
    SocialNetWorksFollowReward byGameId = findByGameId(gameId);
    if (byGameId == null) {

      Map<String, SocialNetWorkFollowData> rewards = new HashMap<>();
      rewards.put(nameOfSocialNet, new SocialNetWorkFollowData(walletChange, url));
      return repository.add(new SocialNetWorksFollowReward(
          gameId,
          gamePackageName,
          env,
          rewards
      ));
    }

    Update update = new Update();
    update.set("rewards." + nameOfSocialNet, new SocialNetWorkFollowData(walletChange, url));

    repository.update(SocialNetWorksFollowReward.class, update,
        Criteria.where("gameId").is(gameId));

    return findByGameId(gameId);
  }

  @Override
  public Map<String, SocialNetWorkFollowData> getAll(
      String gamePackageName,
      String env) {
    SocialNetWorksFollowReward socialNetWorksFollowReward = repository.fetchFirst(
        SocialNetWorksFollowReward.class,
        new String[]{
            "gameId",
            "env",
            "gamePackageName",
            "rewards"
        },
        where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
    );
    if (socialNetWorksFollowReward == null) {
      return new HashMap<>();
    }
    return socialNetWorksFollowReward.getRewards();
  }


  ////////////////////////////////////////////////
  ////////////private///////////////////////////////
  ///////////////////////////////////////////////
  private SocialNetWorksFollowReward findByGameId(int gameId) {
    return repository.fetchFirst(
        SocialNetWorksFollowReward.class,
        new String[]{
            "gameId",
            "env",
            "gamePackageName",
            "rewards"
        },
        where("gameId").is(gameId)
    );
  }
}
