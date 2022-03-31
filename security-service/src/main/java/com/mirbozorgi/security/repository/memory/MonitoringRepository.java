package com.mirbozorgi.security.repository.memory;

import java.util.Map;

public interface MonitoringRepository {

  long incException(String market);

  Map<String, String> get();

  boolean check();
}
