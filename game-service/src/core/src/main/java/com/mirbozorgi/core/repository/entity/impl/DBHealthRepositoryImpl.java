package com.mirbozorgi.core.repository.entity.impl;

import com.mirbozorgi.core.repository.entity.DBHealthRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


@Repository
public class DBHealthRepositoryImpl implements DBHealthRepository {

  @PersistenceContext
  EntityManager entityManager;

  @Override
  public int check() {

    String queryString = "select 1 ";
    Object result = entityManager.createNativeQuery(queryString).getSingleResult();
    return result == null ? 0 : 1;
  }
}
