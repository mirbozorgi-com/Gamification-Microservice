package com.mirbozorgi.core.docuemnt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "used-gift-code")
public class UsedGiftCode {


  @Id
  private ObjectId id;

  private String userUuId;

  private String code;

  private long usedDate;

  private String gamePackageName;

  private String env;

  private String marketName;


  public UsedGiftCode(
      String code,
      long usedDate,
      String gamePackageName,
      String env,
      String marketName,
      String userUuId) {
    this.code = code;
    this.usedDate = usedDate;
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.marketName = marketName;
    this.userUuId = userUuId;
  }

  public UsedGiftCode() {
  }

  public String getUserUuId() {
    return userUuId;
  }

  public ObjectId getId() {
    return id;
  }

  public String getCode() {
    return code;
  }

  public long getUsedDate() {
    return usedDate;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getEnv() {
    return env;
  }

  public String getMarketName() {
    return marketName;
  }

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }

}
