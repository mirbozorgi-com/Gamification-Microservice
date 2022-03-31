package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.document.Config;
import java.util.List;

public interface ConfigRepository {

  void add(Config config);

  Config getConfig(String name);

  Object getCohortConfig(String name, String cohortName);

  List<Config> getAll();

}