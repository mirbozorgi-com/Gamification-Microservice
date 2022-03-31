package com.mirbozorgi.core.repository.document.impl;

import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.constant.EventEndType;
import com.mirbozorgi.core.constant.UserEventStage;
import com.mirbozorgi.core.document.Event;
import com.mirbozorgi.core.domain.EventRepetition;
import com.mirbozorgi.core.repository.document.EventRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepositoryImpl implements EventRepository {

  @Autowired
  private CustomMongoRepository repository;

  @Override
  public Event add(Event event) {
    return repository.add(event);
  }

  @Override
  public Event update(
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
      int configVersion) {
    Update update = new Update();
    update.set("clientEventType", clientEventType);
    update.set("gamePackageName", gamePackageName);
    update.set("eventEndType", eventEndType);
    update.set("marketName", marketName);
    update.set("env", env);
    update.set("name", name);
    update.set("configVersion", configVersion);
    update.set("periodTimeForMiddleJoinTillEnd", periodTimeForMiddleJoinTillEnd);
    update.set("states", states);
    update.set("repetitions", repetitions);
    update.set("versionMetaData", versionMetaData);
    repository.update(Event.class, update, id);
    return findById(id);
  }

  @Override
  public Event findById(String id) {
    return repository.fetchFirst(Event.class, new ObjectId(id));
  }

  @Override
  public Event findByIdAndClientVersion(
      String id,
      String clientVersion) {

    return repository.fetchFirst(
        Event.class,
        new String[]{
            "name",
            "gamePackageName",
            "marketName",
            "configVersion",
            "env",
            "canMiddleJoin",
            "periodTimeForMiddleJoinTillEnd",
            "states",
            "repetitions",
            "versionMetaData"
        },
        Criteria.where("id").is(new ObjectId(id)));
  }

  @Override
  public List<Event> find(
      String gamePackageName,
      String env,
      String marketName,
      int limit) {
    if (limit <= 100) {
      limit = 100;
    }
    return repository.fetch(
        Event.class,
        new String[]{
            "name",
            "gamePackageName",
            "marketName",
            "env",
            "canMiddleJoin",
            "periodTimeForMiddleJoinTillEnd",
            "states",
            "configVersion",
            "repetitions",
            "versionMetaData"
        },
        null,
        limit,
        Criteria.where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
            .and("marketName").is(marketName)
    );

  }

  @Override
  public List<Event> findAllStartedEvent(long nowTime) {
    return repository.fetch(
        Event.class,
        new String[]{
            "name",
            "gamePackageName",
            "marketName",
            "env",
            "canMiddleJoin",
            "periodTimeForMiddleJoinTillEnd",
            "states",
            "repetitions",
            "versionMetaData"
        },
        Criteria.where("repetitions").elemMatch(Criteria.where("startTime").lte(nowTime)
            .and("endTime").gte(nowTime)
            .and("terminated").is(false)
        ));
  }

  @Override
  public Map<UserEventStage, List<Event>> findActiveAndPreActive(
      String gamePackageName,
      String env,
      String marketName,
      long nowTime,
      Boolean active,
      Boolean preActive,
      Boolean finish,
      Boolean terminated,
      Boolean notStart) {
    Map<UserEventStage, List<Event>> eventStageListMap = new HashMap<>();
    if (active) {
      // fetch all active

      List<Event> fetch = repository.fetch(Event.class,
          new String[]{
              "name",
              "clientEventType",
              "gamePackageName",
              "configVersion",
              "marketName",
              "env",
              "periodTimeForMiddleJoinTillEnd",
              "eventEndType",
              "states",
              "repetitions",
              "versionMetaData"},
          Criteria.where("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env),
          Criteria.where("repetitions").elemMatch(Criteria.where("startTime").lte(nowTime)
              .and("endTime").gte(nowTime)
              .and("terminated").is(false)
          )
      );
      eventStageListMap.put(UserEventStage.ACTIVE, fetch);
    }
    if (preActive) {
      // fetch all not begin
      List<Event> fetch = repository.fetch(Event.class,
          new String[]{
              "name",
              "clientEventType",
              "gamePackageName",
              "marketName",
              "env",
              "configVersion",
              "periodTimeForMiddleJoinTillEnd",
              "eventEndType",
              "states",
              "repetitions",
              "versionMetaData"},
          Criteria.where("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env),
          Criteria.where("repetitions").elemMatch(Criteria.where("startTime").gte(nowTime)
              .and("terminated").is(false)
              .and("startPreActiveTime").lte(nowTime)
          )
      );
      eventStageListMap.put(UserEventStage.PRE_ACTIVE, fetch);
    }
    if (finish) {
      // fetch all finished
      List<Event> fetch = repository.fetch(Event.class,
          new String[]{
              "name",
              "clientEventType",
              "gamePackageName",
              "marketName",
              "env",
              "configVersion",
              "periodTimeForMiddleJoinTillEnd",
              "eventEndType",
              "states",
              "repetitions",
              "versionMetaData"},
          Criteria.where("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env),
          Criteria.where("repetitions").elemMatch(Criteria.where("startTime").lt(nowTime)
              .and("terminated").is(false)
              .and("endTime").lt(nowTime)
          )
      );
      eventStageListMap.put(UserEventStage.FINISHED, fetch);
    }
    if (terminated) {
      List<Event> fetch = repository.fetch(Event.class,
          new String[]{
              "name",
              "clientEventType",
              "gamePackageName",
              "marketName",
              "env",
              "configVersion",
              "periodTimeForMiddleJoinTillEnd",
              "eventEndType",
              "states",
              "repetitions",
              "versionMetaData"},
          Criteria.where("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env),
          Criteria.where("repetitions").elemMatch(Criteria.where("terminated").is(true))
      );
      eventStageListMap.put(UserEventStage.TERMINATED, fetch);

    }

    if (notStart) {
      List<Event> fetch = repository.fetch(Event.class,
          new String[]{
              "name",
              "clientEventType",
              "gamePackageName",
              "marketName",
              "env",
              "configVersion",
              "periodTimeForMiddleJoinTillEnd",
              "eventEndType",
              "states",
              "repetitions",
              "versionMetaData"},
          Criteria.where("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env),
          Criteria.where("repetitions").elemMatch(Criteria.where("startTime").gte(nowTime)
              .and("startPreActiveTime").gte(nowTime)
              .and("terminated").is(false)
          )
      );
      eventStageListMap.put(UserEventStage.NOT_START, fetch);
    }

    List<Event> joinAble = repository.fetch(Event.class,
        new String[]{
            "name",
            "clientEventType",
            "gamePackageName",
            "marketName",
            "env",
            "configVersion",
            "periodTimeForMiddleJoinTillEnd",
            "eventEndType",
            "states",
            "repetitions",
            "versionMetaData"},
        Criteria.where("gamePackageName").is(gamePackageName)
            .and("marketName").is(marketName)
            .and("env").is(env),
        Criteria.where("repetitions").elemMatch(Criteria.where("startJoinTime").lte(nowTime)
            .and("endJoinTime").gt(nowTime)
            .and("endTime").gt(nowTime)
            .and("startTime").lt(nowTime)
            .and("terminated").is(false)
        )
    );
    eventStageListMap.put(UserEventStage.JOINABLE, joinAble);
    return eventStageListMap;
  }

  @Override
  public void delete(String id) {
    Event event = findById(id);
    if (event != null) {
      repository.delete(event);
    }
  }

  @Override
  public void terminate(String id, long nowTime) {
    Update update = new Update();
    update.set("repetitions.$.terminated", true);
    repository.update(Event.class, update,
        Criteria.where("id").is(new ObjectId(id)),
        Criteria.where("repetitions").elemMatch(Criteria.where("startTime").lte(nowTime)
            .and("endTime").gte(nowTime)
        )
    );

  }
}
