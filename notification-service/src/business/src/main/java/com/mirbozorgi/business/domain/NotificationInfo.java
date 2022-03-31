package com.mirbozorgi.business.domain;

import mirbozorgi.base.domain.hami.HamiAndNotificationType;
import mirbozorgi.base.domain.user.WalletChange;

public class NotificationInfo {

  private String id;

  private String name;

  private String gamePackageName;

  private String env;

  private String marketName;

  private Object config;

  private long creationDate;

  private int minClientVersion;

  private int maxClientVersion;

  private Boolean removeAble;

  private WalletChange walletChange;

  private HamiAndNotificationType type;

  private String title;

  private String topic;

  private String message;

  private Boolean isFcmSend;

  public NotificationInfo(
      String id,
      String name,
      String gamePackageName,
      String env,
      String marketName,
      Object config,
      long creationDate,
      int minClientVersion,
      int maxClientVersion,
      Boolean removeAble,
      WalletChange walletChange,
      String title,
      String topic,
      String message,
      HamiAndNotificationType type,
      Boolean isFcmSend) {
    this.id = id;
    this.name = name;
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.marketName = marketName;
    this.config = config;
    this.creationDate = creationDate;
    this.minClientVersion = minClientVersion;
    this.maxClientVersion = maxClientVersion;
    this.removeAble = removeAble;
    this.walletChange = walletChange;
    this.message = message;
    this.title = title;
    this.topic = topic;
    this.type = type;
    this.isFcmSend = isFcmSend;
  }

  public Boolean getFcmSend() {
    return isFcmSend;
  }

  public HamiAndNotificationType getType() {
    return type;
  }

  public String getTitle() {
    return title;
  }

  public String getTopic() {
    return topic;
  }

  public String getMessage() {
    return message;
  }

  public WalletChange getWalletChange() {
    return walletChange;
  }

  public NotificationInfo() {
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

  public String getMarketName() {
    return marketName;
  }

  public Object getConfig() {
    return config;
  }

  public long getCreationDate() {
    return creationDate;
  }

  public int getMinClientVersion() {
    return minClientVersion;
  }

  public int getMaxClientVersion() {
    return maxClientVersion;
  }

  public Boolean getRemoveAble() {
    return removeAble;
  }
}
