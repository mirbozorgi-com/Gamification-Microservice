package com.mirbozorgi.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirbozorgi.core.domain.PlayerData;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "player_match")
public class PlayerMatch {

  @Id
  private ObjectId id;

  private String matchId;

  private List<PlayerData> players;

  private List<String> playerUuIds;

  private int playerMaxQuantity;

  private Boolean hasEmptyPlaceForPlayer;

  private Boolean active;

  public PlayerMatch() {
    players = new ArrayList<>();
    playerUuIds = new ArrayList<>();
  }


  public PlayerMatch(String matchId, List<PlayerData> players,
      List<String> playerUuIds, int playerMaxQuantity,
      Boolean hasEmptyPlaceForPlayer) {
    this.matchId = matchId;
    this.players = players;
    this.playerUuIds = playerUuIds;
    this.playerMaxQuantity = playerMaxQuantity;
    this.hasEmptyPlaceForPlayer = hasEmptyPlaceForPlayer;
    this.active = true;
  }

  public List<String> getPlayerUuIds() {
    return playerUuIds;
  }

  public Boolean getActive() {
    return active;
  }

  public Boolean getHasEmptyPlaceForPlayer() {
    return hasEmptyPlaceForPlayer;
  }


  public ObjectId getId() {
    return id;
  }

  public String getMatchId() {
    return matchId;
  }

  public List<PlayerData> getPlayers() {
    return players;
  }

  public int getPlayerMaxQuantity() {
    return playerMaxQuantity;
  }

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }
}
