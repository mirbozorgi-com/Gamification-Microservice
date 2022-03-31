package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.EventInfo;
import com.mirbozorgi.core.document.Event;

public class EventMapper {

  public static EventInfo toInfo(Event event,
      long serverTime) {
    return new EventInfo(
        event.getName(),
        event.getGamePackageName(),
        event.getMarketName(),
        event.getEnv(),
        event.getStringId(),
        event.getPeriodTimeForMiddleJoinTillEnd(),
        event.getStates(),
        event.getRepetitions(),
        event.getVersionMetaData(),
        event.getEventEndType(),
        event.getClientEventType(),
        event.getConfigVersion(),
        serverTime);
  }


}
