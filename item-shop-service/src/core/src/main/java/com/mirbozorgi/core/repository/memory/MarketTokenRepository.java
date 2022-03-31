package com.mirbozorgi.core.repository.memory;

public interface MarketTokenRepository {

  void add(String market, String token, int expSec);

  String get(String market);

  void remove(String market);

  boolean setNx(String market);

  void removeNx(String market);


}
