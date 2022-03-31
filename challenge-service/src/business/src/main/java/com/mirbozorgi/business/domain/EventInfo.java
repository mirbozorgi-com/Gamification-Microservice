package com.mirbozorgi.business.domain;

import com.mirbozorgi.core.constant.EventEndType;
import com.mirbozorgi.core.domain.EventRepetition;
import java.util.List;
import java.util.Map;

public class EventInfo {

  private String id;

  private String name;

  private String gamePackageName;

  private String marketName;

  private String env;

  private long periodTimeForMiddleJoinTillEnd;

  //key : name of state \\ value : config of that state
  private Map<String, Object> states;

  private List<EventRepetition> repetitions;

  //path ---> key : version , value : metaData
  private Map<String, Object> versionMetaData;

  private EventEndType eventEndType;
  private String clientType;
  private int configVersion;

  private long serverTime;

  public EventInfo(
      String name,
      String gamePackageName,
      String marketName,
      String env,
      String id,
      long periodTimeForMiddleJoinTillEnd,
      Map<String, Object> states,
      List<EventRepetition> repetitions,
      Map<String, Object> versionMetaData,
      EventEndType eventEndType,
      String clientType,
      int configVersion,
      long serverTime) {
    this.configVersion = configVersion;
    this.name = name;
    this.gamePackageName = gamePackageName;
    this.marketName = marketName;
    this.env = env;
    this.id = id;
    this.periodTimeForMiddleJoinTillEnd = periodTimeForMiddleJoinTillEnd;
    this.states = states;
    this.repetitions = repetitions;
    this.versionMetaData = versionMetaData;
    this.eventEndType = eventEndType;
    this.clientType = clientType;
    this.serverTime = serverTime;
  }

  public long getServerTime() {
    return serverTime;
  }

  public int getConfigVersion() {
    return configVersion;
  }

  public String getClientType() {
    return clientType;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public EventEndType getEventEndType() {
    return eventEndType;
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

  public long getPeriodTimeForMiddleJoinTillEnd() {
    return periodTimeForMiddleJoinTillEnd;
  }

  public Map<String, Object> getStates() {
    return states;
  }

  public List<EventRepetition> getRepetitions() {
    return repetitions;
  }

  public Map<String, Object> getVersionMetaData() {
    return versionMetaData;
  }
}
