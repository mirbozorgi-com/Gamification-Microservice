package com.mirbozorgi.core.repository.memory;

import java.util.Set;

public interface LeaderBoardMemoryRepository {

  Long add(
      String packageName,
      String env,
      String market,
      String challengeName,
      long score,
      String playerGlobalUniqueId);


  Long update(
      String packageName,
      String env,
      String market,
      String challengeName,
      long score,
      String playerGlobalUniqueId);


  Long getPosition(
      String packageName,
      String env,
      String market,
      String challengeName,
      String playerGlobalUniqueId);

  Set<String> getCashLeaderBoard(
      String packageName,
      String env,
      String market,
      String challengeName,
      long start,
      long stop);

  void deleteSpecificKey(
      String packageName,
      String env,
      String market,
      String challengeName);

}
