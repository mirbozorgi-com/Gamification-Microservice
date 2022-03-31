package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.EventInfo;
import com.mirbozorgi.core.constant.EventEndType;
import com.mirbozorgi.core.constant.UserEventStage;
import com.mirbozorgi.core.document.Event;
import com.mirbozorgi.core.domain.EventRepetition;
import java.util.List;
import java.util.Map;

public interface EventService {

  EventInfo add(
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
      String clientEventType);

  Map<UserEventStage, List<Event>> getActiveAndPreActive(
      String gamePackageName,
      String env,
      String marketName,
      Boolean active,
      Boolean preActive,
      Boolean finish,
      Boolean terminated,
      Boolean notStarted
  );

  EventInfo update(
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
      String clientEventType);

  EventInfo getById(
      String id,
      String clientVersion);

  List<EventInfo> get(
      String gamePackageName,
      String env,
      String marketName,
      Integer limit);

  void delete(String id);

  void terminate(String id);

}
