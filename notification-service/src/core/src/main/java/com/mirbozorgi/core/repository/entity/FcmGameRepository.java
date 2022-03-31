package com.mirbozorgi.core.repository.entity;

import com.mirbozorgi.core.entity.FCMGame;
import java.util.List;

public interface FcmGameRepository {

  FCMGame findBy(
      String gamePackageName,
      String env,
      String marketName);

  FCMGame add(FCMGame fcmGame);

  void delete(
      String gamePackageName,
      String env,
      String marketName);

  List<FCMGame> findAll();
}
