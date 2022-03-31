package com.mirbozorgi.core.repository.document.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mirbozorgi.core.document.UserEventFinishedHistory;
import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.repository.document.UserEventFinishedHistoryRepository;
import com.mirbozorgi.core.domain.UserEventFinishedData;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class UserEventFinishedHistoryRepositoryImpl implements UserEventFinishedHistoryRepository {

  @Autowired
  private CustomMongoRepository repository;

  @Override
  public void add(
      String eventId,
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      UserEventFinishedData userEventData) {
    // add document for new user for first event
    if (!hasEvent(
        gamePackageName,
        globalUniqueId,
        uuid,
        env,
        marketName
    )) {

      List<UserEventFinishedData> userEventDataList = new ArrayList<>();
      userEventDataList.add(userEventData);
      repository.add(
          new UserEventFinishedHistory(
              uuid,
              globalUniqueId,
              gamePackageName,
              env,
              marketName,
              userEventDataList
          )
      );
      return;
    }
    Update update = new Update();
    update.push("userEventData", userEventData);
    if (uuid != null) {
      UserEventFinishedHistory userEvent = repository.fetchFirst(
          UserEventFinishedHistory.class,
          new String[]{"uuid",
              "globalUniqueId",
              "userEventData"},
          where("uuid").is(uuid)
              .and("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env),
          where("userEventData").elemMatch(where("eventId").is(eventId))
      );
      if (userEvent != null) {
        return;
      }

      repository.update(UserEventFinishedHistory.class, update,
          where("uuid").is(uuid)
              .and("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env));

    } else if (globalUniqueId != null) {
      UserEventFinishedHistory userEvent = repository.fetchFirst(
          UserEventFinishedHistory.class,
          new String[]{"uuid",
              "globalUniqueId",
              "userEventData"},
          where("globalUniqueId").is(globalUniqueId)
              .and("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env),
          where("userEventData").elemMatch(where("eventId").is(eventId))
      );
      if (userEvent != null) {
        return;
      }

      repository.update(UserEventFinishedHistory.class, update,
          where("globalUniqueId").is(globalUniqueId)
              .and("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env));
    }
  }

  ////////////////////////////////////////////////
  ////////////private methods///////////////
  ////////////////////////////////////////////////

  private boolean hasEvent(
      String gamePackageName,
      String globalUniqueId,
      String uuid,
      String env,
      String marketName) {
    if (uuid != null) {
      UserEventFinishedHistory userEvent = repository.fetchFirst(
          UserEventFinishedHistory.class,
          new String[]{
              "userEventData"},
          where("uuid").is(uuid)
              .and("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env)
      );
      return userEvent != null;

    } else if (globalUniqueId != null) {
      UserEventFinishedHistory userEvent = repository.fetchFirst(
          UserEventFinishedHistory.class,
          new String[]{
              "userEventData"},
          where("globalUniqueId").is(globalUniqueId)
              .and("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env)
      );
      return userEvent != null;
    }
    return false;

  }
}
