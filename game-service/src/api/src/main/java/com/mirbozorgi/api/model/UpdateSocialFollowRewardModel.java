package com.mirbozorgi.api.model;

import java.util.List;

public class UpdateSocialFollowRewardModel {

  private String url;
  private int gem;
  private int gold;
  private Short level;
  private int xp;
  private boolean hamiAdded;
  private long addedVipPeriodTime;
  private List<Integer> avatarIds;
  private String nameOfSocialNet;
  private int gameId;

  public String getUrl() {
    return url;
  }

  public int getGem() {
    return gem;
  }

  public int getGold() {
    return gold;
  }

  public Short getLevel() {
    return level;
  }

  public int getXp() {
    return xp;
  }

  public boolean isHamiAdded() {
    return hamiAdded;
  }

  public long getAddedVipPeriodTime() {
    return addedVipPeriodTime;
  }

  public List<Integer> getAvatarIds() {
    return avatarIds;
  }

  public String getNameOfSocialNet() {
    return nameOfSocialNet;
  }

  public int getGameId() {
    return gameId;
  }
}
