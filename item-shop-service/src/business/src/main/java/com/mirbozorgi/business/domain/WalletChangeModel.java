package com.mirbozorgi.business.domain;

public class WalletChangeModel {

  private String uuid;
  private int gem;
  private int gold;
  private Short level;
  private int xp;
  private Integer avatarId;


  public WalletChangeModel(String uuid, int gem, int gold, Short level, int xp, Integer avatarId) {
    this.uuid = uuid;
    this.gem = gem;
    this.gold = gold;
    this.level = level;
    this.xp = xp;
    this.avatarId = avatarId;
  }

  public String getUuid() {
    return uuid;
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

  public Integer getAvatarId() {
    return avatarId;
  }
}
