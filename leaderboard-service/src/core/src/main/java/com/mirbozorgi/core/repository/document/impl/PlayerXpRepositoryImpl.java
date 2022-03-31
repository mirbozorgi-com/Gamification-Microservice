package com.mirbozorgi.core.repository.document.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.domain.PlayerXpData;
import com.mirbozorgi.core.entity.PlayerXp;
import com.mirbozorgi.core.entity.PlayerXpWithTime;
import com.mirbozorgi.core.repository.document.PlayerXpRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerXpRepositoryImpl implements PlayerXpRepository {

  @Autowired
  private CustomMongoRepository repository;


  @Override
  public void upsert(
      String uuid,
      String gamepackageName,
      String env,
      String marketName,
      int xp,
      int level,
      long lastUpdate,
      String username,
      List<Integer> avatarActiveIds,
      long endVipTime) {
    PlayerXp playerXp = findXp(uuid, gamepackageName, env, marketName);

    gamepackageName = fix(gamepackageName);
    String keyForGame = String
        .format("playerXpDatas.%s.%s.%s", gamepackageName, env, marketName);

    if (playerXp == null) {
      Map<String, Map<String, Map<String, PlayerXpData>>> playerXpDatas = new HashMap<>();
      Map<String, Map<String, PlayerXpData>> step2 = new HashMap<>();
      Map<String, PlayerXpData> step1 = new HashMap<>();

      step1.put(marketName,
          new PlayerXpData(xp, lastUpdate, username, avatarActiveIds, level, endVipTime));
      step2.put(env, step1);
      playerXpDatas.put(gamepackageName, step2);

      PlayerXp updated = new PlayerXp(uuid, playerXpDatas);
      repository.add(updated);
      return;
    }
    Update update = new Update();

    update.set(keyForGame + ".xp", xp);
    update.set(keyForGame + ".lastUpdate", lastUpdate);
    update.set(keyForGame + ".username", username);
    update.set(keyForGame + ".avatarActiveIds", avatarActiveIds);
    update.set(keyForGame + ".level", level);
    update.set(keyForGame + ".endVipTime", endVipTime);

    repository.update(
        PlayerXp.class,
        update,
        Criteria.where("userUuId").is(uuid)
    );
  }

  @Override
  public PlayerXp findXpByUUID(String uuid) {
    return repository.fetchFirst(
        PlayerXp.class,
        new String[]{
            "userUuId"
        },
        where("userUuId").is(uuid));
  }

  @Override
  public PlayerXp findXp(
      String uuid,
      String gamepackageName,
      String env,
      String marketName) {
    gamepackageName = fix(gamepackageName);
    String keyForGame = String
        .format("playerXpDatas.%s.%s.%s", gamepackageName, env, marketName);

    PlayerXp founded = repository.fetchFirst(
        PlayerXp.class,
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
  //////////////////////----with time-------/////////////////////////////////

  @Override
  public PlayerXpWithTime findXpTime(
      String uuid,
      String gamepackageName,
      String env,
      String marketName) {
    gamepackageName = fix(gamepackageName);
    String keyForGame = String
        .format("playerXpDatas.%s.%s.%s", gamepackageName, env, marketName);

    PlayerXpWithTime founded = repository.fetchFirst(
        PlayerXpWithTime.class,
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
  public PlayerXpWithTime findXpTimeByUUID(String uuid) {
    return repository.fetchFirst(
        PlayerXpWithTime.class,
        new String[]{
            "userUuId"
        },
        where("userUuId").is(uuid));
  }

  @Override
  public void upsertWithTime(
      String uuid,
      String gamepackageName,
      String env,
      String marketName,
      int xp,
      int level,
      long lastUpdate,
      String username,
      List<Integer> avatarActiveIds,
      long endVipTime) {
    PlayerXpWithTime playerXpTime = findXpTime(uuid, gamepackageName, env, marketName);
    gamepackageName = fix(gamepackageName);
    String keyForGame = String
        .format("playerXpDatas.%s.%s.%s", gamepackageName, env, marketName);

    if (playerXpTime == null) {

      Map<String, Map<String, Map<String, PlayerXpData>>> playerXpDatas = new HashMap<>();
      Map<String, Map<String, PlayerXpData>> step2 = new HashMap<>();
      Map<String, PlayerXpData> step1 = new HashMap<>();

      step1.put(marketName,
          new PlayerXpData(xp, lastUpdate, username, avatarActiveIds, level, endVipTime));
      step2.put(env, step1);
      playerXpDatas.put(gamepackageName, step2);
      repository.add(new PlayerXpWithTime(uuid, playerXpDatas));
      return;
    }
    Update update = new Update();

    update.inc(keyForGame + ".xp", xp);
    update.set(keyForGame + ".lastUpdate", lastUpdate);
    update.set(keyForGame + ".username", username);
    update.set(keyForGame + ".avatarActiveIds", avatarActiveIds);
    update.set(keyForGame + ".level", level);
    update.set(keyForGame + ".endVipTime", endVipTime);
    repository.update(
        PlayerXpWithTime.class,
        update,
        Criteria.where("userUuId").is(uuid)
    );

  }

  @Override
  public void resetXpsWithTime(
      String gamepackageName,
      String env,
      String marketName) {
    gamepackageName = fix(gamepackageName);
    String keyForGame = String
        .format("playerXpDatas.%s.%s.%s", gamepackageName, env, marketName);
    repository.deleteDocument(PlayerXpWithTime.class,
        Criteria.where(keyForGame).exists(true));

  }

  private String fix(String packageName) {
    return packageName.replace(".", "_");
  }


}
