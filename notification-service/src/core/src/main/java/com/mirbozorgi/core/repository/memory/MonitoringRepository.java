package com.mirbozorgi.core.repository.memory;

import java.util.Map;


public interface MonitoringRepository {

  long incException(String market);

  Map<String, String> get();

  boolean check();
}
