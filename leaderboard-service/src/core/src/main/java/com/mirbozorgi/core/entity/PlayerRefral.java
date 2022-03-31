package com.mirbozorgi.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirbozorgi.core.domain.PlayerRefralData;
import java.util.HashMap;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "player_refral")
public class PlayerRefral {

  @Id
  private ObjectId id;
  private String userUuId;

  //key : packageName.env.marketName
  private Map<String, Map<String, Map<String, PlayerRefralData>>> playerRefrals;


  public PlayerRefral(String userUuId,
      Map<String, Map<String, Map<String, PlayerRefralData>>> playerRefrals) {
    this.userUuId = userUuId;
    this.playerRefrals = playerRefrals;
  }

  public PlayerRefral() {
    playerRefrals = new HashMap<>();
  }

  public ObjectId getId() {
    return id;
  }

  public String getUserUuId() {
    return userUuId;
  }

  public Map<String, Map<String, Map<String, PlayerRefralData>>> getPlayerRefrals() {
    return playerRefrals;
  }

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }
}
