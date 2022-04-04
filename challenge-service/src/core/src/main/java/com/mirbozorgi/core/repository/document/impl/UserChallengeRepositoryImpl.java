package com.mirbozorgi.core.repository.document.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mirbozorgi.core.document.UserChallenge;
import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.repository.document.UserChallengeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class UserChallengeRepositoryImpl implements UserChallengeRepository {

  @Autowired
  private CustomMongoRepository repository;

  @Override
  public UserChallenge addOrUpdate(UserChallenge userChallenge) {

    if (get(userChallenge.getChallengeId(),
        userChallenge.getUserUuId(),
        userChallenge.getGamePackageName(),
        userChallenge.getEnv(),
        userChallenge.getMarketName()) != null) {
      return userChallenge;
    }

    return repository.add(userChallenge);
  }

  @Override
  public UserChallenge get(String challengeId, String userUuId, String gamePackageName,
      String env, String marketName) {

    return repository.fetchFirst(
        UserChallenge.class,
        new String[]{"challengeId",
            "userUuId",
            "name",
            "gamePackageName",
            "env",
            "marketName",
            "username",
            "score",
            "lastUpdateScoreTime",
            "cheatUpdateRequest",
            "claim",
            "banned",
            "endTime"},
        where("userUuId").is(userUuId)
            .and("challengeId").is(challengeId)
            .and("gamePackageName").is(gamePackageName)
            .and("marketName").is(marketName)
            .and("env").is(env)
    );
  }

  @Override
  public List<UserChallenge> getAll(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName,
      Boolean claim,
      Boolean active,
      long nowTime) {
    ArrayList<CriteriaDefinition> where = new ArrayList<>();

    if (challengeId == null) {
      challengeId = "";
    }

    if (userUuId == null) {
      userUuId = "";
    }

    if (env == null) {
      env = "";
    }

    if (gamePackageName == null) {
      gamePackageName = "";
    }

    if (marketName == null) {
      marketName = "";
    }

    if (claim != null) {
      where.add(where("claim").is(claim));
    }

    if (active != null) {

      if (active) {
        where.add(where("endTime").gte(nowTime));
      } else {
        where.add(where("endTime").lt(nowTime));

      }
    }

    if (!challengeId.isEmpty()) {
      where.add(where("challengeId").is(challengeId));
    }

    if (!userUuId.isEmpty()) {
      where.add(where("userUuId").is(userUuId));
    }

    if (!gamePackageName.isEmpty()) {
      where.add(where("gamePackageName").is(gamePackageName));
    }

    if (!env.isEmpty()) {
      where.add(where("env").is(env));
    }

    if (!marketName.isEmpty()) {
      where.add(where("marketName").is(marketName));
    }

    return repository.fetch(
        UserChallenge.class,
        new String[]{
            "challengeId",
            "userUuId",
            "name",
            "gamePackageName",
            "env",
            "marketName",
            "username",
            "score",
            "lastUpdateScoreTime",
            "cheatUpdateRequest",
            "claim",
            "banned",
            "endTime"},
        null,
        100,
        where.toArray(new CriteriaDefinition[where.size()])
    );

  }

  @Override
  public int incScore(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName,
      int score,
      long nowTime) {

    Update update = new Update();
    update.set("lastUpdateScoreTime", nowTime);
    update.inc("score", score);

    repository.update(
        UserChallenge.class,
        update,
        Criteria.where("userUuId").is(userUuId)
            .and("challengeId").is(challengeId)
            .and("gamePackageName").is(gamePackageName)
            .and("marketName").is(marketName)
            .and("env").is(env));

    return get(
        challengeId,
        userUuId,
        gamePackageName,
        env,
        marketName).getScore();

  }

  @Override
  public void incCheatInt(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName) {

    Update update = new Update();
    update.inc("cheatUpdateRequest", 1);

    repository.update(
        UserChallenge.class,
        update,
        Criteria.where("userUuId").is(userUuId)
            .and("challengeId").is(challengeId)
            .and("gamePackageName").is(gamePackageName)
            .and("marketName").is(marketName)
            .and("env").is(env));

  }

  @Override
  public void updateBanned(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName,
      boolean banned) {

    repository.update(
        UserChallenge.class,
        new Update()
            .set("banned", banned),
        Criteria.where("userUuId").is(userUuId)
            .and("challengeId").is(challengeId)
            .and("gamePackageName").is(gamePackageName)
            .and("marketName").is(marketName)
            .and("env").is(env));
  }

  @Override
  public void updateClaimed(
      String challengeId,
      String userUuId,
      String gamePackageName,
      String env,
      String marketName,
      boolean claim) {
    repository.update(
        UserChallenge.class,
        new Update()
            .set("claim", claim),
        Criteria.where("userUuId").is(userUuId)
            .and("challengeId").is(challengeId)
            .and("gamePackageName").is(gamePackageName)
            .and("marketName").is(marketName)
            .and("env").is(env));
  }
}
