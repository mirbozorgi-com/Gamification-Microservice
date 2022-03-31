package com.mirbozorgi.api.model;

import java.util.List;

public class WalletChangeModel {

  private int gem;
  private int gold;
  private Short level;
  private int xp;
  private List<Integer> currentAvatarIds;
  private int currentLevel;
  private Boolean gemBuy;
  private long endVipTime;

  public long getEndVipTime() {
    return endVipTime;
  }

  public Boolean getGemBuy() {
    return gemBuy;
  }

  public int getCurrentLevel() {
    return currentLevel;
  }

  public List<Integer> getCurrentAvatarIds() {
    return currentAvatarIds;
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

  public Integer getXp() {
    return xp;
  }

}
