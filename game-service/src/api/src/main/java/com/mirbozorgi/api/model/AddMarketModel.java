package com.mirbozorgi.api.model;

import com.mirbozorgi.core.utilities.MarketType;

public class AddMarketModel {

  private String name;
  private MarketType type;
  private String clientId;
  private String clientSecret;
  private String refreshToken;
  private String redirectUrl;
  private boolean byPass;
  private boolean justLengthCheck;
  private byte maxLengthVerification;
  private boolean active;
  private String verificationUrl;
  private String refreshTokenUrl;
  private int refreshTokenTimeOut;
  private String accessToken;
  private String marketUrl;
  private String telegramUrl;
  private String instagramUrl;
  private int lastVersion;
  private int supportedVersion;

  public String getName() {
    return name;
  }

  public MarketType getType() {
    return type;
  }

  public String getClientId() {
    return clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public String getRedirectUrl() {
    return redirectUrl;
  }

  public boolean isByPass() {
    return byPass;
  }

  public boolean isJustLengthCheck() {
    return justLengthCheck;
  }

  public byte getMaxLengthVerification() {
    return maxLengthVerification;
  }

  public boolean isActive() {
    return active;
  }

  public String getVerificationUrl() {
    return verificationUrl;
  }

  public String getRefreshTokenUrl() {
    return refreshTokenUrl;
  }

  public int getRefreshTokenTimeOut() {
    return refreshTokenTimeOut;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public String getMarketUrl() {
    return marketUrl;
  }

  public String getTelegramUrl() {
    return telegramUrl;
  }

  public String getInstagramUrl() {
    return instagramUrl;
  }

  public int getLastVersion() {
    return lastVersion;
  }

  public int getSupportedVersion() {
    return supportedVersion;
  }
}
