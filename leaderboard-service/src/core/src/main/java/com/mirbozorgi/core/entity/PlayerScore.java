package com.mirbozorgi.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirbozorgi.core.domain.PlayerGameScore;
import java.util.HashMap;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "player_score")
public class PlayerScore {

  @Id
  private ObjectId id;

  private String userUuId;

  //key : packageName.env.marketName.challengeId
  private Map<String, Map<String, Map<String, Map<String, PlayerGameScore>>>> playerarsalancore;

  public PlayerScore(String userUuId,
      Map<String, Map<String, Map<String, Map<String, PlayerGameScore>>>> playerarsalancore) {
    this.userUuId = userUuId;
    this.playerarsalancore = playerarsalancore;
  }

  public PlayerScore() {
    playerarsalancore = new HashMap<>();
  }

  public Map<String, Map<String, Map<String, Map<String, PlayerGameScore>>>> getPlayerarsalancore() {
    return playerarsalancore;
  }

  public ObjectId getId() {
    return id;
  }

  public String getUserUuId() {
    return userUuId;
  }

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }
}
