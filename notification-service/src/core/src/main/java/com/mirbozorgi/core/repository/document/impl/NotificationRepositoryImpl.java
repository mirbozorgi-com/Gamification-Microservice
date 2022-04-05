package com.mirbozorgi.core.repository.document.impl;

import com.mirbozorgi.core.docuemnt.Notification;
import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.repository.document.NotificationRepository;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepositoryImpl implements NotificationRepository {

  @Autowired
  private CustomMongoRepository repository;

  @Override
  public Notification save(Notification notification) {
    return repository.add(notification);
  }

  @Override
  public Notification findById(String id) {
    return repository.fetchFirst(Notification.class, new ObjectId(id));
  }

  @Override
  public List<Notification> findAll(
      String name,
      String gamePackageName,
      String env,
      String marketName) {
    ArrayList<CriteriaDefinition> where = new ArrayList<>();

    if (name == null) {
      name = "";
    }

    if (env == null) {
      env = "";
    }

    if (gamePackageName == null) {
      gamePackageName = "";
    }

    if (marketName == null) {
      marketName = "";
    }

    if (!name.isEmpty()) {
      where.add(Criteria.where("name").is(name));
    }

    if (!env.isEmpty()) {
      where.add(Criteria.where("env").is(env));
    }

    if (!gamePackageName.isEmpty()) {
      where.add(Criteria.where("gamePackageName").is(gamePackageName));
    }

    if (!marketName.isEmpty()) {
      where.add(Criteria.where("marketName").is(marketName));
    }

    return repository.fetch(
        Notification.class,
        new String[]{
            "title",
            "topic",
            "message",
            "type",
            "name",
            "gamePackageName",
            "env",
            "marketName",
            "config",
            "creationDate",
            "minClientVersion",
            "maxClientVersion",
            "isFcmSend",
            "removeAble",
            "walletChange"},
        null,
        100,
        where.toArray(new CriteriaDefinition[where.size()])
    );
  }
}
