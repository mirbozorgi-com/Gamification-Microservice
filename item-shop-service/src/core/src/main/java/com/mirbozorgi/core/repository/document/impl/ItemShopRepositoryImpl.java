package com.mirbozorgi.core.repository.document.impl;

import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.docuemnt.ItemShop;
import com.mirbozorgi.core.domain.ItemData;
import com.mirbozorgi.core.repository.document.ItemShopRepository;
import java.util.HashMap;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class ItemShopRepositoryImpl implements ItemShopRepository {

  @Autowired
  private CustomMongoRepository repository;

  @Override
  public ItemShop save(ItemShop itemShop) {
    return repository.add(itemShop);
  }

  @Override
  public ItemShop findById(String id) {
    return repository.fetchFirst(ItemShop.class, new ObjectId(id));
  }

  @Override
  public ItemShop update(
      String name,
      String gamePackageName,
      String env,
      String market,
      ItemData itemData) {

    Update update = new Update();
    update.set("items." + name, itemData);

    repository.update(ItemShop.class, update,
        Criteria.where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
            .and("market").is(market));
    return findBy(
        gamePackageName,
        env,
        market
    );

  }

  @Override
  public void delete(
      String name,
      String gamePackageName,
      String env,
      String market) {

    Update update = new Update();
    update.unset("items." + name);
    repository.update(ItemShop.class, update,
        Criteria.where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
            .and("market").is(market));
  }

  @Override
  public Map<String, ItemData> findAll(
      String gamePackageName,
      String env,
      String market) {

    ItemShop fetch = repository.fetchFirst(
        ItemShop.class,
        new String[]{
            "gamePackageName",
            "env",
            "market",
            "items"},
        Criteria.where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
            .and("market").is(market));

    if (fetch == null) {
      return new HashMap<>();
    }

    return fetch.getItems();
  }

  @Override
  public ItemData findOne(
      String name,
      String gamePackageName,
      String env,
      String market) {
    ItemShop fetch = repository.fetchFirst(
        ItemShop.class,
        new String[]{
            "gamePackageName",
            "env",
            "market",
            "items"},
        Criteria.where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
            .and("market").is(market));

    if (fetch == null) {
      return null;
    }

    return fetch.getItems().get(name);
  }

  @Override
  public Boolean findEntity(String gamePackageName, String env, String market) {
    ItemShop fetch = repository.fetchFirst(
        ItemShop.class,
        new String[]{
            "gamePackageName",
            "env",
            "market",
            "items"},
        Criteria.where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
            .and("market").is(market));

    if (fetch == null) {
      return false;
    }
    return true;

  }


  ////////////////////////////////////////////////
  /////////////////private methods/////////////
  ///////////////////////////////////////////
  private ItemShop findBy(
      String gamePackageName,
      String env,
      String market
  ) {
    return repository.fetchFirst(
        ItemShop.class,
        new String[]{
            "gamePackageName",
            "env",
            "market",
            "config",
            "items"},
        Criteria.where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
            .and("market").is(market));
  }

}
