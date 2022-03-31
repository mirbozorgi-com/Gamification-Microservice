package com.mirbozorgi.core.repository.document.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mirbozorgi.core.repository.document.ConfigRepository;
import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.document.Config;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ConfigRepositoryImpl implements ConfigRepository {

  @Autowired
  MongoTemplate mongoTemplate;

  @Autowired
  private CustomMongoRepository repository;


  @Override
  public void add(Config config) {
    if (getConfig(config.getName()) == null) {
      repository.add(config);

    }

  }

  @Override
  public Config getConfig(String name) {
    return repository.fetchFirst(
        Config.class,
        new String[]{
            "configs", "name"},
        where("name").is(name)
    );
  }

  @Override
  public Object getCohortConfig(String name, String cohortName) {
    return repository.fetchFirst(
        Config.class,
        new String[]{
            "configs", "name"},
        where("name").is(name)
    ).getConfigs().get(cohortName);
  }

  @Override
  public List<Config> getAll() {
    return mongoTemplate.findAll(Config.class);
  }


}
