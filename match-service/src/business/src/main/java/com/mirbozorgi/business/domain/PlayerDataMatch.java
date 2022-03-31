package com.mirbozorgi.business.domain;

import java.util.List;

public class PlayerDataMatch {

  private String userUuId;

  private String username;

  private int currentLevel;

  private List<Integer> currentAvatarIds;

  public PlayerDataMatch(String userUuId, String username, int currentLevel,
      List<Integer> currentAvatarIds) {
    this.userUuId = userUuId;
    this.username = username;
    this.currentLevel = currentLevel;
    this.currentAvatarIds = currentAvatarIds;
  }

  public String getUserUuId() {
    return userUuId;
  }

  public String getUsername() {
    return username;
  }

  public int getCurrentLevel() {
    return currentLevel;
  }

  public List<Integer> getCurrentAvatarIds() {
    return currentAvatarIds;
  }
}
