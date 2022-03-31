package com.mirbozorgi.api.model;

import mirbozorgi.base.domain.user.WalletChange;

public class AddModel {

  private String name;
  private String code;
  private WalletChange walletChange;
  private long expireDate;
  private int maxUseCountGlobal;
  private Boolean active;
  private int minClientVersion;
  private int maxClientVersion;
  private int minLevelPermission;
  private int daysAfterInstall;

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }

  public WalletChange getWalletChange() {
    return walletChange;
  }

  public long getExpireDate() {
    return expireDate;
  }

  public int getMaxUseCountGlobal() {
    return maxUseCountGlobal;
  }

  public Boolean getActive() {
    return active;
  }

  public int getMinClientVersion() {
    return minClientVersion;
  }

  public int getMaxClientVersion() {
    return maxClientVersion;
  }

  public int getMinLevelPermission() {
    return minLevelPermission;
  }

  public int getDaysAfterInstall() {
    return daysAfterInstall;
  }
}
