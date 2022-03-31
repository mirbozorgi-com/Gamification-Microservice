package com.mirbozorgi.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirbozorgi.core.domain.ConfigData;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config")
public class Config {

  @Id
  private ObjectId id;

  private String gamePackageName;

  private String env;

  private String marketName;


  private ConfigData configData;


  public Config() {
  }

  public Config(String gamePackageName, String env, String marketName,
      ConfigData configData) {
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.marketName = marketName;
    this.configData = configData;
  }

  public ObjectId getId() {
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

  public ConfigData getConfigData() {
    return configData;
  }

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }
}