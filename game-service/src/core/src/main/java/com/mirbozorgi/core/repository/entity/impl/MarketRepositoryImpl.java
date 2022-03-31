package com.mirbozorgi.core.repository.entity.impl;

import com.mirbozorgi.core.repository.entity.MarketRepository;
import com.mirbozorgi.core.entity.Market;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class MarketRepositoryImpl extends CustomRepository implements MarketRepository {

  @Override
  public Market findById(int id) {
    return findById(Market.class, id);
  }

  @Override
  public void delete(int id) {
    Market byId = findById(Market.class, id);

    delete(Market.class, byId);
  }

  @Override
  public Market save(Market market) {
    return save(Market.class, market);
  }

  @Override
  public List<Market> findAll() {

    return listQueryWrapper(entityManager.createQuery(
        "select m from Market m order by m.id desc ",
        Market.class));

  }

  @Override
  public Market findByName(String name) {
    return findQueryWrapper(entityManager
        .createQuery("select m from Market m where m.name= :name", Market.class)
        .setParameter("name", name));
  }
}
