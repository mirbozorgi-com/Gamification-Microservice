package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.CohortData;

public class CohortMapper {

  public static CohortData toCohortData(String id,
      String gamePrivateKey,
      String name,
      int chance,
      boolean active,
      String constCohort,
      Object config,
      String defaultCohort,
      int minVersionClient,
      int maxVersionClient) {
    return new CohortData(
        id,
        gamePrivateKey,
        name,
        chance,
        active,
        constCohort,
        config,
        defaultCohort,
        minVersionClient,
        maxVersionClient
    );
  }

}
