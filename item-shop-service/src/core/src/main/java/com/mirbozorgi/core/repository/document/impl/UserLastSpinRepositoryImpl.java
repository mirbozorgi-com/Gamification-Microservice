package com.mirbozorgi.core.repository.document.impl;

import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.repository.document.UserLastSpinRepository;
import com.mirbozorgi.core.docuemnt.UserLastSpin;
import com.mirbozorgi.core.domain.UserLastSpinData;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class UserLastSpinRepositoryImpl implements UserLastSpinRepository {

  @Autowired
  private CustomMongoRepository repository;

  @Override
  public void add(
      String uuid,
      String key,
      String gamePackageName,
      String env,
      String market,
      long time) {
    gamePackageName = fix(gamePackageName);
    UserLastSpin founded = findByUuId(uuid);
    if (founded == null) {
      Map<String, UserLastSpinData> step1 = new HashMap<>();
      Map<String, Map<String, UserLastSpinData>> step2 = new HashMap<>();
      Map<String, Map<String, Map<String, UserLastSpinData>>> step3 = new HashMap<>();
      Map<String, Map<String, Map<String, Map<String, UserLastSpinData>>>> lastTimeOfSpin = new HashMap<>();

      step1.put(key, new UserLastSpinData(time));
      step2.put(market, step1);
      step3.put(env, step2);
      lastTimeOfSpin.put(gamePackageName, step3);

      UserLastSpin userLastSpin = new UserLastSpin(
          uuid,
          lastTimeOfSpin
      );
      repository.add(userLastSpin);
      return;
    }
    update(
        uuid,
        key,
        gamePackageName,
        env,
        market,
        time
    );
  }


  public UserLastSpin findByUuId(String uuid) {
    return repository.fetchFirst(
        UserLastSpin.class,
        new String[]{
            "lastTimeOfSpin",
            "uuid"},
        Criteria.where("uuid").is(uuid));
  }
  ////////////////////////////////////////////////
  ////////////////////private methods//////////////
////////////////////////////////////////////////

  private void update(
      String uuid,
      String key,
      String gamePackageName,
      String env,
      String market,
      long time) {
    gamePackageName = fix(gamePackageName);
    Update update = new Update();
    update.set("lastTimeOfSpin." + gamePackageName + "." +
        env + "." + market + "." + key
        + ".time", time);
    repository.update(UserLastSpin.class, update,
        Criteria.where("uuid").is(uuid));
  }

  private String fix(String packageName) {
    return packageName.replace(".", "_");
  }
}
