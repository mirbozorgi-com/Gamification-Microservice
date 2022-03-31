package com.mirbozorgi.core.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirbozorgi.core.constant.ChallengeType;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "challenge")
public class Challenge {

  @Id
  private ObjectId id;

  private String name;

  private long startTime;

  private long endTime;

  private long maxScorePerUpdate;

  private long minScorePerUpdate;

  private long secondBetweenTwoUpdatingScore;

  private long limitForUpdateRequestPerPeriod;

  private Object reward;

  private ChallengeType type;

  private String gamePackageName;

  private String env;

  private String marketName;

  private Boolean allMarketInclude;

  private int minClientVersion;

  private int maxClientVersion;

  public Challenge(String name, long startTime, long endTime, long maxScorePerUpdate,
      long minScorePerUpdate, long secondBetweenTwoUpdatingScore,
      long limitForUpdateRequestPerPeriod,
      Object reward, ChallengeType type, String gamePackageName, String env,
      String marketName, Boolean allMarketInclude,
      int minClientVersion,
      int maxClientVersion) {
    this.name = name;
    this.startTime = startTime;
    this.endTime = endTime;
    this.maxScorePerUpdate = maxScorePerUpdate;
    this.minScorePerUpdate = minScorePerUpdate;
    this.secondBetweenTwoUpdatingScore = secondBetweenTwoUpdatingScore;
    this.limitForUpdateRequestPerPeriod = limitForUpdateRequestPerPeriod;
    this.reward = reward;
    this.type = type;
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.marketName = marketName;
    this.allMarketInclude = allMarketInclude;
    this.maxClientVersion = maxClientVersion;
    this.minClientVersion = minClientVersion;

  }

  public Challenge() {
  }


  public int getMinClientVersion() {
    return minClientVersion;
  }

  public int getMaxClientVersion() {
    return maxClientVersion;
  }

  public ObjectId getId() {
    return id;
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

  public long getSecondBetweenTwoUpdatingScore() {
    return secondBetweenTwoUpdatingScore;
  }

  public long getLimitForUpdateRequestPerPeriod() {
    return limitForUpdateRequestPerPeriod;
  }

  public Object getReward() {
    return reward;
  }

  public ChallengeType getType() {
    return type;
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

  public Boolean getAllMarketInclude() {
    return allMarketInclude;
  }


  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }
}
