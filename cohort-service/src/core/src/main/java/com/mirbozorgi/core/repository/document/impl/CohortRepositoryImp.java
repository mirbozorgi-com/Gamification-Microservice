package com.mirbozorgi.core.repository.document.impl;

import com.mirbozorgi.core.entity.Cohort;
import com.mirbozorgi.core.repository.document.CohortRepository;
import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class CohortRepositoryImp implements CohortRepository {


  @Autowired
  private CustomMongoRepository repository;

  @Override
  public Cohort save(Cohort cohort) {
    return repository.add(cohort);
  }


  @Override
  public void delete(Cohort cohort) {
    repository.delete(cohort);
  }

  @Override
  public Cohort update(
      String id,
      String gamePackageName,
      String name,
      String market,
      int chance,
      boolean active,
      String constCohort,
      Object config,
      String defaultCohort,
      int minVersionClient,
      int maxVersionClient) {

    Update update = new Update();
    update.set("gamePackageName", gamePackageName);
    update.set("name", name);
    update.set("chance", chance);
    update.set("active", active);
    update.set("market", market);
    update.set("constCohort", constCohort);
    update.set("config", config);
    update.set("defaultCohort", defaultCohort);
    update.set("minVersionClient", minVersionClient);
    update.set("maxVersionClient", maxVersionClient);
    repository.update(Cohort.class, update, id);
    return findById(id);
  }

  @Override
  public Cohort findById(String id) {
    return repository.fetchFirst(Cohort.class, new ObjectId(id));
  }


  @Override
  public List<Cohort> findAll() {
    return repository.fetch(Cohort.class, null);
  }

  @Override
  public Cohort findByNameAndPackageNameAndMarket(String name, String packageName, String market) {
    return repository.fetchFirst(
        Cohort.class,
        new String[]{
            "gamePackageName",
            "name",
            "constCohort",
            "config",
            "defaultCohort",
            "minVersionClient",
            "maxVersionClient"},
        Criteria.where("gamePackageName").is(packageName)
            .and("name").is(name)
            .and("market").is(market));
  }

  @Override
  public List<Cohort> findAllByGame(String packageName) {

    return repository.fetch
        (Cohort.class, new String[]{
            "gamePackageName",
            "name",
            "constCohort",
            "config",
            "minVersionClient",
            "defaultCohort",
            "maxVersionClient"

        }, Criteria.where("gamePackageName").is(packageName));

  }
}
