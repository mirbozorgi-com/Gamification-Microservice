package com.mirbozorgi.core.docuemnt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirbozorgi.core.domain.ItemData;
import java.util.HashMap;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("item-shop")

@CompoundIndex(
    name = "UK_NAME_GAME_PACKAGE_NAME_ENV_MARKET",
    unique = true,
    def = "{ 'gamePackageName':1,'env':1,'market':1}")

public class ItemShop {

  @Id
  private ObjectId id;

  // Map keys: skuName
  private Map<String, ItemData> items;

  private String gamePackageName;

  private String env;

  private String market;


  public ItemShop(
      Map<String, ItemData> items,
      String gamePackageName,
      String env,
      String market) {
    this.items = items;
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.market = market;
  }

  public ItemShop() {
    items = new HashMap<>();
  }

  public ObjectId getId() {
    return id;
  }

  public Map<String, ItemData> getItems() {
    return items;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getEnv() {
    return env;
  }

  public String getMarket() {
    return market;
  }

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }
}
