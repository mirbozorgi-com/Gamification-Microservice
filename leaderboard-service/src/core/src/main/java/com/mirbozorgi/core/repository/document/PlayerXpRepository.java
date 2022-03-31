package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.entity.PlayerXp;
import com.mirbozorgi.core.entity.PlayerXpWithTime;
import java.util.List;

public interface PlayerXpRepository {

  void upsert(
      String uuid,
      String gamepackageName,
      String env,
      String marketName,
      int xp,
      int level,
      long lastUpdate,
      String username,
      List<Integer> avatarActiveIds,
      long endVipTime);


  PlayerXp findXpByUUID(String uuid);

  PlayerXp findXp(
      String uuid,
      String gamepackageName,
      String env,
      String marketName
  );

  PlayerXpWithTime findXpTime(
      String uuid,
      String gamepackageName,
      String env,
      String marketName
  );

  PlayerXpWithTime findXpTimeByUUID(String uuid);


  void upsertWithTime(
      String uuid,
      String gamepackageName,
      String env,
      String marketName,
      int xp,
      int level,
      long lastUpdate,
      String username,
      List<Integer> avatarActiveId,
      long endVipTime);

  void resetXpsWithTime(
      String gamepackageName,
      String env,
      String marketName);

}
