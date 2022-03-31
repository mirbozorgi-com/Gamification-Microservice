package com.mirbozorgi.business.domain;

import com.mirbozorgi.core.constant.ItemType;
import java.util.List;

public class ItemShopData {

  private String id;
  private String name;
  private String gamePackageName;
  private String env;
  private String market;
  private Object config;
  private List<ItemType> type;
  private String fileUrl;
  private long vipPeriodTime;
  private int xp;
  private int gold;
  private int gem;
  private Short level;
  private List<Integer> avatarPurchaseIds;


  public ItemShopData(String id, String name, String gamePackageName, String env,
      String market, Object config, List<ItemType> type, String fileUrl, long vipPeriodTime, int xp,
      int gold, int gem, Short level, List<Integer> avatarPurchaseIds) {
    this.id = id;
    this.name = name;
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.market = market;
    this.config = config;
    this.type = type;
    this.fileUrl = fileUrl;
    this.vipPeriodTime = vipPeriodTime;
    this.xp = xp;
    this.gold = gold;
    this.gem = gem;
    this.level = level;
    this.avatarPurchaseIds = avatarPurchaseIds;
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

  public List<Integer> getAvatarPurchaseIds() {
    return avatarPurchaseIds;
  }

  public String getFileUrl() {
    return fileUrl;
  }

  public long getVipPeriodTime() {
    return vipPeriodTime;
  }

  public List<ItemType> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getEnv() {
    return env;
  }

  public String getMarket() {
    return market;
  }

  public Object getConfig() {
    return config;
  }
}
