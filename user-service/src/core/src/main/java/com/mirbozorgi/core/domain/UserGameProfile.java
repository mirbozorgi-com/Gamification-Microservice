package com.mirbozorgi.core.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mirbozorgi.base.constanct.PlayerType;
import org.springframework.data.mongodb.core.index.Indexed;

public class UserGameProfile {

  private String cohortName;

  private boolean isActive;

  private String username;

  private boolean test;

  private boolean debug;

  private Boolean isBanned;

  private int clientVersion;

  private String marketForFirstAppOpen;

  private Boolean claimReferralPrize;

  private long creationDate;

  private String playerStatus;

  private boolean verified;

  private LocalDate lastDailyRewardClaimDate;


  private List<LocalDate> lastLogin;

  @Indexed
  private Wallet wallet;

  @Indexed
  private Statistic statistic;


  //key: partypackageName
  private Map<String, Long> lastSeenOfThirdPartyAdvertisements;

  private Map<String, NotificationData> notifications;

  private Map<String, NewsData> news;

  private PlayerType playerType;

  private LocalDate registerDate;

  private String tokenFCM;


  public UserGameProfile(
      String cohortName,
      boolean isActive,
      String username,
      boolean test,
      boolean debug,
      Boolean isBanned,
      int clientVersion,
      long creationDate,
      String playerStatus,
      boolean verified,
      Wallet wallet,
      Statistic statistic,
      Map<String, NotificationData> notifications,
      PlayerType playerType,
      Map<String, NewsData> news,
      LocalDate lastDailyRewardClaimDate,
      List<LocalDate> lastLogin,
      LocalDate registerDate,
      Map<String, Long> lastSeenOfThirdPartyAdvertisements,
      Boolean claimReferralPrize,
      String tokenFCM,
      String marketForFirstAppOpen) {
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
    this.statistic = statistic;
    this.notifications = notifications;
    this.playerType = playerType;
    this.news = news;
    this.lastDailyRewardClaimDate = lastDailyRewardClaimDate;
    this.lastLogin = lastLogin;
    this.registerDate = registerDate;
    this.lastSeenOfThirdPartyAdvertisements = lastSeenOfThirdPartyAdvertisements;
    this.claimReferralPrize = claimReferralPrize;
    this.tokenFCM = tokenFCM;
    this.marketForFirstAppOpen = marketForFirstAppOpen;


  }

  public UserGameProfile() {
    notifications = new HashMap<>();
    lastSeenOfThirdPartyAdvertisements = new HashMap<>();
    news = new HashMap<>();
    lastLogin = new ArrayList<>();
  }


  public Map<String, Long> getLastSeenOfThirdPartyAdvertisements() {
    return lastSeenOfThirdPartyAdvertisements;
  }

  public String getTokenFCM() {
    return tokenFCM;
  }

  public LocalDate getRegisterDate() {
    return registerDate;
  }

  public List<LocalDate> getLastLogin() {
    return lastLogin;
  }

  public LocalDate getLastDailyRewardClaimDate() {
    return lastDailyRewardClaimDate;
  }

  public Map<String, NewsData> getNews() {
    return news;
  }

  public Boolean getClaimReferralPrize() {
    return claimReferralPrize;
  }

  public Map<String, NotificationData> getNotifications() {
    return notifications;
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

  public PlayerType getPlayerType() {
    return playerType;
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

  public Statistic getStatistic() {
    return statistic;
  }


}
