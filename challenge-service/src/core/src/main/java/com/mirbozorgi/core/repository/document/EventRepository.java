package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.constant.EventEndType;
import com.mirbozorgi.core.constant.UserEventStage;
import com.mirbozorgi.core.document.Event;
import com.mirbozorgi.core.domain.EventRepetition;
import java.util.List;
import java.util.Map;

public interface EventRepository {

  Event add(Event event);

  Event update(
      String id,
      String name,
      String gamePackageName,
      String marketName,
      String env,
      long periodTimeForMiddleJoinTillEnd,
      Map<String, Object> states,
      List<EventRepetition> repetitions,
      Map<String, Object> versionMetaData,
      EventEndType eventEndType,
      String clientEventType,
      int configVersion);

  Event findById(String id);

  Event findByIdAndClientVersion(
      String id,
      String clientVersion
  );

  List<Event> find(
      String gamePackageName,
      String env,
      String marketName,
      int limit);

  List<Event> findAllStartedEvent(long nowTime);

  Map<UserEventStage, List<Event>> findActiveAndPreActive(
      String gamePackageName,
      String env,
      String marketName,
      long nowTime,
      Boolean active,
      Boolean preActive,
      Boolean finish,
      Boolean terminated,
      Boolean notStart);

  void delete(String id);

  void terminate(String id, long nowTime);

}
