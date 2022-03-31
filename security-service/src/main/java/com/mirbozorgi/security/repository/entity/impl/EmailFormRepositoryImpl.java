package com.mirbozorgi.security.repository.entity.impl;

import com.mirbozorgi.security.entity.EmailForm;
import com.mirbozorgi.security.repository.entity.EmailFormRepository;
import org.springframework.stereotype.Repository;

@Repository
public class EmailFormRepositoryImpl extends CustomRepository implements EmailFormRepository {


  @Override
  public EmailForm find(
      String gamePackageName,
      String env,
      String name) {
    return findQueryWrapper(entityManager
        .createQuery("select u from EmailForm u where u.name= :name "
                + "and u.gamePackageName= :gamePackageName "
                + "and u.env= :env ",
            EmailForm.class)
        .setParameter("name", name)
        .setParameter("gamePackageName", gamePackageName)
        .setParameter("env", env));
  }
}
