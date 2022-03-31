package com.mirbozorgi.core.repository.document.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.repository.document.EventWinnerHistoryRepository;
import com.mirbozorgi.core.document.EventWinnersHistory;
import com.mirbozorgi.core.domain.EventWinnerHistory;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class EventWinnerHistoryRepositoryImpl implements EventWinnerHistoryRepository {

  @Autowired
  private CustomMongoRepository repository;

  @Override
  public void add(
      String eventId,
      String gamePackageName,
      String env,
      String marketName,
      String eventRepetitionRandomUuId,
      EventWinnerHistory eventWinnerHistory) {

    if (hasEvent(
        eventId,
        gamePackageName,
        eventWinnerHistory.getGlobalUniqueId(),
        eventWinnerHistory.getUuid(),
        env,
        marketName,
        eventRepetitionRandomUuId)) {

      List<EventWinnerHistory> eventWinnerHistories = new ArrayList<>();
      eventWinnerHistories.add(eventWinnerHistory);
      Map<String, List<EventWinnerHistory>> winnerMap = new HashMap<>();
      winnerMap.put(eventRepetitionRandomUuId, eventWinnerHistories);
      repository.add(
          new EventWinnersHistory(
              eventId,
              gamePackageName,
              env,
              marketName,
              winnerMap
          )
      );
      return;
    }
    Update update = new Update();
    update.push("userWinnerEventData." + eventRepetitionRandomUuId, eventWinnerHistory);
    repository.update(EventWinnersHistory.class, update,
        where("eventId").is(eventId)
            .and("gamePackageName").is(gamePackageName)
            .and("marketName").is(marketName)
            .and("env").is(env));

  }

  @Override
  public EventWinnersHistory getEventWinnerHistory(
      String eventId,
      String gamePackageName,
      String env,
      String marketName,
      String eventRepetitionRandomUuId) {
    if (eventRepetitionRandomUuId == null) {
      return getEventAllWinnersHistory(
          eventId,
          gamePackageName,
          env,
          marketName
      );
    } else {
      return getEventKeyWinnersHistory(
          eventRepetitionRandomUuId,
          eventId,
          gamePackageName,
          env,
          marketName);
    }
  }

  ////////////////////////////////////////////////
  ////////////private methods///////////////
  ////////////////////////////////////////////////

  private EventWinnersHistory getEventAllWinnersHistory(
      String eventId,
      String gamePackageName,
      String env,
      String marketName) {
    return repository.fetchFirst(
        EventWinnersHistory.class,
        new String[]{
            "eventId",
            "gamePackageName",
            "marketName",
            "env",
            "userWinnerEventData"},
        where("eventId").is(eventId)
            .and("gamePackageName").is(gamePackageName)
            .and("marketName").is(marketName)
            .and("env").is(env)
    );
  }

  private EventWinnersHistory getEventKeyWinnersHistory(
      String key,
      String eventId,
      String gamePackageName,
      String env,
      String marketName) {
    EventWinnersHistory eventWinnersHistory = repository.fetchFirst(
        EventWinnersHistory.class,
        new String[]{
            "eventId",
            "gamePackageName",
            "marketName",
            "env",
            "userWinnerEventData." + key},
        where("eventId").is(eventId)
            .and("gamePackageName").is(gamePackageName)
            .and("marketName").is(marketName)
            .and("env").is(env)
    );
    Map<String, List<EventWinnerHistory>> versionMetaData = new HashMap<>();

    List<EventWinnerHistory> collect = eventWinnersHistory.getUserWinnerEventData().get(key)
        .stream().sorted(Comparator.comparingInt(EventWinnerHistory::getScore)).collect(
            Collectors.toList());
    versionMetaData.put(key, collect);
    eventWinnersHistory.setUserWinnerEventData(versionMetaData);

    return eventWinnersHistory;
  }


  private boolean hasEvent(
      String eventId,
      String gamePackageName,
      String globalUniqueId,
      String uuid,
      String env,
      String marketName,
      String key) {

    EventWinnersHistory eventWinnersHistory = repository.fetchFirst(
        EventWinnersHistory.class,
        new String[]{
            "eventId",
            "userWinnerEventData"
        },
        where("eventId").is(eventId)
            .and("gamePackageName").is(gamePackageName)
            .and("marketName").is(marketName)
            .and("env").is(env)
    );

    if (eventWinnersHistory == null) {
      return true;
    } else {
      EventWinnersHistory checkUserWinnerHistory = null;
      if (uuid != null && !eventWinnersHistory.getUserWinnerEventData().containsKey(key)) {
        checkUserWinnerHistory = repository.fetchFirst(
            EventWinnersHistory.class,
            new String[]{
                "eventId",
                "userWinnerEventData"
            },
            where("eventId").is(eventId)
                .and("gamePackageName").is(gamePackageName)
                .and("marketName").is(marketName)
                .and("env").is(env),
            where("userWinnerEventData").elemMatch(where("uuid").is(uuid)
            ));
      } else if (globalUniqueId != null && eventWinnersHistory.getUserWinnerEventData()
          .containsKey(key)) {
        checkUserWinnerHistory = repository.fetchFirst(
            EventWinnersHistory.class,
            new String[]{
                "eventId",
                "userWinnerEventData"
            },
            where("eventId").is(eventId)
                .and("gamePackageName").is(gamePackageName)
                .and("marketName").is(marketName)
                .and("env").is(env),
            where("userWinnerEventData").elemMatch(where("globalUniqueId").is(eventId)
            ));
      }
      return checkUserWinnerHistory != null;
    }
  }
}
