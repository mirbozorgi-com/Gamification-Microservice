package com.mirbozorgi.core.domain;

public class NewsData {

  private String notificationId;

  private String name;

  private Object config;

  private long creationDate;

  private Boolean read;

  public NewsData(String notificationId, String name, Object config, long creationDate,
      Boolean read) {
    this.notificationId = notificationId;
    this.name = name;
    this.config = config;
    this.creationDate = creationDate;
    this.read = read;
  }

  public NewsData() {
  }

  public Boolean getRead() {
    return read;
  }

  public String getNotificationId() {
    return notificationId;
  }

  public String getName() {
    return name;
  }

  public Object getConfig() {
    return config;
  }

  public long getCreationDate() {
    return creationDate;
  }
}
