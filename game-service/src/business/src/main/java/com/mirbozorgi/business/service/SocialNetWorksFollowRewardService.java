package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.SocialNetWorksFollowRewardInfo;
import com.mirbozorgi.core.domain.SocialNetWorkFollowData;
import java.util.List;
import java.util.Map;

public interface SocialNetWorksFollowRewardService {

  SocialNetWorksFollowRewardInfo update(
      String url,
      int gem,
      int gold,
      Short level,
      int xp,
      boolean hamiAdded,
      long addedVipPeriodTime,
      List<Integer> avatarIds,
      String nameOfSocialNet,
      int gameId,
      String gamePackageName,
      String env);

  Map<String, SocialNetWorkFollowData> getAll(
      String gamePackageName,
      String env
  );

  List<String> getAllNetworks(
      String gamePackageName,
      String env
  );

}
