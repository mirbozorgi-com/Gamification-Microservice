package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.entity.Cohort;
import java.util.List;

public interface CohortRepository {

  Cohort save(Cohort cohort);

  void delete(Cohort cohort);

  Cohort update(
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
      int maxVersionClient);

  Cohort findById(String id);

  List<Cohort> findAll();

  Cohort findByNameAndPackageNameAndMarket(String name, String packageName, String market);

  List<Cohort> findAllByGame(String packageName);


}
