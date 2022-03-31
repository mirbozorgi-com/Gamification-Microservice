package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.service.FCMGameMessageService;
import com.mirbozorgi.business.service.FCMGameService;
import com.mirbozorgi.core.entity.FCMGame;
import com.mirbozorgi.core.entity.FCMGameMessage;
import com.mirbozorgi.core.repository.entity.FCMGameMessageRepository;
import java.util.ArrayList;
import java.util.List;
import mirbozorgi.base.constanct.EnumKeyFCM;
import mirbozorgi.base.domain.notification.FCMGameMessageInfo;
import mirbozorgi.base.domain.notification.FCMGameMessageInfoGetAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FCMGameMessageServiceImpl implements FCMGameMessageService {

  @Autowired
  private FCMGameMessageRepository repository;

  @Autowired
  private FCMGameService fcmGameService;

  @Override
  public FCMGameMessageInfo findBy(String gamePackageName,
      String env,
      String marketName, EnumKeyFCM enumKeyFCM) {
    FCMGame fcmGame = fcmGameService.findBy(gamePackageName,
        env,
        marketName);
    FCMGameMessage by = repository.findBy(fcmGame, enumKeyFCM);

    return new FCMGameMessageInfo(
        by.getEnumKeyFCM(),
        by.getTopic(),
        by.getMessage(),
        by.getTitle()
    );
  }

  @Override
  public FCMGameMessage add(EnumKeyFCM enumKeyFCM,
      String topic,
      String message,
      String title,
      String gamePackageName,
      String env,
      String marketName) {
    FCMGame fcmGame = fcmGameService.findBy(gamePackageName,
        env,
        marketName);
    FCMGameMessage fcmGameMessage = new FCMGameMessage(
        fcmGame,
        enumKeyFCM,
        topic,
        message,
        title
    );
    return repository.add(fcmGameMessage);
  }

  @Override
  public void delete(int id) {
    repository.delete(id);
  }

  @Override
  public FCMGameMessageInfoGetAll findAll(String gamePackageName,
      String env,
      String marketName) {
    List<FCMGameMessageInfo> fcmGameMessageInfos = new ArrayList<>();

    FCMGame fcmGame = fcmGameService.findBy(gamePackageName,
        env,
        marketName);
    List<FCMGameMessage> all = repository.findAll(fcmGame);
    for (FCMGameMessage fcmGameMessage : all) {
      fcmGameMessageInfos.add(new FCMGameMessageInfo(
          fcmGameMessage.getEnumKeyFCM(),
          fcmGameMessage.getTopic(),
          fcmGameMessage.getMessage(),
          fcmGameMessage.getTitle()
      ));
    }
    return new FCMGameMessageInfoGetAll(fcmGameMessageInfos);
  }
}
