package com.mirbozorgi.core.repository.document.impl;

import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.repository.document.PlayerMatchRepository;
import com.mirbozorgi.core.domain.PlayerData;
import com.mirbozorgi.core.entity.PlayerMatch;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerMatchRepositoryImpl implements PlayerMatchRepository {


  @Autowired
  private CustomMongoRepository repository;


  @Override
  public PlayerMatch save(PlayerMatch playerMatch) {
    return repository.add(playerMatch);
  }

  @Override
  public PlayerMatch findByMatchIdAndEmptyPlace(String matchId) {
    return repository.fetchFirst(PlayerMatch.class,
        new String[]{
            "matchId",
            "players",
            "hasEmptyPlaceForPlayer",
            "playerUuIds",
            "playerMaxQuantity"},
        Criteria.where("matchId").is(matchId)
            .and("hasEmptyPlaceForPlayer").is(true));

  }

  @Override
  public PlayerMatch findById(String id) {
    return repository.fetchFirst(PlayerMatch.class, new ObjectId(id));
  }


  @Override
  public PlayerMatch join(
      String id,
      String userUuId,
      String username,
      long lastUpdateScoreTime,
      long cheatUpdateRequest,
      Boolean claim,
      Boolean banned,
      Object config) {

    Update update = new Update();
    update.push("players", new PlayerData(
        userUuId,
        username,
        lastUpdateScoreTime,
        cheatUpdateRequest,
        claim,
        banned,
        config
    ));

    update.push("playerUuIds", userUuId);

    repository.update(
        PlayerMatch.class,
        update,
        new ObjectId(id));
    return findById(id);
  }

  @Override
  public void updateEmptyPlaceForPlayer(Boolean hasEmpty, String id) {
    Update update = new Update();
    update.set("hasEmptyPlaceForPlayer", hasEmpty);
    repository.update(PlayerMatch.class, update, new ObjectId(id));


  }

  @Override
  public PlayerMatch update(
      String id,
      List<PlayerData> players,
      Boolean active) {
    Update update = new Update();
    update.set("active", active);
    update.set("players", players);
    repository.update(PlayerMatch.class, update, new ObjectId(id));

    return findById(id);
  }

  @Override
  public PlayerMatch finish(String id) {
    Update update = new Update();
    update.set("active", false);
    repository.update(PlayerMatch.class, update, new ObjectId(id));

    return findById(id);
  }
}
