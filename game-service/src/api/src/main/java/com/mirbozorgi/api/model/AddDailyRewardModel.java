package com.mirbozorgi.api.model;

public class AddDailyRewardModel {

  private int gameId;
  private int gem;
  private String level;
  private int gold;
  private int xp;
  private long avatarId;
  private long vipPeriodTime;
  private Boolean addHami;

  public int getGameId() {
    return gameId;
  }

  public int getGem() {
    return gem;
  }

  public String getLevel() {
    return level;
  }

  public int getGold() {
    return gold;
  }

  public int getXp() {
    return xp;
  }

  public long getAvatarId() {
    return avatarId;
  }

  public long getVipPeriodTime() {
    return vipPeriodTime;
  }

  public Boolean getAddHami() {
    return addHami;
  }
}
