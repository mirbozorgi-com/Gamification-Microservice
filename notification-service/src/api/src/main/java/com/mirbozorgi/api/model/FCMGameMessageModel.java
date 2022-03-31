package com.mirbozorgi.api.model;

import mirbozorgi.base.constanct.EnumKeyFCM;

public class FCMGameMessageModel {

  private String topic;
  private String title;
  private String message;
  private EnumKeyFCM enumKeyFCM;

  public String getTopic() {
    return topic;
  }

  public String getTitle() {
    return title;
  }

  public String getMessage() {
    return message;
  }

  public EnumKeyFCM getEnumKeyFCM() {
    return enumKeyFCM;
  }
}
