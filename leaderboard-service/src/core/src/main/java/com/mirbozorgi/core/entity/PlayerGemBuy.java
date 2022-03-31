package com.mirbozorgi.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirbozorgi.core.domain.PlayerGemBuyData;
import java.util.HashMap;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "player_gem_buy")
public class PlayerGemBuy {

  @Id
  private ObjectId id;

  private String userUuId;

  //key : packageName.env.marketName
  private Map<String, Map<String, Map<String, PlayerGemBuyData>>> playerGaemBuy;

  public PlayerGemBuy(String userUuId,
      Map<String, Map<String, Map<String, PlayerGemBuyData>>> playerGaemBuy) {
    this.userUuId = userUuId;
    this.playerGaemBuy = playerGaemBuy;
  }


  public PlayerGemBuy() {
    playerGaemBuy = new HashMap<>();
  }

  public ObjectId getId() {
    return id;
  }

  public String getUserUuId() {
    return userUuId;
  }

  public Map<String, Map<String, Map<String, PlayerGemBuyData>>> getPlayerGaemBuy() {
    return playerGaemBuy;
  }

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }
}
