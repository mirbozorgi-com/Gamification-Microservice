package mirbozorgi.base.feignService;

import mirbozorgi.base.constanct.EnumKeyFCM;
import mirbozorgi.base.domain.notification.FCMGameMessageInfo;
import mirbozorgi.base.domain.notification.FCMGameMessageInfoGetAll;

public interface NotificationFeignService {

  void byToken(
      String title,
      String message,
      String topic,
      String token,
      String gamePackageName,
      String marketName,
      String env);

  FCMGameMessageInfo messageGetBy(
      EnumKeyFCM enumKeyFCM,
      String gamePackageName,
      String env,
      String marketName);

  FCMGameMessageInfoGetAll getAllMessageBy(
      String gamePackageName,
      String env,
      String marketName);

}
