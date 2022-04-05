package com.mirbozorgi.core.repository.document.impl;

import com.mirbozorgi.core.constant.ChallengeType;
import com.mirbozorgi.core.document.Challenge;
import com.mirbozorgi.core.repository.document.ChallengeRepository;
import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class ChallengeRepositoryImpl implements ChallengeRepository {

  @Autowired
  private CustomMongoRepository repository;


  @Override
  public Challenge save(Challenge challenge) {
    return repository.add(challenge);
  }

  @Override
  public Challenge findById(String id) {
    return repository.fetchFirst(Challenge.class, new ObjectId(id));
  }

  @Override
  public Challenge update(
      String id,
      String name,
      long startTime,
      long endTime,
      long maxScorePerUpdate,
      long minScorePerUpdate,
      long secondBetweenTwoUpdatingScore,
      long limitForUpdateRequestPerPeriod,
      Object reward,
      ChallengeType type,
      String gamePackageName,
      String env,
      String marketName,
      Boolean allMarketInclude,
      int minClientVersion,
      int maxClientVersion) {
    Update update = new Update();
    update.set("startTime", startTime);
    update.set("endTime", endTime);
    update.set("name", name);
    update.set("maxScorePerUpdate", maxScorePerUpdate);
    update.set("minScorePerUpdate", minScorePerUpdate);
    update.set("secondBetweenTwoUpdatingScore", secondBetweenTwoUpdatingScore);
    update.set("limitForUpdateRequestPerPeriod", limitForUpdateRequestPerPeriod);
    update.set("reward", reward);
    update.set("gamePackageName", gamePackageName);
    update.set("type", type);
    update.set("env", env);
    update.set("marketName", marketName);
    update.set("minClientVersion", minClientVersion);
    update.set("maxClientVersion", maxClientVersion);
    update.set("allMarketInclude", allMarketInclude);

    repository.update(Challenge.class, update, id);
    return findById(id);
  }

  @Override
  public Challenge findBy(String gamePackageName, String env, String marketName, String name) {
    return repository.fetchFirst(
        Challenge.class,
        new String[]{"name",
            "startTime",
            "endTime",
            "maxScorePerUpdate",
            "minScorePerUpdate",
            "secondBetweenTwoUpdatingScore",
            "limitForUpdateRequestPerPeriod",
            "reward",
            "type",
            "gamePackageName",
            "env",
            "marketName",
            "allMarketInclude"},
        Criteria.where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
            .and("name").is(name)
            .and("marketName").is(marketName)

    );


  }

  @Override
  public void delete(Challenge challenge) {
    repository.delete(challenge);
  }

  @Override
  public List<Challenge> findAll(
      String gamePackageName,
      String env,
      String marketName,
      String name,
      Boolean active,
      long nowTime) {

    List<Challenge> activeChallenges = new ArrayList<>();

    List<Challenge> allChallenges = new ArrayList<>();

    ArrayList<CriteriaDefinition> where = new ArrayList<>();

    if (name == null) {
      name = "";
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

    if (!name.isEmpty()) {
      where.add(Criteria.where("name").is(name));
    }

    if (!env.isEmpty()) {
      where.add(Criteria.where("env").is(env));
    }

    if (!gamePackageName.isEmpty()) {
      where.add(Criteria.where("gamePackageName").is(gamePackageName));
    }

    if (!marketName.isEmpty()) {
      where.add(Criteria.where("marketName").is(marketName));
    }

    if (active != null) {
      where.add(Criteria.where("startTime").lte(nowTime)
          .and("endTime").gte(nowTime));
      activeChallenges = repository.fetch(
          Challenge.class,
          new String[]{
              "name",
              "startTime",
              "endTime",
              "maxScorePerUpdate",
              "minScorePerUpdate",
              "secondBetweenTwoUpdatingScore",
              "limitForUpdateRequestPerPeriod",
              "reward",
              "type",
              "gamePackageName",
              "env",
              "marketName",
              "allMarketInclude"},
          null,
          100,
          where.toArray(new CriteriaDefinition[where.size()]));

      if (active) {
        return activeChallenges;
      } else {
        where.remove(where.size() - 1);
        allChallenges = repository.fetch(
            Challenge.class,
            new String[]{
                "name",
                "startTime",
                "endTime",
                "maxScorePerUpdate",
                "minScorePerUpdate",
                "secondBetweenTwoUpdatingScore",
                "limitForUpdateRequestPerPeriod",
                "reward",
                "type",
                "gamePackageName",
                "env",
                "marketName",
                "allMarketInclude"},
            null,
            100,
            where.toArray(new CriteriaDefinition[where.size()]));

        for (Challenge activeChallenge : activeChallenges) {
          allChallenges.removeIf(e -> e.getStringId().equals(activeChallenge.getStringId()));
        }

        return allChallenges;
      }


    }
    return repository.fetch(
        Challenge.class,
        new String[]{
            "name",
            "startTime",
            "endTime",
            "maxScorePerUpdate",
            "minScorePerUpdate",
            "secondBetweenTwoUpdatingScore",
            "limitForUpdateRequestPerPeriod",
            "reward",
            "type",
            "gamePackageName",
            "env",
            "marketName",
            "allMarketInclude"},
        null,
        100,
        where.toArray(new CriteriaDefinition[where.size()]));

  }

  @Override
  public boolean isIncludeAllMarket(String id) {
    return repository.fetchFirst(Challenge.class, new ObjectId(id)).getAllMarketInclude();
  }
}
