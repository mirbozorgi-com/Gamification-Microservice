package com.mirbozorgi.api.model;

import com.mirbozorgi.core.constant.ItemType;
import java.util.List;

public class AddSpinModel {


  private String key;
  private String config;
  private List<ItemType> type;
  private String fileUrl;
  private long vipPeriodTime;
  private int xp;
  private int gold;
  private int gem;
  private Short level;
  private List<Integer> avatarIds;
  private int chence;

  public String getKey() {
    return key;
  }

  public String getConfig() {
    return config;
  }

  public List<ItemType> getType() {
    return type;
  }

  public String getFileUrl() {
    return fileUrl;
  }

  public long getVipPeriodTime() {
    return vipPeriodTime;
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

  public Short getLevel() {
    return level;
  }

  public List<Integer> getAvatarIds() {
    return avatarIds;
  }

  public int getChence() {
    return chence;
  }
}
