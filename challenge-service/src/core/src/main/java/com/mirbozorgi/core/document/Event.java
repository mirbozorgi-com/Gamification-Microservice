package com.mirbozorgi.core.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirbozorgi.core.constant.EventEndType;
import com.mirbozorgi.core.domain.EventRepetition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "event")
public class Event {

  @Id
  private String id;

  private String name;
  private String clientEventType;
  private String gamePackageName;
  private String marketName;
  private String env;

  private long periodTimeForMiddleJoinTillEnd;
  private EventEndType eventEndType;


  //key : name of state \\ value : config of that state
  private Map<String, Object> states;

  private List<EventRepetition> repetitions;

  //path ---> key : version , value : metaData --- client version
  private Map<String, Object> versionMetaData;

  //metaData own version
  private int configVersion;

  public Event() {
    versionMetaData = new HashMap<>();
    states = new HashMap<>();
    repetitions = new ArrayList<>();
  }

  public Event(
      String gamePackageName,
      String marketName,
      String env,
      String name,
      List<EventRepetition> repetitions,
      Map<String, Object> versionMetaData,
      long periodTimeForMiddleJoinTillEnd,
      Map<String, Object> states,
      EventEndType eventEndType,
      String clientEventType,
      int configVersion) {
    this.gamePackageName = gamePackageName;
    this.clientEventType = clientEventType;
    this.marketName = marketName;
    this.env = env;
    this.name = name;
    this.repetitions = repetitions;
    this.versionMetaData = versionMetaData;
    this.periodTimeForMiddleJoinTillEnd = periodTimeForMiddleJoinTillEnd;
    this.states = states;
    this.eventEndType = eventEndType;
    this.configVersion = configVersion;
  }


  public int getConfigVersion() {
    return configVersion;
  }

  public String getClientEventType() {
    return clientEventType;
  }

  public EventEndType getEventEndType() {
    return eventEndType;
  }

  public String getName() {
    return name;
  }

  public long getPeriodTimeForMiddleJoinTillEnd() {
    return periodTimeForMiddleJoinTillEnd;
  }

  public String getId() {
    return id;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getMarketName() {
    return marketName;
  }

  public String getEnv() {
    return env;
  }


  public List<EventRepetition> getRepetitions() {
    return repetitions;
  }


  public Map<String, Object> getVersionMetaData() {
    return versionMetaData;
  }

  public void setVersionMetaData(Map<String, Object> versionMetaData) {
    this.versionMetaData = versionMetaData;
  }

  public Map<String, Object> getStates() {
    return states;
  }

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }
}
