package com.mirbozorgi.api.model;

import java.util.List;

public class AddSeeAdvertisementModel {

  private String username;
  private String thirdPartPackageName;
  private List<Integer> currentAvatarIds;
  private int currentLevel;
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

  public String getThirdPartPackageName() {
    return thirdPartPackageName;
  }
}
