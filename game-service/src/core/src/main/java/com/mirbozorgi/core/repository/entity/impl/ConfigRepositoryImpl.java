package com.mirbozorgi.core.repository.entity.impl;

import com.mirbozorgi.core.repository.entity.ConfigRepository;
import com.mirbozorgi.core.entity.Config;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ConfigRepositoryImpl implements ConfigRepository {

  @PersistenceContext
  private EntityManager entityManager;

  public List<Config> getAll() {
    return entityManager.createQuery("select c from Config c")
        .getResultList();
  }

}