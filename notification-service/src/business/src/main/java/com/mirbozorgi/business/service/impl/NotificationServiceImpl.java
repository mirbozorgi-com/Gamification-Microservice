package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.exception.MarketException;
import com.mirbozorgi.business.mapper.NotificationMapper;
import com.mirbozorgi.business.service.TimeService;
import com.mirbozorgi.business.domain.NotificationInfo;
import com.mirbozorgi.business.service.NotificationService;
import com.mirbozorgi.business.service.SerializerService;
import com.mirbozorgi.core.docuemnt.Notification;
import com.mirbozorgi.core.repository.document.NotificationRepository;
import java.util.ArrayList;
import java.util.List;
import mirbozorgi.base.domain.game.GameInfo;
import mirbozorgi.base.domain.hami.HamiAndNotificationType;
import mirbozorgi.base.domain.notification.AddNotificationToAllUser;
import mirbozorgi.base.domain.user.WalletChange;
import mirbozorgi.base.feignService.GameFeignService;
import mirbozorgi.base.feignService.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

  @Autowired
  private NotificationRepository notificationRepository;

  @Autowired
  private GameFeignService gameFeignService;

  @Autowired
  private SerializerService serializerService;

  @Autowired
  private TimeService timeService;

  @Autowired
  private UserFeignService userFeignService;

  @Override
  public NotificationInfo save(
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
      Boolean isFcmSend) {

    if (maxClientVersion == 0) {
      maxClientVersion = Integer.MAX_VALUE;
    }
    if (avatarIds == null) {
      avatarIds = new ArrayList<>();
    }

    GameInfo gameInfo = gameFeignService.getByPackageNameAndEnv(gamePackageName, env);

    if (!gameInfo.getMarketNames().contains(marketName)) {
      throw new MarketException();
    }
    Notification notification = notificationRepository.save(new Notification(
            name,
            gamePackageName,
            env,
            marketName,
            config,
            timeService.getNowUnixFromInstantClass(),
            minClientVersion,
            maxClientVersion,
            removeAble,
            new WalletChange(
                gem,
                gold,
                level,
                xp,
                hamiAded,
                avatarIds,
                addedVipPeriodTime),
            type,
            title,
            topic,
            message,
            isFcmSend
        )
    );

    userFeignService.addNotificationToAllUser(new AddNotificationToAllUser(
            name,
            notification.getStringId(),
            serializerService.toJson(config),
            minClientVersion,
            maxClientVersion,
            removeAble,
            gem,
            gold,
            level,
            xp,
            hamiAded,
            avatarIds,
            addedVipPeriodTime,
            title,
            message,
            topic,
            type,
            isFcmSend
        ),
        gamePackageName,
        env,
        marketName);

    return NotificationMapper.toNotificationInfo(notification);
  }

  @Override
  public NotificationInfo findById(String id) {
    return NotificationMapper.toNotificationInfo(notificationRepository.findById(id));
  }

  @Override
  public List<NotificationInfo> findAll(
      String name,
      String env,
      String gamePackageName,
      String marketName) {
    List<NotificationInfo> notificationInfos = new ArrayList<>();

    List<Notification> notifications = notificationRepository.findAll(
        name,
        gamePackageName,
        env,
        marketName
    );

    for (Notification notification : notifications) {
      notificationInfos.add(NotificationMapper.toNotificationInfo(notification));
    }

    return notificationInfos;
  }
}
