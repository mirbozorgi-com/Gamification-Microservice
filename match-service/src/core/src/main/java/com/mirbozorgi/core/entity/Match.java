package com.mirbozorgi.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "match")
public class Match {

  @Id
  private ObjectId id;

  private String gamePackageName;

  private String env;

  private String marketName;

  private String name;

  private long startTime;

  private long endTime;

  private long maxScorePerUpdate;

  private long minScorePerUpdate;

  private long minSecondBetweenTwoUpdatingScore;

  private long maxSecondBetweenTwoUpdatingScore;

  private long limitErrorForUpdateRequestPerPeriod;

  private int maxPlayerInMatch;

  private int minPlayerInMatch;

  private int minClientVersion;

  private int maxClientVersion;

  private Object reward;

  private Object config;

  private int maxXpForJoinOffline;

  private int minXpForJoinOffline;

  private int gemCost;

  private int goldCost;

  private int gemWin;

  private int goldWin;

  private int winXpLooser;

  private int winXpWinner;

  private double winnerXpVipCoefficient;

  private double looserXpVipCoefficient;

  private int gemBonus;

  private int goldBonus;

  private int xpBonus;

  public Match() {
  }


  public Match(String gamePackageName, String env, String marketName, String name, long startTime,
      long endTime, long maxScorePerUpdate, long minScorePerUpdate,
      long minSecondBetweenTwoUpdatingScore, long maxSecondBetweenTwoUpdatingScore,
      long limitErrorForUpdateRequestPerPeriod, int maxPlayerInMatch, int minPlayerInMatch,
      int minClientVersion, int maxClientVersion, Object reward, Object config,
      int maxXpForJoinOffline, int minXpForJoinOffline, int gemCost, int goldCost, int gemWin,
      int goldWin, int winXpLooser, int winXpWinner, double winnerXpVipCoefficient,
      double looserXpVipCoefficient, int gemBonus, int goldBonus, int xpBonus) {
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.marketName = marketName;
    this.name = name;
    this.startTime = startTime;
    this.endTime = endTime;
    this.maxScorePerUpdate = maxScorePerUpdate;
    this.minScorePerUpdate = minScorePerUpdate;
    this.minSecondBetweenTwoUpdatingScore = minSecondBetweenTwoUpdatingScore;
    this.maxSecondBetweenTwoUpdatingScore = maxSecondBetweenTwoUpdatingScore;
    this.limitErrorForUpdateRequestPerPeriod = limitErrorForUpdateRequestPerPeriod;
    this.maxPlayerInMatch = maxPlayerInMatch;
    this.minPlayerInMatch = minPlayerInMatch;
    this.minClientVersion = minClientVersion;
    this.maxClientVersion = maxClientVersion;
    this.reward = reward;
    this.config = config;
    this.maxXpForJoinOffline = maxXpForJoinOffline;
    this.minXpForJoinOffline = minXpForJoinOffline;
    this.gemCost = gemCost;
    this.goldCost = goldCost;
    this.gemWin = gemWin;
    this.goldWin = goldWin;
    this.winXpLooser = winXpLooser;
    this.winXpWinner = winXpWinner;
    this.winnerXpVipCoefficient = winnerXpVipCoefficient;
    this.looserXpVipCoefficient = looserXpVipCoefficient;
    this.gemBonus = gemBonus;
    this.goldBonus = goldBonus;
    this.xpBonus = xpBonus;
  }


  public int getGemBonus() {
    return gemBonus;
  }

  public int getGoldBonus() {
    return goldBonus;
  }

  public int getXpBonus() {
    return xpBonus;
  }

  public int getWinXpLooser() {
    return winXpLooser;
  }

  public int getWinXpWinner() {
    return winXpWinner;
  }

  public int getGemWin() {
    return gemWin;
  }

  public int getGoldWin() {
    return goldWin;
  }

  public int getGemCost() {
    return gemCost;
  }

  public int getGoldCost() {
    return goldCost;
  }

  public Object getConfig() {
    return config;
  }

  public int getMaxXpForJoinOffline() {
    return maxXpForJoinOffline;
  }

  public int getMinXpForJoinOffline() {
    return minXpForJoinOffline;
  }

  public ObjectId getId() {
    return id;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getEnv() {
    return env;
  }

  public String getMarketName() {
    return marketName;
  }

  public String getName() {
    return name;
  }

  public long getStartTime() {
    return startTime;
  }

  public long getEndTime() {
    return endTime;
  }

  public long getMaxScorePerUpdate() {
    return maxScorePerUpdate;
  }

  public long getMinScorePerUpdate() {
    return minScorePerUpdate;
  }

  public long getMinSecondBetweenTwoUpdatingScore() {
    return minSecondBetweenTwoUpdatingScore;
  }

  public long getMaxSecondBetweenTwoUpdatingScore() {
    return maxSecondBetweenTwoUpdatingScore;
  }

  public long getLimitErrorForUpdateRequestPerPeriod() {
    return limitErrorForUpdateRequestPerPeriod;
  }

  public int getMaxPlayerInMatch() {
    return maxPlayerInMatch;
  }

  public int getMinPlayerInMatch() {
    return minPlayerInMatch;
  }

  public int getMinClientVersion() {
    return minClientVersion;
  }

  public int getMaxClientVersion() {
    return maxClientVersion;
  }

  public Object getReward() {
    return reward;
  }

  public double getWinnerXpVipCoefficient() {
    return winnerXpVipCoefficient;
  }

  public double getLooserXpVipCoefficient() {
    return looserXpVipCoefficient;
  }

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }
}
