package com.mirbozorgi.api.model;

import java.util.List;

public class HillaBuyModel {

  private String username;
  private String skuName;
  private int currentLevel;
  private List<Integer> currentAvatarIds;
  private long endVipTime;
  private String orderId;
  private String transactionId;

  public String getUsername() {
    return username;
  }

  public String getSkuName() {
    return skuName;
  }


  public int getCurrentLevel() {
    return currentLevel;
  }

  public List<Integer> getCurrentAvatarIds() {
    return currentAvatarIds;
  }

  public long getEndVipTime() {
    return endVipTime;
  }

  public String getOrderId() {
    return orderId;
  }

  public String getTransactionId() {
    return transactionId;
  }
}
