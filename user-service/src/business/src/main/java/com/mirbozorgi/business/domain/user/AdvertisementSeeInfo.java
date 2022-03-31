package com.mirbozorgi.business.domain.user;

import mirbozorgi.base.domain.user.WalletChange;

public class AdvertisementSeeInfo {

  private String thirdPartAdvertisementPackageName;
  private WalletChange walletChange;


  public AdvertisementSeeInfo(
      String thirdPartAdvertisementPackageName, WalletChange walletChange) {
    this.thirdPartAdvertisementPackageName = thirdPartAdvertisementPackageName;
    this.walletChange = walletChange;
  }


  public String getThirdPartAdvertisementPackageName() {
    return thirdPartAdvertisementPackageName;
  }

  public WalletChange getWalletChange() {
    return walletChange;
  }
}
