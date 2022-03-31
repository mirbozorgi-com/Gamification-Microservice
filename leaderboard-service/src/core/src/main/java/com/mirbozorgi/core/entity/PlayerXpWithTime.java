package com.mirbozorgi.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirbozorgi.core.domain.PlayerXpData;
import java.util.HashMap;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "player_xp_with_time")
public class PlayerXpWithTime {

  @Id
  private ObjectId id;

  private String userUuId;

  //key : packageName.env.marketName
  private Map<String, Map<String, Map<String, PlayerXpData>>> playerXpDatas;

  public PlayerXpWithTime(String userUuId,
      Map<String, Map<String, Map<String, PlayerXpData>>> playerXpDatas) {
    this.userUuId = userUuId;
    this.playerXpDatas = playerXpDatas;
  }

  public PlayerXpWithTime() {
    playerXpDatas = new HashMap<>();
  }

  public ObjectId getId() {
    return id;
  }

  public String getUserUuId() {
    return userUuId;
  }

  public Map<String, Map<String, Map<String, PlayerXpData>>> getPlayerXpDatas() {
    return playerXpDatas;
  }

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }
}
