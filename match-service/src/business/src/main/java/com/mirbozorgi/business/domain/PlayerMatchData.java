package com.mirbozorgi.business.domain;

import com.mirbozorgi.core.domain.PlayerData;
import java.util.List;
import mirbozorgi.base.domain.user.WalletChange;

public class PlayerMatchData {


  private String id;

  private String matchId;

  private List<PlayerData> players;

  private List<String> playerUuIds;

  private int playerMaxQuantity;

  private Boolean roomHasEmptyPlace;

  private Boolean active;

  private Object matchConfig;

  private WalletChange walletChange;

  private int currentLevel;


  public PlayerMatchData(String id, String matchId,
      List<PlayerData> players, int playerMaxQuantity, Boolean roomHasEmptyPlace,
      List<String> playerUuIds, Boolean active, Object matchConfig,
      WalletChange walletChange,
      int currentLevel) {
    this.id = id;
    this.matchId = matchId;
    this.players = players;
    this.playerMaxQuantity = playerMaxQuantity;
    this.roomHasEmptyPlace = roomHasEmptyPlace;
    this.playerUuIds = playerUuIds;
    this.active = active;
    this.matchConfig = matchConfig;
    this.walletChange = walletChange;
    this.currentLevel = currentLevel;
  }

  public PlayerMatchData(String id, String matchId,
      List<PlayerData> players, int playerMaxQuantity, Boolean roomHasEmptyPlace,
      List<String> playerUuIds, Boolean active, Object matchConfig,
      WalletChange walletChange) {
    this.id = id;
    this.matchId = matchId;
    this.players = players;
    this.playerMaxQuantity = playerMaxQuantity;
    this.roomHasEmptyPlace = roomHasEmptyPlace;
    this.playerUuIds = playerUuIds;
    this.active = active;
    this.matchConfig = matchConfig;
    this.walletChange = walletChange;
  }

  public int getCurrentLevel() {
    return currentLevel;
  }

  public WalletChange getWalletChange() {
    return walletChange;
  }

  public Object getMatchConfig() {
    return matchConfig;
  }

  public Boolean getActive() {
    return active;
  }

  public String getId() {
    return id;
  }

  public List<String> getPlayerUuIds() {
    return playerUuIds;
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

  public Boolean getRoomHasEmptyPlace() {
    return roomHasEmptyPlace;
  }
}
