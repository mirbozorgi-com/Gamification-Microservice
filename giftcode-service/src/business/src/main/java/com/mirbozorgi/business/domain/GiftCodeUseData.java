package com.mirbozorgi.business.domain;

import mirbozorgi.base.domain.user.WalletChange;

public class GiftCodeUseData {


  private String id;

  private String userUuId;

  private String code;

  private long usedDate;

  private String gamePackageName;

  private String env;

  private String marketName;

  private WalletChange walletChange;


  public GiftCodeUseData(
      String id,
      String userUuId,
      String code,
      long usedDate,
      String gamePackageName,
      String env,
      String marketName,
      WalletChange walletChange) {
    this.id = id;
    this.userUuId = userUuId;
    this.code = code;
    this.usedDate = usedDate;
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.marketName = marketName;
    this.walletChange = walletChange;
  }

  public WalletChange getWalletChange() {
    return walletChange;
  }

  public String getId() {
    return id;
  }

  public String getUserUuId() {
    return userUuId;
  }

  public String getCode() {
    return code;
  }

  public long getUsedDate() {
    return usedDate;
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
}
