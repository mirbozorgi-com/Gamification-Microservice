package com.mirbozorgi.api.model.user_event;

public class MakeUserEventLoseOrWinModel {

  private String eventId;
  private String globalUniqueId;
  private Boolean win;
  private String eventRepetitionUuId;



  public String getEventId() {
    return eventId;
  }

  public String getGlobalUniqueId() {
    return globalUniqueId;
  }

  public Boolean getWin() {
    return win;
  }


  public String getEventRepetitionUuId() {
    return eventRepetitionUuId;
  }
}
