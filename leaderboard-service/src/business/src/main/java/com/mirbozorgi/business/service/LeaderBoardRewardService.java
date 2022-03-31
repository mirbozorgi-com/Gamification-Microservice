package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.LeaderBoardRewardInfo;
import com.mirbozorgi.core.domain.LeaderBoardType;
import java.util.List;
import mirbozorgi.base.domain.user.WalletChange;

public interface LeaderBoardRewardService {

  LeaderBoardRewardInfo add(
      String gamePackageName,
      String env,
      String marketName,
      LeaderBoardType type,
      int gem,
      int gold,
      String level,
      int xp,
      boolean hamiAded,
      List<Integer> avatarIds,
      long addedVipPeriodTime);


  LeaderBoardRewardInfo update(
      String gamePackageName,
      String env,
      String marketName,
      LeaderBoardType type,
      List<WalletChange> walletChanges);


  void delete(String id);

  LeaderBoardRewardInfo findBy(
      String gamePackageName,
      String env,
      String marketName);

  List<WalletChange> findRewardByType(
      LeaderBoardType type,
      String gamePackageName,
      String env,
      String marketName
  );


  WalletChange claim(
      LeaderBoardType type,
      String gamePackageName,
      String env,
      String marketName,
      String uuid,
      String username,
      int currentLevel,
      List<Integer> currentAvatarIds,
      long endVipTime
  );

}
