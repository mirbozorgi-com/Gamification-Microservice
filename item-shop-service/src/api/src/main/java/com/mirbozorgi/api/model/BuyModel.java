package com.mirbozorgi.api.model;

import java.util.List;

public class BuyModel {

  private String username;
  private String skuName;
  private String marketToken;
  private int currentLevel;
  private List<Integer> currentAvatarIds;
  private long endVipTime;

  public long getEndVipTime() {
    return endVipTime;
  }


  public List<Integer> getCurrentAvatarIds() {
    return currentAvatarIds;
  }

  public int getCurrentLevel() {
    return currentLevel;
  }

  public String getSkuName() {
    return skuName;
  }

  public String getMarketToken() {
    return marketToken;
  }

  public String getUsername() {
    return username;
  }


}
