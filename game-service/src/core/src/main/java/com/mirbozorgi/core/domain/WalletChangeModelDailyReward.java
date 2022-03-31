package com.mirbozorgi.core.domain;

import java.util.ArrayList;
import java.util.List;

public class WalletChangeModelDailyReward {

  private int dayNumber;
  private int gem;
  private int gold;
  private Short level;
  private int xp;
  private Boolean gemBuy;
  private boolean hamiAdded;
  private long vipTimeAdded;
  private List<Integer> avatarIds;

  public WalletChangeModelDailyReward() {
    avatarIds = new ArrayList<>();
  }

  public WalletChangeModelDailyReward(int dayNumber, int gem, int gold, Short level, int xp,
      Boolean gemBuy, boolean hamiAdded, long vipTimeAdded,
      List<Integer> avatarIds) {
    this.dayNumber = dayNumber;
    this.gem = gem;
    this.gold = gold;
    this.level = level;
    this.xp = xp;
    this.gemBuy = gemBuy;
    this.hamiAdded = hamiAdded;
    this.vipTimeAdded = vipTimeAdded;
    this.avatarIds = avatarIds;
  }

  public void setDayNumber(int dayNumber) {
    this.dayNumber = dayNumber;
  }

  public int getDayNumber() {
    return dayNumber;
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

  public Boolean getGemBuy() {
    return gemBuy;
  }

  public boolean isHamiAdded() {
    return hamiAdded;
  }

  public long getVipTimeAdded() {
    return vipTimeAdded;
  }

  public List<Integer> getAvatarIds() {
    return avatarIds;
  }
}
