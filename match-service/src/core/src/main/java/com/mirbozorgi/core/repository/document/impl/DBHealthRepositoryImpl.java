package com.mirbozorgi.core.repository.document.impl;

import com.mirbozorgi.core.repository.document.DBHealthRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DBHealthRepositoryImpl implements DBHealthRepository {

  @Autowired
  MongoTemplate mongoTemplate;

  @Override
  public int check() {
    try {
      Set<String> collectionNames = mongoTemplate.getCollectionNames();
      if (collectionNames.isEmpty()) {
        return 0;
      }
    } catch (Exception e) {
      return 0;
    }
    return 1;
  }
}
