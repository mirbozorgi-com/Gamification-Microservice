package com.mirbozorgi.business.service;

import java.util.List;

public interface PlayerGemBuyService {

  void update(
      String uuid,
      String gamePackageName,
      String env,
      String marketName,
      int gem,
      String username,
      List<Integer> avatarIds,
      int level,
      long endVipTime);
}
