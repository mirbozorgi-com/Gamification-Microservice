package com.mirbozorgi.core.repository.document.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mirbozorgi.core.repository.document.ConfigRepository;
import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.domain.ConfigData;
import com.mirbozorgi.core.entity.Config;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class ConfigRepositoryImpl implements ConfigRepository {

  @Autowired
  private CustomMongoRepository repository;

  @Override
  public Config get(String gamePackageName, String env, String marketName) {

    return repository.fetchFirst(
        Config.class,
        new String[]{
            "configData",
            "gamePackageName",
            "env",
            "marketName"
        },
        where("env").is(env)
            .and("gamePackageName").is(gamePackageName)
            .and("marketName").is(marketName)
    );
  }


  @Override
  public Config upsert(
      String gamePackageName,
      String env,
      String marketName,
      ConfigData configData) {

    Config config = get(gamePackageName, env, marketName);
    if (config == null) {
      return repository.add(new Config(
          gamePackageName,
          env,
          marketName,
          configData
      ));
    }

    Update update = new Update();

    update.set("configData", configData);

    repository.update(
        Config.class,
        update,
        Criteria.where("env").is(env)
            .and("gamePackageName").is(gamePackageName)
            .and("marketName").is(marketName)
    );

    return get(gamePackageName, env, marketName);
  }

  @Override
  public List<Config> findAll() {
    return repository.fetch(Config.class, new String[]{
        "gamePackageName",
        "env",
        "marketName",
        "configData"
    });
  }
}
