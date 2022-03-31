package com.mirbozorgi.core.entity;

import com.mirbozorgi.core.utilities.MarketType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "market",
    uniqueConstraints = @UniqueConstraint(name = "UK_CLIENT_SECRET",
        columnNames = {"client_secret"}))

public class Market {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "market_type")
  @Enumerated(EnumType.STRING)
  private MarketType marketType;

  @Column(name = "client_id")
  private String clientId;

  @Column(name = "client_secret")
  private String clientSecret;

  @Column(name = "refresh_token")
  private String refreshToken;

  @Column(name = "redirect_url")
  private String redirectUrl;

  @Column(name = "bypass")
  private boolean byPass;

  @Column(name = "just_length_check")
  private boolean justLengthCheck;

  @Column(name = "max_length_verification")
  private byte maxLengthVerification;

  @Column(name = "active")
  private boolean active;

  @Column(name = "verification_url")
  private String verificationUrl;

  @Column(name = "refresh_token_url")
  private String refreshTokenUrl;

  @Column(name = "refresh_token_time_out")
  private int refreshTokenTimeOut;

  @Column(name = "access_token")
  private String accessToken;

  @Column(name = "market_url")
  private String marketUrl;

  @Column(name = "telegram_url")
  private String telegramUrl;

  @Column(name = "instagram_url")
  private String instagramUrl;

  @Column(name = "last_version")
  private int lastVersion;

  @Column(name = "support_version")
  private int supportedVersion;


  @ManyToMany(mappedBy = "markets", cascade = CascadeType.PERSIST)
  private List<Game> games;


  public Market() {
    games = new ArrayList<>();
  }

  public Market(String name, MarketType marketType, String clientId, String clientSecret,
      String refreshToken, String redirectUrl, boolean byPass, boolean justLengthCheck,
      byte maxLengthVerification, boolean active,
      String verificationUrl, String refreshTokenUrl, int refreshTokenTimeOut,
      String accessToken, String marketUrl, String telegramUrl, String instagramUrl,
      int lastVersion,
      int supportedVersion, List<Game> games) {
    this.name = name;
    this.marketType = marketType;
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.refreshToken = refreshToken;
    this.redirectUrl = redirectUrl;
    this.byPass = byPass;
    this.justLengthCheck = justLengthCheck;
    this.maxLengthVerification = maxLengthVerification;
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
    this.games = games;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public MarketType getMarketType() {
    return marketType;
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

  public List<Game> getGames() {
    return games;
  }
}
