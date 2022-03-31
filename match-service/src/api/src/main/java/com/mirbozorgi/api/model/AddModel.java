package com.mirbozorgi.api.model;

public class AddModel {


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
  private String reward;
  private String config;
  private int maxXpForOffline;
  private int minXpForOffline;
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

  public double getWinnerXpVipCoefficient() {
    return winnerXpVipCoefficient;
  }

  public double getLooserXpVipCoefficient() {
    return looserXpVipCoefficient;
  }

  public int getMaxXpForOffline() {
    return maxXpForOffline;
  }

  public int getMinXpForOffline() {
    return minXpForOffline;
  }

  public int getGemCost() {
    return gemCost;
  }

  public int getGoldCost() {
    return goldCost;
  }

  public int getGemWin() {
    return gemWin;
  }

  public int getGoldWin() {
    return goldWin;
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

  public String getReward() {
    return reward;
  }

  public String getConfig() {
    return config;
  }
}
