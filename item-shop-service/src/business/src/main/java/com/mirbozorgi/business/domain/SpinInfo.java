package com.mirbozorgi.business.domain;

public class SpinInfo {

  private String id;
  private String gamePackageName;
  private String emv;
  private String marketName;

  public SpinInfo(String id, String gamePackageName, String emv, String marketName) {
    this.id = id;
    this.gamePackageName = gamePackageName;
    this.emv = emv;
    this.marketName = marketName;
  }

  public String getId() {
    return id;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getEmv() {
    return emv;
  }

  public String getMarketName() {
    return marketName;
  }
}
