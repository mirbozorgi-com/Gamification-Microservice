package mirbozorgi.base.domain.game;

import java.math.BigDecimal;

public class AvatarInfo {

  private long id;
  private int gameId;
  private String name;
  private String fileUrl;
  private BigDecimal amount;
  private CurrencyType currencyType;
  private Boolean active;
  private String avatarType;
  private Boolean free;
  private AvatarType typeForBuy;

  public AvatarInfo() {
  }

  public AvatarInfo(long id, int gameId, String name, String fileUrl, BigDecimal amount,
      CurrencyType currencyType, Boolean active, String avatarType, Boolean free,
      AvatarType typeForBuy) {
    this.id = id;
    this.gameId = gameId;
    this.name = name;
    this.fileUrl = fileUrl;
    this.amount = amount;
    this.currencyType = currencyType;
    this.active = active;
    this.avatarType = avatarType;
    this.free = free;
    this.typeForBuy = typeForBuy;
  }

  public AvatarType getTypeForBuy() {
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

  public String getFileUrl() {
    return fileUrl;
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
