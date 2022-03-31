package mirbozorgi.base.domain.market;

import java.util.ArrayList;
import java.util.List;

public class MarketInfo {


  private int id;
  private String name;
  private String type;
  private String clientId;
  private String clientSecret;
  private String refreshToken;
  private String redirectUrl;
  private boolean byPass;
  private boolean justLengthCheck;
  private byte maxLengthVerification;
  private boolean defaultMarket;
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
  private List<Integer> gameIds;
  private List<String> gamePackageNames;


  public MarketInfo(int id, String name, String type, String clientId,
      String clientSecret, String refreshToken, String redirectUrl, boolean byPass,
      boolean justLengthCheck, byte maxLengthVerification, boolean defaultMarket, boolean active,
      String verificationUrl, String refreshTokenUrl, int refreshTokenTimeOut,
      String accessToken, String marketUrl, String telegramUrl, String instagramUrl,
      int lastVersion,
      int supportedVersion, List<Integer> gameIds, List<String> gamePackageNames) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.refreshToken = refreshToken;
    this.redirectUrl = redirectUrl;
    this.byPass = byPass;
    this.justLengthCheck = justLengthCheck;
    this.maxLengthVerification = maxLengthVerification;
    this.defaultMarket = defaultMarket;
    this.active = active;
    this.verificationUrl = verificationUrl;
    this.refreshTokenUrl = refreshTokenUrl;
    this.refreshTokenTimeOut = refreshTokenTimeOut;
    this.accessToken = accessToken;
    this.marketUrl = marketUrl;
    this.telegramUrl = telegramUrl;
    this.instagramUrl = instagramUrl;
    this.lastVersion = lastVersion;
    this.supportedVersion = supportedVersion;
    this.gameIds = gameIds;
    this.gamePackageNames = gamePackageNames;
  }


  public MarketInfo() {
    gameIds = new ArrayList<>();
    gamePackageNames = new ArrayList<>();
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getType() {
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

  public boolean isDefaultMarket() {
    return defaultMarket;
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

  public List<Integer> getGameIds() {
    return gameIds;
  }

  public List<String> getGamePackageNames() {
    return gamePackageNames;
  }
}
