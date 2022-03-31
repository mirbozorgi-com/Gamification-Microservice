package com.mirbozorgi.core.repository.document.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.repository.document.PlayerGemBuyHistoryTimeRepository;
import com.mirbozorgi.core.entity.PlayerGemBuyHistoryTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerGemBuyHistoryTimeRepositoryImpl implements PlayerGemBuyHistoryTimeRepository {

  @Autowired
  private CustomMongoRepository repository;

  //for add every week first person and delete others
  @Override
  public PlayerGemBuyHistoryTime add(
      PlayerGemBuyHistoryTime playerGemBuyHistoryTime) {
    return repository.add(playerGemBuyHistoryTime);
  }

  //for add every week first person and delete others
  @Override
  public List<PlayerGemBuyHistoryTime> getLeaderBoard(
      int topNumber,
      String gamePackageName,
      String env,
      String marketName) {
    gamePackageName = fix(gamePackageName);
    String keyForGame = String
        .format("playerMostBuyGem.%s.%s.%s", gamePackageName, env, marketName);

    return repository.fetch(
        PlayerGemBuyHistoryTime.class,
        new String[]{keyForGame, "userUuId"},
        new Sort(new Order(Direction.DESC, keyForGame + ".date")),
        topNumber,
        Criteria.where(keyForGame).exists(true));
  }

  @Override
  public PlayerGemBuyHistoryTime get(
      String uuid,
      String gamePackageName,
      String env,
      String marketName) {
    gamePackageName = fix(gamePackageName);
    String keyForGame = String
        .format("playerGameScore.%s.%s.%s", gamePackageName, env, marketName);

    return repository.fetchFirst(
        PlayerGemBuyHistoryTime.class,
        new String[]{
            "userUuId",
            keyForGame
        },
        where("userUuId").is(uuid)
            .and(keyForGame).exists(true));
  }

  private String fix(String packageName) {
    return packageName.replace(".", "_");
  }

}
