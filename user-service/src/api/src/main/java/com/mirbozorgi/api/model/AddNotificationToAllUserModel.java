package com.mirbozorgi.api.model;

import java.util.List;
import mirbozorgi.base.domain.hami.HamiAndNotificationType;

public class AddNotificationToAllUserModel {

  private String name;
  private String notificationId;
  private String config;
  private int minClientVersion;
  private int maxClientVersion;
  private Boolean removeAble;
  private int gem;
  private int gold;
  private Short level;
  private int xp;
  private boolean hamiAded;
  private List<Integer> avatarIds;
  private long addedVipPeriodTime;
  private String title;
  private String message;
  private String topic;
  private HamiAndNotificationType type;
  private Boolean fcmSend;

  public Boolean getFcmSend() {
    return fcmSend;
  }

  public HamiAndNotificationType getType() {
    return type;
  }

  public String getTitle() {
    return title;
  }

  public String getMessage() {
    return message;
  }

  public String getTopic() {
    return topic;
  }

  public int getGem() {
    return gem;
  }

  public int getGold() {
    return gold;
  }

  public Short getLevel() {
    return level;
  }

  public int getXp() {
    return xp;
  }

  public boolean isHamiAded() {
    return hamiAded;
  }

  public List<Integer> getAvatarIds() {
    return avatarIds;
  }

  public long getAddedVipPeriodTime() {
    return addedVipPeriodTime;
  }

  public Boolean getRemoveAble() {
    return removeAble;
  }

  public String getNotificationId() {
    return notificationId;
  }


  public int getMinClientVersion() {
    return minClientVersion;
  }

  public int getMaxClientVersion() {
    return maxClientVersion;
  }


  public String getName() {
    return name;
  }

  public String getConfig() {
    return config;
  }
}
