package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.UserEventJoinData;
import com.mirbozorgi.business.domain.UserEventInfo;
import com.mirbozorgi.business.domain.UserEventWithEventDataInfo;
import com.mirbozorgi.core.constant.UserEventStage;
import java.util.List;

public interface UserEventService {


  UserEventInfo getHistory(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName);

  void terminateAll(
      String gamePackageName,
      String env,
      String marketName,
      String eventId);

  UserEventWithEventDataInfo join(
      String uuid,
      String globalUniqueId,
      String notifToken,
      String gamePackageName,
      String env,
      String marketName,
      List<UserEventJoinData> userEventJoinData,
      List<UserEventStage> userEventStages);


  void close(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      String eventId,
      Boolean win);

  void closeAndClaim(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      String eventId,
      Boolean win);

  void claim(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      String eventId);


  UserEventInfo getAllAggregate(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      List<UserEventStage> userEventStages);

  UserEventWithEventDataInfo getAllAggregateWithEventData(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      List<UserEventStage> userEventStages);


  void makeUserWinnerOrLoser(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      String eventId,
      Boolean win,
      String eventRepetitionUuId);

}
