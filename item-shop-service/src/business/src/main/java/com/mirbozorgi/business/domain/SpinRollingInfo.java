package com.mirbozorgi.business.domain;

import mirbozorgi.base.domain.user.WalletChange;

public class SpinRollingInfo {

  private Object config;
  private WalletChange walletChange;

  public SpinRollingInfo(Object config, WalletChange walletChange) {
    this.config = config;
    this.walletChange = walletChange;
  }

  public void setConfig(Object config) {
    this.config = config;
  }

  public void setWalletChange(WalletChange walletChange) {
    this.walletChange = walletChange;
  }

  public Object getConfig() {
    return config;
  }

  public WalletChange getWalletChange() {
    return walletChange;
  }
}
