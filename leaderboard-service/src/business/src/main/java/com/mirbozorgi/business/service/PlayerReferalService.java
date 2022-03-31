package com.mirbozorgi.business.service;

import java.util.List;

public interface PlayerReferalService {

  void update(
      String uuid,
      String gamePackageName,
      String env,
      String marketName,
      int quantity,
      int level,
      String username,
      List<Integer> avatarActiveIds,
      long endVipTime);
}