package com.mirbozorgi.api.model;

import com.mirbozorgi.core.constant.ChallengeType;

public class AddModel {

  private String name;

  private long startTime;

  private long endTime;

  private long maxScorePerUpdate;

  private long minScorePerUpdate;

  private long secondBetweenTwoUpdatingScore;

  private long limitForUpdateRequestPerPeriod;

  private String reward;

  private ChallengeType type;

  private Boolean allMarketInclude;

  private int minClientVersion;

  private int maxClientVersion;

  public int getMinClientVersion() {
    return minClientVersion;
  }

  public int getMaxClientVersion() {
    return maxClientVersion;
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

  public String getReward() {
    return reward;
  }

  public ChallengeType getType() {
    return type;
  }

  public Boolean getAllMarketInclude() {
    return allMarketInclude;
  }
}
