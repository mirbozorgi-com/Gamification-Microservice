package com.mirbozorgi.core.docuemnt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mirbozorgi.base.domain.hami.HamiAndNotificationType;
import mirbozorgi.base.domain.user.WalletChange;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "notification")
//@CompoundIndex(
//    name = "UK_NAME_GAME_PACKAGE_NAME_ENV_MARKET_NAME",
//    unique = true,
//    def = "{'name':1,'gamePackageName':1, 'env':1,'marketName':1}")
public class Notification {

  @Id
  private ObjectId id;

  private String name;

  private String gamePackageName;

  private String env;

  private String marketName;

  private Object config;

  private long creationDate;

  private int minClientVersion;

  private int maxClientVersion;

  private HamiAndNotificationType type;

  private Boolean removeAble;

  private WalletChange walletChange;

  private String title;

  private String topic;

  private String message;

  private Boolean isFcmSend;

  public Notification(
      String name,
      String gamePackageName,
      String env,
      String marketName,
      Object config,
      long creationDate,
      int minClientVersion,
      int maxClientVersion,
      Boolean removeAble,
      WalletChange walletChange,
      HamiAndNotificationType type,
      String title,
      String topic,
      String message,
      Boolean isFcmSend) {
    this.name = name;
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.marketName = marketName;
    this.config = config;
    this.creationDate = creationDate;
    this.minClientVersion = minClientVersion;
    this.maxClientVersion = maxClientVersion;
    this.removeAble = removeAble;
    this.walletChange = walletChange;
    this.type = type;
    this.title = title;
    this.isFcmSend = isFcmSend;
    this.topic = topic;
    this.message = message;
  }

  public Boolean getFcmSend() {
    return isFcmSend;
  }

  public Notification() {
  }

  public String getTitle() {
    return title;
  }

  public String getTopic() {
    return topic;
  }

  public String getMessage() {
    return message;
  }

  public HamiAndNotificationType getType() {
    return type;
  }

  public WalletChange getWalletChange() {
    return walletChange;
  }

  public Boolean getRemoveAble() {
    return removeAble;
  }

  public ObjectId getId() {
    return id;
  }

  public String getName() {
    return name;
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

  public Object getConfig() {
    return config;
  }

  public long getCreationDate() {
    return creationDate;
  }

  public int getMinClientVersion() {
    return minClientVersion;
  }

  public int getMaxClientVersion() {
    return maxClientVersion;
  }


  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }
}
