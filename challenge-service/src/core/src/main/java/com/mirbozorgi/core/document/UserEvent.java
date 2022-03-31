package com.mirbozorgi.core.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirbozorgi.core.domain.UserEventData;
import com.mirbozorgi.core.domain.UserEventJoinedData;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_event")
public class UserEvent {

  @Id
  private String id;

  private String uuid;

  private String globalUniqueId;

  private String notificationToken;

  private String gamePackageName;

  private String env;

  private String marketName;

  private List<UserEventJoinedData> joinedEventReputationUuIds;

  private List<UserEventData> userEventData;

  public UserEvent() {
    userEventData = new ArrayList<>();
    joinedEventReputationUuIds = new ArrayList<>();
  }

  public UserEvent(
      String uuid,
      String globalUniqueId,
      String notificationToken,
      String gamePackageName,
      String env,
      String marketName,
      List<UserEventData> userEventData,
      List<UserEventJoinedData> joinedEventReputationUuIds) {
    this.uuid = uuid;
    this.globalUniqueId = globalUniqueId;
    this.notificationToken = notificationToken;
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.marketName = marketName;
    this.userEventData = userEventData;
    this.joinedEventReputationUuIds = joinedEventReputationUuIds;
  }

  public List<UserEventJoinedData> getJoinedEventReputationUuIds() {
    return joinedEventReputationUuIds;
  }

  public String getId() {
    return id;
  }

  public String getUuid() {
    return uuid;
  }

  public String getGlobalUniqueId() {
    return globalUniqueId;
  }

  public String getNotificationToken() {
    return notificationToken;
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

  public List<UserEventData> getUserEventData() {
    return userEventData;
  }

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }
}
