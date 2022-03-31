package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.domain.ConfigData;
import com.mirbozorgi.core.entity.Config;
import java.util.List;

public interface ConfigRepository {

  Config get(String gamePackageName, String env, String marketName);

  Config upsert(String gamePackageName, String env, String marketName, ConfigData configData);


  List<Config> findAll();

}
