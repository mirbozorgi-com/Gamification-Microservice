package com.mirbozorgi.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "fcm_game",
    uniqueConstraints =
    @UniqueConstraint(name = "UK_FCM",
        columnNames = {"game_package_name", "env", "market_name"})
)
public class FCMGame {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "game_package_name")
  private String gamePackageName;

  @Column(name = "env")
  private String env;

  @Column(name = "market_name")
  private String marketName;

  @Column(name = "path_of_json")
  private String pathOfJson;

  public FCMGame() {
  }

  public FCMGame(
      String gamePackageName,
      String env,
      String marketName,
      String pathOfJson) {
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.marketName = marketName;
    this.pathOfJson = pathOfJson;
  }

  public int getId() {
    return id;
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

  public String getPathOfJson() {
    return pathOfJson;
  }

  public void setPathOfJson(String pathOfJson) {
    this.pathOfJson = pathOfJson;
  }
}
