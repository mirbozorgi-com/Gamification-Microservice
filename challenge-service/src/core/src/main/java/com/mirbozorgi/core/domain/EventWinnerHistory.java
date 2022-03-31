package com.mirbozorgi.core.domain;

public class EventWinnerHistory {

  private String uuid;

  private String globalUniqueId;

  private Object userMetaData;

  private int score;

  private long joinTime;

  public EventWinnerHistory(
      String uuid,
      String globalUniqueId,
      Object userMetaData,
      int score,
      long joinTime) {
    this.uuid = uuid;
    this.globalUniqueId = globalUniqueId;
    this.userMetaData = userMetaData;
    this.score = score;
    this.joinTime = joinTime;

  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getGlobalUniqueId() {
    return globalUniqueId;
  }

  public void setGlobalUniqueId(String globalUniqueId) {
    this.globalUniqueId = globalUniqueId;
  }

  public Object getUserMetaData() {
    return userMetaData;
  }

  public void setUserMetaData(Object userMetaData) {
    this.userMetaData = userMetaData;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public long getJoinTime() {
    return joinTime;
  }

  public void setJoinTime(long joinTime) {
    this.joinTime = joinTime;
  }


}
