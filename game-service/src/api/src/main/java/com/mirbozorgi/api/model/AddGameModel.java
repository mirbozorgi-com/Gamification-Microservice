package com.mirbozorgi.api.model;

import com.mirbozorgi.core.entity.Market;
import java.util.List;

public class AddGameModel {


  private String name;
  private boolean active;
  private int lastVersion;
  private int supportedVersion;
  private String instagram;
  private String telegram;
  private String facebook;
  private String twitter;
  private String linkedin;
  private List<Market> markets;
  private boolean haveUserLogin;
  private int defaultMarketId;

  private int defaultGem;
  private int defaultGold;
  private int defaultLevel;
  private int defaultXp;

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

  public int getDefaultMarketId() {
    return defaultMarketId;
  }

  public List<Market> getMarkets() {
    return markets;
  }

  public boolean isHaveUserLogin() {
    return haveUserLogin;
  }

  public String getName() {
    return name;
  }

  public boolean isActive() {
    return active;
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

  public String getLinkedin() {
    return linkedin;
  }
}
