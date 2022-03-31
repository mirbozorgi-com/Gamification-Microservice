package com.mirbozorgi.core.entity;

import com.mirbozorgi.core.utilities.AvatarBuyType;
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

@Entity
@Table(name = "avatar")
public class Avatar {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Game.class)
  @JoinColumn(name = "game_id", foreignKey = @ForeignKey(name = "FK_GAME_AVATAR"), nullable = false)
  private Game game;

  @Column(name = "name", nullable = false)
  private String name;


  @Column(name = "avatar_buy_type")
  @Enumerated(EnumType.STRING)
  private AvatarBuyType avatarBuyType;

  @Column(name = "amount", nullable = false)
  private BigDecimal amount;

  @Column(name = "currency_type")
  @Enumerated(EnumType.STRING)
  private CurrencyType currencyType;

  @Column(name = "is_active")
  private Boolean isActive;


  @Column(name = "free")
  private Boolean free;


  @Column(name = "avatar_type")
  private String avatarType;


  public Avatar(Game game, String name, BigDecimal amount,
      CurrencyType currencyType, Boolean isActive, String avatarType, Boolean free,
      AvatarBuyType avatarBuyType) {
    this.game = game;
    this.name = name;
    this.amount = amount;
    this.currencyType = currencyType;
    this.isActive = isActive;
    this.avatarType = avatarType;
    this.free = free;
    this.avatarBuyType = avatarBuyType;
  }

  public Avatar() {
  }

  public AvatarBuyType getAvatarBuyType() {
    return avatarBuyType;
  }

  public Boolean getFree() {
    return free;
  }

  public String getAvatarType() {
    return avatarType;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public CurrencyType getCurrencyType() {
    return currencyType;
  }

  public Boolean getActive() {
    return isActive;
  }

  public Long getId() {
    return id;
  }

  public Game getGame() {
    return game;
  }

  public String getName() {
    return name;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAvatarBuyType(AvatarBuyType avatarBuyType) {
    this.avatarBuyType = avatarBuyType;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public void setCurrencyType(CurrencyType currencyType) {
    this.currencyType = currencyType;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  public void setFree(Boolean free) {
    this.free = free;
  }

  public void setAvatarType(String avatarType) {
    this.avatarType = avatarType;
  }
}
