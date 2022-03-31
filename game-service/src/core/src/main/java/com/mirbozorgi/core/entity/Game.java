package com.mirbozorgi.core.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "game",
    uniqueConstraints = @UniqueConstraint(name = "UK_PACKAGE_NAME_ENV",
        columnNames = {"package_name", "env"}))

public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "package_name")
  private String packageName;

  @Column(name = "active")
  private boolean active;

  @Column(name = "have_user_login")
  private boolean haveUserLogin;

  @Column(name = "env")
  private String env;

  @Column(name = "global_last_version")
  private int lastVersion;

  @Column(name = "global_support_version")
  private int supportedVersion;

  @Column(name = "instagram")
  private String instagram;

  @Column(name = "telegram")
  private String telegram;

  @Column(name = "facebook")
  private String facebook;

  @OneToOne
  private Market defaultMarket;

  @Column(name = "twitter")
  private String twitter;

  @Column(name = "linkedin")
  private String linkedin;

  @Column(name = "default_gem")
  private int defaultGem;

  @Column(name = "default_gold")
  private int defaultGold;

  @Column(name = "default_level")
  private int defaultLevel;

  @Column(name = "default_xp")
  private int defaultXp;


  @ManyToMany
  @JoinTable(name = "market_game", joinColumns = @JoinColumn(name = "game_id"),
      inverseJoinColumns = @JoinColumn(name = "market_id"))
  private List<Market> markets;

  public Game() {
    markets = new ArrayList<>();
  }


  public Game(String name,
      String packageName,
      boolean active,
      boolean haveUserLogin,
      String env,
      int lastVersion,
      int supportedVersion,
      String instagram,
      String telegram,
      String facebook,
      Market defaultMarket,
      String twitter,
      String linkedin,
      List<Market> markets,
      int defaultGem,
      int defaultGold,
      int defaultLevel,
      int defaultXp) {
    this.name = name;
    this.packageName = packageName;
    this.active = active;
    this.haveUserLogin = haveUserLogin;
    this.env = env;
    this.lastVersion = lastVersion;
    this.supportedVersion = supportedVersion;
    this.instagram = instagram;
    this.telegram = telegram;
    this.facebook = facebook;
    this.defaultMarket = defaultMarket;
    this.twitter = twitter;
    this.linkedin = linkedin;
    this.markets = markets;
    this.defaultGem = defaultGem;
    this.defaultGold = defaultGold;
    this.defaultLevel = defaultLevel;
    this.defaultXp = defaultXp;
  }

  public int getDefaultGem() {
    return defaultGem;
  }

  public void setDefaultGem(int defaultGem) {
    this.defaultGem = defaultGem;
  }

  public int getDefaultGold() {
    return defaultGold;
  }

  public void setDefaultGold(int defaultGold) {
    this.defaultGold = defaultGold;
  }

  public int getDefaultLevel() {
    return defaultLevel;
  }

  public void setDefaultLevel(int defaultLevel) {
    this.defaultLevel = defaultLevel;
  }

  public int getDefaultXp() {
    return defaultXp;
  }

  public void setDefaultXp(int defaultXp) {
    this.defaultXp = defaultXp;
  }

  public Market getDefaultMarket() {
    return defaultMarket;
  }

  public void setDefaultMarket(Market defaultMarket) {
    this.defaultMarket = defaultMarket;
  }

  public void setHaveUserLogin(boolean haveUserLogin) {
    this.haveUserLogin = haveUserLogin;
  }


  public void setMarkets(List<Market> markets) {
    this.markets = markets;
  }

  public boolean isHaveUserLogin() {
    return haveUserLogin;
  }

  public List<Market> getMarkets() {
    return markets;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setEnv(String env) {
    this.env = env;
  }

  public void setLastVersion(int lastVersion) {
    this.lastVersion = lastVersion;
  }

  public void setSupportedVersion(int supportedVersion) {
    this.supportedVersion = supportedVersion;
  }

  public void setInstagram(String instagram) {
    this.instagram = instagram;
  }

  public void setTelegram(String telegram) {
    this.telegram = telegram;
  }

  public void setFacebook(String facebook) {
    this.facebook = facebook;
  }

  public void setTwitter(String twitter) {
    this.twitter = twitter;
  }

  public void setLinkedin(String linkedin) {
    this.linkedin = linkedin;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPackageName() {
    return packageName;
  }

  public boolean isActive() {
    return active;
  }

  public String getEnv() {
    return env;
  }

  public int getLastVersion() {
    return lastVersion;
  }

  public int getSupportedVersion() {
    return supportedVersion;
  }

  public String getInstagram() {
    return instagram;
  }

  public String getTelegram() {
    return telegram;
  }

  public String getFacebook() {
    return facebook;
  }

  public String getTwitter() {
    return twitter;
  }

  public String getLinkedin() {
    return linkedin;
  }


}
