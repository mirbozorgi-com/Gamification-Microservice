package com.mirbozorgi.core.repository.document.impl;

import com.mirbozorgi.core.docuemnt.Config;
import com.mirbozorgi.core.repository.document.ConfigRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ConfigRepositoryImpl implements ConfigRepository {

  @Autowired
  MongoTemplate mongoTemplate;


  @Override
  public List<Config> getAll() {
    return mongoTemplate.findAll(Config.class);
  }
}
