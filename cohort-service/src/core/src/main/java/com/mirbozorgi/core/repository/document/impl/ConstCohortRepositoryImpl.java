package com.mirbozorgi.core.repository.document.impl;

import com.mirbozorgi.core.entity.ConstCohort;
import com.mirbozorgi.core.repository.document.ConstCohortRepository;
import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class ConstCohortRepositoryImpl implements ConstCohortRepository {


  @Autowired
  private CustomMongoRepository repository;

  @Override
  public ConstCohort save(ConstCohort constCohort) {
    return repository.add(constCohort);
  }


  @Override
  public void delete(ConstCohort constCohort) {
    repository.delete(constCohort);

  }

  @Override
  public ConstCohort update(String id, String gamePackageName, String name, Object config) {
    Update update = new Update();
    update.set("gamePackageName", gamePackageName);
    update.set("name", name);
    update.set("config", config);
    repository.update(ConstCohort.class, update, id);
    return findById(id);
  }

  @Override
  public ConstCohort findById(String id) {
    return repository.fetchFirst(ConstCohort.class, new ObjectId(id));
  }

  @Override
  public ConstCohort findByName(String name) {
    return repository.fetchFirst(
        ConstCohort.class,
        null,
        Criteria.where("name").is(name));

  }

  @Override
  public List<ConstCohort> findAll() {
    return repository.fetch(ConstCohort.class, null);
  }
}
