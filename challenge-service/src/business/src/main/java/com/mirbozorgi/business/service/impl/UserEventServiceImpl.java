package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.domain.UserEventInfo;
import com.mirbozorgi.business.domain.UserEventJoinData;
import com.mirbozorgi.business.domain.UserEventWithEventDataInfo;
import com.mirbozorgi.business.exception.EventJoinTimeExcption;
import com.mirbozorgi.business.exception.UserEventClaimException;
import com.mirbozorgi.business.exception.UserEventCloseExpction;
import com.mirbozorgi.business.exception.event.EventNotFoundException;
import com.mirbozorgi.business.exception.event.JoinLateException;
import com.mirbozorgi.business.exception.event.JoinSoonException;
import com.mirbozorgi.business.exception.event.UserEventNotFoundException;
import com.mirbozorgi.business.mapper.UserEventMapper;
import com.mirbozorgi.business.service.EventService;
import com.mirbozorgi.business.service.EventWinnerHistoryService;
import com.mirbozorgi.business.service.TimeService;
import com.mirbozorgi.business.service.UserEventService;
import com.mirbozorgi.core.constant.EventEndType;
import com.mirbozorgi.core.constant.UserEventStage;
import com.mirbozorgi.core.constant.UserEventWinState;
import com.mirbozorgi.core.document.Event;
import com.mirbozorgi.core.document.UserEvent;
import com.mirbozorgi.core.domain.EventRepetition;
import com.mirbozorgi.core.domain.UserEventData;
import com.mirbozorgi.core.domain.UserEventFinishedData;
import com.mirbozorgi.core.repository.document.EventRepository;
import com.mirbozorgi.core.repository.document.UserEventFinishedHistoryRepository;
import com.mirbozorgi.core.repository.document.UserEventRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@Service
@EnableCaching
public class UserEventServiceImpl implements UserEventService {

  @Autowired
  private TimeService timeService;

  @Autowired
  private UserEventRepository userEventRepository;

  @Autowired
  private UserEventFinishedHistoryRepository userEventFinishedHistoryRepository;

  @Autowired
  private EventService eventService;

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private EventWinnerHistoryService eventWinnerHistoryService;


  @Override
  @Cacheable(value = "base", key = "{ #root.targetClass.simpleName, "
      + "#root.methodName, #uuid, #globalUniqueId, #gamePackageName, #env, #marketName}", sync = true)
  public UserEventInfo getHistory(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName) {
    UserEventInfo userEventInfo = null;
    List<String> validates = validateInputs(uuid, globalUniqueId);
    uuid = validates.get(0);
    globalUniqueId = validates.get(1);
    if (uuid != null) {
      userEventInfo = findByUuId(
          uuid,
          gamePackageName,
          env,
          marketName
      );


    } else if (globalUniqueId != null) {
      userEventInfo = findByGlobalUniqueId(
          globalUniqueId,
          gamePackageName,
          env,
          marketName
      );
    }

    return userEventInfo;
  }

  @Override
  public void terminateAll(
      String gamePackageName,
      String env,
      String marketName,
      String eventId) {
    userEventRepository.terminateForAll(
        gamePackageName,
        env,
        marketName,
        eventId
    );

  }


  @Override
  public void close(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      String eventId,
      Boolean win) {
    UserEvent userEvent = null;
    if (uuid != null) {
      userEvent = userEventRepository.findByUuId(
          uuid,
          gamePackageName,
          env,
          marketName
      );

    } else if (globalUniqueId != null) {
      userEvent = userEventRepository.findByGlobalUniqueId(
          globalUniqueId,
          gamePackageName,
          env,
          marketName
      );
    }
    List<String> validates = validateInputs(uuid, globalUniqueId);
    uuid = validates.get(0);
    globalUniqueId = validates.get(1);
    assert userEvent != null;
    UserEventData userEventData = userEvent.getUserEventData().stream()
        .filter(e -> e.getEventId().equals(eventId)).findFirst().get();

    if (userEventData.getUserEventStage().equals(UserEventStage.TERMINATED)) {
      userEventRepository.close(
          uuid,
          globalUniqueId,
          gamePackageName,
          env,
          marketName,
          eventId,
          userEventData
      );
      // add deleted event to history for bi system
      userEventFinishedHistoryRepository.add(
          eventId,
          uuid,
          globalUniqueId,
          gamePackageName,
          env,
          marketName,
          new UserEventFinishedData(
              eventId,
              userEventData.getUserMetaData(),
              userEventData.getScore(),
              userEventData.getJoinTime(),
              userEventData.getEndTime(),
              userEventData.getStartTime(),
              userEventData.getPreActiveStartTime(),
              userEventData.getUserEventWinState()
          )
      );

      // send notif
    } else {
      throw new UserEventCloseExpction();
    }
  }

  @Override
  public void closeAndClaim(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      String eventId,
      Boolean win) {
    List<String> validates = validateInputs(uuid, globalUniqueId);
    uuid = validates.get(0);
    globalUniqueId = validates.get(1);
    long nowTime = timeService.getNowUnixFromInstantClass();
    UserEvent userEvent = userEventRepository.checkEventFinish(
        uuid,
        globalUniqueId,
        gamePackageName,
        env,
        marketName,
        eventId,
        nowTime);

    if (userEvent != null) {
      UserEventData userEventData = userEvent.getUserEventData().stream()
          .filter(e -> e.getEventId().equals(eventId)).findFirst().get();
      userEventRepository.close(
          uuid,
          globalUniqueId,
          gamePackageName,
          env,
          marketName,
          eventId,
          userEventData
      );
      // add deleted event to history for bi system
      userEventFinishedHistoryRepository.add(
          eventId,
          uuid,
          globalUniqueId,
          gamePackageName,
          env,
          marketName,
          new UserEventFinishedData(
              eventId,
              userEventData.getUserMetaData(),
              userEventData.getScore(),
              userEventData.getJoinTime(),
              userEventData.getEndTime(),
              userEventData.getStartTime(),
              userEventData.getPreActiveStartTime(),
              userEventData.getUserEventWinState()
          )
      );
    }


  }

  @Override
  public void claim(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      String eventId) {
    List<String> validates = validateInputs(uuid, globalUniqueId);
    uuid = validates.get(0);
    globalUniqueId = validates.get(1);
    long nowTime = timeService.getNowUnixFromInstantClass();

    UserEvent userEvent = userEventRepository.checkEventFinish(
        uuid,
        globalUniqueId,
        gamePackageName,
        env,
        marketName,
        eventId,
        nowTime);

    if (userEvent != null) {
      userEventRepository.changeStage(
          uuid,
          globalUniqueId,
          gamePackageName,
          env,
          marketName,
          eventId,
          UserEventStage.CLAIMED
      );
    } else {
      throw new UserEventClaimException();
    }
  }


  @Override
  @Cacheable(value = "base", key = "{ #root.targetClass.simpleName, "
      + "#root.methodName, #uuid, #globalUniqueId, #gamePackageName, #env, #marketName, #userEventStages}", sync = true)
  public UserEventInfo getAllAggregate(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      List<UserEventStage> userEventStages) {
    List<UserEventData> userEventData = new ArrayList<>();
    long nowTime = timeService.getNowUnixFromInstantClass();
    UserEvent userEvent = null;
    List<String> validates = validateInputs(uuid, globalUniqueId);
    uuid = validates.get(0);
    globalUniqueId = validates.get(1);
    if (uuid != null) {
      userEvent = userEventRepository.findByUuId(
          uuid,
          gamePackageName,
          env,
          marketName);
    } else if (globalUniqueId != null) {
      userEvent = userEventRepository.findByGlobalUniqueId(
          globalUniqueId,
          gamePackageName,
          env,
          marketName);
    } else {
      throw new UserEventNotFoundException();
    }

    if (userEvent != null) {
      userEventData = userEvent.getUserEventData();
    }
    List<UserEventData> finalUserEventData = new ArrayList<>();

    if (userEventStages.contains(UserEventStage.PRE_ACTIVE)
        && userEventStages.contains(UserEventStage.ACTIVE)
        && userEventStages.contains(UserEventStage.FINISHED)
        && userEventStages.contains(UserEventStage.TERMINATED)
        && userEventStages.contains(UserEventStage.CLAIMED)) {
      return new UserEventInfo(
          uuid,
          globalUniqueId,
          userEventData,
          timeService.getNowUnixFromInstantClass()
      );
    }

    if (userEventStages.contains(UserEventStage.FINISHED)) {
      List<UserEventData> collect = userEventData.stream()
          .filter(e -> e.getEndTime() <= nowTime).collect(Collectors.toList());
      userEventData.removeAll(collect);

      collect.forEach(e ->
          e.setUserEventStage(UserEventStage.FINISHED));
      finalUserEventData.addAll(collect);
      userEventStages.remove(UserEventStage.FINISHED);
    }
    if (userEventStages.contains(UserEventStage.ACTIVE)) {
      List<UserEventData> collect = userEventData.stream()
          .filter(
              e -> e.getEndTime() > nowTime && e.getStartTime() <= nowTime && e.getUserEventStage()
                  .equals(UserEventStage.PRE_ACTIVE_OR_ACTIVE))
          .collect(Collectors.toList());
      userEventData.removeAll(collect);

      collect.forEach(e ->
          e.setUserEventStage(UserEventStage.ACTIVE));
      finalUserEventData.addAll(collect);
      userEventStages.remove(UserEventStage.ACTIVE);
    }

    if (userEventStages.contains(UserEventStage.TERMINATED)) {
      List<UserEventData> collect = userEventData.stream()
          .filter(e -> e.getUserEventStage().equals(UserEventStage.TERMINATED))
          .collect(Collectors.toList());
      userEventData.removeAll(collect);

      collect.forEach(e ->
          e.setUserEventStage(UserEventStage.TERMINATED));
      finalUserEventData.addAll(collect);
      userEventStages.remove(UserEventStage.TERMINATED);
    }

    if (userEventStages.contains(UserEventStage.PRE_ACTIVE)) {
      List<UserEventData> collect = userEventData.stream()
          .filter(e -> e.getStartTime() > nowTime && e.getPreActiveStartTime() <= nowTime)
          .collect(Collectors.toList());
      userEventData.removeAll(collect);

      collect.forEach(e ->
          e.setUserEventStage(UserEventStage.PRE_ACTIVE));
      finalUserEventData.addAll(collect);
      userEventStages.remove(UserEventStage.PRE_ACTIVE);
    }
    userEventStages.remove(UserEventStage.PRE_ACTIVE_OR_ACTIVE);

    for (UserEventStage userEventStage : userEventStages) {
      List<UserEventData> collect = userEventData.stream()
          .filter(e -> e.getUserEventStage().equals(userEventStage)).collect(Collectors.toList());
      finalUserEventData.addAll(collect);
    }

    return new UserEventInfo(
        uuid,
        globalUniqueId,
        finalUserEventData,
        timeService.getNowUnixFromInstantClass()
    );
  }


  @Override
  public UserEventWithEventDataInfo getAllAggregateWithEventData(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      List<UserEventStage> userEventStages) {
    Map<UserEventStage, List<Event>> activeAndPreActive = eventService.getActiveAndPreActive(
        gamePackageName,
        env,
        marketName,
        true,
        true,
        true,
        true,
        true
    );

    UserEventInfo allAggregate = getAllAggregate(
        uuid,
        globalUniqueId,
        gamePackageName,
        env,
        marketName,
        userEventStages
    );

    return new UserEventWithEventDataInfo(
        allAggregate,
        activeAndPreActive,
        timeService.getNowUnixFromInstantClass());
  }

  @Override
  public void makeUserWinnerOrLoser(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      String eventId,
      Boolean win,
      String eventRepetitionUuId) {

    if (win) {
      userEventRepository.makeUserEventLoseOrWin(
          uuid,
          globalUniqueId,
          gamePackageName,
          env,
          marketName,
          eventId,
          UserEventWinState.WIN);

      eventWinnerHistoryService.makeWinner(
          eventId,
          gamePackageName,
          env,
          marketName,
          eventRepetitionUuId,
          uuid,
          globalUniqueId
      );
    } else {
      userEventRepository.makeUserEventLoseOrWin(
          uuid,
          globalUniqueId,
          gamePackageName,
          env,
          marketName,
          eventId,
          UserEventWinState.LOSE);
    }
  }

  @Override
  public UserEventWithEventDataInfo join(
      String uuid,
      String globalUniqueId,
      String notifToken,
      String gamePackageName,
      String env,
      String marketName,
      List<UserEventJoinData> userEventJoinData,
      List<UserEventStage> userEventStagesList) {

    List<String> validates = validateInputs(uuid, globalUniqueId);
    uuid = validates.get(0);
    globalUniqueId = validates.get(1);
    long nowTime = timeService.getNowUnixFromInstantClass();

    for (UserEventJoinData userEventJoinDatum : userEventJoinData) {
      joinEvent(uuid,
          globalUniqueId,
          notifToken,
          gamePackageName,
          env,
          marketName,
          userEventJoinDatum.getEventId(),
          nowTime,
          userEventJoinDatum.getUserEventData());
    }

    UserEventWithEventDataInfo allAggregateWithEventData = getAllAggregateWithEventDataJoin(
        uuid,
        globalUniqueId,
        gamePackageName,
        env,
        marketName,
        userEventStagesList
    );

    return allAggregateWithEventData;

  }

  ///////////////////////////////////////////////////////////////
  //////////////private methods/////////////////////////////////
  //////////////////////////////////////////////////////////////


  // with no cache
  private UserEventWithEventDataInfo getAllAggregateWithEventDataJoin(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      List<UserEventStage> userEventStages) {
    Map<UserEventStage, List<Event>> activeAndPreActive = eventService.getActiveAndPreActive(
        gamePackageName,
        env,
        marketName,
        true,
        true,
        true,
        true,
        true
    );

    UserEventInfo allAggregate = getAllAggregateJoin(
        uuid,
        globalUniqueId,
        gamePackageName,
        env,
        marketName,
        userEventStages
    );

    return new UserEventWithEventDataInfo(
        allAggregate,
        activeAndPreActive,
        timeService.getNowUnixFromInstantClass());
  }

  // with no cache
  private UserEventInfo getAllAggregateJoin(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      List<UserEventStage> userEventStages) {
    List<UserEventData> userEventData = new ArrayList<>();
    long nowTime = timeService.getNowUnixFromInstantClass();
    UserEvent userEvent = null;
    List<String> validates = validateInputs(uuid, globalUniqueId);
    uuid = validates.get(0);
    globalUniqueId = validates.get(1);
    if (uuid != null) {
      userEvent = userEventRepository.findByUuId(
          uuid,
          gamePackageName,
          env,
          marketName);
    } else if (globalUniqueId != null) {
      userEvent = userEventRepository.findByGlobalUniqueId(
          globalUniqueId,
          gamePackageName,
          env,
          marketName);
    } else {
      throw new UserEventNotFoundException();
    }

    if (userEvent != null) {
      userEventData = userEvent.getUserEventData();
    }
    List<UserEventData> finalUserEventData = new ArrayList<>();

    if (userEventStages.contains(UserEventStage.PRE_ACTIVE)
        && userEventStages.contains(UserEventStage.ACTIVE)
        && userEventStages.contains(UserEventStage.FINISHED)
        && userEventStages.contains(UserEventStage.TERMINATED)
        && userEventStages.contains(UserEventStage.CLAIMED)) {
      return new UserEventInfo(
          uuid,
          globalUniqueId,
          userEventData,
          timeService.getNowUnixFromInstantClass()
      );
    }

    if (userEventStages.contains(UserEventStage.FINISHED)) {
      List<UserEventData> collect = userEventData.stream()
          .filter(e -> e.getEndTime() <= nowTime).collect(Collectors.toList());
      userEventData.removeAll(collect);

      collect.forEach(e ->
          e.setUserEventStage(UserEventStage.FINISHED));
      finalUserEventData.addAll(collect);
      userEventStages.remove(UserEventStage.FINISHED);
    }
    if (userEventStages.contains(UserEventStage.ACTIVE)) {
      List<UserEventData> collect = userEventData.stream()
          .filter(
              e -> e.getEndTime() > nowTime && e.getStartTime() <= nowTime && e.getUserEventStage()
                  .equals(UserEventStage.PRE_ACTIVE_OR_ACTIVE))
          .collect(Collectors.toList());
      userEventData.removeAll(collect);

      collect.forEach(e ->
          e.setUserEventStage(UserEventStage.ACTIVE));
      finalUserEventData.addAll(collect);
      userEventStages.remove(UserEventStage.ACTIVE);
    }

    if (userEventStages.contains(UserEventStage.TERMINATED)) {
      List<UserEventData> collect = userEventData.stream()
          .filter(e -> e.getUserEventStage().equals(UserEventStage.TERMINATED))
          .collect(Collectors.toList());
      userEventData.removeAll(collect);

      collect.forEach(e ->
          e.setUserEventStage(UserEventStage.TERMINATED));
      finalUserEventData.addAll(collect);
      userEventStages.remove(UserEventStage.TERMINATED);
    }

    if (userEventStages.contains(UserEventStage.PRE_ACTIVE)) {
      List<UserEventData> collect = userEventData.stream()
          .filter(e -> e.getStartTime() > nowTime && e.getPreActiveStartTime() <= nowTime)
          .collect(Collectors.toList());
      userEventData.removeAll(collect);

      collect.forEach(e ->
          e.setUserEventStage(UserEventStage.PRE_ACTIVE));
      finalUserEventData.addAll(collect);
      userEventStages.remove(UserEventStage.PRE_ACTIVE);
    }
    userEventStages.remove(UserEventStage.PRE_ACTIVE_OR_ACTIVE);

    for (UserEventStage userEventStage : userEventStages) {
      List<UserEventData> collect = userEventData.stream()
          .filter(e -> e.getUserEventStage().equals(userEventStage)).collect(Collectors.toList());
      finalUserEventData.addAll(collect);
    }

    return new UserEventInfo(
        uuid,
        globalUniqueId,
        finalUserEventData,
        timeService.getNowUnixFromInstantClass()
    );
  }


  private List<String> validateInputs(String uuid, String globalUniqueId) {
    if (uuid != null) {

      if (uuid.equals("")) {
        uuid = null;
      }
    }
    if (globalUniqueId != null) {
      if (globalUniqueId.equals("")) {
        globalUniqueId = null;
      }
    }
    List<String> validates = new ArrayList<>();
    validates.add(uuid);
    validates.add(globalUniqueId);
    return validates;
  }

  private void joinEvent(
      String uuid,
      String globalUniqueId,
      String notifToken,
      String gamePackageName,
      String env,
      String marketName,
      String eventId,
      long nowTime,
      Object userMetaData) {
    UserEventData userEventData = null;
    Event event = eventRepository.findById(eventId);
    Optional<EventRepetition> eventRepetition = event.getRepetitions().stream()
        .filter(e -> e.getStartJoinTime() <= nowTime && e.getEndJoinTime() >= nowTime).findAny();
    if (!eventRepetition.isPresent()) {
      throw new EventNotFoundException();
    }

    if (eventRepetition.get().getStartJoinTime() >= nowTime) {
      throw new JoinSoonException();
    } else if (eventRepetition.get().getEndJoinTime() <= nowTime) {
      throw new JoinLateException();
    } else if (event.getEventEndType().equals(EventEndType.ABSOLUTE)) {
      userEventData = new UserEventData(
          eventId,
          UserEventStage.PRE_ACTIVE_OR_ACTIVE,
          UserEventWinState.IN_PROGRESS,
          userMetaData,
          0,
          nowTime,
          eventRepetition.get().getEndTime(),
          eventRepetition.get().getStartTime(),
          eventRepetition.get().getStartPreActiveTime(),
          eventRepetition.get().getEventReputationUuId()

      );
    } else if (event.getEventEndType().equals(EventEndType.RELATIVE)) {
      if (eventRepetition.get().getEndJoinTime() - nowTime >= 0) {
        userEventData = new UserEventData(
            eventId,
            UserEventStage.PRE_ACTIVE_OR_ACTIVE,
            UserEventWinState.IN_PROGRESS,
            userMetaData,
            0,
            nowTime,
            nowTime + event.getPeriodTimeForMiddleJoinTillEnd(),
            nowTime,
            0,
            eventRepetition.get().getEventReputationUuId()
        );
      } else {
        throw new EventJoinTimeExcption();
      }
    }

    userEventRepository.addOrUpdate(
        uuid,
        globalUniqueId,
        notifToken,
        gamePackageName,
        env,
        marketName,
        userEventData,
        eventId,
        eventRepetition.get().getEventReputationUuId());

  }

  //////////////////////////////////////
  //////////PRIVATE METHOD/////////////
  /////////////////////////////////////


  private UserEventInfo findByUuId(
      String uuid,
      String gamePackageName,
      String env,
      String marketName) {

    UserEvent userEvent = userEventRepository.findByUuId(
        uuid,
        gamePackageName,
        env,
        marketName
    );
    return UserEventMapper.toInfo(
        userEvent,
        timeService.getNowUnixFromInstantClass());
  }

  private UserEventInfo findByGlobalUniqueId(
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName) {
    UserEvent userEvent = userEventRepository.findByUuId(
        globalUniqueId,
        gamePackageName,
        env,
        marketName
    );
    return UserEventMapper.toInfo(
        userEvent,
        timeService.getNowUnixFromInstantClass());
  }
}
