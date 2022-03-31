package com.mirbozorgi.business.domain;

import com.mirbozorgi.core.domain.WalletChangeModelDailyReward;
import java.util.List;

public class DailyRewardContinuesInfo {

  private String id;
  private int gameId;
  private String name;
  private String gamePackageName;
  private String env;
  private List<WalletChangeModelDailyReward> rewardFlows;


  public DailyRewardContinuesInfo(
      String id,
      int gameId,
      String name,
      List<WalletChangeModelDailyReward> rewardFlows,
      String gamePackageName,
      String env) {
    this.id = id;
    this.gameId = gameId;
    this.name = name;
    this.rewardFlows = rewardFlows;
    this.gamePackageName = gamePackageName;
    this.env = env;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getEnv() {
    return env;
  }

  public String getId() {
    return id;
  }

  public int getGameId() {
    return gameId;
  }

  public String getName() {
    return name;
  }

  public List<WalletChangeModelDailyReward> getRewardFlows() {
    return rewardFlows;
  }
}
