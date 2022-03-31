package com.mirbozorgi.api.model.user_event;

import com.mirbozorgi.business.domain.UserEventJoinData;
import java.util.List;

public class ActionListModel {

  private String globalUniqueId;
  private List<UserEventJoinData> userEventJoinData;
  private String notificationToken;


  public String getGlobalUniqueId() {
    return globalUniqueId;
  }

  public List<UserEventJoinData> getUserEventJoinData() {
    return userEventJoinData;
  }

  public String getNotificationToken() {
    return notificationToken;
  }
}
