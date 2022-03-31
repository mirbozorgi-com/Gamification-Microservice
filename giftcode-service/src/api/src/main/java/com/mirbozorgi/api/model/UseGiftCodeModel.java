package com.mirbozorgi.api.model;

import java.util.List;

public class UseGiftCodeModel {

  private String code;
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

  public String getCode() {
    return code;
  }

}
