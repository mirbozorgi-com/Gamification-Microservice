package com.mirbozorgi.core.repository.entity.impl;

import com.mirbozorgi.core.entity.SKU;
import com.mirbozorgi.core.repository.entity.SkuRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class SkuRepositoryImpl extends CustomRepository implements SkuRepository {

  @Override
  public SKU findById(long id) {
    return findById(SKU.class, id);
  }

  @Override
  public SKU findByNameAndGameId(String name, int gameId) {
    return findQueryWrapper(entityManager
        .createQuery("select s from SKU s where s.name = :name and s.game.id= :gameId", SKU.class)
        .setParameter("name", name)
        .setParameter("gameId", gameId));
  }

  @Override
  public SKU save(SKU sku) {
    return save(SKU.class, sku);
  }

  @Override
  public List<SKU> findAll(Integer gameId) {
    return listQueryWrapper(
        entityManager
            .createQuery("select s from SKU s where :gameId is null or s.game.id = :gameId",
                SKU.class)
            .setParameter("gameId", gameId)
    );

  }

}
