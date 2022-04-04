package com.mirbozorgi.core.repository.document.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mirbozorgi.core.domain.Playerarsalancore;
import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.repository.document.PlayerarsalancoreRepository;
import com.mirbozorgi.core.entity.PlayerScore;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerarsalancoreRepositoryImpl implements PlayerarsalancoreRepository {

  @Autowired
  private CustomMongoRepository repository;


  @Override
  public Playerarsalancore add(
      String gamePackageName,
      String marketName,
      String env,
      String challengeId,
      String userUuId,
      Playerarsalancore playerarsalancore) {
    gamePackageName = fix(gamePackageName);
    Playerarsalancore founded = get(
        gamePackageName,
        marketName,
        env,
        challengeId,
        userUuId
    );

    if (founded == null) {
      founded = playerarsalancore;
      Map<String, Playerarsalancore> step1 = new HashMap<>();
      Map<String, Map<String, Playerarsalancore>> step2 = new HashMap<>();
      Map<String, Map<String, Map<String, Playerarsalancore>>> step3 = new HashMap<>();
      Map<String, Map<String, Map<String, Map<String, Playerarsalancore>>>> playerarsalancoreFinal = new HashMap<>();

      step1.put(challengeId, founded);
      step2.put(marketName, step1);
      step3.put(env, step2);
      playerarsalancoreFinal.put(gamePackageName, step3);

      repository.add(new PlayerScore(
          userUuId,
          playerarsalancoreFinal
      ));
    }

    return founded;
  }

  @Override
  public Playerarsalancore get(
      String gamePackageName,
      String marketName,
      String env,
      String challengeId,
      String userUuId) {
    gamePackageName = fix(gamePackageName);
    String keyForGame = String
        .format("playerarsalancore.%s.%s.%s.%s", gamePackageName, env, marketName, challengeId);

    PlayerScore founded = repository.fetchFirst(
        PlayerScore.class,
        new String[]{
            "userUuId",
            keyForGame
        },
        where("userUuId").is(userUuId)
            .and(keyForGame).exists(true));

    if (founded == null) {
      return null;
    }

    return founded.getPlayerarsalancore()
        .get(gamePackageName)
        .get(env)
        .get(marketName)
        .get(challengeId);
  }

  @Override
  public int incarsalancore(
      String gamePackageName,
      String marketName,
      String env,
      String challengeId,
      String userUuId,
      int score,
      long nowTime
  ) {
    gamePackageName = fix(gamePackageName);
    String keyForGame = String
        .format("playerarsalancore.%s.%s.%s.%s", gamePackageName, env, marketName, challengeId);
    Update update = new Update();

    update.inc(keyForGame + ".score", score);

    update.set(
        keyForGame + ".lastUpdateScoreDate", nowTime);

    repository.update(
        PlayerScore.class,
        update,
        Criteria.where("userUuId").is(userUuId)
    );

    return get(gamePackageName, marketName, env, challengeId, userUuId).getScore();
  }

  private String fix(String packageName) {
    return packageName.replace(".", "_");
  }

}
