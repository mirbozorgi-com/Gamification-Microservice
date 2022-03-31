package com.mirbozorgi.core.domain;

import com.mirbozorgi.core.constant.UserEventWinState;

public class UserEventFinishedData {

  private String eventId;

  private Object userMetaData;

  private int score;

  private long joinTime;

  private long endTime;

  private long startTime;

  private long preActiveStartTime;

  private UserEventWinState userEventWinState;

  public UserEventFinishedData(String eventId,
      Object userMetaData,
      int score,
      long joinTime,
      long endTime,
      long startTime,
      long preActiveStartTime,
      UserEventWinState userEventWinState) {
    this.eventId = eventId;
    this.userMetaData = userMetaData;
    this.score = score;
    this.joinTime = joinTime;
    this.endTime = endTime;
    this.startTime = startTime;
    this.preActiveStartTime = preActiveStartTime;
    this.userEventWinState = userEventWinState;
  }

  public UserEventWinState getUserEventWinState() {
    return userEventWinState;
  }

  public String getEventId() {
    return eventId;
  }

  public Object getUserMetaData() {
    return userMetaData;
  }

  public int getScore() {
    return score;
  }

  public long getJoinTime() {
    return joinTime;
  }

  public long getEndTime() {
    return endTime;
  }

  public long getStartTime() {
    return startTime;
  }

  public long getPreActiveStartTime() {
    return preActiveStartTime;
  }
}
