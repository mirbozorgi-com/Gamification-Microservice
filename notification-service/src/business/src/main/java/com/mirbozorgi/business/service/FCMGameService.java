package com.mirbozorgi.business.service;

import com.mirbozorgi.core.entity.FCMGame;
import java.util.List;

public interface FCMarsalanervice {

  FCMGame findBy(
      String gamePackageName,
      String env,
      String marketName);

  FCMGame add(String gamePackageName,
      String env,
      String marketName,
      String pathOfJson);

  FCMGame update(String gamePackageName,
      String env,
      String marketName,
      String pathOfJson);


  void delete(
      String gamePackageName,
      String env,
      String marketName);

  List<FCMGame> findAll();
}
