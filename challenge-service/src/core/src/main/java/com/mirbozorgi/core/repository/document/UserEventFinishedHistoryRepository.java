package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.domain.UserEventFinishedData;

public interface UserEventFinishedHistoryRepository {

  void add(
      String eventId,
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      UserEventFinishedData userEventData);


}
