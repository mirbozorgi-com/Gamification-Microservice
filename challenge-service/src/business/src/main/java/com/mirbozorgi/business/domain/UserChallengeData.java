package com.mirbozorgi.business.domain;

import com.mirbozorgi.business.service.impl.TimeServiceImpl;

public class UserChallengeData {

  private String id;

  private String challengeId;

  private String userUuId;

  private String name;

  private String gamePackageName;

  private String env;

  private String marketName;

  private int score;

  private long lastUpdateScoreTime;

  private long cheatUpdateRequest;

  private Boolean claim;

  private Boolean banned;

  private long endTime;

  private Boolean active;

  public UserChallengeData(String id, String challengeId, String userUuId,
      String name, String gamePackageName, String env, String marketName, int score,
      long lastUpdateScoreTime, long cheatUpdateRequest, Boolean claim, Boolean banned,
      long endTime) {
    this.id = id;
    this.challengeId = challengeId;
    this.userUuId = userUuId;
    this.name = name;
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.marketName = marketName;
    this.score = score;
    this.lastUpdateScoreTime = lastUpdateScoreTime;
    this.cheatUpdateRequest = cheatUpdateRequest;
    this.claim = claim;
    this.banned = banned;
    this.endTime = endTime;
    this.active = chckActive(endTime);
  }

  public String getId() {
    return id;
  }

  public String getChallengeId() {
    return challengeId;
  }

  public String getUserUuId() {
    return userUuId;
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

  public int getScore() {
    return score;
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

  public long getEndTime() {
    return endTime;
  }

  public Boolean getActive() {
    return active;
  }

  private boolean chckActive(long endTime) {
    boolean active = false;
    if (new TimeServiceImpl().getNowUnixFromInstantClass() <= endTime) {
      active = true;
    }

    return active;
  }
}
