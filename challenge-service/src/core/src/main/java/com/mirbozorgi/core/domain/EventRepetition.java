package com.mirbozorgi.core.domain;

public class EventRepetition {

  private String eventReputationUuId;
  private long startPreActiveTime;
  private long startTime;
  private long endTime;
  private Boolean terminated;
  private long startJoinTime;
  private long endJoinTime;

  public void setEventReputationUuId(String eventReputationUuId) {
    this.eventReputationUuId = eventReputationUuId;
  }

  public EventRepetition() {
  }

  public EventRepetition(
      long startTime,
      long endTime,
      long startPreActiveTime,
      long startJoinTime,
      long endJoinTime,
      String eventReputationUuId) {
    this.startTime = startTime;
    this.startPreActiveTime = startPreActiveTime;
    this.endTime = endTime;
    this.endJoinTime = endJoinTime;
    this.startJoinTime = startJoinTime;
    this.terminated = false;
    this.eventReputationUuId = eventReputationUuId;
  }

  public String getEventReputationUuId() {
    return eventReputationUuId;
  }

  public long getStartPreActiveTime() {
    return startPreActiveTime;
  }

  public long getStartJoinTime() {
    return startJoinTime;
  }

  public long getEndJoinTime() {
    return endJoinTime;
  }

  public Boolean getTerminated() {
    return terminated;
  }

  public long getStartTime() {
    return startTime;
  }

  public long getEndTime() {
    return endTime;
  }
}
