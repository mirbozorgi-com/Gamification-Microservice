package mirbozorgi.base.domain.notification;

import mirbozorgi.base.constanct.EnumKeyFCM;


public class FCMGameMessageInfo {


  private int id;

  private EnumKeyFCM enumKeyFCM;

  private String topic;

  private String message;

  private String title;

  public FCMGameMessageInfo() {

  }

  public FCMGameMessageInfo(
      EnumKeyFCM enumKeyFCM,
      String topic,
      String message,
      String title) {
    this.enumKeyFCM = enumKeyFCM;
    this.topic = topic;
    this.message = message;
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public int getId() {
    return id;
  }


  public EnumKeyFCM getEnumKeyFCM() {
    return enumKeyFCM;
  }

  public String getTopic() {
    return topic;
  }

  public String getMessage() {
    return message;
  }
}
