package com.mirbozorgi.core.repository.entity;

import com.mirbozorgi.core.entity.FCMGameMessage;
import com.mirbozorgi.core.entity.FCMGame;
import java.util.List;
import mirbozorgi.base.constanct.EnumKeyFCM;

public interface FCMGameMessageRepository {

  FCMGameMessage findBy(FCMGame fcmGame, EnumKeyFCM enumKeyFCM);

  FCMGameMessage add(FCMGameMessage fcmGameMessage);

  void delete(int id);

  List<FCMGameMessage> findAll(FCMGame fcmGame);
}
