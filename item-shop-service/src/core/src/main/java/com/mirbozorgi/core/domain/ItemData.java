package com.mirbozorgi.core.domain;

import com.mirbozorgi.core.constant.ItemType;
import java.util.ArrayList;
import java.util.List;

public class ItemData {

  private Object config;

  //get list because of bundle items
  private List<ItemType> type;

  private String fileUrl;

  private long vipPeriodTime;

  private int gold;

  private int gem;

  private Short level;

  private int xp;

  private int chance;

  private List<Integer> avatarPurchaseIds;

  public ItemData(
      Object config,
      List<ItemType> type,
      long vipPeriodTime,
      String fileUrl,
      int gold,
      int gem,
      Short level,
      int xp,
      int chance,
      List<Integer> avatarPurchaseIds) {
    this.config = config;
    this.type = type;
    this.fileUrl = fileUrl;
    this.vipPeriodTime = vipPeriodTime;
    this.gold = gold;
    this.gem = gem;
    this.level = level;
    this.xp = xp;
    this.chance = chance;
    this.avatarPurchaseIds = avatarPurchaseIds;
  }

  public ItemData() {
    avatarPurchaseIds = new ArrayList<>();
    type = new ArrayList<>();

  }

  public int getChance() {
    return chance;
  }

  public int getGold() {
    return gold;
  }

  public int getGem() {
    return gem;
  }

  public Short getLevel() {
    return level;
  }

  public int getXp() {
    return xp;
  }

  public List<Integer> getAvatarPurchaseIds() {
    return avatarPurchaseIds;
  }

  public String getFileUrl() {
    return fileUrl;
  }

  public long getVipPeriodTime() {
    return vipPeriodTime;
  }

  public Object getConfig() {
    return config;
  }

  public List<ItemType> getType() {
    return type;
  }
}
