package com.mirbozorgi.business.domain;

import com.mirbozorgi.core.domain.Wallet;

public class WalletWithVipTime {

  private Wallet wallet;
  private long endVipTime;

  public WalletWithVipTime() {
  }

  public WalletWithVipTime(Wallet wallet, long endVipTime) {
    this.wallet = wallet;
    this.endVipTime = endVipTime;
  }

  public Wallet getWallet() {
    return wallet;
  }

  public long getEndVipTime() {
    return endVipTime;
  }
}
