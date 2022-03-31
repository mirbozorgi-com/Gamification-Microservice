package com.mirbozorgi.core.repository.entity.impl;

import com.mirbozorgi.core.entity.FCMGame;
import com.mirbozorgi.core.entity.FCMGameMessage;
import com.mirbozorgi.core.repository.entity.FCMGameMessageRepository;
import java.util.List;
import mirbozorgi.base.constanct.EnumKeyFCM;
import org.springframework.stereotype.Repository;

@Repository
public class FCMGameMessageRepositoryImpl extends CustomRepository implements
    FCMGameMessageRepository {

  @Override
  public FCMGameMessage findBy(FCMGame fcmGame, EnumKeyFCM enumKeyFCM) {
    return findQueryWrapper(entityManager
        .createQuery("select g from FCMGameMessage g where g.enumKeyFCM= :enumKeyFCM "
                + " and g.fcmGame= :fcmGame",
            FCMGameMessage.class)
        .setParameter("fcmGame", fcmGame)
        .setParameter("enumKeyFCM", enumKeyFCM));
  }

  @Override
  public FCMGameMessage add(FCMGameMessage fcmGameMessage) {
    return save(FCMGameMessage.class, fcmGameMessage);
  }

  @Override
  public void delete(int id) {
    FCMGameMessage byId = findById(FCMGameMessage.class, id);
    if (byId != null) {
      delete(FCMGameMessage.class, byId);
    }

  }

  @Override
  public List<FCMGameMessage> findAll(FCMGame fcmGame) {
    return listQueryWrapper(entityManager.createQuery(
        "select g from FCMGameMessage g  where g.fcmGame= :fcmGame  order by g.id desc",
        FCMGameMessage.class)
        .setParameter("fcmGame", fcmGame)

    );
  }
}
