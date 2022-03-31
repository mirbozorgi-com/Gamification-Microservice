package mirbozorgi.base.domain.sku;

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

  public SkuInfo() {
  }

  public Long getId() {
    return id;
  }

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

  public String getCurrencyType() {
    return currencyType;
  }

  public Boolean getActive() {
    return active;
  }

  public Boolean getConsumable() {
    return consumable;
  }
}
