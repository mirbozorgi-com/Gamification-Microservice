package com.mirbozorgi.api.model;

import java.util.List;

public class OfflineJoinModel {


  private String matchId;
  private String config;
  private String username;
  private int numberOfPlayerWanted;
  private int currentLevel;
  private List<Integer> currentAvatarIds;

  public String getUsername() {
    return username;
  }

  public int getCurrentLevel() {
    return currentLevel;
  }

  public List<Integer> getCurrentAvatarIds() {
    return currentAvatarIds;
  }

  public String getMatchId() {
    return matchId;
  }

  public String getConfig() {
    return config;
  }

  public int getNumberOfPlayerWanted() {
    return numberOfPlayerWanted;
  }
}
