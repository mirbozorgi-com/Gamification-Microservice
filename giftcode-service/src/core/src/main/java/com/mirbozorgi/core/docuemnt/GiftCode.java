package com.mirbozorgi.core.docuemnt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mirbozorgi.base.domain.user.WalletChange;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gift-code")
@CompoundIndex(
    name = "UK_CODE_GAME_PACKAGE_NAME_MARKET_NAME_ENV",
    unique = true,
    def = "{'code':1,'gamePackageName':1, 'marketName':1 , 'env':1}")
public class GiftCode {

  @Id
  private ObjectId id;

  private String name;

  private String code;

  private String gamePackageName;

  private String marketName;

  private String env;

  private WalletChange walletChange;

  private long creationDate;

  private long expireDate;

  private int maxUseCountGlobal;

  private boolean isActive;

  private int minClientVersion;

  private int maxClientVersion;

  private int minLevelPermission;

  private int daysAfterInstall;

  public GiftCode() {
  }

  public GiftCode(String name, String code, String gamePackageName, String marketName,
      String env,
      WalletChange walletChange, long creationDate, long expireDate, int maxUseCountGlobal,
      boolean isActive,
      int minClientVersion, int maxClientVersion, int minLevelPermission, int daysAfterInstall) {
    this.name = name;
    this.code = code;
    this.gamePackageName = gamePackageName;
    this.marketName = marketName;
    this.env = env;
    this.walletChange = walletChange;
    this.creationDate = creationDate;
    this.expireDate = expireDate;
    this.maxUseCountGlobal = maxUseCountGlobal;
    this.isActive = isActive;
    this.minClientVersion = minClientVersion;
    this.maxClientVersion = maxClientVersion;
    this.minLevelPermission = minLevelPermission;
    this.daysAfterInstall = daysAfterInstall;
  }

  public String getEnv() {
    return env;
  }

  public long getCreationDate() {
    return creationDate;
  }

  public ObjectId getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getMarketName() {
    return marketName;
  }


  public WalletChange getWalletChange() {
    return walletChange;
  }

  public long getExpireDate() {
    return expireDate;
  }

  public int getMaxUseCountGlobal() {
    return maxUseCountGlobal;
  }

  public boolean isActive() {
    return isActive;
  }

  public int getMinClientVersion() {
    return minClientVersion;
  }

  public int getMaxClientVersion() {
    return maxClientVersion;
  }

  public int getMinLevelPermission() {
    return minLevelPermission;
  }

  public int getDaysAfterInstall() {
    return daysAfterInstall;
  }

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }

}
