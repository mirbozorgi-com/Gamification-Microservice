package com.mirbozorgi.business.domain;

import java.math.BigDecimal;

public class SkuInfo {

  private Long id;

  private int gameId;

  private String name;

  private String description;

  private BigDecimal amount;

  private String currencyType;

  private Boolean active;

  private Boolean consumable;

  public SkuInfo(Long id, int gameId, String name, String description, BigDecimal amount,
      String currencyType, Boolean active, Boolean consumable) {
    this.id = id;
    this.gameId = gameId;
    this.name = name;
    this.description = description;
    this.amount = amount;
    this.currencyType = currencyType;
    this.active = active;
    this.consumable = consumable;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getGameId() {
    return gameId;
  }

  public void setGameId(int gameId) {
    this.gameId = gameId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getCurrencyType() {
    return currencyType;
  }

  public void setCurrencyType(String currencyType) {
    this.currencyType = currencyType;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Boolean getConsumable() {
    return consumable;
  }

  public void setConsumable(Boolean consumable) {
    this.consumable = consumable;
  }
}
