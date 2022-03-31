package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.PlayerMatchData;
import com.mirbozorgi.core.domain.PlayerData;
import java.util.List;

public interface PlayerMatchService {


  PlayerMatchData join(
      String gamePackageName,
      String market,
      String matchName,
      String env,
      String userUuId,
      String username,
      Object config);

  PlayerMatchData offlineJoin(
      String userUuId,
      Object config,
      int numberOfPlayerWanted,
      String matchId,
      int currentLevel,
      List<Integer> currentAvatarIds,
      String username);


  PlayerMatchData update(
      String id,
      List<PlayerData> players,
      Boolean active);

  PlayerMatchData load(String id, String playerUuId);


  PlayerMatchData finish(
      String id,
      String userUuId,
      Boolean win,
      int currentLevel,
      List<Integer> currentAvatarIds,
      long endTimeVip);
}
