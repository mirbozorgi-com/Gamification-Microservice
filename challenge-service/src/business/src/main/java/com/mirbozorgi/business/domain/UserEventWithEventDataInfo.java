package com.mirbozorgi.business.domain;

import com.mirbozorgi.core.constant.UserEventStage;
import com.mirbozorgi.core.document.Event;
import java.util.List;
import java.util.Map;

public class UserEventWithEventDataInfo {

  private UserEventInfo userEventInfo;
  private Map<UserEventStage, List<Event>> eventInfos;
  private long serverTime;

  public UserEventWithEventDataInfo(UserEventInfo userEventInfo,
      Map<UserEventStage, List<Event>> eventInfos,
      long serverTime) {
    this.userEventInfo = userEventInfo;
    this.eventInfos = eventInfos;
    this.serverTime = serverTime;
  }

  public long getServerTime() {
    return serverTime;
  }

  public UserEventInfo getUserEventInfo() {
    return userEventInfo;
  }

  public Map<UserEventStage, List<Event>> getEventInfos() {
    return eventInfos;
  }
}
