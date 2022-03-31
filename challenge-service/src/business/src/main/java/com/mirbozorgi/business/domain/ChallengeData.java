package com.mirbozorgi.business.domain;

import com.mirbozorgi.business.service.impl.TimeServiceImpl;
import com.mirbozorgi.core.constant.ChallengeType;

public class ChallengeData {

  private String id;

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

  private boolean active;

  private String marketName;

  private Boolean allMarketInclude;

  private int minClientVersion;

  private int maxClientVersion;


  public ChallengeData(String id, String name, long startTime, long endTime, long maxScorePerUpdate,
      long minScorePerUpdate, long secondBetweenTwoUpdatingScore,
      long limitForUpdateRequestPerPeriod,
      Object reward, ChallengeType type, String gamePackageName, String env,
      String marketName, Boolean allMarketInclude, int minClientVersion,
      int maxClientVersion) {
    this.id = id;
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
    this.active = checkActive(startTime, endTime);
  }

  public ChallengeData() {
  }

  public int getMinClientVersion() {
    return minClientVersion;
  }

  public int getMaxClientVersion() {
    return maxClientVersion;
  }

  public String getId() {
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

  public boolean isActive() {
    return active;
  }

  private boolean checkActive(long startTime, long endTime) {
    boolean active = false;
    long now = new TimeServiceImpl().getNowUnixFromInstantClass();

    if (startTime <= now && endTime >= now) {
      active = true;
    }
    return active;


  }

}
