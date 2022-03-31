package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.ConstCohortData;
import java.util.List;

public interface ConstCohortService {

  ConstCohortData save(
      String gamePackageName,
      String name,
      Object config);

  void delete(String id);

  ConstCohortData update(
      String id,
      String gamePackageName,
      String name,
      Object config);

  ConstCohortData findById(String id);

  ConstCohortData findByName(String name);

  List<ConstCohortData> findAll();

//  String getCohortByChance(String privat);

}
