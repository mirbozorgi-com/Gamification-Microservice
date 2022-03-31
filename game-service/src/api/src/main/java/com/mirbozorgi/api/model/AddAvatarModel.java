package com.mirbozorgi.api.model;

import com.mirbozorgi.core.utilities.AvatarBuyType;
import com.mirbozorgi.core.utilities.CurrencyType;
import java.math.BigDecimal;

public class AddAvatarModel {

  private int gameId;
  private String name;
  private BigDecimal amount;
  private CurrencyType currencyType;
  private Boolean active;
  private String avatarType;
  private Boolean free;
  private AvatarBuyType typeForBuy;

  public AvatarBuyType getTypeForBuy() {
    return typeForBuy;
  }

  public Boolean getFree() {
    return free;
  }

  public String getAvatarType() {
    return avatarType;
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
