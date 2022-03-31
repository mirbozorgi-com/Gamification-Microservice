package com.mirbozorgi.business.domain;

import com.mirbozorgi.core.domain.UserEventData;

public class UserEventJoinData {

  private UserEventData userEventData;
  private String eventId;

  public UserEventJoinData(UserEventData userEventData, String eventId) {
    this.userEventData = userEventData;
    this.eventId = eventId;
  }

  public UserEventJoinData() {
  }

  public UserEventData getUserEventData() {
    return userEventData;
  }

  public String getEventId() {
    return eventId;
  }
}
