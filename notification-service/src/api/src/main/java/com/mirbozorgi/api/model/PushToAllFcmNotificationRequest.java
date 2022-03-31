package com.mirbozorgi.api.model;

public class PushToAllFcmNotificationRequest {
  private String title;
  private String message;
  private String topic;

  public String getMessage() {
    return message;
  }

  public String getTopic() {
    return topic;
  }

  public String getTitle() {
    return title;
  }
}
