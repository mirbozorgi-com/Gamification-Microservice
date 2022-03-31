package com.mirbozorgi.core.repository.document;


import com.mirbozorgi.core.docuemnt.UserLastSpin;

public interface UserLastSpinRepository {

  void add(
      String uuid,
      String key,
      String gamePackageName,
      String env,
      String market,
      long time);

  UserLastSpin findByUuId(String uuid);


}
