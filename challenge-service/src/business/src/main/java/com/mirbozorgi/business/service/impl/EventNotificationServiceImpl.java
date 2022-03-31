package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.service.EventNotificationService;
import com.mirbozorgi.business.service.TimeService;
import com.mirbozorgi.core.document.Event;
import com.mirbozorgi.core.document.UserEvent;
import com.mirbozorgi.core.repository.document.EventRepository;
import com.mirbozorgi.core.repository.document.UserEventRepository;
import java.util.List;
import mirbozorgi.base.constanct.EnumKeyFCM;
import mirbozorgi.base.domain.notification.FCMGameMessageInfo;
import mirbozorgi.base.feignService.NotificationFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EventNotificationServiceImpl implements EventNotificationService {


  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private TimeService timeService;

  @Autowired
  private UserEventRepository userEventRepository;

  @Autowired
  private NotificationFeignService notificationFeignService;

  @Override
  public void sendNotificationToAllUser() {

    long nowTime = timeService.getNowUnixFromInstantClass();
    List<Event> startedEvent = eventRepository.findAllStartedEvent(nowTime);

    for (Event event : startedEvent) {
      List<UserEvent> checkEventExitsForUser = userEventRepository.checkEventExitsForUser(
          event.getGamePackageName(),
          event.getEnv(),
          event.getMarketName(),
          event.getId()
      );
      if (checkEventExitsForUser != null) {
        FCMGameMessageInfo fcmGameMessageInfo = notificationFeignService.messageGetBy(
            EnumKeyFCM.EVENT_JOIN,
            event.getGamePackageName(),
            event.getEnv(),
            event.getMarketName()
        );
        for (UserEvent userEvent : checkEventExitsForUser) {
          notificationFeignService.byToken(
              fcmGameMessageInfo.getTitle(),
              fcmGameMessageInfo.getMessage(),
              fcmGameMessageInfo.getTopic(),
              userEvent.getNotificationToken(),
              event.getGamePackageName(),
              event.getMarketName(),
              event.getEnv()
          );
        }
      }
    }
  }
}
