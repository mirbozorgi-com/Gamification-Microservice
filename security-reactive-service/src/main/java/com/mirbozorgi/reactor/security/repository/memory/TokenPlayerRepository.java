package com.mirbozorgi.reactor.security.repository.memory;

public interface TokenPlayerRepository {

  void save(String token, String playerId);

  void remove(String token);

  String get(String token);
}
