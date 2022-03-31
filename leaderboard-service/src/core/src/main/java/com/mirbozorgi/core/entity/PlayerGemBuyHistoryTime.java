package com.mirbozorgi.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirbozorgi.core.domain.PlayerGemBuyData;
import java.util.HashMap;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "player_gem_buy_history_most")
public class PlayerGemBuyHistoryTime {

  @Id
  private ObjectId id;

  private String userUuId;

  //key : packageName.env.marketName
  private Map<String, Map<String, Map<String, PlayerGemBuyData>>> playerMostBuyGem;


  private long date;


  public PlayerGemBuyHistoryTime(
      String userUuId,
      Map<String, Map<String, Map<String, PlayerGemBuyData>>> playerMostBuyGem,
      long date) {
    this.userUuId = userUuId;
    this.playerMostBuyGem = playerMostBuyGem;
    this.date = date;
  }


  public long getDate() {
    return date;
  }

  public PlayerGemBuyHistoryTime() {
    playerMostBuyGem = new HashMap<>();
  }

  public ObjectId getId() {
    return id;
  }

  public String getUserUuId() {
    return userUuId;
  }

  public Map<String, Map<String, Map<String, PlayerGemBuyData>>> getPlayerMostBuyGem() {
    return playerMostBuyGem;
  }

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }
}
