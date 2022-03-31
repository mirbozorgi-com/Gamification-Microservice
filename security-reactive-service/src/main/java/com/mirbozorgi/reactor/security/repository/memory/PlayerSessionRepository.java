package com.mirbozorgi.reactor.security.repository.memory;

public interface PlayerSessionRepository {

  void add(String uuid, String sessionId);

  String get(String uuid);

  void remove(String uuid);
}
