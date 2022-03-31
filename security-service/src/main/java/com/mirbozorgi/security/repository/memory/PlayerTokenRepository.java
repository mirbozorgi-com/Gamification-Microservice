package com.mirbozorgi.security.repository.memory;

public interface PlayerTokenRepository {

  void save(String playerId, String token);

  String get(String playerId);

  void remove(String playerId);
}
