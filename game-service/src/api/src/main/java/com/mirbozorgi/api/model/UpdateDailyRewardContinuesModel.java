package com.mirbozorgi.api.model;

import com.mirbozorgi.core.domain.WalletChangeModelDailyReward;
import java.util.List;

public class UpdateDailyRewardContinuesModel {

  private List<WalletChangeModelDailyReward> dailyRewardContinuesData;
  private String name;
  private int gameId;

  public List<WalletChangeModelDailyReward> getDailyRewardContinuesData() {
    return dailyRewardContinuesData;
  }

  public String getName() {
    return name;
  }

  public int getGameId() {
    return gameId;
  }
}
