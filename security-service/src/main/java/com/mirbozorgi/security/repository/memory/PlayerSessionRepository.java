package com.mirbozorgi.security.repository.memory;

public interface PlayerSessionRepository {

  void add(
      String uuid,
      String sessionId,
      String gamePackageName,
      String marketName,
      String env);

  String get(
      String uuid,
      String gamePackageName,
      String marketName,
      String env);

  void remove(String uuid,
      String gamePackageName,
      String marketName,
      String env);
}
