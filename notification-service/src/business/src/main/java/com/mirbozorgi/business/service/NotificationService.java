package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.NotificationInfo;
import java.util.List;
import mirbozorgi.base.domain.hami.HamiAndNotificationType;

public interface NotificationService {

  NotificationInfo save(
      String name,
      String gamePackageName,
      String env,
      String marketName,
      Object config,
      int minClientVersion,
      int maxClientVersion,
      Boolean removeAble,
      int gem,
      int gold,
      Short level,
      int xp,
      boolean hamiAded,
      List<Integer> avatarIds,
      long addedVipPeriodTime,
      String title,
      String topic,
      String message,
      HamiAndNotificationType type,
      Boolean isFcmSend);

  NotificationInfo findById(String id);

  List<NotificationInfo> findAll(
      String name,
      String env,
      String gamePackageName,
      String marketName);

}
