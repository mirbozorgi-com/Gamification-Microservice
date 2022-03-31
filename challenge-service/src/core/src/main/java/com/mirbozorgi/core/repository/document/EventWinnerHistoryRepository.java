package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.document.EventWinnersHistory;
import com.mirbozorgi.core.domain.EventWinnerHistory;

public interface EventWinnerHistoryRepository {

  void add(
      String eventId,
      String gamePackageName,
      String env,
      String marketName,
      String eventRepetitionRandomUuId,
      EventWinnerHistory eventWinnerHistory);

  EventWinnersHistory getEventWinnerHistory(
      String eventId,
      String gamePackageName,
      String env,
      String marketName,
      String eventRepetitionRandomUuId);
}
