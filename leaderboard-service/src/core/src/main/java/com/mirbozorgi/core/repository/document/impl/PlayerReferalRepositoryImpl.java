package com.mirbozorgi.core.repository.document.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mirbozorgi.core.domain.PlayerRefralData;
import com.mirbozorgi.core.entity.PlayerRefral;
import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.repository.document.PlayerRefralRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerReferalRepositoryImpl implements PlayerRefralRepository {

  @Autowired
  private CustomMongoRepository repository;

  @Override
  public void upsert(
      String uuid,
      String gamePackageName,
      String env,
      String marketName,
      PlayerRefralData playerRefralData) {
    PlayerRefral founded = find(uuid, gamePackageName, env, marketName);
    gamePackageName = fix(gamePackageName);
    String keyForGame = String
        .format("playerRefrals.%s.%s.%s", gamePackageName, env, marketName);

    if (founded == null) {
      Map<String, Map<String, Map<String, PlayerRefralData>>> playerRefrals = new HashMap<>();
      Map<String, Map<String, PlayerRefralData>> step2 = new HashMap<>();
      Map<String, PlayerRefralData> step1 = new HashMap<>();

      step1.put(marketName, playerRefralData);
      step2.put(env, step1);
      playerRefrals.put(gamePackageName, step2);
      repository.add(new PlayerRefral(uuid, playerRefrals));
      return;
    }
    Update update = new Update();

    update.inc(
        keyForGame + ".quantity", 1);
    update.set(
        keyForGame + ".avatarActiveIds", playerRefralData.getAvatarActiveIds());
    update.set(
        keyForGame + ".username", playerRefralData.getUsername());
    update.set(
        keyForGame + ".level", playerRefralData.getLevel());
    update.set(
        keyForGame + ".endVipTime", playerRefralData.getEndVipTime());

    repository.update(
        PlayerRefral.class,
        update,
        Criteria.where("userUuId").is(uuid)
    );

  }

  @Override
  public PlayerRefral find(
      String uuid,
      String gamepackageName,
      String env,
      String marketName) {
    gamepackageName = fix(gamepackageName);
    String keyForGame = String
        .format("playerRefrals.%s.%s.%s", gamepackageName, env, marketName);

    PlayerRefral founded = repository.fetchFirst(
        PlayerRefral.class,
        new String[]{
            "userUuId",
            keyForGame
        },
        where("userUuId").is(uuid));
    if (founded == null) {
      return null;
    }
    return founded;
  }

  @Override
  public void reset(
      String gamePackageName,
      String env,
      String marketName) {
    gamePackageName = fix(gamePackageName);
    String keyForGame = String
        .format("playerRefrals.%s.%s.%s", gamePackageName, env, marketName);

    repository.deleteDocument(PlayerRefral.class,
        Criteria.where(keyForGame).exists(true));

  }

  private String fix(String packageName) {
    return packageName.replace(".", "_");
  }


}
