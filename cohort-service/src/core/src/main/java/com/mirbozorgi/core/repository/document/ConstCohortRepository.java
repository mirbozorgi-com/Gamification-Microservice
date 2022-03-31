package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.entity.ConstCohort;
import java.util.List;

public interface ConstCohortRepository {

  ConstCohort save(ConstCohort constCohort);

  void delete(ConstCohort constCohort);

  ConstCohort update(String id, String gamePackageName, String name, Object config);

  ConstCohort findById(String id);

  ConstCohort findByName(String name);

  List<ConstCohort> findAll();

}
