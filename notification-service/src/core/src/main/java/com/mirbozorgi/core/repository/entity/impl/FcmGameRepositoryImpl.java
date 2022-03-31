package com.mirbozorgi.core.repository.entity.impl;

import com.mirbozorgi.core.entity.FCMGame;
import com.mirbozorgi.core.repository.entity.FcmGameRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class FcmGameRepositoryImpl extends CustomRepository implements FcmGameRepository {


  @Override
  public FCMGame findBy(
      String gamePackageName,
      String env,
      String marketName) {
    return findQueryWrapper(entityManager
        .createQuery("select g from FCMGame g where g.gamePackageName= :gamePackageName "
                + "and g.env= :env"
                + " and g.marketName= :marketName",
            FCMGame.class)
        .setParameter("gamePackageName", gamePackageName)
        .setParameter("env", env)
        .setParameter("marketName", marketName));

  }


  @Override
  public FCMGame add(FCMGame fcmGame) {
    return save(FCMGame.class, fcmGame);
  }

  @Override
  public void delete(String gamePackageName, String env, String marketName) {
    FCMGame fcmGame = findBy(gamePackageName,
        env,
        marketName);
    if (fcmGame != null) {
      delete(FCMGame.class, fcmGame);
    }

  }

  @Override
  public List<FCMGame> findAll() {
    return listQueryWrapper(entityManager.createQuery(
        "select g from FCMGame g order by g.id desc ",
        FCMGame.class));
  }
}
