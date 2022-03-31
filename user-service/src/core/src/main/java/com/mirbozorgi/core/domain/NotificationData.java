package com.mirbozorgi.core.domain;

import mirbozorgi.base.domain.hami.HamiAndNotificationType;
import mirbozorgi.base.domain.user.WalletChange;

public class NotificationData {

  private String notificationId;

  private String name;

  private Object config;

  private long creationDate;

  private Boolean removeAble;

  private WalletChange walletChange;

  private HamiAndNotificationType type;

  public NotificationData(
      String notificationId,
      String name,
      Object config,
      long creationDate,
      Boolean removeAble,
      WalletChange walletChange,
      HamiAndNotificationType type) {
    this.notificationId = notificationId;
    this.name = name;
    this.config = config;
    this.creationDate = creationDate;
    this.removeAble = removeAble;
    this.walletChange = walletChange;
    this.type = type;
  }

  public HamiAndNotificationType getType() {
    return type;
  }

  public WalletChange getWalletChange() {
    return walletChange;
  }


  public Boolean getRemoveAble() {
    return removeAble;
  }

  public String getNotificationId() {
    return notificationId;
  }

  public String getName() {
    return name;
  }

  public Object getConfig() {
    return config;
  }

  public long getCreationDate() {
    return creationDate;
  }
}
