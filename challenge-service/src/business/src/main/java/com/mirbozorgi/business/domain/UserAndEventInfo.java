package com.mirbozorgi.business.domain;

import com.mirbozorgi.core.document.Event;
import com.mirbozorgi.core.document.UserEvent;
import java.util.List;

public class UserAndEventInfo {

  private UserEvent userEvent;

  private List<Event> events;


  public UserAndEventInfo(UserEvent userEvent,
      List<Event> events) {
    this.userEvent = userEvent;
    this.events = events;
  }

  public UserEvent getUserEvent() {
    return userEvent;
  }

  public List<Event> getEvents() {
    return events;
  }
}
