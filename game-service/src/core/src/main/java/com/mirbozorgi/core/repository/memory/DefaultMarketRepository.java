package com.mirbozorgi.core.repository.memory;

public interface DefaultMarketRepository {

  void add(String gamePackageName, String env, String marketName);

  String get(String gamePackageName, String env);

}
