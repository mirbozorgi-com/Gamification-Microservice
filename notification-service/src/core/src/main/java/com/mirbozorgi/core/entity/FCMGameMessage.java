package com.mirbozorgi.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import mirbozorgi.base.constanct.EnumKeyFCM;

@Entity
@Table(name = "fcm_game_message",
    uniqueConstraints =
    @UniqueConstraint(name = "UK_FCM_MESSAGE",
        columnNames = {"fcm_game_id", "enum_key_fcm"})
)
public class FCMGameMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @OneToOne
  private FCMGame fcmGame;

  @Column(name = "enum_key_fcm")
  @Enumerated(EnumType.STRING)
  private EnumKeyFCM enumKeyFCM;

  @Column(name = "topic")
  private String topic;

  @Column(name = "message")
  private String message;

  @Column(name = "title")
  private String title;

  public FCMGameMessage() {

  }

  public FCMGameMessage(
      FCMGame fcmGame,
      EnumKeyFCM enumKeyFCM,
      String topic,
      String message,
      String title) {
    this.fcmGame = fcmGame;
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

  public FCMGame getFcmGame() {
    return fcmGame;
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
