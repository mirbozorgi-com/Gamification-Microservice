package com.mirbozorgi.core.repository.document.impl;

import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.repository.document.MatchRepository;
import com.mirbozorgi.core.entity.Match;
import java.util.ArrayList;
import java.util.List;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class MatchRepositoryImpl implements MatchRepository {

  @Autowired
  private CustomMongoRepository repository;

  @Override
  public Match save(Match match) {
    return repository.add(match);
  }

  @Override
  public Match findById(String id) {
    return repository.fetchFirst(Match.class, new ObjectId(id));

  }

  @Override
  public void delete(Match match) {
    repository.delete(match);

  }

  @Override
  public List<Match> findAll(
      String gamePackageName,
      String env,
      String marketName,
      String name,
      long nowTime,
      boolean active) {

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

    if (!StringUtils.isEmpty(name)) {
      where.add(Criteria.where("name").is(name));
    }

    if (!StringUtils.isEmpty(env)) {
      where.add(Criteria.where("env").is(env));
    }

    if (!StringUtils.isEmpty(gamePackageName)) {
      where.add(Criteria.where("gamePackageName").is(gamePackageName));
    }

    if (!StringUtils.isEmpty(marketName)) {
      where.add(Criteria.where("marketName").is(marketName));
    }
    if (active) {
      where.add(Criteria.where("startTime").lte(nowTime)
          .and("endTime").gte(nowTime));
    }
    return repository.fetch(
        Match.class,
        new String[]{
            "gamePackageName",
            "env",
            "marketName",
            "name",
            "startTime",
            "endTime",
            "maxScorePerUpdate",
            "minScorePerUpdate",
            "minSecondBetweenTwoUpdatingScore",
            "maxSecondBetweenTwoUpdatingScore",
            "limitErrorForUpdateRequestPerPeriod",
            "maxPlayerInMatch",
            "minPlayerInMatch",
            "minClientVersion",
            "maxClientVersion",
            "reward",
            "config",
            "maxXpForJoinOffline",
            "minXpForJoinOffline",
            "gemCost",
            "goldCost",
            "gemWin",
            "goldWin",
            "winXpLooser",
            "winXpWinner",
            "winnerXpVipCoefficient",
            "looserXpVipCoefficient",
            "gemBonus",
            "goldBonus",
            "xpBonus"},
        null,
        100,
        where.toArray(new CriteriaDefinition[where.size()]));


  }


  @Override
  public Match update(String gamePackageName,
      String env,
      String marketName,
      String name,
      long startTime,
      long endTime,
      long maxScorePerUpdate,
      long minScorePerUpdate,
      long minSecondBetweenTwoUpdatingScore,
      long maxSecondBetweenTwoUpdatingScore,
      long limitErrorForUpdateRequestPerPeriod,
      int maxPlayerInMatch,
      int minPlayerInMatch,
      int minClientVersion,
      int maxClientVersion,
      Object reward,
      Object config,
      int maxXpForJoinOffline,
      int minXpForJoinOffline,
      int gemCost,
      int goldCost,
      int gemWin,
      int goldWin,
      int winXpLooser,
      int winXpWinner,
      double winnerXpVipCoefficient,
      double looserXpVipCoefficient,
      int gemBonus,
      int goldBonus,
      int xpBonus,
      String id) {
    Update update = new Update();
    update.set("name", name);
    update.set("startTime", startTime);
    update.set("endTime", endTime);
    update.set("maxScorePerUpdate", maxScorePerUpdate);
    update.set("minScorePerUpdate", minScorePerUpdate);
    update.set("minSecondBetweenTwoUpdatingScore", minSecondBetweenTwoUpdatingScore);
    update.set("maxSecondBetweenTwoUpdatingScore", maxSecondBetweenTwoUpdatingScore);
    update.set("limitErrorForUpdateRequestPerPeriod", limitErrorForUpdateRequestPerPeriod);
    update.set("maxPlayerInMatch", maxPlayerInMatch);
    update.set("minPlayerInMatch", minPlayerInMatch);
    update.set("minClientVersion", minClientVersion);
    update.set("maxClientVersion", maxClientVersion);
    update.set("reward", reward);
    update.set("config", config);
    update.set("maxXpForJoinOffline", maxXpForJoinOffline);
    update.set("minXpForJoinOffline", minXpForJoinOffline);
    update.set("gemCost", gemCost);
    update.set("goldCost", goldCost);
    update.set("gemWin", gemWin);
    update.set("goldWin", goldWin);
    update.set("winXpLooser", winXpLooser);
    update.set("winXpWinner", winXpWinner);
    update.set("winnerXpVipCoefficient", winnerXpVipCoefficient);
    update.set("looserXpVipCoefficient", looserXpVipCoefficient);
    update.set("gemBonus", gemBonus);
    update.set("goldBonus", goldBonus);
    update.set("xpBonus", xpBonus);

    repository.update(Match.class, update,
        Criteria.where("_id").is(id));

    return findBy(
        gamePackageName,
        env,
        marketName
    );
  }

  private Match findBy(String gamePackageName,
      String env,
      String marketName) {
    return repository.fetchFirst(Match.class,
        new String[]{
            "gamePackageName",
            "env",
            "marketName",
            "name",
            "startTime",
            "endTime",
            "maxScorePerUpdate",
            "minScorePerUpdate",
            "minSecondBetweenTwoUpdatingScore",
            "maxSecondBetweenTwoUpdatingScore",
            "limitErrorForUpdateRequestPerPeriod",
            "maxPlayerInMatch",
            "minPlayerInMatch",
            "minClientVersion",
            "maxClientVersion",
            "reward",
            "config",
            "maxXpForJoinOffline",
            "minXpForJoinOffline",
            "gemCost",
            "goldCost",
            "gemWin",
            "goldWin",
            "winXpLooser",
            "winXpWinner",
            "winnerXpVipCoefficient",
            "looserXpVipCoefficient",
            "gemBonus",
            "goldBonus",
            "xpBonus"},
        Criteria.where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
            .and("marketName").is(marketName));
  }

}
