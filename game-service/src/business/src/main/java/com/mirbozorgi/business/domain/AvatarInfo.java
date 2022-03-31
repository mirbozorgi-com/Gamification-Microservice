package com.mirbozorgi.business.domain;

import com.mirbozorgi.core.utilities.AvatarBuyType;
import com.mirbozorgi.core.utilities.CurrencyType;
import java.math.BigDecimal;

public class AvatarInfo {

  private long id;
  private int gameId;
  private String name;
  private BigDecimal amount;
  private CurrencyType currencyType;
  private Boolean active;
  private String avatarType;
  private Boolean free;
  private AvatarBuyType typeForBuy;

  public AvatarInfo(long id, int gameId, String name, BigDecimal amount,
      CurrencyType currencyType, Boolean active, String avatarType, Boolean free,
      AvatarBuyType typeForBuy) {
    this.id = id;
    this.gameId = gameId;
    this.name = name;
    this.amount = amount;
    this.currencyType = currencyType;
    this.active = active;
    this.avatarType = avatarType;
    this.free = free;
    this.typeForBuy = typeForBuy;
  }

  public AvatarBuyType getTypeForBuy() {
    return typeForBuy;
  }

  public String getAvatarType() {
    return avatarType;
  }

  public Boolean getFree() {
    return free;
  }

  public long getId() {
    return id;
  }

  public int getGameId() {
    return gameId;
  }

  public String getName() {
    return name;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public CurrencyType getCurrencyType() {
    return currencyType;
  }

  public Boolean getActive() {
    return active;
  }
}
