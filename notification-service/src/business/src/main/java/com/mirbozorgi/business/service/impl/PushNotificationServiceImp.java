package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.service.TimeService;
import com.mirbozorgi.business.domain.PushNotificationRequest;
import com.mirbozorgi.business.service.FCMService;
import com.mirbozorgi.business.service.PushNotificationService;
import com.mirbozorgi.core.docuemnt.Notification;
import com.mirbozorgi.core.repository.document.NotificationRepository;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import mirbozorgi.base.domain.hami.HamiAndNotificationType;
import mirbozorgi.base.domain.notification.AddNotificationToAllUser;
import mirbozorgi.base.domain.user.UserGetGlobalResponse;
import mirbozorgi.base.domain.user.WalletChange;
import mirbozorgi.base.feignService.GameFeignService;
import mirbozorgi.base.feignService.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationServiceImp implements PushNotificationService {

  @Autowired
  private FCMService fcmService;

  @Autowired
  private UserFeignService userFeignService;

  @Autowired
  private NotificationRepository notificationRepository;

  @Autowired
  private TimeService timeService;

  @Autowired
  private GameFeignService gameFeignService;


  @Override
  public void byUuId(
      String title,
      String message,
      String topic,
      String uuid,
      String gamePackageName,
      String marketName,
      String env) throws ExecutionException, InterruptedException {
    UserGetGlobalResponse userGetGlobalResponse = userFeignService.getByUuId(
        uuid,
        gamePackageName,
        env,
        marketName
    );
    fcmService.sendMessageToToken(
        new PushNotificationRequest(
            title,
            message,
            topic,
            userGetGlobalResponse.getTokenFCM()
        ),
        gamePackageName,
        env,
        marketName
    );

  }

  @Override
  public void byToken(
      String title,
      String message,
      String topic,
      String token,
      String gamePackageName,
      String marketName,
      String env)
      throws ExecutionException, InterruptedException {
    fcmService.sendMessageToToken(
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
  }

  @Override
  public void addAll(
      String title,
      String message,
      String topic,
      String gamePackageName,
      String env) {
    String defaultMarket = gameFeignService.getDefaultMarket(gamePackageName, env);
    Notification notification = notificationRepository.save(new Notification(
            topic,
            gamePackageName,
            env,
            defaultMarket,
            null,
            timeService.getNowUnixFromInstantClass(),
            0,
            Integer.MAX_VALUE,
            true,
            new WalletChange(
                0,
                0,
                (short) 0,
                0,
                false,
                null,
                0),
            HamiAndNotificationType.OTHER,
            title,
            topic,
            message,
            true
        )
    );
    userFeignService.addNotificationToAllUser(new AddNotificationToAllUser(
            topic,
            notification.getStringId(),
            null,
            0,
            Integer.MAX_VALUE,
            true,
            0,
            0,
            (short) 0,
            0,
            false,
            new ArrayList<>(),
            0,
            title,
            message,
            topic,
            HamiAndNotificationType.OTHER,
            true
        ),
        gamePackageName,
        env,
        defaultMarket);

  }
}
