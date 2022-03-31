package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.domain.LeaderBoardType;
import com.mirbozorgi.core.entity.LeaderBoardReward;
import java.util.List;
import mirbozorgi.base.domain.user.WalletChange;

public interface LeaderBoardRewardRepository {

  LeaderBoardReward add(
      String gamePackageName,
      String env,
      String marketName,
      LeaderBoardType type,
      WalletChange walletChange);


  LeaderBoardReward update(
      String gamePackageName,
      String env,
      String marketName,
      LeaderBoardType type,
      List<WalletChange> walletChanges);

  void delete(String id);

  LeaderBoardReward findBy(
      String gamePackageName,
      String env,
      String marketName);


}
