package com.mirbozorgi.api.model;

import java.util.List;

public class SpinModel {

  private String key;
  private String username;
  private int currentLevel;
  private List<Integer> currentAvatarIds;
  private long endVipTime;

  public long getEndVipTime() {
    return endVipTime;
  }

  public int getCurrentLevel() {
    return currentLevel;
  }

  public List<Integer> getCurrentAvatarIds() {
    return currentAvatarIds;
  }

  public String getUsername() {
    return username;
  }

  public String getKey() {
    return key;
  }
}
