package com.mirbozorgi.core.entity;

import com.mirbozorgi.core.utilities.CurrencyType;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "sku",
    uniqueConstraints = @UniqueConstraint(name = "UK_SKU_NAME_GAME",
        columnNames = {"name", "game_id"}))
public class SKU {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Game.class)
  @JoinColumn(name = "game_id", foreignKey = @ForeignKey(name = "FK_GAME_SKU"), nullable = false)
  private Game game;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "amount", nullable = false)
  private BigDecimal amount;

  @Column(name = "currency_type")
  @Enumerated(EnumType.STRING)
  private CurrencyType currencyType;

  @Column(name = "is_active")
  private Boolean isActive;

  @Column(name = "is_consumable")
  private boolean consumable;

  public SKU() {
  }

  public SKU(Game game, String name, String description,
      BigDecimal amount,
      CurrencyType currencyType, Boolean isActive, boolean consumable) {
    this.game = game;
    this.name = name;
    this.description = description;
    this.amount = amount;
    this.currencyType = currencyType;
    this.isActive = isActive;
    this.consumable = consumable;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
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

  public CurrencyType getCurrencyType() {
    return currencyType;
  }

  public void setCurrencyType(CurrencyType currencyType) {
    this.currencyType = currencyType;
  }

  public Boolean isActive() {
    return isActive;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  public Boolean isConsumable() {
    return consumable;
  }

  public void setConsumable(Boolean consumable) {
    this.consumable = consumable;
  }
}