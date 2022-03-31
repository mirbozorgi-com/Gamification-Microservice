package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.DailyRewardContinuesInfo;
import com.mirbozorgi.core.domain.WalletChangeModelDailyReward;
import java.util.List;

public interface DailyRewardContinuesService {


  DailyRewardContinuesInfo update(
      List<WalletChangeModelDailyReward> walletChangeModels,
      String name,
      int gameId);

  List<WalletChangeModelDailyReward> getAllFlow(
      String gamePackageName,
      String env,
      String name
  );


}
