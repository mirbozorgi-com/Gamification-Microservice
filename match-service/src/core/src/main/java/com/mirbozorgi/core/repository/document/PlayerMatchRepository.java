package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.domain.PlayerData;
import com.mirbozorgi.core.entity.PlayerMatch;
import java.util.List;

public interface PlayerMatchRepository {

  PlayerMatch save(PlayerMatch playerMatch);

  PlayerMatch findByMatchIdAndEmptyPlace(String matchId);

  PlayerMatch findById(String id);

  PlayerMatch join(
      String id,
      String userUuId,
      String username,
      long lastUpdateScoreTime,
      long cheatUpdateRequest,
      Boolean claim,
      Boolean banned,
      Object config);

  void updateEmptyPlaceForPlayer(Boolean hasEmpty, String id);

  PlayerMatch update(
      String id,
      List<PlayerData> players,
      Boolean active);

  PlayerMatch finish(String id);


}
