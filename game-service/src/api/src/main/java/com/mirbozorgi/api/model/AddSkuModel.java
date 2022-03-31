package com.mirbozorgi.api.model;

import com.mirbozorgi.core.utilities.CurrencyType;
import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AddSkuModel {

  @NotNull
  @Min(0)
  private int gameId;

  @NotNull
  private String name;

  @NotNull
  private String description;

  @NotNull
  private BigDecimal amount;

  @NotNull
  private CurrencyType currency;

  @NotNull
  private Boolean active;

  @NotNull
  private Boolean consumable;

  public int getGameId() {
    return gameId;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public CurrencyType getCurrency() {
    return currency;
  }

  public Boolean getActive() {
    return active;
  }

  public Boolean getConsumable() {
    return consumable;
  }
}
