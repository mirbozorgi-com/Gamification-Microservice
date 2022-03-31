package com.mirbozorgi.business.domain.game;

import com.mirbozorgi.business.constant.UpdateStatus;
import com.mirbozorgi.business.domain.WalletChangeModelDailyReward;
import com.mirbozorgi.core.domain.UserGameProfile;

public class AddGameToUserData {

  private UserGameProfile userGameProfile;

  private Object config;

  private long serverTimeUtc;

  private UpdateStatus status;

  private WalletChangeModelDailyReward walletChangeForDailyReward;

  private long endVipTime;

  public UserGameProfile getUserGameProfile() {
    return userGameProfile;
  }

  public Object getConfig() {
    return config;
  }

  public WalletChangeModelDailyReward getWalletChangeForDailyReward() {
    return walletChangeForDailyReward;
  }

  public long getEndVipTime() {
    return endVipTime;
  }

  public long getServerTimeUtc() {
    return serverTimeUtc;
  }

  public UpdateStatus getStatus() {
    return status;
  }

  public AddGameToUserData(
      UserGameProfile userGameProfile,
      Object config,
      long serverTimeUtc,
      UpdateStatus status,
      WalletChangeModelDailyReward walletChangeForDailyReward,
      long endVipTime) {
    this.userGameProfile = userGameProfile;
    this.config = config;
    this.serverTimeUtc = serverTimeUtc;
    this.status = status;
    this.walletChangeForDailyReward = walletChangeForDailyReward;
    this.endVipTime = endVipTime;
  }
}
