package com.mirbozorgi.core.repository.document.impl;

import com.mirbozorgi.core.entity.PlayerGemBuy;
import com.mirbozorgi.core.entity.PlayerRefral;
import com.mirbozorgi.core.entity.PlayerXp;
import com.mirbozorgi.core.entity.PlayerXpWithTime;
import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.entity.PlayerScore;
import com.mirbozorgi.core.repository.document.LeaderBoardRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

@Repository
public class LeaderBoardRepositoryImpl implements LeaderBoardRepository {

  @Autowired
  private CustomMongoRepository repository;


  @Override
  public List<PlayerScore> byScore(
      int topNumber,
      String gamePackageName,
      String marketName,
      String env,
      String challengeId) {

    gamePackageName = fix(gamePackageName);
    String keyForGame = String
        .format("playerGameScore.%s.%s.%s.%s", gamePackageName, env, marketName, challengeId);

    return repository.fetch(
        PlayerScore.class,
        new String[]{keyForGame, "userUuId"},
        new Sort(new Order(Direction.DESC, keyForGame + ".score")),
        topNumber,
        Criteria.where(keyForGame).exists(true));
  }

  @Override
  public List<PlayerXp> byXp(
      int topNumber,
      String gamePackageName,
      String marketName,
      String env) {
    gamePackageName = fix(gamePackageName);
    String keyForGame = String
        .format("playerXpDatas.%s.%s.%s", gamePackageName, env, marketName);

    return repository.fetch(
        PlayerXp.class,
        new String[]{keyForGame, "userUuId"},
        new Sort(new Order(Direction.DESC, keyForGame + ".xp")),
        topNumber,
        Criteria.where(keyForGame).exists(true));
  }

  @Override
  public List<PlayerXpWithTime> byXpLastSevenDays(
      int topNumber,
      String gamePackageName,
      String marketName,
      String env) {
    gamePackageName = fix(gamePackageName);
    String keyForGame = String
        .format("playerXpDatas.%s.%s.%s", gamePackageName, env, marketName);

    return repository.fetch(
        PlayerXpWithTime.class,
        new String[]{keyForGame, "userUuId"},
        new Sort(new Order(Direction.DESC, keyForGame + ".xp")),
        topNumber,
        Criteria.where(keyForGame).exists(true));
  }

  @Override
  public List<PlayerRefral> byMostRefralLastSevenDays(
      int topNumber,
      String gamePackageName,
      String marketName,
      String env) {
    gamePackageName = fix(gamePackageName);
    String keyForGame = String
        .format("playerRefrals.%s.%s.%s", gamePackageName, env, marketName);
    return repository.fetch(
        PlayerRefral.class,
        new String[]{keyForGame, "userUuId"},
        new Sort(new Order(Direction.DESC, keyForGame + ".quantity")),
        topNumber,
        Criteria.where(keyForGame).exists(true));
  }

  @Override
  public List<PlayerGemBuy> theMostBuyerGem(
      int topNumber,
      String gamePackageName,
      String marketName,
      String env) {
    gamePackageName = fix(gamePackageName);
    String keyForGame = String
        .format("playerGaemBuy.%s.%s.%s", gamePackageName, env, marketName);

    return repository.fetch(
        PlayerGemBuy.class,
        new String[]{keyForGame, "userUuId"},
        new Sort(new Order(Direction.DESC, keyForGame + ".gem")),
        topNumber,
        Criteria.where(keyForGame).exists(true));
  }


  private String fix(String packageName) {
    return packageName.replace(".", "_");
  }

}
