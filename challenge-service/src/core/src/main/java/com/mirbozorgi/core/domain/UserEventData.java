package com.mirbozorgi.core.domain;

import com.mirbozorgi.core.constant.UserEventWinState;
import com.mirbozorgi.core.constant.UserEventStage;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

public class UserEventData {

  private String eventId;

  private String eventRepetitionUuId;

  private UserEventStage userEventStage;

  private UserEventWinState userEventWinState;

  private Object userMetaData;

  @Indexed(direction = IndexDirection.DESCENDING)
  private int score;

  private long joinTime;

  private long endTime;

  private long startTime;

  private long preActiveStartTime;


  public UserEventData(
      String eventId,
      UserEventStage userEventStage,
      UserEventWinState userEventWinState,
      Object userMetaData,
      int score,
      long joinTime,
      long endTime,
      long startTime,
      long preActiveStartTime,
      String eventRepetitionUuId) {
    this.eventId = eventId;
    this.userEventStage = userEventStage;
    this.userEventWinState = userEventWinState;
    this.userMetaData = userMetaData;
    this.score = score;
    this.endTime = endTime;
    this.joinTime = joinTime;
    this.startTime = startTime;
    this.preActiveStartTime = preActiveStartTime;
    this.eventRepetitionUuId = eventRepetitionUuId;
  }

  public void setUserEventStage(UserEventStage userEventStage) {
    this.userEventStage = userEventStage;
  }

  public String getEventRepetitionUuId() {
    return eventRepetitionUuId;
  }

  public long getStartTime() {
    return startTime;
  }

  public long getPreActiveStartTime() {
    return preActiveStartTime;
  }

  public long getJoinTime() {
    return joinTime;
  }

  public long getEndTime() {
    return endTime;
  }

  public String getEventId() {
    return eventId;
  }

  public UserEventStage getUserEventStage() {
    return userEventStage;
  }

  public Object getUserMetaData() {
    return userMetaData;
  }

  public int getScore() {
    return score;
  }

  public UserEventWinState getUserEventWinState() {
    return userEventWinState;
  }

  public void setUserEventWinState(UserEventWinState userEventWinState) {
    this.userEventWinState = userEventWinState;
  }
}
