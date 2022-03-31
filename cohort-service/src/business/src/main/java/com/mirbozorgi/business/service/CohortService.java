package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.ChooseCohortData;
import com.mirbozorgi.business.domain.CohortData;
import com.mirbozorgi.business.domain.GetConfigCohortData;
import java.io.IOException;
import java.util.List;

public interface CohortService {

  CohortData save(
      String gamePackageName,
      String env,
      String name,
      String market,
      int chance,
      boolean active,
      String constCohort,
      Object config,
      String defaultCohort,
      int minVersionClient,
      int maxVersionClient);

  void delete(String id);

  CohortData update(
      String id,
      String gamePackageName,
      String env,
      String name,
      String market,
      int chance,
      boolean active,
      String constCohort,
      Object config,
      String defaultCohort,
      int minVersionClient,
      int maxVersionClient);

  CohortData findById(String id);

  CohortData findByNameAndPackageNameAndMarket(String name, String packageName, String market);

  List<CohortData> findAll();

  ChooseCohortData chooseRandom(
      int clientVersion,
      String packageName,
      String env,
      String market,
      String cohort);


  GetConfigCohortData getConfigCohort(String packageName, String cohortName, String marketName)
      throws IOException;

}
