package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.ConstCohortData;

public class ConstCohortMapper {


  public static ConstCohortData toConstCohortData(
      String id,
      String gamePrivateKey,
      String name,
      Object config) {
    return new ConstCohortData(
        id,
        gamePrivateKey,
        name,
        config
    );
  }

}
