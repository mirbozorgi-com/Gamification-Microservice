package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.domain.UserEventInfo;
import com.mirbozorgi.business.service.EventWinnerHistoryService;
import com.mirbozorgi.business.service.UserEventService;
import com.mirbozorgi.core.document.EventWinnersHistory;
import com.mirbozorgi.core.domain.EventWinnerHistory;
import com.mirbozorgi.core.domain.UserEventData;
import com.mirbozorgi.core.repository.document.EventWinnerHistoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventWinnerHistoryServiceImp implements EventWinnerHistoryService {

  @Autowired
  private EventWinnerHistoryRepository eventWinnerHistoryRepository;

  @Autowired
  private UserEventService userEventService;


  @Override
  public void makeWinner(
      String eventId,
      String gamePackageName,
      String env,
      String marketName,
      String eventRepetitionRandomUuId,
      String uuid,
      String globalUniqueId) {
    UserEventInfo userEventInfo = userEventService.getHistory(
        uuid,
        globalUniqueId,
        gamePackageName,
        env,
        marketName);

    List<UserEventData> collect = userEventInfo.getUserEventData().stream().filter(
        e -> e.getEventId().equals(eventId) && e.getEventRepetitionUuId()
            .equals(eventRepetitionRandomUuId)).collect(
        Collectors.toList());
    UserEventData userEventData = collect.get(0);
    EventWinnerHistory eventWinnerHistory = new EventWinnerHistory(
        uuid,
        globalUniqueId,
        userEventData.getUserMetaData(),
        userEventData.getScore(),
        userEventData.getJoinTime()
    );

    eventWinnerHistoryRepository.add(
        eventId,
        gamePackageName,
        env,
        marketName,
        eventRepetitionRandomUuId,
        eventWinnerHistory
    );
  }

  @Override
  public EventWinnersHistory getEventWinner(
      String eventId,
      String gamePackageName,
      String env,
      String marketName,
      String eventRepetitionRandomUuId) {

    return eventWinnerHistoryRepository.getEventWinnerHistory(
        eventId,
        gamePackageName,
        env,
        marketName,
        eventRepetitionRandomUuId
    );

  }
}
