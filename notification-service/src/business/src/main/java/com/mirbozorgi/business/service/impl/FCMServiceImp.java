package com.mirbozorgi.business.service.impl;


import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.mirbozorgi.business.domain.NotificationParameter;
import com.mirbozorgi.business.domain.PushNotificationRequest;
import com.mirbozorgi.business.service.FCMService;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;

@Service
public class FCMServiceImp implements FCMService {

  @Override
  public void sendMessage(Map<String, String> data, PushNotificationRequest request,
      String gamePackageName,
      String env,
      String marketNam)
      throws InterruptedException, ExecutionException {
    Message message = getPreconfiguredMessageWithData(data, request);
    String response = sendAndGetResponse(message,
        gamePackageName,
        env,
        marketNam);
  }

  @Override
  public void sendMessageWithoutData(PushNotificationRequest request,
      String gamePackageName,
      String env,
      String marketNam)
      throws InterruptedException, ExecutionException {
    Message message = getPreconfiguredMessageWithoutData(request);
    String response = sendAndGetResponse(message,
        gamePackageName,
        env,
        marketNam);
  }

  @Override
  public void sendMessageToToken(PushNotificationRequest request,
      String gamePackageName,
      String env,
      String marketNam)
      throws InterruptedException, ExecutionException {
    Message message = getPreconfiguredMessageToToken(request);
    String response = sendAndGetResponse(message,
        gamePackageName,
        env,
        marketNam);
  }

  @Override
  public String sendAndGetResponse(Message message,
      String gamePackageName,
      String env,
      String marketName)
      throws InterruptedException, ExecutionException {
    String key = String.format("fcm_%s_%s_%s",
        gamePackageName,
        env,
        marketName);

    return FirebaseMessaging.getInstance(FirebaseApp.getInstance(key)).sendAsync(message).get();
  }

  @Override
  public AndroidConfig getAndroidConfig(String topic) {
    return AndroidConfig.builder()
        .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
        .setPriority(AndroidConfig.Priority.HIGH)
        .setNotification(
            AndroidNotification.builder().setSound(NotificationParameter.SOUND.getValue())
                .setColor(NotificationParameter.COLOR.getValue()).setTag(topic).build()).build();
  }

  @Override
  public ApnsConfig getApnsConfig(String topic) {
    return ApnsConfig.builder()
        .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
  }

  @Override
  public Message getPreconfiguredMessageToToken(PushNotificationRequest request) {

    return getPreconfiguredMessageBuilder(request).setToken(request.getToken())
        .build();
  }

  @Override
  public Message getPreconfiguredMessageWithoutData(PushNotificationRequest request) {
    return getPreconfiguredMessageBuilder(request).setTopic(request.getTopic())
        .build();
  }

  @Override
  public Message getPreconfiguredMessageWithData(Map<String, String> data,
      PushNotificationRequest request) {
    return getPreconfiguredMessageBuilder(request).putAllData(data).setTopic(request.getTopic())
        .build();
  }

  @Override
  public Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequest request) {
    AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
    ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
    return Message.builder()
        .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig).setNotification(
            new Notification(request.getTitle(), request.getMessage()));
  }
}
