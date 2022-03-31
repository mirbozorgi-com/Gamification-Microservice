package com.mirbozorgi.business.service;

import com.mirbozorgi.core.entity.FCMGameMessage;
import mirbozorgi.base.constanct.EnumKeyFCM;
import mirbozorgi.base.domain.notification.FCMGameMessageInfo;
import mirbozorgi.base.domain.notification.FCMGameMessageInfoGetAll;

public interface FCMGameMessageService {

  FCMGameMessageInfo findBy(
      String gamePackageName,
      String env,
      String marketName,
      EnumKeyFCM enumKeyFCM);


  FCMGameMessage add(EnumKeyFCM enumKeyFCM,
      String topic,
      String message,
      String title,
      String gamePackageName,
      String env,
      String marketName);

  void delete(int id);

  FCMGameMessageInfoGetAll findAll(String gamePackageName,
      String env,
      String marketName);
}
