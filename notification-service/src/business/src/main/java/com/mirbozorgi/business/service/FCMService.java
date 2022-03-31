package com.mirbozorgi.business.service;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Message;
import com.mirbozorgi.business.domain.PushNotificationRequest;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface FCMService {

  void sendMessage(Map<String, String> data, PushNotificationRequest request,
      String gamePackageName,
      String env,
      String marketNam)
      throws InterruptedException, ExecutionException;

  void sendMessageWithoutData(PushNotificationRequest request, String gamePackageName,
      String env,
      String marketNam)
      throws InterruptedException, ExecutionException;

  void sendMessageToToken(PushNotificationRequest request, String gamePackageName,
      String env,
      String marketNam)
      throws InterruptedException, ExecutionException;

  String sendAndGetResponse(Message message,
      String gamePackageName,
      String env,
      String marketName) throws InterruptedException, ExecutionException;

  AndroidConfig getAndroidConfig(String topic);

  ApnsConfig getApnsConfig(String topic);

  Message getPreconfiguredMessageToToken(PushNotificationRequest request);

  Message getPreconfiguredMessageWithoutData(PushNotificationRequest request);

  Message getPreconfiguredMessageWithData(
      Map<String, String> data, PushNotificationRequest request);


  Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequest request);
}
