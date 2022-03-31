package com.mirbozorgi.core.repository.document.impl;

import com.mirbozorgi.core.docuemnt.Spin;
import com.mirbozorgi.core.domain.ItemData;
import com.mirbozorgi.core.domain.SpinData;
import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.repository.document.SpinRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class SpinRepositoryImpl implements SpinRepository {


  @Autowired
  private CustomMongoRepository repository;

  @Override
  public Spin save(Spin spin) {
    return repository.add(spin);
  }

  @Override
  public Spin findById(String id) {
    return repository.fetchFirst(Spin.class, new ObjectId(id));
  }

  @Override
  public Spin findSpin(String gamePackageName, String env, String market) {

    return repository.fetchFirst(
        Spin.class,
        new String[]{
            "gamePackageName",
            "env",
            "market",
            "spinDatas"},
        Criteria.where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
            .and("market").is(market));
  }

  @Override
  public Spin updatePeriodTime(
      String gamePackageName,
      String env,
      String market,
      String key,
      long periodTime) {
    Update update = new Update();
    update.set("spinDatas." + key + ".timePeriod", periodTime);

    repository.update(Spin.class, update,
        Criteria.where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
            .and("market").is(market));

    return findSpin(
        gamePackageName,
        env,
        market
    );
  }

  @Override
  public Spin deleteItem(String gamePackageName, String env, String market, String key,
      long periodTime) {
    return null;
  }

  @Override
  public Boolean existKey(
      String gamePackageName,
      String env,
      String market,
      String key) {
    Boolean result = false;
    Spin spin = repository.fetchFirst(
        Spin.class,
        null,
        Criteria.where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
            .and("market").is(market)
            .and("spinDatas." + key).exists(true)
    );

    if (spin != null) {
      result = true;
    }

    return result;
  }

  @Override
  public List<String> getKeys(String gamePackageName, String env, String market) {

    List<String> keyList = new ArrayList<>();

    Spin spin = repository.fetchFirst(
        Spin.class,
        new String[]{
            "spinDatas"},
        Criteria.where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
            .and("market").is(market));

    Map<String, SpinData> spinDatas = spin.getSpinDatas();
    Set<String> keys = spinDatas.keySet();
    for (String key : keys) {
      keyList.add(key);
    }
    return keyList;
  }

  @Override
  public SpinData getAllByKey(
      String gamePackageName,
      String env,
      String market,
      String key) {

    Spin spin = repository.fetchFirst(
        Spin.class,
        new String[]{
            "spinDatas"},
        Criteria.where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
            .and("market").is(market)
            .and("spinDatas." + key).exists(true)

    );

    if (spin == null) {
      return null;
    }

    return spin.getSpinDatas().get(key);
  }

  @Override
  public void update(
      String key,
      String gamePackageName,
      String env,
      String market,
      ItemData spinData) {

    Update update = new Update();
    update.push("spinDatas." + key + ".itemDatas",
        spinData);
    repository.update(Spin.class,
        update,
        Criteria.where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
            .and("market").is(market)
    );
  }


  @Override
  public Spin updateItemSpin(String key,
      String gamePackageName,
      String env,
      String market,
      List<ItemData> itemDatas) {

    Update update = new Update();
    update.set("spinDatas." + key + ".itemDatas",
        itemDatas);
    repository.update(Spin.class,
        update,
        Criteria.where("gamePackageName").is(gamePackageName)
            .and("env").is(env)
            .and("market").is(market)
    );
    return findSpin(gamePackageName, env, market);
  }


}
