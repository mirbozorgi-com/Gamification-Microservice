package com.mirbozorgi.core.repository.document.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mirbozorgi.core.constant.UserEventStage;
import com.mirbozorgi.core.constant.UserEventWinState;
import com.mirbozorgi.core.document.UserEvent;
import com.mirbozorgi.core.domain.UserEventData;
import com.mirbozorgi.core.domain.UserEventJoinedData;
import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.repository.document.UserEventRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class UserEventRepositoryImpl implements UserEventRepository {

  @Autowired
  private CustomMongoRepository repository;


  @Override
  public UserEvent addOrUpdate(
      String uuid,
      String globalUniqueId,
      String notifToken,
      String gamePackageName,
      String env,
      String marketName,
      UserEventData userEventData,
      String eventId,
      String eventReputationUuId
  ) {

    // add document for new user for first event
    if (!hasEvent(gamePackageName,
        globalUniqueId,
        uuid,
        env,
        marketName)) {

      List<UserEventData> userEventDataList = new ArrayList<>();
      List<UserEventJoinedData> joinedEventIds = new ArrayList<>();
      joinedEventIds.add(new UserEventJoinedData(eventReputationUuId));
      userEventDataList.add(userEventData);
      return repository.add(
          new UserEvent(
              uuid,
              globalUniqueId,
              notifToken,
              gamePackageName,
              env,
              marketName,
              userEventDataList,
              joinedEventIds
          )
      );
    }
    Update update = new Update();
    update.push("userEventData", userEventData);
    update.push("joinedEventReputationUuIds", new UserEventJoinedData(eventReputationUuId));
    if (uuid != null) {
      UserEvent userEvent = repository.fetchFirst(
          UserEvent.class,
          new String[]{"uuid",
              "globalUniqueId",
              "joinedEventIds",
              "userEventData"},
          where("uuid").is(uuid)
              .and("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env),
          where("joinedEventReputationUuIds")
              .elemMatch(where("eventReputationUuId").is(eventReputationUuId))
      );

      if (userEvent != null) {
        return userEvent;
      }

      repository.update(UserEvent.class, update,
          where("uuid").is(uuid)
              .and("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env));
      return findByUuId(
          uuid,
          gamePackageName,
          env,
          marketName
      );

    } else if (globalUniqueId != null) {
      UserEvent userEvent = repository.fetchFirst(
          UserEvent.class,
          new String[]{"uuid",
              "globalUniqueId",
              "userEventData"},
          where("globalUniqueId").is(globalUniqueId)
              .and("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env),
          where("joinedEventReputationUuIds")
              .elemMatch(where("eventReputationUuId").is(eventReputationUuId)));
      if (userEvent != null) {
        return userEvent;
      }

      repository.update(UserEvent.class, update,
          where("globalUniqueId").is(globalUniqueId)
              .and("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env));
      return findByGlobalUniqueId(
          globalUniqueId,
          gamePackageName,
          env,
          marketName
      );
    }
    return null;
  }


  @Override
  public UserEvent findByUuId(
      String uuid,
      String gamePackageName,
      String env,
      String marketName) {
    return repository.fetchFirst(
        UserEvent.class,
        new String[]{"uuid",
            "globalUniqueId",
            "joinedEventIds",
            "userEventData"},
        where("uuid").is(uuid)
            .and("gamePackageName").is(gamePackageName)
            .and("marketName").is(marketName)
            .and("env").is(env)
    );
  }


  @Override
  public UserEvent findByGlobalUniqueId(
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName) {
    return repository.fetchFirst(
        UserEvent.class,
        new String[]{"uuid",
            "globalUniqueId",
            "joinedEventIds",
            "userEventData"},
        where("globalUniqueId").is(globalUniqueId)
            .and("gamePackageName").is(gamePackageName)
            .and("marketName").is(marketName)
            .and("env").is(env)
    );
  }


  @Override
  public void terminateForAll(
      String gamePackageName,
      String env,
      String marketName,
      String eventId) {
    Update update = new Update();
    update.set("userEventData.$.userEventStage", UserEventStage.TERMINATED);
    repository.updateAll(UserEvent.class, update,
        where("gamePackageName").is(gamePackageName)
            .and("marketName").is(marketName)
            .and("env").is(env),
        Criteria.where("userEventData").elemMatch(Criteria.where("eventId").is(eventId)
        ));
  }

  @Override
  public void makeUserEventLoseOrWin(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      String eventId,
      UserEventWinState userEventWinState) {

    Update update = new Update();
    update.set("userEventData.$.userEventWinState", userEventWinState);

    if (uuid != null) {
      repository.update(UserEvent.class, update,
          where("uuid").is(uuid)
              .and("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env),
          Criteria.where("userEventData").elemMatch(Criteria.where("eventId").is(eventId)));
    } else if (globalUniqueId != null) {
      repository.update(UserEvent.class, update,
          where("globalUniqueId").is(globalUniqueId)
              .and("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env),
          Criteria.where("userEventData").elemMatch(Criteria.where("eventId").is(eventId)));
    }

  }

  @Override
  public void changeStage(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      String eventId,
      UserEventStage userEventStage) {
    Update update = new Update();
    update.set("userEventData.$.userEventStage", userEventStage);
    if (uuid != null) {
      repository.update(UserEvent.class, update,
          where("uuid").is(uuid)
              .and("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env),
          Criteria.where("userEventData").elemMatch(Criteria.where("eventId").is(eventId)));
    } else if (globalUniqueId != null) {
      repository.update(UserEvent.class, update,
          where("globalUniqueId").is(globalUniqueId)
              .and("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env),
          Criteria.where("userEventData").elemMatch(Criteria.where("eventId").is(eventId)));
    }


  }

  @Override
  public void close(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      String eventId,
      UserEventData userEventData) {
    Update update = new Update();
    update.pull("userEventData", userEventData);

    if (uuid != null) {
      repository.update(UserEvent.class, update,
          where("uuid").is(uuid)
              .and("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env),
          Criteria.where("userEventData").elemMatch(Criteria.where("eventId").is(eventId)));
    } else if (globalUniqueId != null) {
      repository.update(UserEvent.class, update,
          where("globalUniqueId").is(globalUniqueId)
              .and("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env),
          Criteria.where("userEventData").elemMatch(Criteria.where("eventId").is(eventId)));
    }
  }


  @Override
  public UserEvent checkEventFinish(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      String eventId,
      long nowTime) {

    if (uuid != null) {

      return repository.fetchFirst(
          UserEvent.class,
          new String[]{"uuid",
              "globalUniqueId",
              "joinedEventIds",
              "userEventData"},
          where("uuid").is(uuid)
              .and("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env),
          Criteria.where("userEventData")
              .elemMatch(Criteria.where("eventId").is(eventId).and("endTime").lte(nowTime))
      );

    } else if (globalUniqueId != null) {

      return repository.fetchFirst(
          UserEvent.class,
          new String[]{},
          where("globalUniqueId").is(globalUniqueId)
              .and("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env),
          where("userEventData").elemMatch(where("eventId").is(eventId).
              and("endTime").lte(nowTime)

          ));
    } else {

      return null;

    }
  }

  @Override
  public List<UserEvent> checkEventExitsForUser(
      String gamePackageName,
      String env,
      String marketName,
      String eventId) {
    List<UserEvent> userDontHaveThisEvent = repository.fetch(
        UserEvent.class,
        new String[]{"uuid",
            "globalUniqueId",
            "notificationToken",
            "userEventData"},
        where("gamePackageName").is(gamePackageName)
            .and("marketName").is(marketName)
            .and("env").is(env),
        where("userEventData").ne(eventId));
    return userDontHaveThisEvent;
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
      UserEvent userEvent = repository.fetchFirst(
          UserEvent.class,
          new String[]{
              "userEventDataMap"},
          where("uuid").is(uuid)
              .and("gamePackageName").is(gamePackageName)
              .and("marketName").is(marketName)
              .and("env").is(env)
      );
      return userEvent != null;

    } else if (globalUniqueId != null) {
      UserEvent userEvent = repository.fetchFirst(
          UserEvent.class,
          new String[]{
              "userEventDataMap"},
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
