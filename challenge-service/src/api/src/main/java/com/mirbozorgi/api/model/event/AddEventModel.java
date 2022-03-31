package com.mirbozorgi.api.model.event;

import com.mirbozorgi.core.constant.EventEndType;
import com.mirbozorgi.core.domain.EventRepetition;
import java.util.List;
import java.util.Map;

public class AddEventModel {

  private int configVersion;
  private String name;
  private String clientEventType;
  private List<EventRepetition> repetitions;
  Map<String, Object> versionMetaData;
  private Map<String, Object> states;
  private long periodTimeForMiddleJoinTillEnd;
  private EventEndType eventEndType;

  public int getConfigVersion() {
    return configVersion;
  }


  public String getClientEventType() {
    return clientEventType;
  }

  public Map<String, Object> getStates() {
    return states;
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
}
