package com.mirbozorgi.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cohort")
@CompoundIndex(
    name = "UK_NAME_MARKET_ID_GAME_PACKAGE_NAME",
    unique = true,
    def = "{'name':1,'marketId':1, 'gamePackageName':1}")

public class Cohort {

  @Id
  private ObjectId id;

  private String gamePackageName;

  private String name;

  private int chance;

  private boolean active;

  private String constCohort;

  private String market;

  private Object config;

  private String defaultCohort;

  private int minVersionClient;

  private int maxVersionClient;

  public Cohort(String gamePackageName, String name, int chance, boolean active,
      String constCohort, String market, Object config, String defaultCohort, int minVersionClient,
      int maxVersionClient) {
    this.gamePackageName = gamePackageName;
    this.name = name;
    this.chance = chance;
    this.active = active;
    this.constCohort = constCohort;
    this.config = config;
    this.defaultCohort = defaultCohort;
    this.minVersionClient = minVersionClient;
    this.maxVersionClient = maxVersionClient;
    this.market = market;
  }

  public Cohort() {
  }

  public String getMarket() {
    return market;
  }

  public ObjectId getId() {
    return id;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getName() {
    return name;
  }

  public int getChance() {
    return chance;
  }

  public boolean isActive() {
    return active;
  }

  public String getConstCohort() {
    return constCohort;
  }

  public Object getConfig() {
    return config;
  }

  public String getDefaultCohort() {
    return defaultCohort;
  }

  public int getMinVersionClient() {
    return minVersionClient;
  }

  public int getMaxVersionClient() {
    return maxVersionClient;
  }

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }
}
