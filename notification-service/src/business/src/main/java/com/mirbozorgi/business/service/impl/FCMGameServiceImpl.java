package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.exception.NotFoundException;
import com.mirbozorgi.business.service.FCMarsalanervice;
import com.mirbozorgi.core.entity.FCMGame;
import com.mirbozorgi.core.repository.entity.FcmGameRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FCMarsalanerviceImpl implements FCMarsalanervice {

  @Autowired
  private FcmGameRepository repository;

  @Override
  public FCMGame findBy(String gamePackageName,
      String env,
      String marketName) {
    FCMGame fcmGame = repository.findBy(gamePackageName,
        env,
        marketName);
    if (fcmGame == null) {
      throw new NotFoundException();
    }
    return fcmGame;
  }

  @Override
  public FCMGame add(String gamePackageName,
      String env,
      String marketName,
      String pathOfJson) {

    FCMGame fcmGame = new FCMGame(
        gamePackageName,
        env,
        marketName,
        pathOfJson
    );

    return repository.add(fcmGame);
  }

  @Override
  public FCMGame update(String gamePackageName,
      String env,
      String marketName,
      String pathOfJson) {

    FCMGame fcmGame = findBy(gamePackageName, env, marketName);
    fcmGame.setPathOfJson(pathOfJson);
    return repository.add(fcmGame);
  }

  @Override
  public void delete(String gamePackageName, String env, String marketName) {

    repository.delete(gamePackageName, env, marketName);
  }

  @Override
  public List<FCMGame> findAll() {
    List<FCMGame> all = repository.findAll();
    if (all == null) {
      return new ArrayList<>();
    }
    return all;
  }
}
