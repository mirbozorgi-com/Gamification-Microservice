package com.mirbozorgi.business.domain;

import java.util.List;

public class GameInfo {


  private int id;

  private String name;

  private String packageName;

  private boolean active;

  private String env;

  private int lastVersion;

  private int supportedVersion;

  private String instagram;

  private String telegram;

  private String facebook;

  private String twitter;

  private String linkedIn;

  private List<Integer> marketIds;

  private List<String> marketNames;

  private int defaultMarketId;

  private String defaultMarketName;

  private int defaultGem;

  private int defaultGold;

  private int defaultLevel;

  private int defaultXp;


  public GameInfo(int id, String name, String packageName, boolean active, String env,
      int lastVersion, int supportedVersion, String instagram, String telegram,
      String facebook, String twitter, List<Integer> marketIds,
      List<String> marketNames,
      int defaultMarketId,
      String defaultMarketName,
      String linkedIn,
      int defaultGem,
      int defaultGold,
      int defaultLevel,
      int defaultXp) {
    this.id = id;
    this.name = name;
    this.packageName = packageName;
    this.active = active;
    this.env = env;
    this.lastVersion = lastVersion;
    this.supportedVersion = supportedVersion;
    this.instagram = instagram;
    this.telegram = telegram;
    this.facebook = facebook;
    this.twitter = twitter;
    this.marketIds = marketIds;
    this.marketNames = marketNames;
    this.defaultMarketId = defaultMarketId;
    this.defaultMarketName = defaultMarketName;
    this.linkedIn = linkedIn;
    this.defaultGem = defaultGem;
    this.defaultGold = defaultGold;
    this.defaultLevel = defaultLevel;
    this.defaultXp = defaultXp;
  }

  public int getDefaultGem() {
    return defaultGem;
  }

  public int getDefaultGold() {
    return defaultGold;
  }

  public int getDefaultLevel() {
    return defaultLevel;
  }

  public int getDefaultXp() {
    return defaultXp;
  }

  public String getLinkedIn() {
    return linkedIn;
  }

  public String getDefaultMarketName() {
    return defaultMarketName;
  }

  public int getDefaultMarketId() {
    return defaultMarketId;
  }

  public List<String> getMarketNames() {
    return marketNames;
  }

  public List<Integer> getMarketIds() {
    return marketIds;
  }

  public String getEnv() {
    return env;
  }

  public int getLastVersion() {
    return lastVersion;
  }

  public int getSupportedVersion() {
    return supportedVersion;
  }

  public String getInstagram() {
    return instagram;
  }

  public String getTelegram() {
    return telegram;
  }

  public String getFacebook() {
    return facebook;
  }

  public String getTwitter() {
    return twitter;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
