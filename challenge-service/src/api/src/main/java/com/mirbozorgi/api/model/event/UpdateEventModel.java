package com.mirbozorgi.api.model.event;

import com.mirbozorgi.core.constant.EventEndType;
import com.mirbozorgi.core.domain.EventRepetition;
import java.util.List;
import java.util.Map;

public class UpdateEventModel {

  private int configVersion;
  private String id;
  private String name;
  private String clientEventType;
  private List<EventRepetition> repetitions;
  private int minClientVersion;
  private int maxClientVersion;
  private Map<String, Object> versionMetaData ;
  private Map<String, Object> states;
  private long periodTimeForMiddleJoinTillEnd;
  private EventEndType eventEndType;

  public int getMinClientVersion() {
    return minClientVersion;
  }

  public int getMaxClientVersion() {
    return maxClientVersion;
  }

  public String getClientEventType() {
    return clientEventType;
  }

  public Map<String, Object> getStates() {
    return states;
  }

  public int getConfigVersion() {
    return configVersion;
  }

  public EventEndType getEventEndType() {
    return eventEndType;
  }


  public String getName() {
    return name;
  }


  public List<EventRepetition> getRepetitions() {
    return repetitions;
  }

  public Map<String, Object> getVersionMetaData() {
    return versionMetaData;
  }

  public long getPeriodTimeForMiddleJoinTillEnd() {
    return periodTimeForMiddleJoinTillEnd;
  }

  public String getId() {
    return id;
  }
}
