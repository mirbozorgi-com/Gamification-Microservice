package com.mirbozorgi.business.domain;

import com.mirbozorgi.core.domain.LeaderBoardType;
import java.util.List;
import java.util.Map;
import mirbozorgi.base.domain.user.WalletChange;

public class LeaderBoardRewardInfo {

  private String id;

  private String gamePackageName;

  private String env;

  private String marketName;

  //key :type
  private Map<LeaderBoardType, List<WalletChange>> rewards;


  public LeaderBoardRewardInfo(
      String id,
      String gamePackageName,
      String env,
      String marketName,
      Map<LeaderBoardType, List<WalletChange>> rewards) {
    this.id = id;
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.marketName = marketName;
    this.rewards = rewards;
  }

  public String getId() {
    return id;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getEnv() {
    return env;
  }

  public String getMarketName() {
    return marketName;
  }

  public Map<LeaderBoardType, List<WalletChange>> getRewards() {
    return rewards;
  }
}
