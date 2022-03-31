package com.mirbozorgi.business.service;

import java.util.List;

public interface PlayerXpService {


  void update(
      String uuid,
      String gamepackageName,
      String env,
      String marketName,
      int xpGlobal,
      int xp,
      int level,
      String username,
      List<Integer> avatarActiveIds,
      long endVipTime);

  void resetWithTime(
      String gamepackageName,
      String env,
      String marketName);
}
