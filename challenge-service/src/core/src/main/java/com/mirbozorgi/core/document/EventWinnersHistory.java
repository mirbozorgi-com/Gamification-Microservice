package com.mirbozorgi.core.document;


import com.mirbozorgi.core.domain.EventWinnerHistory;
import java.util.List;
import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "event_winners_history")
public class EventWinnersHistory {

  @Id
  private String id;

  private String eventId;

  private String gamePackageName;

  private String env;

  private String marketName;

  //key : event repetition random uuid
  private Map<String, List<EventWinnerHistory>> userWinnerEventData;

  public EventWinnersHistory() {
  }


  public EventWinnersHistory(
      String eventId,
      String gamePackageName,
      String env,
      String marketName,
      Map<String, List<EventWinnerHistory>> userWinnerEventData) {
    this.eventId = eventId;
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.marketName = marketName;
    this.userWinnerEventData = userWinnerEventData;
  }

  public String getEventId() {
    return eventId;
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

  public Map<String, List<EventWinnerHistory>> getUserWinnerEventData() {
    return userWinnerEventData;
  }

  public void setUserWinnerEventData(Map<String, List<EventWinnerHistory>> userWinnerEventData) {
    this.userWinnerEventData = userWinnerEventData;
  }
}
