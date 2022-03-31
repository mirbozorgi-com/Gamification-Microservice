package com.mirbozorgi.core.domain;

public class UserEventJoinedData {

  private String eventReputationUuId;

  public UserEventJoinedData(String eventReputationUuId) {
    this.eventReputationUuId = eventReputationUuId;
  }

  public String getEventReputationUuId() {
    return eventReputationUuId;
  }
}
