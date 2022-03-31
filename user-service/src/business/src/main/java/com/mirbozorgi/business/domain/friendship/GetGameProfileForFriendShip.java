package com.mirbozorgi.business.domain.friendship;

import com.mirbozorgi.core.domain.Statistic;
import com.mirbozorgi.core.domain.Wallet;

public class GetGameProfileForFriendShip {

  private String username;

  private Wallet wallet;

  private String uuid;

  private Statistic statistic;

  private long endVipTime;

  public GetGameProfileForFriendShip(
      String username,
      Wallet wallet,
      String uuid,
      Statistic statistic,
      long endVipTime) {
    this.username = username;
    this.wallet = wallet;
    this.uuid = uuid;
    this.statistic = statistic;
    this.endVipTime = endVipTime;
  }

  public long getEndVipTime() {
    return endVipTime;
  }

  public Statistic getStatistic() {
    return statistic;
  }

  public String getUuid() {
    return uuid;
  }

  public String getUsername() {
    return username;
  }

  public Wallet getWallet() {
    return wallet;
  }
}
