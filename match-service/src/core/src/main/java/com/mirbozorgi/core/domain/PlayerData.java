package com.mirbozorgi.core.domain;

import java.util.List;
import org.springframework.data.mongodb.core.index.Indexed;

public class PlayerData {

  @Indexed
  private String userUuId;

  private String username;

  private long lastUpdateScoreTime;

  private long cheatUpdateRequest;

  private Boolean claim;

  private Boolean banned;

  private Object config;

  private int currentLevel;

  private List<Integer> currentAvatarIds;

  private long finishTimeVip;


  public PlayerData() {
  }

  public PlayerData(String userUuId,
      String username,
      long lastUpdateScoreTime,
      long cheatUpdateRequest,
      Boolean claim,
      Boolean banned,
      Object config,
      int currentLevel,
      List<Integer> currentAvatarIds,
      long finishTimeVip) {
    this.userUuId = userUuId;
    this.username = username;
    this.lastUpdateScoreTime = lastUpdateScoreTime;
    this.cheatUpdateRequest = cheatUpdateRequest;
    this.claim = claim;
    this.banned = banned;
    this.config = config;
    this.currentLevel = currentLevel;
    this.currentAvatarIds = currentAvatarIds;
    this.finishTimeVip = finishTimeVip;
  }

  public PlayerData(String userUuId, String username, long lastUpdateScoreTime,
      long cheatUpdateRequest, Boolean claim, Boolean banned, Object config) {
    this.userUuId = userUuId;
    this.username = username;
    this.lastUpdateScoreTime = lastUpdateScoreTime;
    this.cheatUpdateRequest = cheatUpdateRequest;
    this.claim = claim;
    this.banned = banned;
    this.config = config;
  }

  public long getFinishTimeVip() {
    return finishTimeVip;
  }

  public int getCurrentLevel() {
    return currentLevel;
  }

  public List<Integer> getCurrentAvatarIds() {
    return currentAvatarIds;
  }

  public String getUserUuId() {
    return userUuId;
  }

  public String getUsername() {
    return username;
  }

  public long getLastUpdateScoreTime() {
    return lastUpdateScoreTime;
  }

  public long getCheatUpdateRequest() {
    return cheatUpdateRequest;
  }

  public Boolean getClaim() {
    return claim;
  }

  public Boolean getBanned() {
    return banned;
  }

  public Object getConfig() {
    return config;
  }
}
