package com.mirbozorgi.business.service;

import com.mirbozorgi.core.document.EventWinnersHistory;

public interface EventWinnerHistoryService {


  void makeWinner(
      String eventId,
      String gamePackageName,
      String env,
      String marketName,
      String eventRepetitionRandomUuId,
      String uuid,
      String globalUniqueId);

  EventWinnersHistory getEventWinner(
      String eventId,
      String gamePackageName,
      String env,
      String marketName,
      String eventRepetitionRandomUuId);

}
