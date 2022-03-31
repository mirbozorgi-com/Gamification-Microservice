package com.mirbozorgi.core.repository.memory.impl;

import com.mirbozorgi.core.repository.base.MemoryRepository;
import com.mirbozorgi.core.repository.memory.AddPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddPurchaseRepositoryImpl implements AddPurchaseRepository {

  @Autowired
  MemoryRepository redisRepository;

  @Override
  public void add(String uuid, String token, String consumptionState, int expSec) {
    redisRepository.setItem(getKey(uuid, token), consumptionState);
    redisRepository.setExpiry(getKey(uuid, token), expSec);
  }

  @Override
  public String get(String uuid, String token) {
    return redisRepository.getItem(getKey(uuid, token));
  }

  @Override
  public void remove(String uuid, String token) {
    redisRepository.removeItem(getKey(uuid, token));
  }


  //////////////////////////////////////////
////////////private//////////////////
/////////////////////////////////////
  private String getKey(String uuid, String token) {

    return String.format("%S_%S", uuid, token);
  }

}
