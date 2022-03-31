package mirbozorgi.base.feignService.impl;

import mirbozorgi.base.constanct.EnumKeyFCM;
import mirbozorgi.base.domain.notification.FCMGameMessageInfo;
import mirbozorgi.base.domain.notification.FCMGameMessageInfoGetAll;
import mirbozorgi.base.domain.notification.PushNotificationRequest;
import mirbozorgi.base.exception.GlobalExceptionService;
import mirbozorgi.base.feignService.FeignErrorFeignService;
import mirbozorgi.base.feignService.NotificationFeignService;
import mirbozorgi.base.feignService.SerializerFeignService;
import mirbozorgi.base.feigns.NotificationFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationFeignServiceImpl implements NotificationFeignService {

  @Autowired
  private NotificationFeign notificationFeign;

  @Autowired
  private FeignErrorFeignService feignErrorFeign;

  @Autowired
  private SerializerFeignService serializerFeignService;

  @Override
  public void byToken(
      String title,
      String message,
      String topic,
      String token,
      String gamePackageName,
      String marketName,
      String env) {
    try {

      notificationFeign.byToken(
          new PushNotificationRequest(
              title,
              message,
              topic,
              token
          ),
          gamePackageName,
          env,
          marketName

      );


    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "byToken", "notification-service-fcm", ex, feignErrorFeign
      );
    }

  }

  @Override
  public FCMGameMessageInfo messageGetBy(
      EnumKeyFCM enumKeyFCM,
      String gamePackageName,
      String env,
      String marketName) {
    FCMGameMessageInfo fcmGameMessageInfo = null;
    try {

      Object o = notificationFeign.getBy(
          enumKeyFCM,
          gamePackageName,
          env,
          marketName
      );

      fcmGameMessageInfo = serializerFeignService
          .toObjectFromFeign(o, FCMGameMessageInfo.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "messageGetBy", "notification-service-fcm", ex, feignErrorFeign
      );
    }

    return fcmGameMessageInfo;
  }

  @Override
  public FCMGameMessageInfoGetAll getAllMessageBy(
      String gamePackageName,
      String env,
      String marketName) {
    FCMGameMessageInfoGetAll fcmGameMessageInfoGetAll = null;
    try {

      Object o = notificationFeign.getAllMessageBy(
          gamePackageName,
          env,
          marketName
      );

      fcmGameMessageInfoGetAll = serializerFeignService
          .toObjectFromFeign(o, FCMGameMessageInfoGetAll.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getAllMessageBy", "notification-service-fcm", ex, feignErrorFeign
      );
    }

    return fcmGameMessageInfoGetAll;
  }
}
