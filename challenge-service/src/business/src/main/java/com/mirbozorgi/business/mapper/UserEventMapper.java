package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.UserEventInfo;
import com.mirbozorgi.core.document.UserEvent;

public class UserEventMapper {

  public static UserEventInfo toInfo(UserEvent userEvent,
      long serverTime) {
    return new UserEventInfo(
        userEvent.getUuid(),
        userEvent.getGlobalUniqueId(),
        userEvent.getUserEventData(),
        serverTime
    );
  }

}
