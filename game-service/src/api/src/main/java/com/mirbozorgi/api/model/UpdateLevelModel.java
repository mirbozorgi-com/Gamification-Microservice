package com.mirbozorgi.api.model;

public class UpdateLevelModel {

  private String levelName;
  private int id;
  private int minXp;
  private int maxXp;
  private int levelNumber;

  public int getLevelNumber() {
    return levelNumber;
  }

  public String getLevelName() {
    return levelName;
  }

  public int getId() {
    return id;
  }

  public int getMinXp() {
    return minXp;
  }

  public int getMaxXp() {
    return maxXp;
  }
}
