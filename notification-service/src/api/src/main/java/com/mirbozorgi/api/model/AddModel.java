package com.mirbozorgi.api.model;

import java.util.List;
import mirbozorgi.base.domain.hami.HamiAndNotificationType;

public class AddModel {

  private String name;
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
  private String topic;
  private String message;
  private Boolean fcmSend;


  public Boolean getFcmSend() {
    return fcmSend;
  }

  private HamiAndNotificationType type;

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

  public Short getLevel() {
    return level;
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

  public int getXp() {
    return xp;
  }

  public int getGem() {
    return gem;
  }

  public int getGold() {
    return gold;
  }

  public Boolean getRemoveAble() {
    return removeAble;
  }

  public String getName() {
    return name;
  }

  public String getConfig() {
    return config;
  }

  public int getMinClientVersion() {
    return minClientVersion;
  }

  public int getMaxClientVersion() {
    return maxClientVersion;
  }

}
