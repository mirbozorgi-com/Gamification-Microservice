package com.mirbozorgi.core.document;

import com.mirbozorgi.core.domain.UserEventFinishedData;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_event_finished_history")
public class UserEventFinishedHistory {

  @Id
  private String id;

  private String uuid;

  private String globalUniqueId;

  private String gamePackageName;

  private String env;

  private String marketName;

  private List<UserEventFinishedData> userEventData;

  public UserEventFinishedHistory() {
    userEventData = new ArrayList<>();
  }

  public UserEventFinishedHistory(
      String uuid,
      String globalUniqueId,
      String gamePackageName,
      String env,
      String marketName,
      List<UserEventFinishedData> userEventData) {
    this.uuid = uuid;
    this.globalUniqueId = globalUniqueId;
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.marketName = marketName;
    this.userEventData = userEventData;
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

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getEnv() {
    return env;
  }

  public String getMarketName() {
    return marketName;
  }

  public List<UserEventFinishedData> getUserEventData() {
    return userEventData;
  }
}
