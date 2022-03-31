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
  private Map<String, Map<String, Map<String, Map<String, PlayerGameScore>>>> playerGameScore;

  public PlayerScore(String userUuId,
      Map<String, Map<String, Map<String, Map<String, PlayerGameScore>>>> playerGameScore) {
    this.userUuId = userUuId;
    this.playerGameScore = playerGameScore;
  }

  public PlayerScore() {
    playerGameScore = new HashMap<>();
  }

  public Map<String, Map<String, Map<String, Map<String, PlayerGameScore>>>> getPlayerGameScore() {
    return playerGameScore;
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
