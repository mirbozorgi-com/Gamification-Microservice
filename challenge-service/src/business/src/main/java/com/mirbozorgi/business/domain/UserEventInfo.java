package com.mirbozorgi.business.domain;

import com.mirbozorgi.core.domain.UserEventData;
import java.util.List;

public class UserEventInfo {

  private String uuid;
  private String globalUniqueId;
  private List<UserEventData> userEventData;
  private long serverTime;

  public UserEventInfo(
      String uuid,
      String globalUniqueId,
      List<UserEventData> userEventData,
      long serverTime) {
    this.uuid = uuid;
    this.globalUniqueId = globalUniqueId;
    this.userEventData = userEventData;
    this.serverTime = serverTime;
  }

  public long getServerTime() {
    return serverTime;
  }

  public List<UserEventData> getUserEventData() {
    return userEventData;
  }

  public String getUuid() {
    return uuid;
  }

  public String getGlobalUniqueId() {
    return globalUniqueId;
  }


}
