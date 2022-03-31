package com.mirbozorgi.business.domain.user;

import com.mirbozorgi.core.domain.Wallet;

public class UserInfo {


  private String uuid;

  private String gamePackageName;

  private String market;

  private String env;

  private String cohortName;

  private boolean isActive;

  private String username;

  private boolean test;

  private boolean debug;

  private Boolean isBanned;

  private int clientVersion;


  private long creationDate;

  private String playerStatus;

  private boolean verified;

  private Wallet wallet;


  public UserInfo(String uuid, String gamePackageName,
      String market, String env, String cohortName, boolean isActive,
      String username, boolean test, boolean debug,
      Boolean isBanned, int clientVersion, long creationDate, String playerStatus, boolean verified,
      Wallet wallet) {
    this.uuid = uuid;
    this.gamePackageName = gamePackageName;
    this.market = market;
    this.env = env;
    this.cohortName = cohortName;
    this.isActive = isActive;
    this.username = username;
    this.test = test;
    this.debug = debug;
    this.isBanned = isBanned;
    this.clientVersion = clientVersion;
    this.creationDate = creationDate;
    this.playerStatus = playerStatus;
    this.verified = verified;
    this.wallet = wallet;
  }


  public String getUuid() {
    return uuid;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getMarket() {
    return market;
  }

  public String getEnv() {
    return env;
  }

  public String getCohortName() {
    return cohortName;
  }

  public boolean isActive() {
    return isActive;
  }


  public String getUsername() {
    return username;
  }


  public boolean isTest() {
    return test;
  }

  public boolean isDebug() {
    return debug;
  }

  public Boolean getBanned() {
    return isBanned;
  }

  public int getClientVersion() {
    return clientVersion;
  }

  public long getCreationDate() {
    return creationDate;
  }

  public String getPlayerStatus() {
    return playerStatus;
  }

  public boolean isVerified() {
    return verified;
  }

  public Wallet getWallet() {
    return wallet;
  }
}
