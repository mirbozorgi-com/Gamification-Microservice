package mirbozorgi.base.feigns;

import mirbozorgi.base.constanct.EnumKeyFCM;
import mirbozorgi.base.domain.notification.PushNotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient("notification-service")
public interface NotificationFeign {

  @PostMapping("push-notification/token")
  Object byToken(
      @RequestBody PushNotificationRequest model,
      @RequestHeader String gamePackageName,
      @RequestHeader String env,
      @RequestHeader String marketName);


  @RequestMapping(value = "game-fcm-message/get-by", method = RequestMethod.GET)
  Object getBy(@RequestParam EnumKeyFCM enumKeyFCM,
      @RequestHeader String gamePackageName,
      @RequestHeader String env,
      @RequestHeader String marketName);

  @RequestMapping(value = "game-fcm-message/get-all", method = RequestMethod.GET)
  Object getAllMessageBy(
      @RequestHeader String gamePackageName,
      @RequestHeader String env,
      @RequestHeader String marketName);

}