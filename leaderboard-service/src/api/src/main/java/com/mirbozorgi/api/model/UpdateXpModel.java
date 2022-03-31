package com.mirbozorgi.api.model;

import java.util.List;

public class UpdateXpModel {

  private int xp;
  private int xpGlobal;
  private int level;
  private String username;
  private List<Integer> avatarIds;
  private long endVipTime;

  public int getXpGlobal() {
    return xpGlobal;
  }

  public long getEndVipTime() {
    return endVipTime;
  }


  public int getLevel() {
    return level;
  }

  public List<Integer> getAvatarIds() {
    return avatarIds;
  }

  public int getXp() {
    return xp;
  }

  public String getUsername() {
    return username;
  }
}
