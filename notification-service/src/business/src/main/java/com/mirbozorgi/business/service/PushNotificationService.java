package com.mirbozorgi.business.service;

import java.util.concurrent.ExecutionException;

public interface PushNotificationService {


  void byUuId(
      String title,
      String message,
      String topic,
      String uuid,
      String gamePackageName,
      String marketName,
      String env) throws ExecutionException, InterruptedException;


  void byToken(
      String title,
      String message,
      String topic,
      String token,
      String gamePackageName,
      String marketName,
      String env) throws ExecutionException, InterruptedException;

  void addAll(
      String title,
      String message,
      String topic,
      String gamePackageName,
      String env);

}
