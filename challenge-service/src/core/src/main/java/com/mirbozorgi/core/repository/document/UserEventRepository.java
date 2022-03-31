package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.document.UserEvent;
import com.mirbozorgi.core.constant.UserEventStage;
import com.mirbozorgi.core.constant.UserEventWinState;
import com.mirbozorgi.core.domain.UserEventData;
import java.util.List;

public interface UserEventRepository {


  UserEvent addOrUpdate(
      String uuid,
      String globalUniqueId,
      String notifToken,
      String gamePackageName,
      String env,
      String marketName,
      UserEventData userEventData,
      String eventId,
      String eventReputationUuId
  );


  UserEvent findByUuId(
      String uuid,
      String gamePackageName,
      String env,
      String marketName);


  UserEvent findByGlobalUniqueId(
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName);


  void terminateForAll(
      String gamePackageName,
      String env,
      String marketName,
      String eventId);

  void makeUserEventLoseOrWin(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      String eventId,
      UserEventWinState userEventWinState);

  void changeStage(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      String eventId,
      UserEventStage userEventStage);

  void close(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      String eventId,
      UserEventData userEventData);

  UserEvent checkEventFinish(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      String eventId,
      long nowTime);

  List<UserEvent> checkEventExitsForUser(
      String gamePackageName,
      String env,
      String marketName,
      String eventId
  );
}
