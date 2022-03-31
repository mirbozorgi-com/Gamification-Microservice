package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.domain.EventInfo;
import com.mirbozorgi.business.mapper.EventMapper;
import com.mirbozorgi.business.service.EventService;
import com.mirbozorgi.business.service.StringService;
import com.mirbozorgi.business.service.UserEventService;
import com.mirbozorgi.business.service.TimeService;
import com.mirbozorgi.core.constant.EventEndType;
import com.mirbozorgi.core.constant.UserEventStage;
import com.mirbozorgi.core.document.Event;
import com.mirbozorgi.core.domain.EventRepetition;
import com.mirbozorgi.core.repository.document.EventRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@Service
@EnableCaching
public class EventServiceImpl implements EventService {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private TimeService timeService;

  @Autowired
  private UserEventService userEventService;

  @Autowired
  private StringService stringService;

  @Override
  public EventInfo add(
      int configVersion,
      String gamePackageName,
      String marketName,
      String env,
      String name,
      long periodTimeForMiddleJoinTillEnd,
      Map<String, Object> states,
      List<EventRepetition> repetitions,
      Map<String, Object> versionMetaData,
      EventEndType eventEndType,
      String clientEventType) {
    for (EventRepetition repetition : repetitions) {
      repetition.setEventReputationUuId(stringService.generateRandomString(
          true,
          true,
          15));
    }

    Event event = new Event(
        gamePackageName,
        marketName,
        env,
        name,
        repetitions,
        versionMetaData,
        periodTimeForMiddleJoinTillEnd,
        states,
        eventEndType,
        clientEventType,
        configVersion);
    event = eventRepository.add(event);
    return EventMapper.toInfo(
        event,
        timeService.getNowUnixFromInstantClass());
  }

  @Override
  @Cacheable(value = "base", key = "{ #root.targetClass.simpleName, "
      + "#root.methodName, #gamePackageName, #env, #marketName , #active, #preActive, #finish, #terminated, #notStarted}",
      sync = true)
  public Map<UserEventStage, List<Event>> getActiveAndPreActive(
      String gamePackageName,
      String env,
      String marketName,
      Boolean active,
      Boolean preActive,
      Boolean finish,
      Boolean terminated,
      Boolean notStarted
  ) {
    long nowTime = timeService.getNowUnixFromInstantClass();
    return eventRepository.findActiveAndPreActive(
        gamePackageName,
        env,
        marketName,
        nowTime,
        active,
        preActive,
        finish,
        terminated,
        notStarted
    );


  }

  @Override
  public EventInfo update(
      int configVersion,
      String gamePackageName,
      String marketName,
      String env,
      String name,
      String id,
      long periodTimeForMiddleJoinTillEnd,
      Map<String, Object> states,
      List<EventRepetition> repetitions,
      Map<String, Object> versionMetaData,
      EventEndType eventEndType,
      String clientEventType) {

    Event event = eventRepository.update(
        id,
        name,
        gamePackageName,
        marketName,
        env,
        periodTimeForMiddleJoinTillEnd,
        states,
        repetitions,
        versionMetaData,
        eventEndType,
        clientEventType,
        configVersion);

    return EventMapper.toInfo(
        event,
        timeService.getNowUnixFromInstantClass());
  }

  @Override
  public EventInfo getById(
      String id,
      String clientVersion) {
    Event event = eventRepository.findByIdAndClientVersion(id, clientVersion);

    return EventMapper.toInfo(
        event,
        timeService.getNowUnixFromInstantClass());
  }

  @Override
  @Cacheable(value = "base", key = "{ #root.targetClass.simpleName, "
      + "#root.methodName, #gamePackageName, #env, #marketName , #limit}",
      sync = true)
  public List<EventInfo> get(
      String gamePackageName,
      String env,
      String marketName,
      Integer limit) {
    List<Event> events = eventRepository.find(
        gamePackageName,
        env,
        marketName,
        limit
    );
    List<EventInfo> eventInfos = new ArrayList<>();
    for (Event event : events) {
      eventInfos.add(EventMapper.toInfo(
          event,
          timeService.getNowUnixFromInstantClass()));
    }

    return eventInfos;
  }

  @Override
  public void delete(String id) {
    eventRepository.delete(id);
  }

  @Override
  public void terminate(String id) {
    Event event = eventRepository.findById(id);
    if (event != null) {
      long nowTime = timeService.getNowUnixFromInstantClass();
      eventRepository.terminate(id, nowTime);
      userEventService.terminateAll(
          event.getGamePackageName(),
          event.getEnv(),
          event.getMarketName(),
          event.getStringId()
      );
    }

  }
}
