package com.mirbozorgi.core.repository.memory.impl;

import com.mirbozorgi.core.repository.memory.MarketTokenRepository;
import com.mirbozorgi.core.repository.base.BaseInMemoryRepository;
import com.mirbozorgi.core.repository.base.MemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MarketTokenRepositoryImpl implements BaseInMemoryRepository, MarketTokenRepository {

  @Autowired
  MemoryRepository redisRepository;

  @Override
  public String getKey() {
    return "MARKET_TOKEN";
  }

  private String getKey(String market) {

    return String.format("%S_%S", getKey(), market);
  }

  private String getNxKey(String market) {

    return String.format("%S_REFRESHING_%S", getKey(), market);
  }

  @Override
  public void add(String market, String token, int expSec) {
    redisRepository.setItem(getKey(market), token);
    redisRepository.setExpiry(getKey(market), expSec);
  }

  @Override
  public String get(String market) {
    return redisRepository.getItem(getKey(market));
  }

  @Override
  public void remove(String market) {
    redisRepository.removeItem(getKey(market));
  }

  @Override
  public boolean setNx(String market) {
    boolean result = redisRepository.setItemNx(getNxKey(market), market);
    if (result) {
      redisRepository.setExpiry(getNxKey(market), 60);
    }
    return result;
  }

  @Override
  public void removeNx(String market) {
    redisRepository.removeItem(getNxKey(market));
  }
}
