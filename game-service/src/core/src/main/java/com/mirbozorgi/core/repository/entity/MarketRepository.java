package com.mirbozorgi.core.repository.entity;

import com.mirbozorgi.core.entity.Market;
import java.util.List;

public interface MarketRepository {

  Market findById(int id);

  void delete(int id);

  Market save(Market market);

  List<Market> findAll();

  Market findByName(String name);


}
