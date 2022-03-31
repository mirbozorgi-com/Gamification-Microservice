package com.mirbozorgi.core.repository.entity.impl;

import com.mirbozorgi.core.entity.GameDetail;
import com.mirbozorgi.core.repository.entity.GameDetailRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GameDetailRepositoryImpl extends CustomRepository implements GameDetailRepository {

  @Override
  public GameDetail findById(int id) {
    return findById(GameDetail.class, id);
  }

  @Override
  public GameDetail findByPublicKey(String publicKey) {
    return findQueryWrapper(entityManager
        .createQuery(
            "select gd from GameDetail gd where gd.publicKey = :publicKey",
            GameDetail.class
        )
        .setParameter("publicKey", publicKey));
  }
}
