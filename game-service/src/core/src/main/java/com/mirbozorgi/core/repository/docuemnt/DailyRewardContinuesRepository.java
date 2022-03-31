package com.mirbozorgi.core.repository.docuemnt;

import com.mirbozorgi.core.document.DailyRewardContinues;
import com.mirbozorgi.core.domain.WalletChangeModelDailyReward;
import java.util.List;

public interface DailyRewardContinuesRepository {


  DailyRewardContinues update(
      List<WalletChangeModelDailyReward> walletChangeModels,
      String name,
      int gameId,
      String gamePackageName,
      String env);

  List<WalletChangeModelDailyReward> getAllDailyFlows(
      String gamePackageName,
      String env,
      String name
  );


}
