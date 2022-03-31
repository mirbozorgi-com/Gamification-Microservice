package com.mirbozorgi.api.model;

import com.mirbozorgi.core.constant.ItemType;
import java.util.List;

public class ItemShopModel {

  private String name;
  private String config;
  private List<ItemType> type;
  private String fileUrl;
  private long vipPeriodTime;
  private int xp;
  private int gold;
  private int gem;
  private int level;
  private int chance;
  private List<Integer> avatarIds;

  public int getChance() {
    return chance;
  }

  public List<Integer> getAvatarIds() {
    return avatarIds;
  }

  public int getXp() {
    return xp;
  }

  public int getGold() {
    return gold;
  }

  public int getGem() {
    return gem;
  }

  public int getLevel() {
    return level;
  }

  public long getVipPeriodTime() {
    return vipPeriodTime;
  }

  public String getFileUrl() {
    return fileUrl;
  }

  public List<ItemType> getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public String getConfig() {
    return config;
  }
}
