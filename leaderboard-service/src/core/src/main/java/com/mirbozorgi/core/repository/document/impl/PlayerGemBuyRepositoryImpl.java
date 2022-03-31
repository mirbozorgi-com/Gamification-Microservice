package com.mirbozorgi.core.repository.document.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.domain.PlayerGemBuyData;
import com.mirbozorgi.core.entity.PlayerGemBuy;
import com.mirbozorgi.core.repository.document.PlayerGemBuyRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerGemBuyRepositoryImpl implements PlayerGemBuyRepository {

  @Autowired
  private CustomMongoRepository repository;

  @Override
  public void upsert(
      String uuid,
      String gamePackageName,
      String env,
      String marketName,
      PlayerGemBuyData playerGemBuyData) {
    PlayerGemBuy founded = find(uuid, gamePackageName, env, marketName);
    gamePackageName = fix(gamePackageName);
    String keyForGame = String
        .format("playerGaemBuy.%s.%s.%s", gamePackageName, env, marketName);

    if (founded == null) {
      Map<String, Map<String, Map<String, PlayerGemBuyData>>> playerGaemBuy = new HashMap<>();
      Map<String, Map<String, PlayerGemBuyData>> step2 = new HashMap<>();
      Map<String, PlayerGemBuyData> step1 = new HashMap<>();

      step1.put(marketName, playerGemBuyData);
      step2.put(env, step1);
      playerGaemBuy.put(gamePackageName, step2);
      repository.add(new PlayerGemBuy(uuid, playerGaemBuy));
      return;
    }
    Update update = new Update();

    update.inc(
        keyForGame + ".gem", playerGemBuyData.getGem());
    update.set(
        keyForGame + ".date", playerGemBuyData.getDate());
    update.set(
        keyForGame + ".level", playerGemBuyData.getLevel());
    update.set(
        keyForGame + ".username", playerGemBuyData.getUsername());

    update.set(
        keyForGame + ".avatarActiveIds", playerGemBuyData.getAvatarActiveIds());

    update.set(
        keyForGame + ".endVipTime", playerGemBuyData.getEndVipTime());

    repository.update(
        PlayerGemBuy.class,
        update,
        Criteria.where("userUuId").is(uuid)
    );


  }

  @Override
  public PlayerGemBuy find(
      String uuid,
      String gamepackageName,
      String env,
      String marketName) {
    gamepackageName = fix(gamepackageName);
    String keyForGame = String
        .format("playerGaemBuy.%s.%s.%s", gamepackageName, env, marketName);

    PlayerGemBuy founded = repository.fetchFirst(
        PlayerGemBuy.class,
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
        .format("playerGaemBuy.%s.%s.%s", gamePackageName, env, marketName);

    repository.deleteDocument(PlayerGemBuy.class,
        Criteria.where(keyForGame).exists(true));


  }

  private String fix(String packageName) {
    return packageName.replace(".", "_");
  }
}
