package com.mirbozorgi.core.domain;

import mirbozorgi.base.domain.user.WalletChange;

public class SocialNetWorkFollowData {

  private WalletChange walletChange;

  private String url;

  public SocialNetWorkFollowData(WalletChange walletChange, String url) {
    this.walletChange = walletChange;
    this.url = url;
  }

  public SocialNetWorkFollowData() {
  }

  public WalletChange getWalletChange() {
    return walletChange;
  }

  public String getUrl() {
    return url;
  }
}
