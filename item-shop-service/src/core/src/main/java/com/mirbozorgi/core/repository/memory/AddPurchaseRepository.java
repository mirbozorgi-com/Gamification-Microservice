package com.mirbozorgi.core.repository.memory;

public interface AddPurchaseRepository {

  void add(String uuid, String token, String consumptionState, int expSec);

  String get(String uuid, String token);

  void remove(String uuid, String token);
}
