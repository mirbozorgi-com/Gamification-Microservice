package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.domain.PlayerRefralData;
import com.mirbozorgi.core.entity.PlayerRefral;

public interface PlayerRefralRepository {

  void upsert(
      String uuid,
      String gamePackageName,
      String env,
      String marketName,
      PlayerRefralData playerRefralData);

  PlayerRefral find(
      String uuid,
      String gamepackageName,
      String env,
      String marketName
  );


  void reset(
      String gamePackageName,
      String env,
      String marketName
  );
}
