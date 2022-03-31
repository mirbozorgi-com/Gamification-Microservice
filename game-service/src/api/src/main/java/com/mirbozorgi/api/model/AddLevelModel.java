package com.mirbozorgi.api.model;

public class AddLevelModel {

  private String levelName;
  private int gameId;
  private int minXp;
  private int maxXp;
  private int levelNumber;

  public int getLevelNumber() {
    return levelNumber;
  }

  public String getLevelName() {
    return levelName;
  }

  public int getGameId() {
    return gameId;
  }

  public int getMinXp() {
    return minXp;
  }

  public int getMaxXp() {
    return maxXp;
  }
}
